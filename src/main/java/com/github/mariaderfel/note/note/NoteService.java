package com.github.mariaderfel.note.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.github.mariaderfel.note.note.NoteFunction.noteEntityToNoteDto;
import static com.github.mariaderfel.note.note.NoteFunction.noteDtoToNoteEntity;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public UUID addNote(NoteDto noteDto) {
        NoteEntity noteEntity = noteDtoToNoteEntity.apply(noteDto);
        noteEntity.setCreated(LocalDateTime.now());
        NoteEntity savedNote = noteRepository.save(noteEntity);
        return savedNote.getId();
    }


}
