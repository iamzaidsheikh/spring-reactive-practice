package io.github.iamzaidsheikh.springreactivepractice.nested_handlers;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class NestedHandler {
    
    private Map<String, String> greet(Optional<String> name) {
        var finalName = name.orElse("World!");

        return Map.of("message", String.format("Hello %s", finalName));
    }
    
    Mono<ServerResponse> pathVariable(ServerRequest r) {
        return ServerResponse.ok().bodyValue(greet(Optional.of(r.pathVariable("pv"))));
    }

    Mono<ServerResponse> noPathVariable(ServerRequest r) {
        return ServerResponse.ok().bodyValue(greet(Optional.ofNullable(null)));
    }
}
