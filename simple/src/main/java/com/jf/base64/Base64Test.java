package com.jf.base64;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/6/11 14:49
 */
public class Base64Test {

    @Test
    public void testEncoder2Decoder() throws IOException {
        byte[] bytes = "123456789".getBytes(StandardCharsets.UTF_8);

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(bytes);
        System.out.println(new String(encode, StandardCharsets.UTF_8));

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encode);
        System.out.println(new String(decode, StandardCharsets.UTF_8));
    }

    @Test
    public void testUrlEncoder2UrlDecoder() {
        String url = "https://flowus.cn/seinfish?name='mike'&pwd=123456";

        Base64.Encoder urlEncoder = Base64.getUrlEncoder();
        String res = urlEncoder.encodeToString(url.getBytes(StandardCharsets.UTF_8));
        System.out.println(res);



    }
}
