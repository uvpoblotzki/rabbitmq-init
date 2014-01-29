package io.github.uvpoblotzki.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class BigBenListener implements MessageListener {

  @Override
  public void onMessage(Message message) {
    System.out.println(message);
  }
}
