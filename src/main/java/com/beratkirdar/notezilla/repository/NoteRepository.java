package com.beratkirdar.notezilla.repository;

import com.beratkirdar.notezilla.entity.Note;
import com.beratkirdar.notezilla.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);

    List<Note> findByUserAndTitleContainingOrUserAndNoteContaining(User user, String keyword, User user1, String keyword1);
}
