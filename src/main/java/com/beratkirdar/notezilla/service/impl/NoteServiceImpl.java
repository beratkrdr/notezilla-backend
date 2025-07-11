package com.beratkirdar.notezilla.service.impl;

import com.beratkirdar.notezilla.entity.Note;
import com.beratkirdar.notezilla.entity.User;
import com.beratkirdar.notezilla.repository.NoteRepository;
import com.beratkirdar.notezilla.service.NoteService;
import com.beratkirdar.notezilla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserService userService;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, UserService userService){
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    @Override
    public List<Note> findAll() {
        User user = userService.getCurrentUser();

        return noteRepository.findByUser(user);
    }

    @Override
    public Note findById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @Override
    public Note save(Note note) {

        User user = userService.getCurrentUser();
        String title = note.getNote().substring(0, 30);

        note.setTitle(title);
        note.setUser(user);

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
    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> search(String keyword) {
        User user = userService.getCurrentUser();

        return noteRepository.findByUserAndTitleContainingOrUserAndNoteContaining(user, keyword, user, keyword);
    }

}
