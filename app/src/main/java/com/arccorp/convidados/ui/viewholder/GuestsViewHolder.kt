package com.arccorp.convidados.ui.viewholder

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.arccorp.convidados.databinding.RowGuestBinding
import com.arccorp.convidados.model.GuestModel
import com.arccorp.convidados.ui.listner.OnGuestListener

class GuestsViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {
    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }

//        bind.textName.setOnLongClickListener {
//            listener.onDelete(guest.id)
//            true
//        }

        bind.btnDelete.setOnClickListener{

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Convidado")
                .setMessage("Tem certeza que deseja excluir esse convidado? Essa ação é inrreversivel")
                .setPositiveButton("Sim, tenho certeza"
                ) { dialog, witch -> listener.onDelete(guest.id) }
                .setNegativeButton("Não", null)
                .create()
                .show()
        }
    }
}