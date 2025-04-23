package ee.ivkhkdev.jktv23bookshop.services;

import ee.ivkhkdev.jktv23bookshop.model.entity.Author;
import ee.ivkhkdev.jktv23bookshop.model.entity.Book;
import ee.ivkhkdev.jktv23bookshop.model.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookService implements ee.ivkhkdev.jktv23bookshop.interfaces.BookService{
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public void add(Book book) {
        Set<Author> bookAuthor = book.getAuthors();
        for (Author author : bookAuthor) {
            author.getBooks().add(book);
        }
        bookRepository.save(book);
    }

    @Override
    public List<Book> list() {
        return bookRepository.findAll();
    }
}
