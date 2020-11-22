package com.github.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Service;

/**
 * @author hangs.zhang
 * @date 2019/3/4.
 * *****************
 * function:
 */
@Service
public class IndicatorService {

    private Logger LOG = LoggerFactory.getLogger(IndicatorService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public IndicatorService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = {"topic1", "topic2"})
    public void processMessage(ConsumerRecord<Integer, String> record) {
        LOG.info("processMessage, topic = {}, msg = {}", record.topic(), record.value());
    }

    public void sendMessage(String topic, String data) {
        LOG.info("向kafka推送数据:[{}]", data);
        try {
            kafkaTemplate.send(topic, data);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("发送数据出错！！！topic:{}\tdata:{}", topic, data);
            LOG.error("发送数据出错=====>", e);
        }

        // 消息发送的监听器，用于回调返回信息
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
                System.out.println("发送成功");
            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {
                System.out.println("发送失败");
            }
        });
    }
}
