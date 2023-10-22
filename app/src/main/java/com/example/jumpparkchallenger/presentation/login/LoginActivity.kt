package com.example.jumpparkchallenger.presentation.login

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.jumpparkchallenger.R
import com.example.jumpparkchallenger.databinding.ActivityLoginBinding
import com.example.jumpparkchallenger.presentation.main.MainActivity
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showDrawable
import com.github.razir.progressbutton.showProgress
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel : LoginViewModel by viewModel()
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.checkToken()
        viewModel.tokenExists.observe(this){hasToken ->
            if (hasToken) openHomeActivity()
        }

        viewModel.responseHome.observe(this){info ->
            if(info != null) {
                Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show()

                binding.loginButton.hideProgress("Enviar")

                openHomeActivity()
            }
        }

        viewModel.errorMessage.observe(this) { message ->
            binding.loginButton.hideProgress("Enviar")
            message?.let {
                AlertDialog.Builder(this)
                    .setTitle("Erro ao entrar")
                    .setMessage(it)
                    .setNeutralButton("ok") { dialog, _ ->
                    dialog.dismiss()
                }.show()
                viewModel.errorMessage.value = null
            }
        }
        initListeners()
    }


    private fun openHomeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initListeners(){
        bindProgressButton(binding.loginButton)

        binding.firstLoginTextView.setOnClickListener{
            openUrl()
        }

        binding.loginButton.setOnClickListener {
            if(checkLoginInfos()) {
                binding.loginButton.showProgress {
                    buttonText = "Enviando"
                    progressColor = Color.WHITE
                }
                viewModel.login(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            }
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

    private fun openUrl(){
        val url = "https://teste-admin.jumppark.com.br/cadastro-inicial"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
            startActivity(intent)
    }
}