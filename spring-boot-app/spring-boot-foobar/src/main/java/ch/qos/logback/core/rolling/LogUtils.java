package ch.qos.logback.core.rolling;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.LoggerFactory;

/**
 * @author hangs.zhang
 * @date 2020/7/21 上午11:08
 * *********************
 * function:
 */
public final class LogUtils {

    private LogUtils() {
    }

    public static Logger createLogger(String logName, String fileName) {
        System.out.println("/home/app/logs");

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();

        patternLayoutEncoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS}###%m%n");
        patternLayoutEncoder.setContext(loggerContext);
        patternLayoutEncoder.start();

        LevelFilter levelFilter = new LevelFilter();
        levelFilter.setLevel(Level.INFO);
        levelFilter.start();

        // 这里使用CustomRollingFileAppender
        CustomRollingFileAppender<ILoggingEvent> fileAppender = new CustomRollingFileAppender<>();
        fileAppender.setName(logName);
        fileAppender.setFile("/home/app/logs" + fileName + ".log");
        fileAppender.setContext(loggerContext);

        TimeBasedRollingPolicy<Object> timeBasedRollingPolicy = new TimeBasedRollingPolicy<>();
        timeBasedRollingPolicy.setParent(fileAppender);
        timeBasedRollingPolicy.setContext(loggerContext);
        timeBasedRollingPolicy.setFileNamePattern("/home/app/logs" + fileName + ".%d{yyyy-MM-dd}.log");
        timeBasedRollingPolicy.setMaxHistory(7);
        timeBasedRollingPolicy.start();

        fileAppender.setAppend(true);
        fileAppender.setRollingPolicy(timeBasedRollingPolicy);
        fileAppender.setEncoder(patternLayoutEncoder);
        fileAppender.addFilter(levelFilter);
        fileAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger(logName);
        logger.addAppender(fileAppender);
        logger.setLevel(Level.INFO);
        logger.setAdditive(false);

        return logger;
    }

}
