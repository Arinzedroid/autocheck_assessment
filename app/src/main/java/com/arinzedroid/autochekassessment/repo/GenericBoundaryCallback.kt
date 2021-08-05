package com.arinzedroid.autochekassessment.repo

import androidx.paging.PagedList
import com.arinzedroid.autochekassessment.model.GenericResponseStatus
import com.arinzedroid.autochekassessment.model.Page
import com.arinzedroid.autochekassessment.model.PaginatedResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.Executor

class GenericBoundaryCallback<T : Page, R, L>(
    private val save: (List<L>?) -> Unit,
    private val mapper: (R?, Int?) -> L,
    var loadPage: suspend (pageNum: Int) -> Response<PaginatedResponse<R>>
) : PagedList.BoundaryCallback<T>() {

    override fun onZeroItemsLoaded() {
        handleResponse(1)
    }

    override fun onItemAtFrontLoaded(itemAtFront: T) {}

    override fun onItemAtEndLoaded(itemAtEnd: T) {
        val nextPage = itemAtEnd.currentPage

        handleResponse(nextPage + 1)
    }

    private fun handleResponse(pageNum: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = loadPage(pageNum)
                if(response.isSuccessful) {
                    val pageResponse = response.body() ?: return@launch
                    val mappedResponse = pageResponse.result?.map {
                        mapper(it, pageResponse.pagination?.currentPage)
                    }
                    save(mappedResponse)
                }
            }catch (ex: Exception){
                ex.printStackTrace()
            }
        }
    }
}