package com.ling.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/fileupload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传...");
        // 获取上传文件位置
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        // 判断上传文件夹是否存在
        File file = new File(realPath);
        if(!file.exists()){
            // 文件夹不存在，创建文件夹
            file.mkdirs();
        }
        // 解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解析request
        List<FileItem> items = upload.parseRequest(request);
        // 遍历
        for (FileItem item : items) {
            // 判断当前item对象是否是上传文件项
            if(item.isFormField()){
                // 说明是普通表单
            }else {
                // 说明是上传文件
                String fileName = item.getName();
                // 把文件的名称设置唯一值，uuid
                String uuid = UUID.randomUUID().toString().replace("-","" );
                fileName = uuid+"_"+fileName;
                // 完成文件上传
                item.write(new File(realPath,fileName));
                // 删除临时文件
                item.delete();
            }
        }
        return "success";
    }

    @RequestMapping("/fileupload2")
    public String fileUpload2(HttpServletRequest request,MultipartFile upload) throws IOException {
        System.out.println("SpringMVC文件上传...");
        // 获取上传文件位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        // 判断上传文件夹是否存在
        File file = new File(path);
        if(!file.exists()){
            // 文件夹不存在，创建文件夹
            file.mkdirs();
        }

        // 说明是上传文件
        String fileName = upload.getOriginalFilename();

        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-","" );
        fileName = uuid+"_"+fileName;
        // 完成文件上传
        upload.transferTo(new File(path,fileName));

        return "success";
    }

    @RequestMapping("/fileupload3")
    public String fileUpload3(MultipartFile upload) throws IOException {
        System.out.println("跨服务器文件上传...");

        // 定义上传文件服务器路径
        String path = "http://localhost:9090/uploads/";
        // 获取上传文件名
        String fileName = upload.getOriginalFilename();

        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-","" );
        fileName = uuid+"_"+fileName;

        // 创建客户端对象
        Client client = Client.create();

        // 和图片服务器进行连接
        WebResource webResource = client.resource(path + fileName);

        // 上传文件
        webResource.put(upload.getBytes());

        return "success";
    }
}
