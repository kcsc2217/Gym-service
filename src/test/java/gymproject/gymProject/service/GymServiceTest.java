package gymproject.gymProject.service;

import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import gymproject.gymProject.entity.Dto.Form.MemberForm;
import gymproject.gymProject.entity.Dto.GymInfDto;
import gymproject.gymProject.entity.Likes;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.Review;
import gymproject.gymProject.entity.exception.DuplicatePasswordException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GymServiceTest {

    @Autowired
    private GymService gymService;

    @Test
    public void 스크롤방식검사() throws Exception {
        //given

        Slice<GymInfDto> allGymInfoDto = gymService.findAllGymInfoDto(PageRequest.of(0, 10));
        //then
        List<GymInfDto> content = allGymInfoDto.getContent();

        for (GymInfDto gymInfDto : content) {
            System.out.println(gymInfDto.getGymName() + " " +gymInfDto.getStorePath());
        }

        Assertions.assertThat(allGymInfoDto.getSize()).isEqualTo(10);
    }






}