package com.arccorp.convidados.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arccorp.convidados.R
import com.arccorp.convidados.constans.Consts
import com.arccorp.convidados.databinding.ActivityGuestFormBinding
import com.arccorp.convidados.model.GuestModel
import com.arccorp.convidados.viewModel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.btnSalvar.setOnClickListener(this)
        binding.radiobtnPresent.isChecked = true

        observe()
        loadData()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_salvar) {
            val name = binding.editNome.text.toString()
            val presence = binding.radiobtnPresent.isChecked
            val model = GuestModel(guestId, name, presence)

            viewModel.save(model)
        }
    }

    private fun observe(){
        viewModel.guest.observe(this, Observer {
            binding.editNome.setText(it.name)
            if(it.presence){
                binding.radiobtnPresent.isChecked = true
            }
            else{
                binding.radiobtnAusente.isChecked = true
            }
        })

        viewModel.saveGuest.observe(this, Observer {
            if (it){
                if (guestId == 0){
                    Toast.makeText(
                        applicationContext,
                        "Convidado adicionado com sucesso!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
                else{

                    Toast.makeText(
                        applicationContext,
                        "Convidado alterado com sucesso!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
            else{

                Toast.makeText(
                    applicationContext,
                    "Erro ao adicionar/atualizar convidado tente novamente",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(Consts.Guest.ID)
            viewModel.getConvidado(guestId)
        }
    }
}