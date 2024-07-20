package gymproject.gymProject.init;

import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.GymImage;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.address.Address;
import gymproject.gymProject.service.GymService;
import gymproject.gymProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
@RequiredArgsConstructor
@Slf4j
public class testInit  {

    private final GymService gymService;

//    @Override
//    public void run(String... args) throws Exception {
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 1L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 2L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 3L);
//        gymService.saveBasicImage(new GymImage("금강1.jpg"), 4L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 5L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 6L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 7L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 8L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 9L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 10L);
//        gymService.saveBasicImage(new GymImage("에스짐.jpg"), 11L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 12L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 13L);
//        gymService.saveBasicImage(new GymImage("스파렉스.jpg"), 14L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 15L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 16L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 17L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 18L);
//        gymService.saveBasicImage(new GymImage("크로스핏야인.jpg"), 19L);
//        gymService.saveBasicImage(new GymImage("HJ퍼스널.jpg"), 20L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 21L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 22L);
//        gymService.saveBasicImage(new GymImage("BB짐.jpg"), 23L);
//        gymService.saveBasicImage(new GymImage("크로스핏예스.jpg"), 24L);
//        gymService.saveBasicImage(new GymImage("라이언.jpg"), 25L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 26L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 27L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 28L);
//        gymService.saveBasicImage(new GymImage("머팩.jpg"), 29L);
//        gymService.saveBasicImage(new GymImage("헐크짐.jpg"), 30L);
//        gymService.saveBasicImage(new GymImage("basicjpg.jpg"), 31L);
//        gymService.saveBasicImage(new GymImage("다짐.jpg"), 32L);
//        gymService.saveBasicImage(new GymImage("짐포유.jpg"), 33L);
//        gymService.saveBasicImage(new GymImage("MAXQGYM.jpg"), 34L);
//    }
}
