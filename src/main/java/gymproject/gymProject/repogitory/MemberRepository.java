package gymproject.gymProject.repogitory;

import gymproject.gymProject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email = :email and m.username = :username")
    Optional<Member> findByEmailAndUsername(@Param("email") String email, @Param("username") String username);


    Optional<Member> findByUsername(String username);



}
