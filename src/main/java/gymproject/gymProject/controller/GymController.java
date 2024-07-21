package gymproject.gymProject.controller;

import gymproject.gymProject.entity.Dto.GymContentDto;
import gymproject.gymProject.entity.Dto.GymInfDto;
import gymproject.gymProject.entity.Gym;
import gymproject.gymProject.entity.exception.GymNotFoundException;
import gymproject.gymProject.repogitory.GymRepository;
import gymproject.gymProject.service.GymService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GymController {

    private final GymService gymService;
    private final GymRepository gymRepository;

    @ResponseBody
    @GetMapping("/gyms")
    public Slice<GymInfDto> getGyms(@RequestParam("page") int page, @RequestParam("size") int size){
        return gymService.findAllGymInfoDto(PageRequest.of(page,size));
    }


    @GetMapping("/gyms/{id}")
    public String getGymById(@PathVariable("id") Long id, Model model){

        Gym gym = gymRepository.findFetchByGymId(id).orElseThrow(() -> new GymNotFoundException("해당 헬스장은 존재하지 않습니다"));

        GymContentDto gymContentDto = new GymContentDto(gym);

        model.addAttribute("gym", gymContentDto);


        return "/gym/gym";
    }




}
