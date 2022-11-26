package com.artzvrzn.service.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {

  void handle(Update update);
}
