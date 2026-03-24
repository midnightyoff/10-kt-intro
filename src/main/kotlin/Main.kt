package com.eltex


fun main() {
    val noteService = NoteService()

    println(noteService.getNote())

    noteService.updateText("Hello World")

    println(noteService.getNote())
}