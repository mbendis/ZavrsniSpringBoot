package hr.fer.marin.zavrsni.repository;

import hr.fer.marin.zavrsni.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
