package com.github.controller.controller;

import com.github.controller.service.websocket.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author hangs.zhang
 * @date 2018/12/11
 * *****************
 * function:
 */
@Controller
public class WebSocketController {

    /**
     * 页面请求
     */
    @GetMapping("/socket/{cid}")
    public String socket(@PathVariable String cid, Model model) {
        model.addAttribute("cid", cid);
        return "socket";
    }

    /**
     * 推送数据接口
     */
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public String pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
            return cid + " # " + e.getMessage();
        }
        return "success # " + cid;
    }

}

