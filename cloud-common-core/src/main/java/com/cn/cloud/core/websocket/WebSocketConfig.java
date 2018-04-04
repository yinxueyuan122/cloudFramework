package com.cn.cloud.core.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 
 * @ClassName   : WebSocketConfig.java
 * @Description : 
 * @author Yin Xueyuan
 * @since 2017年3月27日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年3月27日        Yin Xueyuan           fisrt create
 * </pre>
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	
	
	/**
	 * topic,user表示支持主题和点对点
	 */
    @Override  
    public void configureMessageBroker(MessageBrokerRegistry config) {  
        config.enableSimpleBroker("/topic","/user");  
        config.setApplicationDestinationPrefixes("/app");  
        config.setUserDestinationPrefix("/user/");  
    } 
    
    /**
     * 客户端用URL
     */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/webServer").setAllowedOrigins("*").withSockJS(); 
	}

}
