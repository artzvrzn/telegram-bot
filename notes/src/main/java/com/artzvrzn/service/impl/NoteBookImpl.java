package com.artzvrzn.service.impl;

import com.artzvrzn.dao.ChatRepository;
import com.artzvrzn.dao.NoteRepository;
import com.artzvrzn.dao.entity.ChatEntity;
import com.artzvrzn.dao.entity.NoteEntity;
import com.artzvrzn.domain.Note;
import com.artzvrzn.service.NoteBook;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteBookImpl implements NoteBook {
  private final NoteRepository noteRepository;
  private final ChatRepository chatRepository;
  private final ModelMapper mapper;

  @Override
  @Transactional
  public void createNote(Note note) {
    if (note == null) {
      log.debug("note is null");
      return;
    }
    ChatEntity chatEntity = chatRepository.getReferenceById(note.getChatId());
    NoteEntity noteEntity = new NoteEntity(chatEntity, note.getContent());
    noteRepository.save(noteEntity);
  }

  @Override
  public List<Note> getNotes(Long chatId) {
    return noteRepository.findAllByChatEntity_Id(chatId)
        .stream()
        .map(e -> mapper.map(e, Note.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<Note> getNotes(Long chatId, int page, int size) {
    return null;
  }

  @Override
  @Transactional
  public void updateNote(UUID id, Note note) {
    NoteEntity noteEntity = noteRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("note doesn't exist"));
    noteEntity.setContent(note.getContent());
    noteRepository.save(noteEntity);
  }

  @Override
  public void deleteNote(UUID id) {
    noteRepository.deleteById(id);
  }
}
