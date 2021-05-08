package com.github.mariaderfel.note.note;

import com.github.mariaderfel.note.searcher.NoteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
}
