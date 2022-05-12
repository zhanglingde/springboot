package com.flowable;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
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
 * @date 2022/5/11 10:37 上午
 */
@SpringBootTest
public class ProcessTest {

    private static final Logger log = LoggerFactory.getLogger(ProcessTest.class);

    String staffId = "张灵";

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;

    /**
     * 开启一个流程
     */
    @Test
    public void askForLeave() {
        HashMap<String, Object> map = new HashMap<>();
        // 为 leaveTask 任务节点分配 人
        map.put("leaveTask", staffId);
        // ask_for_leave 流程定义启动一个流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ask_for_leave_test", map);
        runtimeService.setVariable(processInstance.getId(), "name", "ling");
        runtimeService.setVariable(processInstance.getId(), "reason", "休息一下");
        runtimeService.setVariable(processInstance.getId(), "days", 10);
        log.info("创建请假流程 processId：{}", processInstance.getId());
    }

    String zuzhangId = "张三";

    /**
     * 提交给组长审批
     */
    @Test
    void submitToZuzhang() {

        //员工查找到自己的任务，然后提交给组长审批
        List<Task> list = taskService.createTaskQuery().taskAssignee(staffId).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
            Map<String, Object> map = new HashMap<>();
            // 提交给组长的时候，需要指定组长的 id
            map.put("zuzhangTask", zuzhangId);
            taskService.complete(task.getId(), map);
        }
    }

    String managerId = "李四";

    /**
     * 组长审批-批准
     */
    @Test
    void zuZhangApprove() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(zuzhangId).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("组长 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            //组长审批的时候，如果是同意，需要指定经理的 id
            map.put("managerTask", managerId);
            map.put("checkResult", "通过");
            taskService.complete(task.getId(), map);
        }
    }

    /**
     * 组长审批-拒绝
     */
    @Test
    void zuZhangReject() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(zuzhangId).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("组长 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            //组长审批的时候，如果是拒绝，就不需要指定经理的 id
            map.put("checkResult", "驳回");
            taskService.complete(task.getId(), map);
        }
    }

    /**
     * 经理通过
     */
    @Test
    void managerPass() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(managerId).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("经理 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            //组长审批的时候，如果是拒绝，就不需要指定经理的 id
            map.put("checkResult", "通过");
            taskService.complete(task.getId(), map);
        }
    }

    /**
     * 经理驳回
     */
    @Test
    void managerReject() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(managerId).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("经理 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("checkResult", "驳回");
            taskService.complete(task.getId(), map);
        }
    }

}
