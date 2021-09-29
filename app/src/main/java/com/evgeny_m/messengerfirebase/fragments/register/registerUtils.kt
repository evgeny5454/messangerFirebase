package com.evgeny_m.messengerfirebase.fragments.register

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.evgeny_m.messengerfirebase.utils.showToast

/**
 *  fun sendPhone
 *  Производит обработку введенного номера, что позволяет вводить номер с цифр 8,7,9
 *  в итоге получается +7 *** *** ** **
 */

lateinit var replacePhoneNumber: String

fun EnterPhoneNumberFragment.sendPhone(inputPhoneNumber: String, toolbar: Toolbar) {

    replacePhoneNumber =
        when {
            inputPhoneNumber[0].toString() == "8" -> {
                inputPhoneNumber.replaceFirst("8", "+7")
            }
            inputPhoneNumber[0].toString() == "7" -> {
                inputPhoneNumber.replaceFirst("7", "+7")
            }
            inputPhoneNumber[0].toString() == "9" -> {
                "+7$inputPhoneNumber"
            }
            else -> {
                inputPhoneNumber
            }
            //сделал разные условия ввода номера телефона, пользователь сможет вводить свой номер
        }

    if (replacePhoneNumber.length == 12) { // проверяем номер должно быть 12 знаков
        toolbar.title = replacePhoneNumber // устанавливаем текст после обработки в тулбаре
        //authUser()
    } else {
        showToast("Введите номер телефона в корректном формате")
    }
}

/*fun authUser() {

}*/
