package com.example.jumpparkchallenger.presentation.widget

import com.google.gson.annotations.SerializedName
import java.io.Serializable

enum class FormFieldType {
    @SerializedName("text")
    Text,

    @SerializedName("number")
    Number,

    @SerializedName("money")
    Money,

    @SerializedName("single_choice")
    SingleChoice,

    @SerializedName("multiple_choice")
    MultipleChoice,

    @SerializedName("date")
    Date,

    @SerializedName("picture")
    Picture,

    @SerializedName("text_multiline")
    TextMultiline,

    @SerializedName("boolean")
    Boolean,

    @SerializedName("numerical_quantity")
    NumericalQuantity,

    @SerializedName("warning")
    Warning
}

data class FormField(
    val property: String,
    val parentProperty: String?,
    val title: String?,
    val description: String?,
    val required: Boolean = true,
    val items: ArrayList<FormFieldValue>? = null,
    val type: FormFieldType? = null,
    var propertyValue: Any?
) : Serializable

data class FormFieldValue(
    val value: Any,
    val text: String,
    val children: ArrayList<String>? = null
)
