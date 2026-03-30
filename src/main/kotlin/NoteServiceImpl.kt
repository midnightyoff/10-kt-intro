package com.eltex

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import java.time.Instant

class NoteServiceImpl : NoteService {
    private var nextId = 1L
    private var notes = emptyList<Note>()

    override fun updateText(id: Long, text: String): Either<NoteError, Note> {
        val note = notes.find { it.id == id }
            ?: return NoteError.NoteNotFoundError.left()
        val updatedNote = note.copy(text = text)
        notes = notes.map { if (it.id == id) updatedNote else it }
        return updatedNote.right()
    }

    override fun getNote(id: Long): Either<NoteError, Note> {
        return notes.find { it.id == id }?.right()
            ?: NoteError.NoteNotFoundError.left()
    }

    override fun save(note: Note): Either<NoteError, Note> {
        val currentTime = Instant.now()
        return if (note.id == 0L) {
            val newNote = note.copy(
                id = nextId++,
                createdAt = currentTime,
                updatedAt = currentTime,
            )
            notes = notes + newNote
            newNote.right()
        } else {
            if (notes.none { it.id == note.id }) {
                return NoteError.NoteNotFoundError.left()
            }
            val updatedNote = note.copy(updatedAt = currentTime)
            notes = notes.map { if (it.id == note.id) updatedNote else it }
            updatedNote.right()
        }
    }

    override fun getAll(): List<Note> = notes.toList()

    override fun getAllUniqueTexts(): List<String> = notes.map { it.text }
        .distinct()

    override fun getBefore(count: Int, id: Long): List<Note> = notes.dropLastWhile { it.id >= id }.takeLast(count)

    override fun getAfter(count: Int, id: Long): List<Note> = notes.dropWhile { it.id <= id }.take(count)
}