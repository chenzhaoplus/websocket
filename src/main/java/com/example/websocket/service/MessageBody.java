package com.example.websocket.service;

import lombok.Data;

/**
 * @author khzhang
 * @date 2021/6/23 10:20
 * @description
 */
@Data
public class MessageBody {
    /** 消息内容 */
    private String content;
    /** 广播转发的目标地址（告知 STOMP 代理转发到哪个地方） */
    private String destination;
}
