package gymproject.gymProject.service;

import gymproject.gymProject.entity.Gym;
import gymproject.gymProject.entity.GymImage;
import gymproject.gymProject.repogitory.GymImageRepository;
import gymproject.gymProject.repogitory.GymRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class GymService {
    private final GymRepository gymRepository;
    private final GymImageRepository gymImageRepository;

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




}
