package com.arccorp.convidados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.arccorp.convidados.databinding.ActivityGuestFormBinding
import com.arccorp.convidados.databinding.ActivityMainBinding

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.btnSalvar.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_salvar){

        }
    }
}