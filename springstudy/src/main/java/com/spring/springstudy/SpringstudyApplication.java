package com.spring.springstudy;
import com.spring.springstudy.config.ProjectProperties;
import com.spring.springstudy.redis.DefaultProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import java.net.InetAddress;
import java.net.UnknownHostException;


@ComponentScan
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
@EnableConfigurationProperties({ProjectProperties.class})
public class SpringstudyApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringstudyApplication.class);


    /**
     * Main函数---SpringBoot入口
     *
     * @param args 参数
     * @throws UnknownHostException Localhost不能被解析成为地址
     */
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(SpringstudyApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        ConfigurableApplicationContext run = app.run(args);
        Environment env = run.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 正在运行! 链接地址为:\n\t" +
                        "swagger-ui:\thttp://localhost:{}/swagger-ui.html\n\t" +
                        "本地地址: \thttp://localhost:{}\n\t" +
                        "网络地址: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

}
