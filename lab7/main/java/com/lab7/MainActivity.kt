package com.lab7

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.MediaController
import com.lab7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoPlayer)
        val uri:Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.video)
        binding.videoPlayer.setMediaController(mediaController)
        binding.videoPlayer.setVideoURI(uri)
        binding.videoPlayer.requestFocus()
        binding.videoPlayer.start()

        binding.audioButton.setOnClickListener {
            val i = Intent(this, AudioPlay::class.java)
            startActivity(i)
        }
    }
}