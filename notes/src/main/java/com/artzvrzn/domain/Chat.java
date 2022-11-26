package com.artzvrzn.domain;

import com.artzvrzn.domain.constants.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chat {
  private Long id;
  private State state;
}
