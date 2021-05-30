package top.bootzhong.qqbot.dao;

import org.springframework.stereotype.Repository;
import top.bootzhong.qqbot.entity.Task;

@Repository
public interface TaskMapper {
    void insert(Task task);
}
