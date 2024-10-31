package com.ling.websocket;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.jcraft.jsch.ChannelSftp;


import java.io.*;
import java.util.Vector;

public class SFTPTest {


    public static void main(String[] args) throws Exception {
        SftpClientUtil client = new SftpClientUtil("192.168.65.130", "sftpuser", "123456", 22, "UTF-8");
        while (true) {
            Thread.sleep(10000);
            String dir = "/upload";
            Vector vector = client.listFiles(dir);
            for (Object o : vector) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) o;
                if (".".equals(entry.getFilename()) || "..".equals(entry.getFilename())) {
                    continue;
                }
                String path = dir + "/" + entry.getFilename();
                System.out.println(path);
                // 下载文件并读取内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getSftp().get(path)));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                client.getSftp().rm(path);
                System.out.println(content);
            }
        }

    }


}
