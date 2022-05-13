package com.flowable;

import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
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
 * @date 2022/5/13 3:56 下午
 */
@SpringBootTest
public class TaskServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceTest.class);

    @Autowired
    TaskService taskService;

    String taskId = "fcb7e37c-d2a1-11ec-b4cb-92bb877396c0";

    /**
     * 列出所有待执行的任务
     */
    @Test
    void allTasks() {
        List<Task> list = taskService.createTaskQuery().orderByTaskId().desc().list();
        for (Task task : list) {
            logger.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
        }
    }


    /**
     * 查看自己的任务
     */
    @Test
    void assigneeTaskList() {
        String assignee = "张灵";
        List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskId().desc().list();
        for (Task task : list) {
            Map<String, Object> info = taskService.getVariables(task.getId());
            logger.info("请假人：{};请假时间：{} 天；请假理由：{}", info.get("name"), info.get("days"), info.get("reason"));
            logger.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
        }
    }


    /**
     * 申领任务
     */
    @Test
    void claimTask() {
        // 申领任务
        taskService.claim(taskId, "老师");
    }

    // 完成任务
    @Test
    void completeTask() {
        Map<String, Object> map = new HashMap<>();
        //组长审批的时候，如果是同意，需要指定经理的 id
        map.put("command", "reject");
        taskService.complete(taskId,map);
    }
}
