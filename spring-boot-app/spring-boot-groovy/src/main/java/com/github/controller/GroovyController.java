package com.github.controller;

import com.github.service.GroovyBeanCommand;
import com.github.utils.GroovyContextUtils;
import groovy.lang.GroovyClassLoader;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@RestController
@RequestMapping("/groovy")
public class GroovyController {

    @GetMapping("/runScript")
    public Object runScript(@RequestParam String script) throws Exception {
        if (script != null) {
            // 这里其实就是groovy的api动态的加载生成一个Class, 然后反射生成对象, 然后执行run方法, 最后返回结果
            Class<?> clazz = new GroovyClassLoader().parseClass(script);
            Method run = clazz.getMethod("run");
            Object o = clazz.newInstance();
            return run.invoke(o);
        } else {
            return "no script";
        }
    }

    @PostMapping("/command/add")
    public void addGroovyCommand(@RequestParam String groovyBeanName, @RequestParam String script) {
        GroovyContextUtils.autowireBean(groovyBeanName, script);
    }

    @GetMapping("/command/run")
    public Object runGroovyCommand(@RequestParam String groovyBeanName) {
        GroovyBeanCommand command = GroovyContextUtils.getBean(groovyBeanName, GroovyBeanCommand.class);
        return command.run();
    }

}
