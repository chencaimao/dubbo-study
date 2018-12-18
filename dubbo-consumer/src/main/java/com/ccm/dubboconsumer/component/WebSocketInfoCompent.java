package com.ccm.dubboconsumer.component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by chencm on 2018/12/7
 */

public class WebSocketInfoCompent {
    public static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    public static Set<WebSocketServer> webSocketServerSet = new CopyOnWriteArraySet<>();
}
