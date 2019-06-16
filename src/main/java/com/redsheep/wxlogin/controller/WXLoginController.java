package com.redsheep.wxlogin.controller;

import com.redsheep.wxlogin.model.WXSessionModel;
import com.redsheep.wxlogin.util.HttpClientUtil;
import com.redsheep.wxlogin.util.JSONResultUtil;
import com.redsheep.wxlogin.util.JSONUtil;
import com.redsheep.wxlogin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author redsheep
 * @date 2019/6/16
 */
@RestController
public class WXLoginController {
    @Autowired
    private RedisUtil redis;


    @PostMapping("/wxLogin")
    public JSONResultUtil wxLogin(String code) {

        System.out.println("wxlogin - code: " + code);

//		https://api.weixin.qq.com/sns/jscode2session?
//				appid=APPID&
//				secret=SECRET&
//				js_code=JSCODE&
//				grant_type=authorization_code

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<>();
        //TODO 写入app的id和密钥
        param.put("appid", "");
        param.put("secret", "");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");

        String wxResult = HttpClientUtil.doGet(url, param);
        System.out.println(wxResult);

        WXSessionModel model = JSONUtil.jsonToPojo(wxResult, WXSessionModel.class);

        // 存入session到redis
        redis.set("user-redis-session:" + model.getOpenid(),
                model.getSession_key(),
                1000 * 60 * 30);

        return JSONResultUtil.ok();
    }
}
