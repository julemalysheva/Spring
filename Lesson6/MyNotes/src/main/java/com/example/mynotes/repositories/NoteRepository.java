package com.example.mynotes.repositories;

import com.example.mynotes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с заметками.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Найти заметку по идентификатору.
     *
     * @param id идентификатор заметки
     * @return заметка, обернутая в Optional
     */
    Optional<Note> findById(Long id);

    /**
     * Найти заметки, у которых заголовок содержит заданное ключевое слово игнорируя регистр.
     *
     * @param keywordTitle ключевое слово для поиска в заголовках заметок
     * @return список заметок, удовлетворяющих условию поиска
     */
    List<Note> findByTitleContainingIgnoreCase(String keywordTitle);

    /**
     * Найти заметки, у которых содержимое содержит заданное ключевое слово игнорируя регистр.
     *
     * @param keywordContent ключевое слово для поиска в содержимом заметок
     * @return список заметок, удовлетворяющих условию поиска
     */
    List<Note> findNotesByContentContainingIgnoreCase(String keywordContent);

    /**
     * Найти заметки, у которых заголовок и содержимое содержат заданные ключевые слова игнорируя регистр.
     *
     * @param keywordTitle ключевое слово для поиска в заголовках заметок
     * @param keywordContent ключевое слово для поиска в содержимом заметок
     * @return список заметок, удовлетворяющих условиям поиска
     */
    List<Note> findByTitleContainingIgnoreCaseAndContentContainingIgnoreCase(
            String keywordTitle, String keywordContent);



}

