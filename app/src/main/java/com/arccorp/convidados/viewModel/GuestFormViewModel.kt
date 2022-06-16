package com.arccorp.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arccorp.convidados.model.GuestModel
import com.arccorp.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    fun insert(guest: GuestModel){
        repository.insert(guest)
    }

    fun getConvidado(id: Int){
        repository.getConvidado(id)
    }
}