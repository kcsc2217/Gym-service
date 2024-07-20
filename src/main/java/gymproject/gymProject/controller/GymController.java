package gymproject.gymProject.controller;

import gymproject.gymProject.entity.Dto.GymInfDto;
import gymproject.gymProject.service.GymService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GymController {

    private final GymService gymService;

    @ResponseBody
    @GetMapping("/gyms")
    public Slice<GymInfDto> getGyms(@RequestParam("page") int page, @RequestParam("size") int size){
        return gymService.findAllGymInfoDto(PageRequest.of(page,size));
    }


}
