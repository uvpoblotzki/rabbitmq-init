package io.github.uvpoblotzki.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

@Configuration
@EnableAutoConfiguration
public class ProducerApplication {

  @Autowired
  private AmqpTemplate amqpTemplate;

  @Autowired
  private ConnectionFactory connectionFactory;

  @Bean
  public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
    return new ScheduledAnnotationBeanPostProcessor();
  }

  @Bean
  public BigBen bigBenn() {
    return new BigBen();
  }

  public static void main(String[] args) {
    SpringApplication.run(ProducerApplication.class, args);
  }
}
