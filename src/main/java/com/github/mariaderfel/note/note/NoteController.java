package com.github.mariaderfel.note.note;

import com.github.mariaderfel.note.searcher.NoteId;
import com.github.mariaderfel.note.searcher.NoteKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public NoteId addNote(@RequestBody NoteDto noteDto) {
        return noteService.addNote(noteDto);
    }

    @PostMapping("/list")
    public List<NoteDto> notesListByNoteKey(@RequestBody NoteKey noteKey) {
        return noteService.findLastByNoteKey(noteKey);
    }

    @PostMapping("/get")
    public NoteDto noteById(@RequestBody NoteId noteId) {
        NoteEntity note = noteService.findNoteById(noteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return NoteFunction.noteEntityToNoteDto.apply(note);
    }

}
