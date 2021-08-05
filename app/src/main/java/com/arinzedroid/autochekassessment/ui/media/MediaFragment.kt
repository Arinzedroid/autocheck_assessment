package com.arinzedroid.autochekassessment.ui.media

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagedList
import com.arinzedroid.autochekassessment.databinding.FragmentMediaBinding
import com.arinzedroid.autochekassessment.model.MediaEntity
import com.arinzedroid.autochekassessment.ui.BaseFragment
import com.arinzedroid.autochekassessment.ui.ItemClickListener
import com.arinzedroid.autochekassessment.ui.home.HomeViewModel
import com.arinzedroid.autochekassessment.utils.getAppComponent
import com.arinzedroid.autochekassessment.utils.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaFragment : BaseFragment(),ItemClickListener<MediaEntity>{

    @Inject
    lateinit var mediaViewModel: MediaViewModel
    @Inject
    lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentMediaBinding? = null

    private val args: MediaFragmentArgs by navArgs()
    private val adapter = MediaAdapter(this)
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        binding.titleTv.text = args.carTitle ?: ""
        binding.itemsRecyclerView.adapter = adapter

        mediaViewModel.fetchCarMedia(args.carId ?: return)

        mediaViewModel.loader.observe(viewLifecycleOwner,{
            toggleBusyDialog(it)
        })

        mediaViewModel.response.observe(viewLifecycleOwner,{
            handleResponse(it)
        })
    }

    private fun handleResponse(data: LiveData<PagedList<MediaEntity>>){
        data.observe(viewLifecycleOwner,{
            adapter.submitList(it)
            showEmpty(it.size)
        })
    }
    private fun showEmpty(size: Int){
        if(size == 0)
            binding.emptyTextview.visibility = View.VISIBLE
        else
            binding.emptyTextview.visibility = View.GONE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        getAppComponent().inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItem(item: MediaEntity) {
        if (item.url.lowercase().endsWith(".mp4")) {
            val directions = MediaFragmentDirections
                .actionNavigationMediaToNavigationPlayer(item.url)

            findNavController().navigate(directions)
        }

    }
}