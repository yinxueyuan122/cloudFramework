package com.cn.cloud.core.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.could.core.websocket.model.Greeting;
import com.cn.could.core.websocket.model.UserMessage;

/**
 * 
 * @ClassName   : WebSocketController.java
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
@Controller
public class WebSocketController {
	
	
	private SimpMessagingTemplate template;
	 
    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        System.out.println("init");
        this.template = template;
    }
	  
    @MessageMapping("/hello")  
    @SendTo("/topic/greetings")  
    @ResponseBody
    public Greeting greeting(@RequestBody Greeting message) throws Exception {  
        return message;  
    }  
  
    @MessageMapping("/message")  
    @SendToUser("/message")  
    @ResponseBody
    public UserMessage userMessage(UserMessage userMessage) throws Exception {  
        return userMessage;  
    }  
    
    @RequestMapping("/webServer/test1")  
    public void userMessage() throws Exception { 
    	Greeting greeting = new Greeting();
    	greeting.setContent("yin.xueyuan");
    	template.convertAndSend("/topic/greetings", greeting);
    }  
    
    @RequestMapping("/websocket_test.do")  
    public String websocket_test() throws Exception {  
    	return "/websocket_test";  
    }  

}
