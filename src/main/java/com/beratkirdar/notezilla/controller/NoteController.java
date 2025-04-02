package com.beratkirdar.notezilla.controller;

import com.beratkirdar.notezilla.model.Note;
import com.beratkirdar.notezilla.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Note> findAll(){
        return noteService.findAll();
    }

    @GetMapping("/{noteId}")
    public Note findById(@PathVariable Long noteId){
        return noteService.findById(noteId);
    }

    @PostMapping
    public Note save(@RequestBody Note note){
        return noteService.save(note);
    }

    @PutMapping
    public Note update(@RequestBody Note note){
        return noteService.update(note);
    }

}
