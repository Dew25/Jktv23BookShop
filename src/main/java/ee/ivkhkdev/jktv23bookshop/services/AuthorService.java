package ee.ivkhkdev.jktv23bookshop.services;

import ee.ivkhkdev.jktv23bookshop.model.entity.Author;
import ee.ivkhkdev.jktv23bookshop.model.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements ee.ivkhkdev.jktv23bookshop.interfaces.AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public void add(Author author) {
        authorRepository.save(author);
    }

    @Override
    public List<Author> list() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long authorId) {
        return authorRepository.findById(authorId);
    }
}
