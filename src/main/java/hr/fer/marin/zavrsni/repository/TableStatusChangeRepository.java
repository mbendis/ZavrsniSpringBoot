package hr.fer.marin.zavrsni.repository;

import hr.fer.marin.zavrsni.model.TableStatusChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableStatusChangeRepository extends JpaRepository<TableStatusChange, Integer> {
}
