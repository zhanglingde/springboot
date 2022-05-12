package com.flowable.controller;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * 请假失败
 * @author zhangling
 * @date 2022/5/11 2:10 下午
 */
@Component
public class AskForLeaveFail implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("请假失败...");
    }
}
