package gymproject.gymProject.repogitory;

import gymproject.gymProject.entity.Gym;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {

    @Query("select g from Gym g")
    Slice<Gym> findByGymAndGymList(Pageable pageable);


    @Query("select  distinct  g from Gym g join fetch g.gymImageList WHERE g.id = :id")
    Optional<Gym> findFetchByGymId(Long id);

}
