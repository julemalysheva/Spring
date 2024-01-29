package com.example.mynotes.controllers;

import com.example.mynotes.models.Note;
import com.example.mynotes.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с заметками.
 */

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService service;

    /**
     * Получить все заметки.
     *
     * @return список всех заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(service.getAllNotes());
    }

    /**
     * Получить заметку по идентификатору.
     *
     * @param id идентификатор заметки
     * @return заметка с указанным идентификатором
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getNoteById(id));
    }

    /**
     * Поиск заметок по ключевым словам в заголовках и содержимом.
     * Если оба ключевых слова не указаны, вернуть все заметки.
     *
     * @param keywordContent ключевое слово для поиска в содержимом заметок
     * @param keywordTitle   ключевое слово для поиска в заголовках заметок
     * @return список заметок, удовлетворяющих условиям поиска или все заметки, если оба ключевых слова не указаны
     */
    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchNotes(@RequestParam(required = false) String keywordContent,
                                                  @RequestParam(required = false) String keywordTitle) {
        if (keywordContent != null && keywordTitle != null) {
            return ResponseEntity.ok(service.getNotesByTitleAndContentContaining(keywordTitle, keywordContent));
        } else if (keywordContent != null) {
            return ResponseEntity.ok(service.getNotesByContentContaining(keywordContent));
        } else if (keywordTitle != null) {
            return ResponseEntity.ok(service.getNotesByTitleContaining(keywordTitle));
        } else {
            return ResponseEntity.ok(service.getAllNotes());
        }
    }

    /**
     * Создать новую заметку.
     *
     * @param note новая заметка
     * @return созданная заметка
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return ResponseEntity.ok(service.createNote(note));
    }

    /**
     * Обновить существующую заметку.
     *
     * @param note обновленная заметка
     * @return обновленная заметка
     */
    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note note) {
        return ResponseEntity.ok(service.updateNote(note));
    }

    /**
     * Удалить заметку по идентификатору.
     *
     * @param id идентификатор заметки
     * @return ответ без содержимого
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }

}



