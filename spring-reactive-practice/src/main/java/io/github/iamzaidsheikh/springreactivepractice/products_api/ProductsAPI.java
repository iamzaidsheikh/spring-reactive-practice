package io.github.iamzaidsheikh.springreactivepractice.products_api;

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
public class ProductsAPI {

  private final ProductHandler ph;

  @Bean
  RouterFunction<ServerResponse> productsRouter() {

    return RouterFunctions.route()
        .path("/products", builder -> builder
            .nest(RequestPredicates.accept(MediaType.APPLICATION_JSON), nestedBuilder -> nestedBuilder
                .GET("/{id}", ph::handleFindById)
                .GET(ph::handleFindAll))
            .POST("", ph::handleCreateProduct))
        .build();

  }
}
