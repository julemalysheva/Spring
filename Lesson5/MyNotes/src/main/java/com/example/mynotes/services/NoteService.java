package com.example.mynotes.services;

import com.example.mynotes.models.Note;

import java.util.List;

/**
 * Вначале, создадим интерфейс для нашего сервиса. Это хорошая практика,
 * поскольку она делает наш код более гибким и тестируемым:
 */
public interface NoteService {
    List<Note> getAllNotes();
    Note getNoteById(Long id);
    Note createNote(Note note);
    Note updateNote(Long id, Note note);
    void deleteNote(Long id);
    List<Note> searchNotes(String keyword);
    List<Note> getNotesByAuthor(String author);
    List<Note> searchByAuthorAndTitleContainingIgnoreCase(String author, String keyword);

}
