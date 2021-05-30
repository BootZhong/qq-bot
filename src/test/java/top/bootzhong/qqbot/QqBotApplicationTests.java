package top.bootzhong.qqbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.bootzhong.qqbot.dao.TaskMapper;
import top.bootzhong.qqbot.entity.Task;
import top.bootzhong.qqbot.service.TaskService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class QqBotApplicationTests {
    @Autowired
    TaskService taskService;
    @Autowired
    TaskMapper taskMapper;

    @Test
    void contextLoads() {
        Task task = new Task();
        task.setId(123L);
        task.setImg("sd");
        task.setTxt("111");
        List<Long> list = new ArrayList<>();
        list.add(12331L);
        taskMapper.insert(task);
    }

}
