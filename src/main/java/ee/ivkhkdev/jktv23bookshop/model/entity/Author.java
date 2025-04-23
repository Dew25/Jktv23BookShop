package ee.ivkhkdev.jktv23bookshop.model.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.MERGE)
    private Set<Book> books;

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(firstname, author.firstname) && Objects.equals(lastname, author.lastname);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(firstname);
        result = 31 * result + Objects.hashCode(lastname);

        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firsname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", books=" + Arrays.toString(books.toArray()) +
                '}';
    }
}
