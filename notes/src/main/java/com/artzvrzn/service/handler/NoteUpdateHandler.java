package com.artzvrzn.service.handler;

import static com.artzvrzn.domain.constants.Button.NOTE_DELETE_YES;
import static com.artzvrzn.domain.constants.Button.NOTE_MAIN_CREATE;
import static com.artzvrzn.domain.constants.Button.NOTE_MAIN_DELETE;
import static com.artzvrzn.domain.constants.Button.NOTE_MAIN_GET_ALL;

import com.artzvrzn.controller.telegram.NoteKeyboard;
import com.artzvrzn.domain.Chat;
import com.artzvrzn.domain.Note;
import com.artzvrzn.domain.constants.State;
import com.artzvrzn.service.ChatService;
import com.artzvrzn.service.NoteBook;
import com.artzvrzn.service.ResponseProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Component
@RequiredArgsConstructor
public class NoteUpdateHandler implements UpdateHandler {
  private final ChatService chatService;
  private final NoteBook noteBook;
  private final ResponseProducer responseProducer;

  @Override
  public void handle(Update update) {
    //TODO проверки
    Long chatId = update.getMessage().getChatId();
    Chat chat = chatService.get(chatId);
    String message = update.getMessage().getText();
    if (State.MAIN.equals(chat.getState())) {
      handleMain(message, chatId);
    } else if (State.CREATE.equals(chat.getState())) {
      handleCreate(message, chatId);
    }
  }

  private void handleMain(String message, Long chatId) {
    switch (message) {
      case NOTE_MAIN_CREATE:
        chatService.updateState(chatId, State.CREATE);
        responseProducer.produce(responseMessage(chatId, "Note creation stage"));
        break;
      case NOTE_MAIN_GET_ALL:
        responseProducer.produce(responseMessage(chatId, noteBook.getNotes(chatId).toString()));
        break;
      case NOTE_MAIN_DELETE:
        chatService.updateState(chatId, State.DELETE);
        responseProducer.produce(
            responseMessage(chatId, "Delete stage?", NoteKeyboard.getDeletionKeyboard()));
        break;
      default:
        responseProducer.produce(responseMessage(chatId, "hi"));
    }
  }

  private void handleCreate(String message, Long chatId) {
    Note note = new Note();
    note.setContent(message);
    note.setChatId(chatId);
    noteBook.createNote(note);
    chatService.updateState(chatId, State.MAIN);
    responseProducer.produce(responseMessage(chatId, "Note has been created!"));
  }

  private void handleDelete(String message, Long chatId) {
  }

  private SendMessage responseMessage(Long chatId, String text) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(text);
    sendMessage.setReplyMarkup(NoteKeyboard.getMainKeyboard());
    return sendMessage;
  }

  private SendMessage responseMessage(Long chatId, String text, ReplyKeyboard keyboard) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.setText(text);
    sendMessage.setReplyMarkup(NoteKeyboard.getMainKeyboard());
    return sendMessage;
  }
}
