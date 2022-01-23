package io.github.iamzaidsheikh.springreactivepractice.functional_reactive_handlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Configuration
@Log4j2
public class GreetingsHandler {

    @Bean
    RouterFunction<ServerResponse> handler() {

        return RouterFunctions.route()
                .GET("/hello/{name}", this::greet)
                .build();
    }

    Mono<ServerResponse> greet(ServerRequest request) {
        var name = request.pathVariable("name");
        log.info("Saying Hello! to {}", name);
        return ServerResponse.ok().bodyValue("Hello! " + name);
    }
}
