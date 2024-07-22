package gymproject.gymProject.repogitory;

import gymproject.gymProject.entity.UserGym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserGymRepository extends JpaRepository<UserGym, Long> {

    boolean existsByMemberIdAndGymId(Long memberId, Long gymId);
}
