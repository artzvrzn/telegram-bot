package com.artzvrzn.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Note {
  private UUID id;
  private LocalDateTime created;
  private LocalDateTime updated;
  private Long chatId;
  private String content;
}
