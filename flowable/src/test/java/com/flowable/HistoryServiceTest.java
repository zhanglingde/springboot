package com.flowable;

import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.variable.api.history.HistoricVariableInstanceQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zhangling
 * @date 2022/5/13 4:05 下午
 */
@SpringBootTest
public class HistoryServiceTest {

    @Autowired
    HistoryService historyService;


    String processDefinitionKey = "ask_for_leave_test";
    @Test
    void historyProcessList(){
        // 历史流程实例
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(processDefinitionKey).list();
        System.out.println("list = " + list);

        // 历史任务
        List<HistoricTaskInstance> taskInstanceList = historyService.createHistoricTaskInstanceQuery().processDefinitionKey(processDefinitionKey).list();
        System.out.println("taskInstanceList = " + taskInstanceList);

        // 实例历史变量
        HistoricVariableInstanceQuery variableInstanceQuery = historyService.createHistoricVariableInstanceQuery().processInstanceId("");
        // 任务历史变量
        HistoricVariableInstanceQuery taskId = historyService.createHistoricVariableInstanceQuery().taskId("taskId");

    }
}
