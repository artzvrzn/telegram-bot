package com.artzvrzn.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateConsumer {

  void consume(Update update);
}
