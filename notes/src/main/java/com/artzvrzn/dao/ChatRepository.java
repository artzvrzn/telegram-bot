package com.artzvrzn.dao;

import com.artzvrzn.dao.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

}
