package com.goit.dev13;

import com.goit.dev13.entities.Note;
import com.goit.dev13.service.NoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Dev13Application {

	public static void main(String[] args) {
		SpringApplication.run(Dev13Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(NoteService noteService) {
		return args -> {
			Note note = new Note("Title", "Content");

			noteService.add(note);

			long idToGet = note.getId();
			System.out.println("\nGet by id " + idToGet + ": ");
			Note retrievedNote = noteService.getById(idToGet);
			System.out.println(retrievedNote);

			long idToUpdate = note.getId();
			System.out.println("\nUpdate by id " + idToUpdate + ": ");
			Note updateNote = new Note("Update title", "Update content");
			updateNote.setId(idToUpdate);
			noteService.update(updateNote);

			long idToDelete = note.getId();
			System.out.println("\nDelete by id " + idToDelete + ": ");
			noteService.deleteById(idToDelete);
		};
	}

}
