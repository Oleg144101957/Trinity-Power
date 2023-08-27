package ua.kulya.speechw

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.kulya.speechw.databinding.ActivityMenuBinding
import kotlin.system.exitProcess

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private var mediaPlayer: MediaPlayer? = null
    private var isSoundPlay: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.soundtrack)
        mediaPlayer?.start()

        setClickListenners()

    }

    private fun setClickListenners(){

        binding.button1.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
            exitProcess(0)
        }

        binding.settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.sound.setOnClickListener {
            if (isSoundPlay){
                mediaPlayer?.stop()
                binding.sound.setImageResource(R.drawable.soundoff)
                isSoundPlay = false
            } else {
                mediaPlayer = MediaPlayer.create(this, R.raw.soundtrack)
                mediaPlayer?.start()
                binding.sound.setImageResource(R.drawable.soundon)
                isSoundPlay = true
            }
        }
    }

    override fun onBackPressed() {

    }
}