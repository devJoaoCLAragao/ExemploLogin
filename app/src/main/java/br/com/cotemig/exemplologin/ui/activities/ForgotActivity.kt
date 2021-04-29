package br.com.cotemig.exemplologin.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.cotemig.exemplologin.R
import br.com.cotemig.exemplologin.model.Account
import br.com.cotemig.exemplologin.services.RetrofitInitializer
import retrofit2.Call
import retrofit2.Response

class ForgotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        var emailText = intent.getStringExtra("email")

        var email = findViewById<EditText>(R.id.email)
        email.setText(emailText)

        var sendPassword = findViewById<Button>(R.id.sendPassword)
        sendPassword.setOnClickListener {
            sendPassword(email.text.toString())
        }

    }

    fun sendPassword(email: String) {

        var account = Account()
        account.email = email

        var s = RetrofitInitializer().accountService()
        var call = s.forgot(account)

        call.enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 204) { // sucesso, enviou a senha por email

                } else if (response.code() == 404) { // usuário não existe

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }
        })

    }
}