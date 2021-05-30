package top.bootzhong.qqbot.until;

import java.util.List;

public class ListUntil {

    public static String join(List<Long> list){
        if (list == null || list.size() <= 0){
            return null;
        }

        String result = "";
        for (Long item:list){
            result += item+",";
        }
        //去掉最后一个多余的,
        result = result.substring(0, result.length() - 1);
        return result;
    }
}
