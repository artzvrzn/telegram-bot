package com.artzvrzn.dao.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "notes", schema = "app")
@Getter
@Setter
@NoArgsConstructor
public class NoteEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  @Setter(AccessLevel.PACKAGE)
  private UUID id;
  @CreatedDate
  @Setter(AccessLevel.PACKAGE)
  private LocalDateTime created;
  @Version
  @LastModifiedDate
  @Setter(AccessLevel.PACKAGE)
  private LocalDateTime updated;
  @ManyToOne
  @JoinColumn(
      name = "chat_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "chat_note_fk")
  )
  @Setter(AccessLevel.PACKAGE)
  private ChatEntity chatEntity;
  private String content;

  public NoteEntity(ChatEntity chatEntity, String content) {
    this.chatEntity = chatEntity;
    this.content = content;
  }
}
