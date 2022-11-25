package com.artzvrzn.service.impl;

import static com.artzvrzn.domain.constants.RabbitQueues.RESPONSE_MESSAGE_UPDATE;

import com.artzvrzn.controller.UpdateController;
import com.artzvrzn.service.UpdateConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@Log4j2
@RequiredArgsConstructor
public class UpdateConsumerImpl implements UpdateConsumer {
  private final UpdateController updateController;

  @Override
  @RabbitListener(queues = RESPONSE_MESSAGE_UPDATE)
  public void consume(SendMessage message) {
    log.info("response received: " + message.getText());
    updateController.sendResponse(message);
  }
}
