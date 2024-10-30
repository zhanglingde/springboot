package com.ling.websocket;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpProgressMonitor;

public class SFTPGetTest {

    public SFTPChannel getSFTPChannel() {
        return new SFTPChannel();
    }

    public static void main(String[] args) throws Exception {
        SFTPGetTest test = new SFTPGetTest();

        Map<String, String> sftpDetails = new HashMap<String, String>();
        // 设置主机ip，端口，用户名，密码
        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "192.168.65.130");
        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "sftpuser");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "123456");
        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
        
        SFTPChannel channel = test.getSFTPChannel();
        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
        
        String filename = "/upload/test.txt";
        SftpATTRS attr = chSftp.stat(filename);
        long fileSize = attr.getSize();
        
        String dst = "D:\\test.txt";
        OutputStream out = new FileOutputStream(dst);
        try {
            
            chSftp.get(filename, dst, new FileProgressMonitor(fileSize)); // 代码段1
            
            // chSftp.get(filename, out, new FileProgressMonitor(fileSize)); // 代码段2
            

            InputStream is = chSftp.get(filename, new SftpProgressMonitor() {
                @Override
                public void init(int i, String s, String s1, long l) {
                    System.out.println("init...");
                }

                @Override
                public boolean count(long l) {
                    System.out.println("count...");
                    return false;
                }

                @Override
                public void end() {
                    System.out.println("ent...");
                }
            });
            byte[] buff = new byte[1024 * 2];
            int read;
            if (is != null) {
                System.out.println("Start to read input stream");
                do {
                    read = is.read(buff, 0, buff.length);
                    if (read > 0) {
                        out.write(buff, 0, read);
                    }
                    out.flush();
                } while (read >= 0);
                System.out.println("input stream read done.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            chSftp.quit();
            channel.closeChannel();
        }
    }
}
