package com.example.mynotes.services;

import com.example.mynotes.models.Note;
import com.example.mynotes.repositories.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Теперь создадим класс NoteServiceImpl, который будет реализовывать наш
 * интерфейс NoteService:
 */
@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));
    }

    @Override
    public Note createNote(Note note) {
        return repository.save(note);
    }

    @Override
    public Note updateNote(Long id, Note note) {
        // мы должны сначала проверить, существует ли заметка с данным ID
        Note existingNote = getNoteById(id);
// обновляем поля существующей заметки
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
// сохраняем и возвращаем обновленную заметку
        return repository.save(existingNote);

    }

    @Override
    public void deleteNote(Long id) {
        // проверяем, существует ли заметка с данным ID
        getNoteById(id);
// если да, то удаляем ее
        repository.deleteById(id);
    }

    @Override
    public List<Note> searchNotes(String keyword) {
        return repository.findByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public List<Note> getNotesByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    @Override
    public List<Note> searchByAuthorAndTitleContainingIgnoreCase(String author, String keyword) {
        if (author != null && keyword != null) {
            return repository.findByAuthorAndTitleContainingIgnoreCase(author, keyword);
        } else {
            throw new IllegalArgumentException("Не указаны оба параметра для поиска");
        }
    }
}
