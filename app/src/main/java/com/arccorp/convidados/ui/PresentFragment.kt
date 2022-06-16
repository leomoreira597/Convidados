package com.arccorp.convidados.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arccorp.convidados.constans.Consts
import com.arccorp.convidados.databinding.FragmentPresentBinding
import com.arccorp.convidados.ui.adapter.GuestsAdapter
import com.arccorp.convidados.ui.listner.OnGuestListener
import com.arccorp.convidados.viewModel.GuestsViewModel


class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        b: Bundle?
    ): View {
         viewModel =
            ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentPresentBinding.inflate(inflater, container, false)


        //layout do recyclerview
        binding.recyclerAllGuest.layoutManager = LinearLayoutManager(context)


        //adapter
        binding.recyclerAllGuest.adapter = adapter

        val listener = object: OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(Consts.Guest.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }



            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getPresent()
            }

        }

        adapter.attachListener(listener)

        viewModel.getPresent()

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val atualizacao = viewModel.getPresent()
        return atualizacao
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }

}