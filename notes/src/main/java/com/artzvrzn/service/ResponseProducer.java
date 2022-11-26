package com.artzvrzn.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ResponseProducer {

  void produce(SendMessage sendMessage);
}
