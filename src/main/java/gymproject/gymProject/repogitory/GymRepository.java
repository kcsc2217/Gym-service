package gymproject.gymProject.repogitory;

import gymproject.gymProject.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
