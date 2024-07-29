package gymproject.gymProject.service;

import gymproject.gymProject.entity.Dto.GymInfDto;
import gymproject.gymProject.entity.Gym;
import gymproject.gymProject.entity.GymImage;
import gymproject.gymProject.repogitory.GymImageRepository;
import gymproject.gymProject.repogitory.GymRepository;
import gymproject.gymProject.repogitory.UserGymRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class GymService {
    private final GymRepository gymRepository;
    private final GymImageRepository gymImageRepository;
    private final UserGymRepository userGymRepository;

    @Transactional
    public void saveAll(List<Gym> dataList){
        gymRepository.saveAll(dataList);
    }


    @Transactional
    public void saveBasicImage(GymImage gymImages, Long id){
        Gym findGym = gymRepository.findById(id).orElseThrow(() -> new RuntimeException("Gym not found")); // 짐을 찾음

        findGym.addGymImage(gymImages);
        gymImageRepository.save(gymImages);
    }

    public Slice<GymInfDto> findAllGymInfoDto(Pageable pageable){
        Slice<Gym> gymList = gymRepository.findByGymAndGymList(pageable);

        return gymList.map(gym -> new GymInfDto(gym));

    }

    public boolean existsGymAndUser(Long memberId,  Long gymId){
       return  userGymRepository.existsByMemberIdAndGymId(memberId, gymId);
    }

}
