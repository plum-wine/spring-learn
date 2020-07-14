package com.github.web;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author hangs.zhang
 * @date 2019/3/21
 * *****************
 * function:
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    public Mono<String> test() throws InterruptedException {
        // Mono代表0或1个元素
        // Flux代表0或N个元素
        log.info("Start");
        Mono<String> result = Mono.fromSupplier(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "test";
        });
        log.info("End");
        // return Mono.just("test");
        return result;
    }

    @GetMapping(value = "/test2", produces = "text/event-stream")
    public Flux<String> test2() {
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Flux Data -- " + i;
        }));
        return result;
    }

    @GetMapping(value = "/test3")
    public String test3() throws InterruptedException {
        log.info("Start");
        TimeUnit.SECONDS.sleep(5);
        log.info("End");
        return "test3";
    }

    public static void main(String[] args) {
        String[] strs = {"1", "12", "123"};

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {

            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                this.subscription = s;
                subscription.request(1);
            }

            @Override
            public void onNext(Integer data) {
                System.out.println("accept : " + data);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("End...");
                subscription.cancel();
            }
        };

        Flux.fromArray(strs).map(Integer::parseInt).subscribe(subscriber);
    }

}
