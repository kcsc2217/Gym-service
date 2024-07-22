package gymproject.gymProject.service;

import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import gymproject.gymProject.entity.Dto.Form.MemberForm;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.UserGym;
import gymproject.gymProject.entity.exception.DuplicatePasswordException;
import gymproject.gymProject.repogitory.UserGymRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class UserGymServiceTest {

    @Autowired
    private UserGymService userGymService;

    @Autowired
    private EntityManager em;

    @Autowired
    private UserGymRepository userGymRepository;



    @Test
    public void usrGym데이터넣기() throws Exception {
        //given
                Long Id = userGymService.save(1L, 1L);

                //when
        em.flush();
        em.clear();

        UserGym userGym = userGymRepository.findById(Id).get();

        //then

        Assertions.assertThat(userGym.getGym().getGymName()).isEqualTo("발리 휘트니스");



    }


    @Test
    public void 유효성검사() throws Exception {
        //given
        userGymService.save(1L, 1L);

        em.flush();
        em.clear();


        //then

        Assertions.assertThatThrownBy(() -> userGymService.save(1L, 1L))
                .isInstanceOf(RuntimeException.class);


    }

    @Test
    @Rollback(false)
    public void 테스트데이터넣기() throws Exception {
        //given
        userGymService.save(1L, 1L);
        userGymService.save(1L, 2L);


    }




}