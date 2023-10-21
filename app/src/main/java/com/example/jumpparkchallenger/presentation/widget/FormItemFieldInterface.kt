package com.example.jumpparkchallenger.presentation.widget

import android.widget.EditText

interface FormItemFieldInterface {
    fun validItem(): Boolean
    fun getValue(): Any?
    fun getProperty(): String
    fun getEditText(): EditText?
}
