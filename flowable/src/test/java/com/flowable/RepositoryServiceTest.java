package com.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @author zhangling
 * @date 2022/5/13 3:20 下午
 */
@SpringBootTest
public class RepositoryServiceTest {

    private static final Logger log = LoggerFactory.getLogger(RepositoryServiceTest.class);

    @Autowired
    RepositoryService repositoryService;

    /**
     * 部署 xml（压缩到zip形式，直接xml需要配置相对路径）
     */
    @Test
    void deploymentTest() {
        File file = new File("/Users/ling/test.bpmn20.zip");

        // 89aba863-d28e-11ec-a099-56b80cda99c0
        try {
            ZipInputStream testZip = new ZipInputStream(new FileInputStream(file));
            Deployment deploy = repositoryService
                    .createDeployment()
                    .addZipInputStream(testZip)
                    .deploy();
            log.info("部署成功：{}", deploy.getId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * 查询部署的流程定义
     */
    @Test
    void processDefinition(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey("leave_approval").list();
        List<ProcessDefinition> pages = repositoryService.createProcessDefinitionQuery().processDefinitionKey("ask_for_leave_test").listPage(1,10);
        System.out.println("list = " + list);
    }

    /**
     * 删除流程定义
     */
    @Test
    void deleteDefinition(){
        repositoryService.deleteDeployment("89aba863-d28e-11ec-a099-56b80cda99c0");
    }

}
