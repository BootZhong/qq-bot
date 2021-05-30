package top.bootzhong.qqbot.plugin;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * 好友添加请求
 */
@Slf4j
@Component
public class FriendAddRequestPlugin extends BotPlugin {

    @Override
    public int onFriendRequest(@NotNull Bot bot, @NotNull OnebotEvent.FriendRequestEvent event) {
        log.info("收到好友申请");
        bot.setFriendAddRequest(event.getFlag(), true, "通过");
        return MESSAGE_IGNORE;
    }
}
