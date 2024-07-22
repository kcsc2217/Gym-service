package gymproject.gymProject.service;

import gymproject.gymProject.entity.Enum.MemberShip;
import gymproject.gymProject.entity.Gym;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.UserGym;
import gymproject.gymProject.entity.exception.GymNotFoundException;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.repogitory.GymRepository;
import gymproject.gymProject.repogitory.MemberRepository;
import gymproject.gymProject.repogitory.UserGymRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j

public class UserGymService {

    private  final UserGymRepository userGymRepository;
    private final MemberRepository memberRepository;
    private final GymRepository gymRepository;


    @Transactional
    public Long save(Long memberId, Long gymId) {

        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("Member Not Found"));
        Gym findByGym = gymRepository.findById(gymId).orElseThrow(() -> new GymNotFoundException("Gym Not Found"));

        validUserGym(memberId, gymId);

        UserGym userGym = new UserGym(findMember, findByGym, MemberShip.ACTIVE);

        UserGym saveGym = userGymRepository.save(userGym);

        return saveGym.getId();

    }

    private void validUserGym(Long memberId, Long gymId) {
        if(userGymRepository.existsByMemberIdAndGymId(memberId, gymId)) {
            throw new RuntimeException("해당 회원은 이미 존재합니다.");
        }
    }
}
