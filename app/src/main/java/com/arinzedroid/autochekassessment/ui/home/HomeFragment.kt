package com.arinzedroid.autochekassessment.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import com.arinzedroid.autochekassessment.databinding.FragmentHomeBinding
import com.arinzedroid.autochekassessment.model.PopularCarEntity
import com.arinzedroid.autochekassessment.model.SearchCarEntity
import com.arinzedroid.autochekassessment.ui.BaseFragment
import com.arinzedroid.autochekassessment.ui.ItemClickListener
import com.arinzedroid.autochekassessment.utils.getAppComponent
import javax.inject.Inject

class HomeFragment : BaseFragment(), ItemClickListener<SearchCarEntity> {

    @Inject
    lateinit var homeViewModel: HomeViewModel
    @Inject
    lateinit var statusViewModel: StatusViewModel
    private var _binding: FragmentHomeBinding? = null

    private val adapter = StatusAdapter()
    private val carAdapter = CarAdapter(this)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView(){
        binding.statusRecyclerView.adapter = adapter
        binding.itemsRecyclerView.adapter = carAdapter
        statusViewModel.response.observe(viewLifecycleOwner, {
            handleStatusResponse(it)
        })
        homeViewModel.response.observe(viewLifecycleOwner,{
            handleCarResponse(it)
        })
        homeViewModel.loader.observe(viewLifecycleOwner, {
            toggleBusyDialog(it)
        })
    }

    private fun moveToDetailsPage(item: SearchCarEntity){
        val directions = HomeFragmentDirections
            .actionNavigationHomeToNavigationDashboard(item.id, item.title)

        findNavController().navigate(directions)
    }

    private fun handleStatusResponse(data: LiveData<PagedList<PopularCarEntity>>){
        data.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private fun handleCarResponse(data: LiveData<PagedList<SearchCarEntity>>){
        data.observe(viewLifecycleOwner, {
            carAdapter.submitList(it)
        })
    }

    override fun onItem(item: SearchCarEntity) {
        moveToDetailsPage(item)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        getAppComponent().inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}