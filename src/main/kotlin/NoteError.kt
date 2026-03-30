package com.eltex

sealed interface NoteError {
    data object NoteNotFoundError : NoteError
}