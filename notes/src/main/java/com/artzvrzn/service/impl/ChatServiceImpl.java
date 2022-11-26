package com.artzvrzn.service.impl;

import com.artzvrzn.dao.ChatRepository;
import com.artzvrzn.dao.entity.ChatEntity;
import com.artzvrzn.domain.Chat;
import com.artzvrzn.domain.constants.State;
import com.artzvrzn.service.ChatService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
  private final ChatRepository chatRepository;
  private final ModelMapper mapper;

  @Override
  @Transactional
  public Chat get(Long id) {
    Optional<ChatEntity> optionalChat = chatRepository.findById(id);
    if (optionalChat.isPresent()) {
      return mapper.map(optionalChat.get(), Chat.class);
    }
    ChatEntity entity = buildChatEntity(id);
    chatRepository.save(entity);
    return mapper.map(entity, Chat.class);
  }

  @Override
  @Transactional
  public void updateState(Long id, State state) {
    ChatEntity chatEntity = chatRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("note doesn't exist"));
    chatEntity.setState(state);
    chatRepository.save(chatEntity);
  }

  private ChatEntity buildChatEntity(Long id) {
    ChatEntity entity = new ChatEntity();
    entity.setId(id);
    entity.setState(State.MAIN);
    return entity;
  }
}
