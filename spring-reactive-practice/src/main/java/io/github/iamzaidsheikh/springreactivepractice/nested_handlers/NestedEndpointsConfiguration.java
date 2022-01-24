package io.github.iamzaidsheikh.springreactivepractice.nested_handlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class NestedEndpointsConfiguration {

  private final NestedHandler nh;

  @Bean
  RouterFunction<ServerResponse> nestedRouter() {
    var jsonRP = RequestPredicates.accept(MediaType.APPLICATION_JSON);

    return RouterFunctions.route()
        .path("/nested", builder -> builder
            .nest(jsonRP, nestedBuilder -> nestedBuilder
                .GET("/{pv}", nh::pathVariable)
                .GET("", nh::noPathVariable)))
        .build();
  }
}
