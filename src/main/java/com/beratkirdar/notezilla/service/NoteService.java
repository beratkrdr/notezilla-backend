package com.beratkirdar.notezilla.service;

import com.beratkirdar.notezilla.model.Note;

import java.util.List;

public interface NoteService {

    List<Note> findAll();

    Note findById(int id);

    Note save(Note note);

    Note update(Note note);

    void deleteById(int id);

}
