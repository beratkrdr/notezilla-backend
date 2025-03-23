package com.beratkirdar.notezilla.service.impl;

import com.beratkirdar.notezilla.model.Note;
import com.beratkirdar.notezilla.repository.NoteRepository;
import com.beratkirdar.notezilla.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note findById(int id) {
        return noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note update(Note note) {

        Note foundNote = noteRepository.findById(note.getId()).orElseThrow(() -> new RuntimeException("Note not found"));

        foundNote.setTitle(note.getTitle());
        foundNote.setNote(note.getNote());
        foundNote.setDateUpdated(new Date());

        return noteRepository.save(foundNote);
    }

    @Override
    public void deleteById(int id) {
        noteRepository.deleteById(id);
    }

}
