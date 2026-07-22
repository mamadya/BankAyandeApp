package com.example.bankayande

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bankayande.databinding.ActivityDashboardBinding
import java.text.NumberFormat
import java.util.Locale

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PrefsKeys.FILE, MODE_PRIVATE)
        val balance = prefs.getLong(PrefsKeys.BALANCE, 0L)

        val formatted = NumberFormat.getNumberInstance(Locale("fa", "IR")).format(balance)
        binding.tvBalance.text = "$formatted ${getString(R.string.currency_toman)}"

        val demoClick = { _: android.view.View ->
            Toast.makeText(this, R.string.demo_notice, Toast.LENGTH_SHORT).show()
        }
        binding.actionTransfer.setOnClickListener(demoClick)
        binding.actionDeposit.setOnClickListener(demoClick)
        binding.actionWithdraw.setOnClickListener(demoClick)
        binding.actionCards.setOnClickListener(demoClick)

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
