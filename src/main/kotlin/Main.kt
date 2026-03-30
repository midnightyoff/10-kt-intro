package com.eltex

import java.time.Instant


fun main() {
    val noteService = NoteServiceImpl()

    val note1 = noteService.save(Note(0, "First note", Instant.now(), Instant.now()))
    val note2 = noteService.save(Note(0, "Second note", Instant.now(), Instant.now()))
    val note3 = noteService.save(Note(0, "Third note", Instant.now(), Instant.now()))
    val note4 = noteService.save(Note(0, "Fourth note", Instant.now(), Instant.now()))
    val note5 = noteService.save(Note(0, "Fifth note", Instant.now(), Instant.now()))
    val note6 = noteService.save(Note(0, "Fifth note", Instant.now(), Instant.now()))
    val note7 = noteService.save(Note(0, "Seventh note", Instant.now(), Instant.now()))
    val note8 = noteService.save(Note(0, "Eighth note", Instant.now(), Instant.now()))

    println("Все заметки:")
    noteService.getAll().forEach { println(it) }

    println("Уникальные тексты:")
    println(noteService.getAllUniqueTexts())

    println("Заметки перед id 4:")
    noteService.getBefore(3, 4).forEach { println(it) }

    println("Заметки после id 4:")
    noteService.getAfter(3, 4).forEach { println(it) }
}