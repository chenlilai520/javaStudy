package com.spring.springstudy.redis;

import com.spring.springstudy.config.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * 加载默认SpringProfile的工具类
 * 如果环境设置或者启动命令中不存在：<code>spring.profiles.active</code>
 * 或者 <code>application.yml</code> 文件中不存在该值时，默认环境将设置为： <code>dev</code>
 */
public final class DefaultProfileUtil {

    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    private DefaultProfileUtil(){
    }

    /**
     * 如果有显式设置Profile则使用默认值
     *
     * @param app the spring application
     */
    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties =  new HashMap<>();
        /*
            默认为开发环境：dev
         */
        defProperties.put(SPRING_PROFILE_DEFAULT, Constants.SPRING_PROFILE_DEVELOPMENT);
        app.setDefaultProperties(defProperties);
    }

    /**
     * 获取当前激活的环境Profile
     */
    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }
}
