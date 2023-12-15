package com.goit.dev13.springMVC;

import com.goit.dev13.entities.Note;
import com.goit.dev13.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private static NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public Note addNote(@RequestBody Note note) {
        return noteService.add(note);
    }

    @GetMapping("/list")
    public String listNote(Model model) {
        model.addAttribute("note", noteService.listAll());
        return "/note/list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editNote(@RequestParam long id, Model model) {
        model.addAttribute("note", noteService.getById(id));
        return "note/edit";
    }

    @PostMapping("/edit")
    public String saveEditNote(@ModelAttribute Note editedNote) {
        noteService.update(editedNote);
        return "redirect:/note/list";
    }
}
