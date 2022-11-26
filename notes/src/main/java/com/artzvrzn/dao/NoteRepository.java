package com.artzvrzn.dao;

import com.artzvrzn.dao.entity.NoteEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, UUID> {

  List<NoteEntity> findAllByChatEntity_Id(Long chatId);
}
