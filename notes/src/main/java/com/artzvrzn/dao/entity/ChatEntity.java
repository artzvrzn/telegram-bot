package com.artzvrzn.dao.entity;

import com.artzvrzn.domain.constants.State;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chat", schema = "app")
@Getter
@Setter
public class ChatEntity {
  @Id
  @Column(unique = true)
  private Long id;
  private State state;
}
