package com.zhh.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description: WebSocket服务
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/21 10:27
 */
@Component
@ServerEndpoint("/ws")
public class ApplicationWebSocket {
    Logger logger = LoggerFactory.getLogger(ApplicationWebSocket.class);

    // 静态变量，用于存放每个客户端对应的websocket对象，ConcurrentHashMap
    private static ConcurrentHashMap<String,Session> webSocketMap = new ConcurrentHashMap<String,Session>();
    private static CopyOnWriteArraySet<ApplicationWebSocket> webSocketSet = new CopyOnWriteArraySet<ApplicationWebSocket>();

    // 每个客户端连接的会话
    private Session session;

    // 当前用户
    private String username;

    // 新建连接成功时调用方法
    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName){
        this.session = session;
        this.username = userName;
        webSocketMap.put(session.getId(),session);
        webSocketSet.add(this);
        String message = "有新连接加入：" + userName +",当前在线人数："+ ApplicationWebSocket.webSocketSet.size();
        logger.info(message);
        broadcastMessage(message);
    }

    // 收到客户端消息时调用方法
    @OnMessage
    public void onMessage(String message, Session session){
        // 群发消息
        broadcastMessage(message);
    }

    // 连接退出时调用方法
    @OnClose
    public void onClose(){
        webSocketMap.remove(this.session.getId());
        webSocketSet.remove(this);
        String message = "有一连接退出，当前在线人数：" + ApplicationWebSocket.webSocketSet.size();
        logger.info(message);
        broadcastMessage(message);
    }

    // 发生错误时调用方法
    @OnError
    public void onError(Session session, Throwable error){
        String message = "发生错误："+error.toString();
        logger.error(message);
        session.getAsyncRemote().sendText(message);
    }

    // 群聊
    public void broadcastMessage(String message){
        for(ApplicationWebSocket item : webSocketSet){
            // 异步发送
            item.session.getAsyncRemote().sendText(message);
            // 同步发送
            //item.session.getBasicRemote().sendText(message);
        }
    }

}


