package com.goit.dev13.service;

import com.goit.dev13.entities.Note;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NoteService {
    private final Map<Long, Note> noteMap = new HashMap<>();

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

        Note existingNote = noteMap.get(id);
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
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
}
