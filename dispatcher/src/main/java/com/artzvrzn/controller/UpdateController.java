package com.artzvrzn.controller;

import static com.artzvrzn.domain.constants.RabbitQueues.TEXT_MESSAGE_UPDATE;

import com.artzvrzn.service.UpdateProducer;
import com.artzvrzn.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
@Log4j2
public class UpdateController {
  private final UpdateProducer updateProducer;
  private TelegramBot bot;

  void registerBot(TelegramBot bot) {
    this.bot = bot;
  }

  public void processUpdate(Update update) {
    if (update == null) {
      log.error("null update has been received");
      return;
    }
    if (update.hasMessage()) {
      handleMessage(update);
    } else {
      bot.sendMessage(MessageUtil.generateMessage(update, "формат сообщения не поддерживается"));
    }
  }

  public void sendResponse(SendMessage message) {
    bot.sendMessage(message);
  }

  private void handleMessage(Update update) {
    Message message = update.getMessage();
    if (message.hasText()) {
      updateProducer.produce(TEXT_MESSAGE_UPDATE, update);
    }
  }
}
