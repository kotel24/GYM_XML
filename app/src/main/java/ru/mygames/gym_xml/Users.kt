package ru.mygames.gym_xml

import android.provider.ContactsContract.CommonDataKinds.Email

data class Users(
    var name:String? = null,
    var email: String? = null,
    var phone: String? = null,
    var uid: String? = null
)
