package com.artzvrzn.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageUtil {

  public static SendMessage generateMessage(Update update, String text) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(update.getMessage().getChatId().toString());
    sendMessage.setText(text);
    return sendMessage;
  }
}
