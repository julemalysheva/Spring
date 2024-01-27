package org.example.demo4.controller;

import lombok.RequiredArgsConstructor;
import org.example.demo4.model.Reader;
import org.example.demo4.repo.ReaderRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
@RequiredArgsConstructor
public class ReaderController {
    private final ReaderRepo readerRepo;

    @GetMapping
    public ResponseEntity<List<Reader>> getAll(){
        List<Reader> readers = readerRepo.findAll();
        return ResponseEntity.ok(readers);
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader){
        return new ResponseEntity<>(readerRepo.save(reader), HttpStatus.CREATED);
    }
}
