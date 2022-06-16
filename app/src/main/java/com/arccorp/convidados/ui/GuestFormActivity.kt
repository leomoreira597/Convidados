package com.arccorp.convidados.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.arccorp.convidados.R
import com.arccorp.convidados.constans.Consts
import com.arccorp.convidados.databinding.ActivityGuestFormBinding
import com.arccorp.convidados.model.GuestModel
import com.arccorp.convidados.viewModel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.btnSalvar.setOnClickListener(this)
        binding.radiobtnPresent.isChecked = true

        loadData()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_salvar) {
            val name = binding.editNome.text.toString()
            val presence = binding.radiobtnPresent.isChecked
            val model = GuestModel(0, name, presence)
            viewModel.insert(model)
            Toast.makeText(
                applicationContext,
                "Convidado adicionado com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            val guestId = bundle.getInt(Consts.Guest.ID)
            viewModel.getConvidado(guestId)
        }
    }
}