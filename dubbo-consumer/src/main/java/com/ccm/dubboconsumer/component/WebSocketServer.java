package com.ccm.dubboconsumer.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by chencm on 2018/12/11
 */
@Slf4j
@Component
@ServerEndpoint("/webSocket/{id}")
public class WebSocketServer {
    //用于判定唯一用户
    private String id;
    //用于建立通信
    private Session session;

    /**
     * 建立socket连接
     * @param id
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "id") String id,
                        Session session){
        try {
            this.session=session;
            this.id=id;
            WebSocketInfoCompent.webSocketMap.put(id,this);
            log.debug("用户id："+this.id +",已上线");
        } catch (Exception e) {
            log.error("onOpen():"+e.getMessage());
        }
    }

    @OnClose
    public void onClose(){
        log.debug("用户id："+this.id +",已下线");
        WebSocketInfoCompent.webSocketMap.remove(this.id);
    }

    @OnMessage
    public void onMessage(String message){
        sendMessageToAll(message);
    }

    @OnError
    public void onError(Throwable e){
        log.error("某客户端异常：",e);
    }

    private void sendMessageToAll(String message){
        WebSocketInfoCompent.webSocketMap.values().forEach(s->{
            s.sendMessage(message);
        });
    }

    private void sendMessage(String message){
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("sendMessage():",e);
        }
    }
}
