package com.example.jumpparkchallenger.presentation.widget

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.example.jumpparkchallenger.R

open class FormItemEditTextView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs), FormItemFieldInterface {

    private var txtTitle: AppCompatTextView
    private var edtText: AppCompatEditText
    private var required = false
    private lateinit var property: String

    open var error: String?
        get() = edtText.error?.toString()
        set(value) {
            edtText.error = value
            if (!TextUtils.isEmpty(value)) requestFocus()
        }

    open var text: String
        get() = edtText.text?.trim().toString()
        set(value) = edtText.setText(value)

    open var title: String
        get() = txtTitle.text?.trim().toString()
        set(value) {
            txtTitle.text = value
        }

    init {

        val view = LayoutInflater.from(context).inflate(R.layout.form_field_edit_text_item, null)
        view.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        txtTitle = view.findViewById(R.id.form_item_title)
        edtText = view.findViewById(R.id.form_item_edit_text)

        attrs?.let {

            val style = context.obtainStyledAttributes(attrs, R.styleable.FormItemEditTextView, 0, 0)
            val maxLength = style.getInt(R.styleable.FormItemEditTextView_view_edit_text_max_length, 50)
            val inputType = style.getInt(R.styleable.FormItemEditTextView_view_edit_text_input_type, InputType.TYPE_CLASS_TEXT)

            txtTitle.text = style.getString(R.styleable.FormItemEditTextView_view_edit_text_title)
            edtText.hint = style.getString(R.styleable.FormItemEditTextView_view_edit_text_hint)
            edtText.filters = arrayOf(*edtText.filters, InputFilter.LengthFilter(maxLength))
            edtText.inputType = inputType

            style.recycle()
        }

        addView(view)
    }

    open fun setFormItem(item: FormField) {
        this.required = item.required
        this.property = item.property

        txtTitle.text = item.title
        edtText.hint = item.description

//        val maxLength = if(config.maxLength?: 0 > 0) config.maxLength!! else 20
//        edtText.filters = arrayOf(*edtText.filters, InputFilter.LengthFilter(maxLength))

        edtText.inputType = (
            when (item.type) {
                FormFieldType.Text -> InputType.TYPE_CLASS_TEXT
                FormFieldType.Money -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                FormFieldType.Number -> InputType.TYPE_CLASS_NUMBER
                else -> InputType.TYPE_CLASS_TEXT
            }
            )

        item.propertyValue?.let {
            setValue(it)
        }
    }

    private fun setValue(propertyValue: Any) {
        text = propertyValue.toString()
    }

    override fun getEditText(): EditText = edtText

    override fun validItem(): Boolean {
        return when {
            (visibility == View.VISIBLE && required) -> {
                when {
                    TextUtils.isEmpty(edtText.text) -> {
                        edtText.error = edtText.hint
                        edtText.requestFocus()
                        false
                    }
                    else -> true
                }
            }
            else -> true
        }
    }

    override fun getValue(): Any? {
        return when (visibility) {
            View.VISIBLE -> text
            else -> null
        }
    }

    override fun getProperty(): String = property

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        edtText.isEnabled = enabled
    }
}
