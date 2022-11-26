package com.artzvrzn.service;

import com.artzvrzn.dao.entity.ChatEntity;
import com.artzvrzn.domain.Chat;
import com.artzvrzn.domain.constants.State;

public interface ChatService {

  Chat get(Long id);

  void updateState(Long id, State state);
}
