package gymproject.gymProject.service;

import gymproject.gymProject.entity.Enum.ExerciseIntensity;
import gymproject.gymProject.entity.Dto.Form.ProfileForm;
import gymproject.gymProject.entity.Profile;
import gymproject.gymProject.repogitory.ProfileRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
@Transactional

class ProfileServiceTest {

    @Autowired ProfileService profileService;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    EntityManager em;

    @Test
    public void  프로필추가() throws Exception {
       //given
        ProfileForm profileForm = new ProfileForm(25, "오운완", ExerciseIntensity.FREQUENT);

       //when
        Profile profile = new Profile(profileForm.getAge(), profileForm.getExercise_goal(), profileForm.getExerciseIntensity());

        //then

        //프로필 저장
        Long id = profileService.saveProfile(profile);

        em.flush();
        em.clear();


        Optional<Profile> optionalProfile= profileRepository.findById(id);

        Profile profile1 = optionalProfile.get();

        Assertions.assertThat(profile1.getAge()).isEqualTo(25);


    }

    @Test
    public void 영속성컨텐츠() throws Exception {
       //given

       //when

       //then
    }

}