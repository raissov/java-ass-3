package kz.raissov.springProject.repository;

import kz.raissov.springProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmailId(String emailAddress);
}
