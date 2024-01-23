package com.example.mynotes.repositories;

import com.example.mynotes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * В этом интерфейсе мы указываем два параметра типа для JpaRepository: тип нашей
 * сущности (Note) и тип идентификатора (Long). JpaRepository предоставляет
 * нам множество полезных методов, таких как findAll(), findById(), save(), delete(), и
 * т.д., без необходимости их реализовывать.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    /**
     * В дополнение к методам, предоставляемым JpaRepository, мы можем добавить свои
     * собственные методы в репозиторий. Например, давайте добавим метод для поиска
     * заметок по автору:
     * @param author
     * @return
     */
    List<Note> findByAuthor(String author);

    List<Note> findByTitleContainingIgnoreCase(String keyword);

    List<Note> findByAuthorAndTitleContainingIgnoreCase(String author, String keyword);

    /**
     * Мы просто определили метод с именем findByAuthor, и Spring Data автоматически
     * предоставит его реализацию для нас. Теперь, когда мы вызываем этот метод, Spring
     * Data выполнит запрос к базе данных, чтобы найти все заметки с указанным
     * автором
     */
}

