package com.evgeny_m.messengerfirebase.fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.evgeny_m.messengerfirebase.R
import com.evgeny_m.messengerfirebase.databinding.FragmentEnterPhoneNumberBinding
import com.evgeny_m.messengerfirebase.utils.AppTextWatcher


class EnterPhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentEnterPhoneNumberBinding
    private lateinit var inputPhoneNumber: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterPhoneNumberBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val buttonNext = binding.enterPhoneNextBtn

        binding.enterPhoneEditText.addTextChangedListener(AppTextWatcher{
            inputPhoneNumber = binding.enterPhoneEditText.text.toString()
            if (inputPhoneNumber.length == 11) {
                buttonNext.visibility = View.VISIBLE
            }
        })
    }

}