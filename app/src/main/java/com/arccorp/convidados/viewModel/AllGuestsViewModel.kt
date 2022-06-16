package com.arccorp.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arccorp.convidados.model.GuestModel
import com.arccorp.convidados.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests
    private val repository =
        GuestRepository.getInstance(application.applicationContext)

    fun getAll() {
       listAllGuests.value = repository.getAll()
    }

    fun delete(id: Int){
        repository.delete(id)
    }

}