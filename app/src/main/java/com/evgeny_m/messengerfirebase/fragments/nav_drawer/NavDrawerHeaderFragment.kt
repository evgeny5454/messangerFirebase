package com.evgeny_m.messengerfirebase.fragments.nav_drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evgeny_m.messengerfirebase.R
import com.evgeny_m.messengerfirebase.databinding.FragmentNavDrawerHeaderBinding


class NavDrawerHeaderFragment : Fragment() {


    private lateinit var binding: FragmentNavDrawerHeaderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavDrawerHeaderBinding.inflate(layoutInflater)
        return binding.root
    }

}