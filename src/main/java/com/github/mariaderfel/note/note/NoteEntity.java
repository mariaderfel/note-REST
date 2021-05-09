package com.github.mariaderfel.note.note;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "note")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String type;
    private String text;
    private String noteKey;
    private LocalDateTime created;

    public NoteEntity() {
    }

    public NoteEntity(Long id, String title, String type, String text, String noteKey, LocalDateTime created) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.text = text;
        this.noteKey = noteKey;
        this.created = created;
    }

    public NoteEntity(String title, String type, String text, String noteKey, LocalDateTime created) {
        this.title = title;
        this.type = type;
        this.text = text;
        this.noteKey = noteKey;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNoteKey() {
        return noteKey;
    }

    public void setNoteKey(String key) {
        this.noteKey = key;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
