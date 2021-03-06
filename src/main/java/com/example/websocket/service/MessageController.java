package com.example.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author khzhang
 * @date 2021/6/23 10:23
 * @description
 */
@RestController
@MessageMapping("/msg")
public class MessageController {

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    /**
     * 广播发送消息，将消息发送到指定的目标地址
     */
    @MessageMapping("/test")
    public void sendTopicMessage(MessageBody messageBody) {
        // 将消息发送到 WebSocket 配置类中配置的代理中（/topic）进行消息转发
        simpMessageSendingOperations.convertAndSend(messageBody.getDestination(), messageBody);
    }

    @GetMapping("/add")
    public String add(String dest) {
        MessageBody messageBody = new MessageBody();
        messageBody.setContent("咋地？");
        messageBody.setDestination("/topic" + dest);
        simpMessageSendingOperations.convertAndSend(messageBody.getDestination(), messageBody);
        return "成功";
    }

}
