package com.rafael.proffy.ui.forgotpassword

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityForgotPasswordBinding
import com.rafael.proffy.ui.finallyComponent.FinallyActivity

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonGoBack.setOnClickListener {
            finish()
        }

        val enabledButtonColor = ContextCompat.getColor(this, R.color.green)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        binding.textInputEditEmail.addTextChangedListener {
            validateEmail(enabledButtonColor, disabledButtonColor)
        }

        binding.textInputEditEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateEmail(enabledButtonColor, disabledButtonColor)
            }
        }

        binding.buttonAction.setOnClickListener {
            val email = binding.textInputEditEmail.text.toString().trim()
            if (isValidEmail(email)) {
                val intent = Intent(this, FinallyActivity::class.java).apply {
                    putExtra("TITLE", getString(R.string.forgot_password_success_title))
                    putExtra("SUBTITLE", getString(R.string.forgot_password_success_subtitle))
                }
                startActivity(intent)
            }
        }
    }

    private fun validateEmail(enabledColor: Int, disabledColor: Int) {
        val email = binding.textInputEditEmail.text.toString().trim()
        val isValid = isValidEmail(email)
        
        if (email.isEmpty()) {
            binding.textInputLayoutEmail.error = null
            setButtonState(false, enabledColor, disabledColor)
        } else if (!isValid) {
            binding.textInputLayoutEmail.error = getString(R.string.error_invalid_email)
            setButtonState(false, enabledColor, disabledColor)
        } else {
            binding.textInputLayoutEmail.error = null
            setButtonState(true, enabledColor, disabledColor)
        }
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        binding.buttonAction.isEnabled = enabled
        val color = if (enabled) enabledColor else disabledColor
        binding.buttonAction.backgroundTintList = ColorStateList.valueOf(color)
        
        val textColor = if (enabled)
            ContextCompat.getColor(this, R.color.shape_01_white)
        else
            ContextCompat.getColor(this, R.color.text_complement)
            
        binding.buttonAction.setTextColor(textColor)
        binding.buttonAction.text = if (enabled) getString(R.string.button_enviar) else getString(R.string.button_entrar)
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
