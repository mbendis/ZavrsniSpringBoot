package hr.fer.marin.zavrsni.repository;

import hr.fer.marin.zavrsni.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
    List<Table> findByCameraId(Integer camera_id);
}
