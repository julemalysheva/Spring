package com.example.mynotes.controllers;

import com.example.mynotes.models.Note;
import com.example.mynotes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    // методы контроллера
    @GetMapping
    public List<Note> getAllNotes() {
        return service.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return service.getNoteById(id);
    }

    //    @GetMapping("/search")
//    public List<Note> searchNotes(@RequestParam("keyword") String keyword) {
//        return service.searchNotes(keyword);
//    }
    @GetMapping("/search")
    public List<Note> searchNotes(@RequestParam(required = false) String author, @RequestParam(required = false) String keyword) {
        if (author != null && keyword != null) {
            // Поиск по обоим параметрам author и keyword
            return service.searchByAuthorAndTitleContainingIgnoreCase(author, keyword);
        } else if (author != null) {
            // Поиск только по параметру author
            return service.getNotesByAuthor(author);
        } else if (keyword != null) {
            // Поиск только по параметру keyword
            return service.searchNotes(keyword);
        } else {
            // Обрабатывать случай, когда не предоставлено ни одного параметра
            throw new IllegalArgumentException("Не указаны параметры поиска");
        }
    }


    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return service.createNote(note);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note note) {
        return service.updateNote(id, note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        service.deleteNote(id);
    }

}

/**
 * В этом классе мы используем NoteService для выполнения операций над заметками.
 * Аннотация @RequestMapping("/api/notes") указывает, что все методы в этом
 * контроллере будут обрабатывать запросы, начинающиеся с /api/notes
 * <p>
 * Каждый из этих методов соответствует определенному типу HTTP-запроса (GET,
 * POST, PUT, DELETE) и обрабатывает определенный тип операции.
 * ● @GetMapping, @PostMapping, @PutMapping и @DeleteMapping – это
 * специализированные версии аннотации @RequestMapping, которые
 * указывают тип HTTP-запроса.
 * ● @PathVariable используется для привязки части URL к параметру метода.
 * ● @RequestBody используется для привязки тела запроса к параметру метода.
 * Таким образом, контроллер предоставляет “входную точку” в наше приложение для
 * клиентов, переводя HTTP-запросы в вызовы методов нашего сервиса.
 */

