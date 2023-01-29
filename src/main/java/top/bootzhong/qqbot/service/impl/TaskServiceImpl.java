package top.bootzhong.qqbot.service.impl;

import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotContainer;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.bootzhong.qqbot.dao.TaskMapper;
import top.bootzhong.qqbot.entity.Task;
import top.bootzhong.qqbot.service.TaskService;
import top.bootzhong.qqbot.until.ListUntil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    BotContainer botContainer;
    private static final Long BOT = 3381391605L;

    /**
     * 通过事务管理保证一致性
     * @param task
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void push(Task task) {
        //保证幂等性  每次执行逻辑前先对该记录进行数据库插入 若是数据库已经存在 则退出
        List<Long> recList = task.getRecList();

        String rec = ListUntil.join(recList);
        task.setRec(rec);

        if (task.getId() == null){
            task.setId(System.currentTimeMillis());
        }

        //插入数据库
        try{
            taskMapper.insert(task);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return;
        }

        Bot bot = botContainer.getBots().get(BOT);

        Msg msg = Msg.builder().text(task.getTxt());
        if (StringUtils.hasText(task.getImg())) {
            msg.image(task.getImg());
        }

        recList.forEach(e -> {
            if (e != null) {
                bot.sendPrivateMsg(e, msg, false);
            }
        });
    }

    @Override
    public List<Long> getFriendList() {
        Bot bot = botContainer.getBots().get(BOT);
        List<OnebotApi.GetFriendListResp.Friend> friendList = bot.getFriendList().getFriendList();
        List<Long> qqList = friendList.stream()
                .map(OnebotApi.GetFriendListResp.Friend::getUserId)
                .collect(Collectors.toList());
        return qqList;
    }

    @Override
    public Boolean checkIsExist(Long qq) {
        Bot bot = botContainer.getBots().get(BOT);
        List<OnebotApi.GetFriendListResp.Friend> friendList = bot.getFriendList().getFriendList();
        Optional<OnebotApi.GetFriendListResp.Friend> any = friendList
                .stream().filter(e -> e.getUserId() == qq)
                .findAny();

        if (any.isPresent()){
            return true;
        } else {
            return false;
        }
    }
}
