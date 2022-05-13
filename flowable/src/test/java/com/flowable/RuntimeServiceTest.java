package com.flowable;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangling
 * @date 2022/5/13 3:39 下午
 */
@SpringBootTest
public class RuntimeServiceTest {
    private static final Logger log = LoggerFactory.getLogger(RuntimeServiceTest.class);

    @Autowired
    RuntimeService runtimeService;

    // 流程定义的 key，对应具体的哪一个流程图
    String processDefinitionKey = "leave_approval";
    // 业务代码
    String businessKey = "ask_leave";

    /**
     * 启动流程，创建实例
     */
    @Test
    void startProcessInstance() {
        // 流程变量，可以自定义扩充   fe92a70c-d290-11ec-86a6-9ebe93dc58da
        Map<String, Object> variablesDefinition = new HashMap<>();
        // leaveTask 是流程中预先设置的 委托人
        // variablesDefinition.put("leaveTask", "张灵");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variablesDefinition);
        log.info("流程启动成功：{}",processInstance.getId());
    }

    /**
     * 查询指定流程所有启动的实例列表
     */
    @Test
    void processInstanceList(){
        List<Execution> list = runtimeService.createExecutionQuery().processDefinitionKey(processDefinitionKey).list();
        List<Execution> pages = runtimeService.createExecutionQuery().processDefinitionKey(processDefinitionKey).listPage(1, 10);
        System.out.println("list = " + list);
    }


    /**
     * 删除流程实例
     */
    @Test
    void deleteProcessInstance(){
        runtimeService.deleteProcessInstance("533050dc-d1d4-11ec-bc3d-22ab6a6744a8","删除流程实例");
    }

}
