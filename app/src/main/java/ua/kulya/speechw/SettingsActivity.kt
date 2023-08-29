package ua.kulya.speechw

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.kulya.speechw.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp = getSharedPreferences(SHARED, Context.MODE_PRIVATE)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val speedFromSharedPref = sp.getLong(SPEED, 3000L)
        binding.tvSpeed.text = "Speed $speedFromSharedPref"


        binding.btnAdd.setOnClickListener {
            val newSpeed = sp.getLong(SPEED, 3000L) + 200L
            binding.tvSpeed.text = "Speed $newSpeed"
            sp.edit().putLong(SPEED, newSpeed).apply()
        }

        binding.btnRemove.setOnClickListener {
            val newSpeed = sp.getLong(SPEED, 3000L) - 200L
            binding.tvSpeed.text = "Speed $newSpeed"
            sp.edit().putLong(SPEED, newSpeed).apply()
        }
    }
    companion object{
        const val SHARED = "SHARED"
        const val SPEED = "SPEED"
    }
}