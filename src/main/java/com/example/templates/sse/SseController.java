package com.example.templates.sse;

import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SseController {

    @GetMapping(path = "/sse/test")
    public String test() {
        return "OK";
    }

    @RequestMapping(path = "/sse", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter createConnection(@RequestHeader(name = "Last-Event-ID", required = false, defaultValue = "0") int lastId) {
        SseEmitter emitter = new SseEmitter(0L);

        Thread worker = new Thread(() -> serverPush(emitter, lastId));
        worker.start();
        
        return emitter;
    }

    private void serverPush(SseEmitter emitter, int start)  {
        while (true) {
            try {
                emitter.send(
                    SseEmitter.event().name("new name")
                    .comment("this is comment")
                    .data(start++)
                    .id("neo")
                    );
                emitter.send(LocalDateTime.now());

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                emitter.completeWithError(e);
                break;
            }
        }
    }
}
