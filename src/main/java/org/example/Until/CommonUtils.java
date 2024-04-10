package org.example.Until;

import java.io.IOException;
import java.io.InputStream;

public class CommonUtils {


    // 读取资源文件
    public static InputStream readResourceFile(String fileName) throws IOException {
        // 使用类加载器加载资源文件
        ClassLoader classLoader = CommonUtils.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream(fileName);
        if (in == null) {
            throw new IOException("Resource file not found: " + fileName);
        }
        return in;
    }
}
