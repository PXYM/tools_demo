package com.jf.controller;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/6/12 21:23
 */
import cn.hutool.core.util.StrUtil;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthWeChatOpenRequest;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


@RestController
@RequestMapping("/oauth")
public class RestAuthController {

    @Value("${wxauth.appid}")
    public String appId;

    @Value("${wxauth.appsecret}")
    public String appSecret;

    @Value("${wxauth.token}")
    public String TOKEN;

    @Value("${wxauth.url}")
    public String url;

    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback")
    public Object login(AuthCallback callback) {
        AuthRequest authRequest = getAuthRequest();
        return authRequest.login(callback);
    }

    @GetMapping("/signature")
    public String signature(String signature,String timestamp, String nonce, String echostr){
        System.out.println("--------------");
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[] { TOKEN, timestamp, nonce };
        Arrays.sort(arr);

        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (String s : arr) {
            content.append(s);
        }
        String tmpStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = new String(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 3. 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (StrUtil.equals(tmpStr, signature)){
            return echostr;
        }
        return "error";
    }

    private AuthRequest getAuthRequest() {
        return new AuthWeChatOpenRequest(AuthConfig.builder()
                .clientId(appId)
                .clientSecret(appSecret)
                .redirectUri(url)
                .build());
    }
}

