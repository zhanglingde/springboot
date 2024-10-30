package com.ling.websocket;

import com.jcraft.jsch.*;
import java.io.*;
import java.util.*;


public class SftpClientUtil {
    // private static Logger logger = Logger.getLogger(SftpClientUtil.class);
    private Session session;
    private Channel channel;
    private ChannelSftp sftp;
    private InputStream in;
    private OutputStream out;

    public ChannelSftp getSftp() {
        return sftp;
    }

    /**
     * 构造函数1
     *
     * @param host       主机
     * @param username   用户名
     * @param password   密码
     * @param port       端口
     * @param isHightSSH :jsch 与 ssh 版本适配，ssh>7.6 为true,反之为false
     * @throws Exception
     */
    public SftpClientUtil(String host, String username, String password, int port, boolean isHightSSH) throws Exception {
        JSch jsch = new JSch();
        this.session = jsch.getSession(username, host, port);
        session.setPassword(password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no"); // 不验证 HostKey
        if (isHightSSH) {
            config.put("kex", "diffie-hellman-group1-sha1,"
                    + "diffie-hellman-group-exchange-sha1,"
                    + "diffie-hellman-group-exchange-sha256"); // 适配新版本ssh需添加对应的加密算法
        }
        session.setConfig(config);
        try {
            session.connect();
        } catch (Exception e) {
            if (session.isConnected())
                session.disconnect();
            // System.out.println("链接报错!!!", e);
            throw new Exception("连接服务器失败,请检查主机[" + host + "],端口[" + port + "],用户名[" + username + "],端口[" + port + "]是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
        }
        channel = session.openChannel("sftp");
        try {
            if (channel.isConnected())
                channel.disconnect();
            channel.connect();
        } catch (Exception e) {
            throw new Exception("连接服务器失败,请检查主机[" + host + "],端口[" + port + "],用户名[" + username + "],端口[" + port + "]是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
        }
        sftp = (ChannelSftp) channel;
    }

    /**
     * 构造函数2
     *
     * @param host     主机
     * @param username 用户名
     * @param password 密码
     * @param port     端口
     * @param encoding 字符集编码
     * @throws Exception
     */
    public SftpClientUtil(String host, String username, String password, int port, String encoding) throws Exception {
        JSch jsch = new JSch();
        this.session = jsch.getSession(username, host, port);
        session.setPassword(password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no"); // 不验证 HostKey
        session.setConfig(config);
        try {
            session.connect();
        } catch (Exception e) {
            if (session.isConnected())
                session.disconnect();
            throw new Exception("连接服务器失败,请检查主机[" + host + "],端口[" + port + "],用户名[" + username + "],端口[" + port + "]是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
        }
        channel = session.openChannel("sftp");
        try {
            channel.connect();
        } catch (Exception e) {
            if (channel.isConnected())
                channel.disconnect();
            throw new Exception("连接服务器失败,请检查主机[" + host + "],端口[" + port + "],用户名[" + username + "],密码[" + password + "]是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
        }
        sftp = (ChannelSftp) channel;
        sftp.setFilenameEncoding(encoding);
    }

    /**
     * 构造函数3
     *
     * @param host     主机
     * @param username 用户名
     * @param password 密码
     * @param port     端口
     * @param encoding 字符集
     * @param timeout  超时时间
     * @throws Exception
     */
    private SftpClientUtil(String host, String username, String password, int port, String encoding, int timeout)
            throws Exception {
        JSch jsch = new JSch();
        this.session = jsch.getSession(username, host, port);
        session.setPassword(password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no"); // 不验证 HostKey
        session.setConfig(config);
        try {
            session.connect();
        } catch (Exception e) {
            if (session.isConnected())
                session.disconnect();
            throw new Exception("连接服务器失败,请检查主机[" + host + "],端口[" + port + "],用户名[" + username + "],密码[" + password + "]是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
        }
        session.setTimeout(timeout);
        channel = session.openChannel("sftp");
        try {
            channel.connect();
        } catch (Exception e) {
            if (channel.isConnected())
                channel.disconnect();
            throw new Exception("连接服务器失败,请检查主机[" + host + "],端口[" + port + "],用户名[" + username + "],密码[" + password + "]是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
        }
        sftp = (ChannelSftp) channel;
        sftp.setFilenameEncoding(encoding);
    }

    /**
     * 文件上传
     *
     * @param remotePath     远端路径
     * @param remoteFileName 上传到远端后的文件名
     * @param localPath      本地路径
     * @param localFileName  本地文件名
     * @return
     */
    public boolean uploadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        FileInputStream in = null;
        try {
            createDir(remotePath);
            File file = new File(localPath + localFileName);
            in = new FileInputStream(file);
            sftp.put(in, remoteFileName);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // 创建目录
    public boolean createDir(String createpath) {
        try {
            createpath = createpath.trim();
            if (isDirExist(createpath)) {
                this.sftp.cd(createpath);
                System.out.println("cd " + createpath);
                return true;
            }
            String[] pathArray = createpath.split("/");
            for (String path : pathArray) {
                path = path.trim();
                if ("".equals(path)) {
                    continue;
                }
                if (!isDirExist(path)) {
                    sftp.mkdir(path);
                }
                System.out.println("cd " + path);
                sftp.cd(path);
            }
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 判断目录是否存在
    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                System.out.println("directory：" + directory + ",no such file ERROR!!！");
            }
        }
        return isDirExistFlag;
    }

    /**
     * 文件下载
     *
     * @param remotePath    远程文件存放路径
     * @param localPath     下载到本地的路径
     * @return 下载后的文件名集合
     * @throws Exception
     */
    public List<String> downloadFiles(String remotePath, String localPath){
        List<String> downloadFiles = new ArrayList<String>();

        try {
            System.out.println("切换到指定目录：" + remotePath);
            boolean flag = openDir(remotePath, sftp);
            if (flag) {
                // 1.获取远端路径下所有文件
                Vector<?> vv = listFiles("*");
                if (vv == null) {
                    return null;
                } else {
                    for (Object object : vv) {
                        ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) object;
                        String remoteFileName = entry.getFilename();

                        System.out.println("校验文件名：" + remoteFileName);
                        String path = localPath.substring(localPath.length() - 1).equals("/") ? localPath : localPath + "/";
                        File file = new File(path + remoteFileName);
                        System.out.println("保存校对文件的本地路径为:" + file.getAbsolutePath());

                        System.out.println("start downLoad " + remoteFileName + " ~~");
                        sftp.get(remoteFileName, new FileOutputStream(file));
                        System.out.println("downLoad ok ~~");

                        downloadFiles.add(remoteFileName);
                    }

                    if (downloadFiles.size() < 1) {
                        System.out.println("remotePath：" + remotePath + "路径下，未匹配到校对文件！");
                    }
                }
            } else {
                System.out.println("对应的目录" + remotePath + "不存在！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                }
            }
            if (session != null) {
                if (session.isConnected()) {
                    session.disconnect();
                }
            }
        }
        return downloadFiles;
    }

    // 打开或者进入指定目录
    public boolean openDir(String directory, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            System.out.println("cd " + directory + " ok");
            return true;
        } catch (SftpException e) {
            System.out.println(e + "");
            return false;
        }
    }

    // 返回目录下所有文件信息
    public Vector listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 断开与主机的连接
     */
    public void disconnect() {
        try {
            sftp.disconnect();
        } catch (Exception ignored) {
        }
        try {
            channel.disconnect();
        } catch (Exception ignored) {
        }
        try {
            session.disconnect();
        } catch (Exception ignored) {
        }
    }

    /**
     * 得到SFTP实例
     *
     * @param host     主机
     * @param username 用户名
     * @param password 密码
     * @param port     端口
     * @return
     * @throws Exception
     */
    public static SftpClientUtil getInstans(String host, String username, String password, int port) throws Exception {
        return new SftpClientUtil(host, username, password, port, false);
    }

    /**
     * 得到SFTP实例
     *
     * @param host     主机
     * @param username 用户名
     * @param password 密码
     * @param port     端口
     * @param encoding 字符集编码
     * @return
     * @throws Exception
     */
    public static SftpClientUtil getInstans(String host, String username, String password, int port, String encoding)
            throws Exception {
        return new SftpClientUtil(host, username, password, port, encoding);
    }

    /**
     * 得到SFTP实例
     *
     * @param host     主机
     * @param username 用户名
     * @param password 密码
     * @param port     端口
     * @param encoding 字符集编码
     * @param timeout  超时时间
     * @return
     * @throws Exception
     */
    public static SftpClientUtil getInstans(String host, String username, String password, int port, String encoding,
                                            int timeout) throws Exception {
        return new SftpClientUtil(host, username, password, port, encoding, timeout);
    }
}
