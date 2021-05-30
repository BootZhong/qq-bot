package top.bootzhong.qqbot.service;

import top.bootzhong.qqbot.entity.Task;

import java.util.List;

/**
 * 任务service层
 */
public interface TaskService {
    void push(Task task);

    List<Long> getFriendList();

    Boolean checkIsExist(Long qq);
}
