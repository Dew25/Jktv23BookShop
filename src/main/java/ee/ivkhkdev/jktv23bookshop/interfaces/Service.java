package ee.ivkhkdev.jktv23bookshop.interfaces;

import java.util.List;

public interface Service<T> {
    void add(T t);
    List<T> list();
}
