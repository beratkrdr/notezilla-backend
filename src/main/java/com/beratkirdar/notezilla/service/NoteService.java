package com.beratkirdar.notezilla.service;

import com.beratkirdar.notezilla.entity.Note;

import java.util.List;

public interface NoteService {

    List<Note> findAll();

    Note findById(Long id);

    Note save(Note note);

    Note update(Note note);

    void deleteById(Long id);

}
