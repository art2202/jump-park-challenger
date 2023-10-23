package com.example.jumpparkchallenger.presentation

enum class ColorEnum(val nome: String, val hexValue: String) {
    VERMELHO("Vermelho", "#FF0000"),
    AZUL("Azul", "#0000FF"),
    VERDE("Verde", "#008000"),
    AMARELO("Amarelo", "#FFFF00"),
    LARANJA("Laranja", "#FFA500"),
    ROXO("Roxo", "#800080"),
    ROSA("Rosa", "#FFC0CB"),
    CINZA("Cinza", "#808080"),
    PRETO("Preto", "#000000"),
    BRANCO("Branco", "#FFFFFF");

    companion object {
        fun getNomeList(): List<String> {
            return values().map { it.nome }
        }

        fun getHexValueByName(nome: String): String? {
            return values().firstOrNull { it.nome == nome }?.hexValue
        }

        fun getColorByHexValue(hexValue: String) : String?{
            return values().firstOrNull { it.hexValue == hexValue }?.nome
        }
    }
}
