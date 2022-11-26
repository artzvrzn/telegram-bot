package com.artzvrzn.service;

import com.artzvrzn.domain.Note;
import java.util.List;
import java.util.UUID;

public interface NoteBook {

  void createNote(Note note);

  List<Note> getNotes(Long chatId);

  List<Note> getNotes(Long chatId, int page, int size);

  void updateNote(UUID id, Note note);

  void deleteNote(UUID id);
}
