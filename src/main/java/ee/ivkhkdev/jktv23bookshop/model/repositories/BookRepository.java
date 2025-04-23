package ee.ivkhkdev.jktv23bookshop.model.repositories;

import ee.ivkhkdev.jktv23bookshop.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
