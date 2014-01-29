package io.github.uvpoblotzki.consumer;

import  static io.github.uvpoblotzki.common.Constants.*;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class ConsumerApplication {

  @Autowired
  private ConnectionFactory connectionFactory;

  @Autowired
  private AmqpAdmin amqpAdmin;

  @Bean
  public SimpleMessageListenerContainer simpleMessageListenerContainer() {
    SimpleMessageListenerContainer container =
        new SimpleMessageListenerContainer(this.connectionFactory);

    Queue queue = new Queue(BIGBEN_QUEUE, true);
    this.amqpAdmin.declareQueue(queue);

    container.setMessageListener(new BigBenListener());
    container.setQueueNames(BIGBEN_QUEUE);
    return container;
  }

  public static void main(String[] args) {
    SpringApplication.run(ConsumerApplication.class, args);
  }
}
