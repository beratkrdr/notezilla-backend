package com.beratkirdar.notezilla.repository;

import com.beratkirdar.notezilla.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {



}
