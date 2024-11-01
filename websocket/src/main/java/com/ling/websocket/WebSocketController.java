package com.ling.websocket;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/open/socket")
public class WebSocketController {

    @Value("${mySocket.myPwd}")
    public String myPwd;

    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 手机客户端请求接口
     * @param id    发生异常的设备ID
     * @param pwd   密码（实际开发记得加密）
     * @param status true 正常，false 异常
     * @throws IOException
     */
    @PostMapping(value = "/onReceive")
    public void onReceive(String id,String pwd,boolean status) throws IOException {
        if(pwd.equals(myPwd)){  // 密码校验一致（这里举例，实际开发还要有个密码加密的校验的），则进行群发
            JSONObject jsonObject = JSONObject.of("id", id, "status", status);
            webSocketServer.broadCastInfo(jsonObject.toJSONString());
        }
    }

}