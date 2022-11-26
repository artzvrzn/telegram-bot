package com.artzvrzn.controller.telegram;

import static com.artzvrzn.domain.constants.Button.NOTE_DELETE_NO;
import static com.artzvrzn.domain.constants.Button.NOTE_DELETE_YES;
import static com.artzvrzn.domain.constants.Button.NOTE_MAIN_CREATE;
import static com.artzvrzn.domain.constants.Button.NOTE_MAIN_DELETE;
import static com.artzvrzn.domain.constants.Button.NOTE_MAIN_GET_ALL;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class NoteKeyboard {

  public static ReplyKeyboard getMainKeyboard() {
    KeyboardRow row = new KeyboardRow();
    row.add(new KeyboardButton(NOTE_MAIN_CREATE));
    row.add(new KeyboardButton(NOTE_MAIN_GET_ALL));
    row.add(new KeyboardButton(NOTE_MAIN_DELETE));
    List<KeyboardRow> keyboardRowList = new ArrayList<>();
    keyboardRowList.add(row);
    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
    keyboardMarkup.setKeyboard(keyboardRowList);
    keyboardMarkup.setSelective(true);
    keyboardMarkup.setResizeKeyboard(true);
    keyboardMarkup.setOneTimeKeyboard(false);
    return keyboardMarkup;
  }

  public static ReplyKeyboard getDeletionKeyboard() {
    KeyboardRow row = new KeyboardRow();
    row.add(new KeyboardButton(NOTE_DELETE_YES));
    row.add(new KeyboardButton(NOTE_DELETE_NO));
    List<KeyboardRow> keyboardRowList = new ArrayList<>();
    keyboardRowList.add(row);
    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
    keyboardMarkup.setKeyboard(keyboardRowList);
    keyboardMarkup.setSelective(true);
    keyboardMarkup.setResizeKeyboard(true);
    keyboardMarkup.setOneTimeKeyboard(false);
    return keyboardMarkup;
  }
}
