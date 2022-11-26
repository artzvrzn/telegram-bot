package com.artzvrzn.service.impl;

import static com.artzvrzn.domain.constants.RabbitQueues.RESPONSE_MESSAGE_UPDATE;
import static com.artzvrzn.domain.constants.RabbitQueues.TEXT_MESSAGE_UPDATE;

import com.artzvrzn.service.ResponseProducer;
import com.artzvrzn.service.UpdateConsumer;
import com.artzvrzn.service.handler.UpdateHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Log4j2
@RequiredArgsConstructor
public class UpdateConsumerImpl implements UpdateConsumer {
  private final UpdateHandler updateHandler;

  @Override
  @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
  public void consume(Update update) {
    updateHandler.handle(update);
  }
}
