package br.com.cotemig.exemplologin.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.cotemig.exemplologin.R
import br.com.cotemig.exemplologin.model.Account
import br.com.cotemig.exemplologin.services.RetrofitInitializer
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var create = findViewById<Button>(R.id.create)

        create.setOnClickListener {
            createAccount()
        }

    }

    fun createAccount() {

        var email = findViewById<EditText>(R.id.email)
        var password = findViewById<EditText>(R.id.password)

        var account = Account()
        account.name = "Dirceu Belém"
        account.email = email.text.toString()
        account.password = password.text.toString()

        var s = RetrofitInitializer().accountService()
        var call = s.create(account)

        call.enqueue(object : retrofit2.Callback<Account> {
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                // TODO: obter os valores e mostrar

                if (response.code() == 200) {

                    response.body()?.let {

                        // TODO: mostrar essa mensagem em um MessageDialog
                        Toast.makeText(
                            this@MainActivity,
                            "Usuário criado com sucesso!" + it.id,
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                } else if (response.code() == 409) {
                    // TODO: mostrar essa mensagem em um MessageDialog
                    Toast.makeText(
                        this@MainActivity,
                        "Ops! Usuário já criado",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

            override fun onFailure(call: Call<Account>, t: Throwable) {
                // TODO: tratar o erro da requisição

                Toast.makeText(
                    this@MainActivity,
                    "Ops! Algo aconteceu de errado, tente novamente mais tarde.",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })

    }
}