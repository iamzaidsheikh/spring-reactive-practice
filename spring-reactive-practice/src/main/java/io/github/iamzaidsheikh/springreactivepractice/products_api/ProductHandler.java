package io.github.iamzaidsheikh.springreactivepractice.products_api;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.github.iamzaidsheikh.springreactivepractice.database_connectivity.Product;
import io.github.iamzaidsheikh.springreactivepractice.database_connectivity.iProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Log4j2
public class ProductHandler {

    private final iProductRepository pr;

    Mono<ServerResponse> handleFindAll(ServerRequest r) {
        log.info("Getting all products");
        var all = pr.findAll();
        
        return ServerResponse.ok().body(all, Product.class);
    }

    Mono<ServerResponse> handleFindById(ServerRequest r) {
        var id = r.pathVariable("id");
        log.info("Getting product: {}", id);
        var byId = pr.findById(id);

        return ServerResponse.ok().body(byId, Product.class);
    }

    Mono<ServerResponse> handleCreateProduct(ServerRequest r) {
        log.info("Creating new product");
        
        return r.bodyToMono(Product.class)
                .flatMap(pr::save)
                .flatMap(saved -> ServerResponse.created(URI.create("/products/" + saved.getId()))
                        .build());

    }
}
