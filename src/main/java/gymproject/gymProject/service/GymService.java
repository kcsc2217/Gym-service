package gymproject.gymProject.service;

import gymproject.gymProject.entity.Gym;
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

    @Transactional
    public void saveAll(List<Gym> dataList){
        gymRepository.saveAll(dataList);
    }

}
