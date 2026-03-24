package com.eltex

import java.time.Instant

class NoteService {
    private val createdAt = Instant.now()

    private var note: Note = Note(
        text = "",
        createdAt = createdAt,
        updatedAt = createdAt
    )

    fun updateText(text: String) {
        note = note.copy(text = text, updatedAt = Instant.now())
    }

    fun getNote(): Note {
        return note
    }
}