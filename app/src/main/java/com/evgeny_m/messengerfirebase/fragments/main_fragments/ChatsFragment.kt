package com.evgeny_m.messengerfirebase.fragments.main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.evgeny_m.messengerfirebase.R
import com.evgeny_m.messengerfirebase.databinding.FragmentChatsBinding
import com.evgeny_m.messengerfirebase.utils.drawerLayout
import com.evgeny_m.messengerfirebase.utils.initGamburgerButton


class ChatsFragment : Fragment() {

    private lateinit var binding: FragmentChatsBinding
    private lateinit var toolbar: Toolbar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        toolbar = binding.chatsToolbar // получил ссылку на тулбар
        initGamburgerButton(toolbar) // установил в тулбаре гамбургер
    }
}