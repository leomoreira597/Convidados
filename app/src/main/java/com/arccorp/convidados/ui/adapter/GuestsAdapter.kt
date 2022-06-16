package com.arccorp.convidados.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arccorp.convidados.databinding.RowGuestBinding
import com.arccorp.convidados.model.GuestModel
import com.arccorp.convidados.ui.listner.OnGuestListener
import com.arccorp.convidados.ui.viewholder.GuestsViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestsViewHolder>() {

    private var guestsList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)

        return GuestsViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        holder.bind(guestsList[position])
    }

    override fun getItemCount(): Int {
        return guestsList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        guestsList = list
        notifyDataSetChanged()
    }

    fun attachListener(guestListener: OnGuestListener){
        listener = guestListener
    }
}