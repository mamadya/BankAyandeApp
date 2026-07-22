package com.example.bankayande

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bankayande.databinding.ActivitySetupBinding

class SetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveSetup.setOnClickListener {
            val password = binding.etNewPassword.text.toString()
            val balanceText = binding.etInitialBalance.text.toString()

            if (password.isBlank() || balanceText.isBlank()) {
                Toast.makeText(this, R.string.setup_error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val balance = balanceText.toLongOrNull() ?: 0L

            val prefs = getSharedPreferences(PrefsKeys.FILE, MODE_PRIVATE)
            prefs.edit()
                .putString(PrefsKeys.PASSWORD, password)
                .putLong(PrefsKeys.BALANCE, balance)
                .putBoolean(PrefsKeys.SETUP_DONE, true)
                .apply()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
