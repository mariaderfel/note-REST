package com.github.mariaderfel.note.note;

import com.github.mariaderfel.note.searcher.NoteId;
import com.github.mariaderfel.note.searcher.NoteKey;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    private static List<NoteEntity> noteEntities = new ArrayList<>();
    private static NoteKey existNoteKey = new NoteKey();
    private static NoteKey noExistNoteKey = new NoteKey();
    private static NoteId existNoteId = new NoteId();
    private static NoteId noExistNoteId = new NoteId();

    @Mock
    private NoteRepository noteRepository;
    @InjectMocks
    private NoteService noteService;

    @BeforeAll
    public static void setUp() {
        noteEntities.add(new NoteEntity(1L, "ważna notatka1", "spotkanie", "omówienie projektu1", "qwe", LocalDateTime.now().minusDays(2)));
        noteEntities.add(new NoteEntity(2L, "ważna notatka2", "spotkanie", "omówienie projektu2", "asd", LocalDateTime.now().minusDays(1)));
        noteEntities.add(new NoteEntity(3L, "ważna notatka3", "spotkanie", "omówienie projektu3", "qwe", LocalDateTime.now()));
        noteEntities.add(new NoteEntity(4L, "ważna notatka4", "spotkanie", "omówienie projektu4", "dfg", LocalDateTime.now().minusDays(7)));
        noteEntities.add(new NoteEntity(5L, "ważna notatka5", "spotkanie", "omówienie projektu5", "qwe", LocalDateTime.now().minusDays(3)));
        existNoteKey.setNoteKey("qwe");
        noExistNoteKey.setNoteKey("ghj");
        existNoteId.setId(3L);
        noExistNoteId.setId(25L);
    }

    @AfterAll
    public static void tearDown() {
        noteEntities = null;
        existNoteKey = null;
        noExistNoteKey = null;
        existNoteId = null;
        noExistNoteId = null;
    }

    @Test
    public void testIfAddNote() {
        //given
        NoteEntity newNote = new NoteEntity(10L, "nowa ważna notatka", "nowe spotkanie", "omówienie nowego projektu", "qwe", LocalDateTime.now());
        NoteDto noteDto = new NoteDto(10L, "nowa ważna notatka", "nowe spotkanie", "omówienie nowego projektu", "qwe", LocalDateTime.now());
        when(noteRepository.save(any())).thenReturn(newNote);

        //when
        NoteId noteId = noteService.addNote(noteDto);

        //then
        assertThat(noteId.getId()).isEqualTo(newNote.getId());
    }

    @Test
    public void testIfFindNoteByNoteKey() {
        //given
        when(noteRepository.findAll(Sort.by(Sort.Direction.DESC, "created"))).thenReturn(noteEntities);

        //when
        List<NoteDto> noteDtos = noteService.findLastByNoteKey(existNoteKey);

        //then
        assertThat(noteDtos).hasSize(3);
    }

    @Test
    public void testIfReturnEmptyListIfNoteKeyDoesNotExist() {
        //given
        when(noteRepository.findAll(Sort.by(Sort.Direction.DESC, "created"))).thenReturn(noteEntities);

        //when
        List<NoteDto> noteDtos = noteService.findLastByNoteKey(noExistNoteKey);

        //then
        assertThat(noteDtos).hasSize(0);
    }

    @Test
    public void testIfFindNoteById() {
        //given
        Optional<NoteEntity> noteEntityOptional = Optional.of(noteEntities.get(2));
        when(noteRepository.findById(existNoteId.getId())).thenReturn(noteEntityOptional);

        //when
        Optional<NoteEntity> noteEntity = noteService.findNoteById(existNoteId);

        //then
        assertThat(noteEntity.get().getId()).isEqualTo(existNoteId.getId());
    }

    @Test
    public void testIfReturnEmptyOptionalIfNoteIdDoesNotExist() {
        //given
        Optional<NoteEntity> noteEntityOptional = Optional.empty();
        when(noteRepository.findById(noExistNoteId.getId())).thenReturn(noteEntityOptional);

        //when
        Optional<NoteEntity> noteEntity = noteService.findNoteById(noExistNoteId);

        //then
        assertThat(noteEntity).isEmpty();
    }
}
