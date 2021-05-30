package top.bootzhong.qqbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import top.bootzhong.qqbot.common.ServerResponse;
import top.bootzhong.qqbot.entity.Task;
import top.bootzhong.qqbot.service.TaskService;
import java.util.List;

@RestController
@RequestMapping("/qqbot")
public class TaskPushController {
    @Autowired
    TaskService taskService;


    @PostMapping("/push")
    public String demo(@RequestBody Task task) {
        if (CollectionUtils.isEmpty(task.getRecList())){
            return "recList is not allow to be null";
        }
        taskService.push(task);
        return "ok";
    }

    @GetMapping("/friend/list")
    public ServerResponse getFriendList() {
        List<Long> qqList = taskService.getFriendList();
        return ServerResponse.createBySuccess(qqList);
    }

    /**
     * 检查好友是否存在
     */
    @PostMapping("/friend/{qq}")
    public ServerResponse checkIsExist(@PathVariable Long qq){
        Boolean isExist = taskService.checkIsExist(qq);
        if (isExist){
            return ServerResponse.createBySuccess();
        }

        return ServerResponse.createByError();
    }
}
