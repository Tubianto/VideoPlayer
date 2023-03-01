package com.tubianto.videoplayer

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var playButton: ImageButton
    private lateinit var pauseButton: ImageButton
    private lateinit var resumeButton: ImageButton
    private lateinit var stopButton: ImageButton
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mengambil referensi ke elemen UI
        videoView = findViewById(R.id.videoView)
        playButton = findViewById(R.id.playButton)
        pauseButton = findViewById(R.id.pauseButton)
        resumeButton = findViewById(R.id.resumeButton)
        stopButton = findViewById(R.id.stopButton)

        // Membuat MediaController baru
        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Mengatur loop video sehingga otomatis diputar lagi ketika selesai
        videoView.setOnCompletionListener { mp ->
            mp.isLooping = true
            mp.start()
        }

        // Menambahkan listener ke tombol play, pause, resume, dan stop
        playButton.setOnClickListener {
            playVideo()
        }

        pauseButton.setOnClickListener {
            pauseVideo()
        }

        resumeButton.setOnClickListener {
            resumeVideo()
        }

        stopButton.setOnClickListener {
            stopVideo()
        }
    }

    private fun playVideo() {
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.sample_video)
        videoView.setVideoURI(videoUri)
        videoView.start()

        playButton.visibility = View.GONE
        pauseButton.visibility = View.VISIBLE
        resumeButton.visibility = View.GONE
    }

    private fun pauseVideo() {
        if (videoView.isPlaying) {
            videoView.pause()
        }

        playButton.visibility = View.GONE
        pauseButton.visibility = View.GONE
        resumeButton.visibility = View.VISIBLE
    }

    private fun resumeVideo() {
        if (!videoView.isPlaying) {
            videoView.start()
        }

        playButton.visibility = View.GONE
        pauseButton.visibility = View.VISIBLE
        resumeButton.visibility = View.GONE
    }

    private fun stopVideo() {
        if (videoView.isPlaying) {
            videoView.stopPlayback()
        }

        playButton.visibility = View.VISIBLE
        pauseButton.visibility = View.GONE
        resumeButton.visibility = View.GONE
    }
}
