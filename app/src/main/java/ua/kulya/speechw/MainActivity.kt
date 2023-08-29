package ua.kulya.speechw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.kulya.speechw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Loading activity

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scope = MainScope()
        scope.launch {
            nextScreen()
        }
    }

    private suspend fun nextScreen(){
        delay(1900)
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {

    }
}