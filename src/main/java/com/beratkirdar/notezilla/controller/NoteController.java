package com.beratkirdar.notezilla.controller;

import com.beratkirdar.notezilla.entity.Note;
import com.beratkirdar.notezilla.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> findAll(){
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> findById(@PathVariable Long noteId){
        return ResponseEntity.ok(noteService.findById(noteId));
    }

    @PostMapping
    public ResponseEntity<Note> save(@RequestBody Note note){
        return ResponseEntity.ok(noteService.save(note));
    }

    @PutMapping
    public ResponseEntity<Note> update(@RequestBody Note note){
        return ResponseEntity.ok(noteService.update(note));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> delete(@PathVariable Long noteId){
        noteService.deleteById(noteId);
        return ResponseEntity.ok().build();
    }

}
