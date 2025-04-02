package com.beratkirdar.notezilla.repository;

import com.beratkirdar.notezilla.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {



}
