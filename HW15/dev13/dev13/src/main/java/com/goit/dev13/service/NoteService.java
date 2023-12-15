package com.goit.dev13.service;

import com.goit.dev13.entities.Note;
import com.goit.dev13.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public Note add(Note note) {
        note.setId(null);
        return noteRepository.save(note);
    }

    public void deleteById(long id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        long id = note.getId();
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }

        noteRepository.save(note);
    }

    public Note getById(long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        return optionalNote.orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
    }

    private long generateUniqueId() {
        return System.currentTimeMillis();
    }

}
