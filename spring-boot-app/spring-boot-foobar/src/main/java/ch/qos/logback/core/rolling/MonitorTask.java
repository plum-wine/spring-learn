package ch.qos.logback.core.rolling;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2020/7/21 下午4:56
 * *********************
 * function:
 */
@Component
public class MonitorTask implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void monitors() {
        Map<String, SystemMonitor> beansOfType = applicationContext.getBeansOfType(SystemMonitor.class);
        beansOfType.values().forEach(SystemMonitor::monitor);
    }
}
