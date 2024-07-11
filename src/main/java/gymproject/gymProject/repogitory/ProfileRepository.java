package gymproject.gymProject.repogitory;

import gymproject.gymProject.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
