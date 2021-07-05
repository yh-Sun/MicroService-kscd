package com.sun.mongo;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AESTest {

    @Test
    public void 加密() {
        String data = "testAES001";
        String secretKey = "5b6a4df4f8c54a3p";
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, secretKey.getBytes(StandardCharsets.UTF_8));
        String s = aes.encryptHex(data);
        Console.log(s);
    }

    @Test
    public void 解密() {
        String data = ResourceUtil.readUtf8Str("jm.txt");;
        String secretKey = "a68f4qd0ae3e6k6d";
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, secretKey.getBytes(StandardCharsets.UTF_8));
        String s = aes.decryptStr(data, CharsetUtil.CHARSET_UTF_8);
        Console.log(s);
    }

}
