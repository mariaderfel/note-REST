package com.github.mariaderfel.note.note;

import com.github.mariaderfel.note.searcher.NoteId;
import com.github.mariaderfel.note.searcher.NoteKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.mariaderfel.note.note.NoteFunction.noteDtoToNoteEntity;
import static com.github.mariaderfel.note.note.NoteFunction.noteEntityToNoteDto;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteId addNote(NoteDto noteDto) {
        NoteId noteId = new NoteId();
        NoteEntity noteEntity = noteDtoToNoteEntity.apply(noteDto);
        noteEntity.setCreated(LocalDateTime.now());
        NoteEntity savedNote = noteRepository.save(noteEntity);
        noteId.setId(savedNote.getId());
        return noteId;
    }

    public List<NoteDto> findLastByNoteKey(NoteKey noteKey) {
        final int NUMBER_OF_NOTES = 20;
        List<NoteEntity> noteEntities = noteRepository.findAll(Sort.by(Sort.Direction.DESC, "created"));
        return noteEntities.stream()
                .filter(noteEntity -> noteEntity.getNoteKey().equals(noteKey.getNoteKey()))
                .limit(NUMBER_OF_NOTES)
                .map(noteEntity -> noteEntityToNoteDto.apply(noteEntity))
                .collect(Collectors.toList());
    }

    public Optional<NoteEntity> findNoteById(NoteId noteId) {
        return noteRepository.findById(noteId.getId());
    }

}
