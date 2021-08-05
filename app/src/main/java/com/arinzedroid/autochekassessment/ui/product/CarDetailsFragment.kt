package com.arinzedroid.autochekassessment.ui.product

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arinzedroid.autochekassessment.databinding.FragmentCarDetailBinding
import com.arinzedroid.autochekassessment.model.CarDetailResponse
import com.arinzedroid.autochekassessment.model.GenericResponseStatus
import com.arinzedroid.autochekassessment.ui.BaseFragment
import com.arinzedroid.autochekassessment.ui.ProductDetailClickListener
import com.arinzedroid.autochekassessment.utils.getAppComponent
import com.arinzedroid.autochekassessment.utils.toast
import javax.inject.Inject

class CarDetailsFragment : BaseFragment(), ProductDetailClickListener {

    @Inject
    lateinit var carDetailViewModel: CarDetailViewModel
    private var _binding: FragmentCarDetailBinding? = null

    val args: CarDetailsFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentCarDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        binding.listener = this
        carDetailViewModel.getCarDetails(args.carId ?: return)

        carDetailViewModel.loader.observe(viewLifecycleOwner,{
            toggleBusyDialog(it)
        })

        carDetailViewModel.response.observe(viewLifecycleOwner,{
            when(it){
                is GenericResponseStatus.OnFailed -> {
                    toast(it.error.message ?: "Error occurred")
                }
                is GenericResponseStatus.OnSuccess -> {
                    displayDetails(it.data)
                }
            }
        })
    }

    private fun displayDetails(data: CarDetailResponse?){
        data ?: return
        binding.item = data.copy(title = args.carTitle)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        getAppComponent().inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onGoBack() {
        findNavController().popBackStack()
    }

    override fun viewMedia(item: CarDetailResponse) {
        val directions = CarDetailsFragmentDirections
            .actionNavigationDashboardToNavigationMedia(item.id,args.carTitle)
        findNavController().navigate(directions)
    }

    override fun onInterested(item: CarDetailResponse) {
        toast("Coming soon")
    }

    override fun onFinancing(item: CarDetailResponse) {
        toast("Coming soon")
    }
}