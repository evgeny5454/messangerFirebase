package com.evgeny_m.messengerfirebase.fragments.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.evgeny_m.messengerfirebase.databinding.FragmentEnterPhoneNumberBinding
import com.evgeny_m.messengerfirebase.utils.AppTextWatcher
import com.evgeny_m.messengerfirebase.utils.showToast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

/**
 * После этого фрагмента нужно сделать фрагмент ввода кода
 */

class EnterPhoneNumberFragment : Fragment() {

    val TAG = "LOG_REG_FRAGMENT"

    private lateinit var binding: FragmentEnterPhoneNumberBinding
    private lateinit var inputPhoneNumber: String
    private lateinit var toolbar: Toolbar
    private lateinit var buttonNext: FloatingActionButton
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterPhoneNumberBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        toolbar = binding.enterPhoneToolbar
        buttonNext = binding.enterPhoneNextBtn
        watchToEnterPhone() // следим за вводом номера и показываем кнопку далее

        buttonNext.setOnClickListener {
            sendPhone(
                inputPhoneNumber,
                toolbar
            ) // передаем введенный номер и тулбар, устанавливаем номер в тулбаре
            authUser()
        }
        /**
         * callbacks - автоматически производит аутентификацию по номеру телефонаю
         * В случае успешной авторизации передаются данные пользователя в базу данных
         */
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:$credential")
                singInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(exeptionMessage: FirebaseException) {
                Log.d(TAG, "onVerificationFailed:$exeptionMessage")
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                //super.onCodeSent(verificationId, token)
                storedVerificationId = verificationId
                resendToken = token.toString()
            }

        }
    }

    private fun singInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference

        auth.signInWithCredential(credential).addOnCompleteListener { it ->
            if (it.isSuccessful) {
                val uid = auth.currentUser?.uid.toString() // получаем ID пользователя после регистрации
                val userPhone = replacePhoneNumber
                val dateMap = mutableMapOf<String, Any>()
                dateMap["id"] = uid
                dateMap["phone"] = userPhone

                /**
                 * databaseRef должна связаться с базой данных
                 * и передать туда id пользователя и номер телефона
                 */
                databaseRef
                    .child("users")
                    .child(uid)
                    .updateChildren(dateMap).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToast("авторизация прошла")
                            // тормознул тут. наверно надо делать фрагмент ввода кода
                        }
                    }
            }
        }
    }

    private fun authUser() {

        val option = PhoneAuthOptions.newBuilder()
            .setPhoneNumber(replacePhoneNumber)
            .setTimeout(120L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(option)

        /**
         * начал делать функцию authUser. Остановился на создании переменной callbacks
         */
    }


    private fun watchToEnterPhone() {
        binding.enterPhoneEditText.addTextChangedListener(AppTextWatcher {
            inputPhoneNumber = binding.enterPhoneEditText.text.toString()
            when {
                inputPhoneNumber.length == 10 -> {
                    buttonNext.visibility = View.VISIBLE
                }
                inputPhoneNumber.length < 5 -> {
                    buttonNext.visibility = View.GONE
                }
            }
        })
    }

    companion object {
        var storedVerificationId: String = ""
        var resendToken: String = ""
    }
}

