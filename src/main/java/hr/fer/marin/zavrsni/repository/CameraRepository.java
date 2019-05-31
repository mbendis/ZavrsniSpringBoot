package hr.fer.marin.zavrsni.repository;

import hr.fer.marin.zavrsni.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Integer> {
    List<Camera> getByObject_Id(Integer objectId);
}
