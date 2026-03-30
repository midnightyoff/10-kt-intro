package com.eltex

import java.time.Instant

class NoteServiceImpl : NoteService {
    private var nextId = 1L
    private var notes = emptyList<Note>()

    fun updateText(id: Long, text: String) {
        notes = notes.map {
            if (it.id == id) {
                it.copy(text = text)
            } else
                it
        }
    }

    fun getNote(id: Long): Note? = notes.find { it.id == id }

    override fun save(note: Note): Note {
        val currentTime = Instant.now()
        return if (note.id == 0L) {
            val newNote = note.copy(id = nextId++, createdAt = currentTime, updatedAt = currentTime)
            notes = notes + newNote
            newNote
        } else {
            if (notes.none { it.id == note.id }) {throw IllegalArgumentException("Note with id ${note.id} not found") }
            val updatedNote = note.copy(updatedAt = currentTime)
            notes = notes.map {
                if (it.id == note.id) {
                    updatedNote
                } else
                    it
            }
            updatedNote
        }
    }

    override fun getAll(): List<Note> = notes.toList()

    override fun getAllUniqueTexts(): List<String> = notes.map { it.text }
        .distinct()

    override fun getBefore(count: Int, id: Long): List<Note> = notes.dropLastWhile { it.id >= id }.takeLast(count)

    override fun getAfter(count: Int, id: Long): List<Note> = notes.dropWhile { it.id <= id }.take(count)
}