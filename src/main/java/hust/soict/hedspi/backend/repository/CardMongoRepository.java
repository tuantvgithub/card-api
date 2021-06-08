package hust.soict.hedspi.backend.repository;

import hust.soict.hedspi.backend.model.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardMongoRepository extends MongoRepository<Card, Long> {
    List<Card> findByProvider(String provider);
    List<Card> findByCategory(String category);
    List<Card> findByCategoryAndProvider(String category, String provider);

    Page<Card> findByProvider(String provider, Pageable pageable);
    Page<Card> findByCategory(String category, Pageable pageable);
    Page<Card> findByCategoryAndProvider(String category, String provider, Pageable pageable);
}
