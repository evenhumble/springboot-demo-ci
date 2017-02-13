package io.hewig.cidemo.controller;

import io.hewig.cidemo.entity.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HelloWorldController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/hello")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/hellorest")
    public @ResponseBody  Greeting greetingRest(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/helloentity")
    public ResponseEntity<Greeting> greetingResposeEntity(@RequestParam(value="name", defaultValue="World") String name) {
        return ResponseEntity.ok(new Greeting(counter.incrementAndGet(),
                String.format(template, name)));
    }
}