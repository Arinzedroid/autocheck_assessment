package com.arinzedroid.autochekassessment.ui.mediaPlayer

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arinzedroid.autochekassessment.databinding.FragmentMediaPlayerBinding
import com.arinzedroid.autochekassessment.ui.BaseFragment
import com.arinzedroid.autochekassessment.ui.ItemClickListener
import com.arinzedroid.autochekassessment.utils.toast

class MediaPlayerFragment : BaseFragment(), MediaPlayer.OnErrorListener,
    ItemClickListener<Nothing?>, MediaPlayer.OnPreparedListener{


    private var _binding: FragmentMediaPlayerBinding? = null

    private  val binding get() = _binding!!
    private val args: MediaPlayerFragmentArgs by navArgs()
    private lateinit var mediaController: MediaController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMediaPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaController = MediaController(requireContext())

        initView()
    }


    private fun initView(){
        binding.listener = this
        if(args.mediaUrl == null) {
            toast("Invalid file path")
            findNavController().popBackStack()
            return
        }
        val uri = Uri.parse(args.mediaUrl)
        binding.videoView.apply {
            toggleBusyDialog(true)
            setOnPreparedListener(this@MediaPlayerFragment)
            setOnErrorListener(this@MediaPlayerFragment)
            setMediaController(mediaController)
            setVideoURI(uri)
            start()
        }
    }

    override fun onItem(item: Nothing?) {
        findNavController().popBackStack()
    }

    override fun onPrepared(p0: MediaPlayer?) {
        toggleBusyDialog(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
        toggleBusyDialog(false)
        toast("Error playing media")
        return true
    }

}