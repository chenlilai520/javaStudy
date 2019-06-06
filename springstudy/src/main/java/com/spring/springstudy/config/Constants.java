package com.spring.springstudy.config;

/**
 * Application constants.
 *
 * @author zhongxianyin
 */
public final class Constants {

    /**
     * 账号正则表达式
     */
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
    /**
     * Spring profile：开发以及生产环境
     */
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    /**
     * Spring profile 使用 Spring Cloud (used when deploying to CloudFoundry)
     */
    public static final String SPRING_PROFILE_CLOUD = "cloud";
    /**
     * Spring profile 在 Heroku 部署
     */
    public static final String SPRING_PROFILE_HEROKU = "heroku";
    /**
     * Spring profile 开启 swagger
     */
    public static final String SPRING_PROFILE_SWAGGER = "swagger";
    /**
     * Spring profile 禁用 liquibase
     */
    public static final String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";

    public static final String SYSTEM_ACCOUNT = "system";

    private Constants() {
    }
}
