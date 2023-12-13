package com.goit.dev13.service;

import com.goit.dev13.entities.Note;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {
    private final Map<Long, Note> noteMap = new HashMap<>();

    public List<Note> listAll() {
        return new ArrayList<>(noteMap.values());
    }

    public Note add(Note note) {
        note.setId(generateUniqueId());
        noteMap.put(note.getId(), note);
        return note;
    }

    public void deleteById(long id) {
        if (!noteMap.containsKey(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }
        noteMap.remove(id);
    }

    public void update(Note note) {
        long id = note.getId();
        if (!noteMap.containsKey(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }

        noteMap.put(id, note);
    }

    public Note getById(long id) {
        if (!noteMap.containsKey(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }
        return noteMap.get(id);
    }

    private long generateUniqueId() {
        return System.currentTimeMillis();
    }

    @PostConstruct
    private void filler() {
        add(new Note(1L, "World", "Hello world"));
        add(new Note(2L, "Hello", "World hello"));
    }
}
