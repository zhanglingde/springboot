package com.ling.websocket;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpProgressMonitor;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class SFTPTest {



    public static void main(String[] args) throws Exception {
        SftpClientUtil client = new SftpClientUtil("192.168.65.130", "sftpuser", "123456", 22, "UTF-8");
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
            System.out.println(content.toString());
            reader.close();
            if ("销项.sql".equals(entry.getFilename())) {
                client.getSftp().rm(path);
            }
        }
        client.disconnect();
    }
}
