package kz.raissov.springProject.repository;

import kz.raissov.springProject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
