package ee.ivkhkdev.jktv23bookshop.model.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
    private String publisherYear;
    @Lob
    @Column(name = "cover", columnDefinition = "LONGBLOB")
    private byte[] cover;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getPublisherYear() {
        return publisherYear;
    }

    public void setPublisherYear(String publisherYear) {
        this.publisherYear = publisherYear;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(authors, book.authors) && Objects.equals(publisherYear, book.publisherYear) && Objects.equals(cover, book.cover);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(authors);
        result = 31 * result + Objects.hashCode(publisherYear);
        result = 31 * result + Objects.hashCode(cover);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + Arrays.toString(authors.toArray()) +
                ", publisherYear='" + publisherYear + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
