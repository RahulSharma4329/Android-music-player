package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //MediaPlayer Initialisation
        val mediaplayer:MediaPlayer = MediaPlayer.create(this,R.raw.music)

        binding.seekbar.progress = 0

        binding.seekbar.max = mediaplayer.duration

        binding.playBtn.setOnClickListener {
            if(!mediaplayer.isPlaying){
                mediaplayer.start()
                binding.playBtn.setImageResource(R.drawable.baseline_pause_24)
            }
            else{
                mediaplayer.pause()
                binding.playBtn.setImageResource(R.drawable.baseline_play_arrow_24)
            }
        }
        binding.seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed){
                    mediaplayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }


        })

        runnable = Runnable {
            binding.seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaplayer.setOnCompletionListener {
            binding.playBtn.setImageResource(R.drawable.baseline_play_arrow_24)
            binding.seekbar.progress = 0
        }


    }
}