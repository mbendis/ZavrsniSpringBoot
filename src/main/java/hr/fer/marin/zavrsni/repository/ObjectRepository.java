package hr.fer.marin.zavrsni.repository;

import hr.fer.marin.zavrsni.model.Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends JpaRepository<Object, Integer> {
}
