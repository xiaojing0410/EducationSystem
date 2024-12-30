package com.example.demo.commom;
import com.example.demo.entity.Student;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:龙宝
 * Date:2023-10-24
 * Time:22:44
 */
public class TokenUtil {
    //存储token
    public final static HashMap<String, Integer> map = new HashMap<>();
    public final static HashMap<Integer, String> map_copy = new HashMap<>();
    //生成唯一标识--token
    public static String getToken(Integer id) {
        if(map.containsValue(id)){
            return map_copy.get(id);
        }
        String token = UUID.randomUUID().toString();
        map.put(token,id);
        map_copy.put(id,token);
        return token;
    }

    // 通过token获取id
    public static Integer getIdByToken(String token){
        return map.get(token);
    }
    // 删除token信息
    public static void deleteToken(String token){
        map_copy.remove(map.get(token));
        map.remove(token);
    }
}
