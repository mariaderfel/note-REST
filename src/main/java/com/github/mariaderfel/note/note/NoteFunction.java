package com.github.mariaderfel.note.note;

import java.util.function.Function;

public class NoteFunction {

    public static final Function<NoteEntity, NoteDto> noteEntityToNoteDto = noteEntity -> new NoteDto(
            noteEntity.getId(),
            noteEntity.getTitle(),
            noteEntity.getType(),
            noteEntity.getText(),
            noteEntity.getNoteKey(),
            noteEntity.getCreated()
    );

    public static final Function<NoteDto, NoteEntity> noteDtoToNoteEntity = noteDto -> new NoteEntity(
            noteDto.getTitle(),
            noteDto.getType(),
            noteDto.getText(),
            noteDto.getNoteKey(),
            noteDto.getCreated()
    );
}
