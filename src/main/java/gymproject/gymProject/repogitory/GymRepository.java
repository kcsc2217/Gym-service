package gymproject.gymProject.repogitory;

import gymproject.gymProject.entity.Gym;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GymRepository extends JpaRepository<Gym, Long> {

    @Query("select g from Gym g  JOIN FETCH g.gymImage")
    Slice<Gym> findByGymAndGymList(Pageable pageable);

}
