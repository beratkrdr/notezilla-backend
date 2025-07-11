package com.beratkirdar.notezilla.controller;

import com.beratkirdar.notezilla.entity.Note;
import com.beratkirdar.notezilla.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@Tag(name = "Note", description = "Operations related to notes such as create, read, update, delete, and search")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    @Operation(
            summary = "Get All Notes",
            description = "Retrieves a list of all notes belonging to the authenticated user."
    )
    public ResponseEntity<List<Note>> findAll(){
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping("/{noteId}")
    @Operation(
            summary = "Get Note by ID",
            description = "Retrieves a single note by its unique ID."
    )
    public ResponseEntity<Note> findById(@PathVariable Long noteId){
        return ResponseEntity.ok(noteService.findById(noteId));
    }

    @PostMapping
    @Operation(
            summary = "Create Note",
            description = "Creates a new note for the authenticated user."
    )
    public ResponseEntity<Note> save(@RequestBody Note note){
        return ResponseEntity.ok(noteService.save(note));
    }

    @PutMapping
    @Operation(
            summary = "Update Note",
            description = "Updates an existing note. Note ID must be provided in the request body."
    )
    public ResponseEntity<Note> update(@RequestBody Note note){
        return ResponseEntity.ok(noteService.update(note));
    }

    @DeleteMapping("/{noteId}")
    @Operation(
            summary = "Delete Note",
            description = "Deletes the note with the specified ID."
    )
    public ResponseEntity<Void> delete(@PathVariable Long noteId){
        noteService.deleteById(noteId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search Notes",
            description = "Searches for notes containing the specified keyword in the title or content."
    )
    public ResponseEntity<List<Note>> search(@RequestParam String keyword){
        return ResponseEntity.ok(noteService.search(keyword));
    }

}
