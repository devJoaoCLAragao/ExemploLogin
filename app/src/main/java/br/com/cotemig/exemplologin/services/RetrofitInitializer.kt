package br.com.cotemig.exemplologin.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    // instanciando o retrofit para ser usado na URL e
    // Conversor JSON > Classe e Classe > JSON
    private val retrofit = Retrofit.Builder().
        baseUrl("https://api.fluo.work/v1/").
        addConverterFactory(GsonConverterFactory.create()).
        build()

    fun accountService() : AccountService{
        return retrofit.create(AccountService::class.java)
    }

}