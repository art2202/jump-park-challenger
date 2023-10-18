package com.example.jumpparkchallenger.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.databinding.ActivityLoginBinding
import com.example.jumpparkchallenger.presentation.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel : LoginViewModel by viewModel()
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.responseHome.observe(this){info ->
            if(info != null) {
                Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show()
                openHomeActivity()
            }
        }
        initListeners()
    }

    private fun openHomeActivity() {
        //passar infos aqui
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initListeners(){
        binding.loginButton.setOnClickListener {
            if(checkLoginInfos())
                viewModel.login(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
        }
    }

    private fun checkLoginInfos() : Boolean{

        val email = binding.emailEditText.text.toString()
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEditText.error = "Insira um email valido!"
            return false
        }
        if(binding.passwordEditText.text.toString().isEmpty()){
            binding.passwordEditText.error = "Insira uma senha!"
            return false
        }
        return true
    }
}