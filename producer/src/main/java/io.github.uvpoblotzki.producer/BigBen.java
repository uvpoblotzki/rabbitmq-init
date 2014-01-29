package io.github.uvpoblotzki.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.Collections;

import static io.github.uvpoblotzki.common.Constants.BIGBEN_EXCHANGE;
import static io.github.uvpoblotzki.common.Constants.BIGBEN_QUEUE;

public class BigBen {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private AmqpAdmin amqpAdmin;

  @PostConstruct
  public void setupExchange() {
    Exchange exchange = new FanoutExchange(BIGBEN_EXCHANGE, true, false);
    Queue queue = new Queue(BIGBEN_QUEUE, true);
    this.amqpAdmin.declareExchange(exchange);
    this.amqpAdmin.declareQueue(queue);
    this.amqpAdmin.declareBinding(
        new Binding(BIGBEN_QUEUE, Binding.DestinationType.QUEUE, BIGBEN_EXCHANGE, "tick", Collections.EMPTY_MAP)
    );
  }

  @Scheduled(fixedRate = 1000L)
  public void tick() {
    this.rabbitTemplate.convertAndSend(BIGBEN_EXCHANGE, "tick", System.currentTimeMillis());
  }

}
