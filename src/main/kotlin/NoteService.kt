package com.eltex

import arrow.core.Either

interface NoteService {
    /**
     * Если id == 0, создаёт новую заметку.
     * Если id равен существующей заметке — обновляет её.
     * Если указан несуществующий id — возвращает Either.Left(NoteError.NoteNotFoundError).
     * При обновлении updatedAt заполняется текущим временем.
     */
    fun save(note: Note): Either<NoteError, Note>

    /**
     * Обновляет текст заметки по id.
     * Если заметка не найдена — возвращает Either.Left(NoteError.NoteNotFoundError).
     */
    fun updateText(id: Long, text: String): Either<NoteError, Note>

    /**
     * Возвращает заметку по id.
     * Если заметка не найдена — возвращает Either.Left(NoteError.NoteNotFoundError).
     */
    fun getNote(id: Long): Either<NoteError, Note>

    /**
     * Возвращает копию внутреннего списка
     */
    fun getAll(): List<Note>

    /**
     * Возвращает список текстов без повторов
     */
    fun getAllUniqueTexts(): List<String>

    /**
     * Возвращает несколько заметок старше указанного id
     * @param count – сколько заметок отсчитать
     * @param id - относительно какого элемента отсчитывать
     */
    fun getBefore(count: Int, id: Long): List<Note>

    /**
     * Возвращает несколько заметок новее указанного id
     * @param count – сколько заметок отсчитать
     * @param id - относительно какого элемента отсчитывать
     */
    fun getAfter(count: Int, id: Long): List<Note>
}