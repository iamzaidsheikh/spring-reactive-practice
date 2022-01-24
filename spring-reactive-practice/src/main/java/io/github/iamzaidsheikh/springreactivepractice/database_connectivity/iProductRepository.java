package io.github.iamzaidsheikh.springreactivepractice.database_connectivity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface iProductRepository extends ReactiveCrudRepository<Product, String> {
    
}
