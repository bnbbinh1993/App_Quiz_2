package com.example.app_quiz.utils

import android.graphics.Color


/*
val: read-only (chỉ để đọc) chỉ khỏi tạo getter,
với var: mutable (có thể thay đổi được): khởi tạo getter và setter
 */
object ColorPicker {
    val color =  arrayOf(
            "#FFBB86FC",
            "#B9F6CA",
            "#FF6200EE",
            "#FF3700B3",
            "#FF03DAC5",
            "#FF018786",
            "#FF000000",
            "#FFFFFFFF",
            "#F44336",
            "#B9F6CA",
            "#FF2962FF",
            "#FFD600")
    var currentColorIndex = 0
    fun getColor():String{
        currentColorIndex = (currentColorIndex+1)% color.size
        return color[currentColorIndex]
    }

}
