package com.example.bankayande

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bankayande.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PrefsKeys.FILE, MODE_PRIVATE)
        val setupDone = prefs.getBoolean(PrefsKeys.SETUP_DONE, false)

        if (!setupDone) {
            startActivity(Intent(this, SetupActivity::class.java))
            finish()
            return
        }

        binding.btnLogin.setOnClickListener {
            val entered = binding.etPassword.text.toString()
            val savedPassword = prefs.getString(PrefsKeys.PASSWORD, "")

            if (entered == savedPassword) {
                binding.tvLoginError.visibility = View.INVISIBLE
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                binding.tvLoginError.visibility = View.VISIBLE
            }
        }

        binding.tvResetSetup.setOnClickListener {
            prefs.edit().clear().apply()
            startActivity(Intent(this, SetupActivity::class.java))
            finish()
        }
    }
}
