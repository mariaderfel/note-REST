package com.github.mariaderfel.note.note;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

public class NoteDto {
    private UUID id;
    @NotBlank(message = "Please, write title")
    @Length(max = 50)
    private String title;
    @NotBlank(message = "Please, write type")
    @Length(max = 50)
    private String type;
    @NotBlank(message = "Please, write text")
    @Length(max = 200)
    private String text;
    private String key;
    private LocalDateTime created;

    public NoteDto() {
    }

    public NoteDto(UUID id, String title, String type, String text, String key, LocalDateTime created) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.text = text;
        this.key = key;
        this.created = created;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
