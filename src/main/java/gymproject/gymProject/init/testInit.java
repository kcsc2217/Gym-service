package gymproject.gymProject.init;

import gymproject.gymProject.entity.Enum.Role;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.address.Address;
import gymproject.gymProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class testInit implements CommandLineRunner {

    private final  MemberService memberService;
    @Override
    public void run(String... args) throws Exception {
        Member member = new Member("kcsc2217", "k12002","k12002@nate.com", "010-7119-8112", Role.user, "이성원", new Address("광양시", "광장로",
                "305동 1501호"));

            memberService.createMember(member);

    }
}
