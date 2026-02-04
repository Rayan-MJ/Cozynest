package com.cozynest.cozynest.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.cozynest.cozynest.model.dto.LogEntryDTO;


@Service
public class LogProducer {

    private RabbitTemplate rabbitTemplate;


    public LogProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public LogEntryDTO send(LogEntryDTO  log){

        rabbitTemplate.convertAndSend(
                "logs-exchange",
                "logs-routing",
                log
        );

        return log;
    }
}