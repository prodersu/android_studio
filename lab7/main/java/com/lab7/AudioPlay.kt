package com.lab7

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.lab7.databinding.ActivityAudioPlayBinding

class AudioPlay : AppCompatActivity() {
    private lateinit var binding: ActivityAudioPlayBinding
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.audio)
        binding.apply {
            playBtn.setOnClickListener {
                if (pause) {
                    mediaPlayer.seekTo(mediaPlayer.currentPosition)
                    mediaPlayer.start()
                    pause = false
                } else {
                    mediaPlayer.start()
                }
                playBtn.isEnabled = false
                pauseBtn.isEnabled = true
                stopBtn.isEnabled = true

                mediaPlayer.setOnCompletionListener {
                    playBtn.isEnabled = true
                    pauseBtn.isEnabled = false
                    stopBtn.isEnabled = false
                }
            }
            pauseBtn.setOnClickListener {
                if(mediaPlayer.isPlaying){
                    mediaPlayer.pause()
                    pause = true
                    playBtn.isEnabled = true
                    pauseBtn.isEnabled = false
                    stopBtn.isEnabled = true
                }
            }
            stopBtn.setOnClickListener{
                if(mediaPlayer.isPlaying || pause){
                    pause = false
                    mediaPlayer.pause()
                    mediaPlayer.seekTo(0)

                    playBtn.isEnabled = true
                    pauseBtn.isEnabled = false
                    stopBtn.isEnabled = false
                }
            }
        }
        binding.videoButton.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }
    }
}