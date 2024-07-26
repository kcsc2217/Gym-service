package gymproject.gymProject;

import gymproject.gymProject.api.ApiRequest;
import gymproject.gymProject.api.JsonParser;
import gymproject.gymProject.entity.Gym;
import gymproject.gymProject.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing // 스프링 부트 설정 클래스에 적용함(createdateTime)
public class GymProjectApplication  {

	@Autowired
	private GymService gymService;

	public static void main(String[] args) {
		SpringApplication.run(GymProjectApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		String jsonResponse = ApiRequest.getApiResponse(
//				"DAdIB7IH45PyJGrEXkp1rsg9CWlIEpT77AVMZ9DxR6zKXw6JHKarUgV3YM7Qk6LMeMd5yA2a0ylVtPe69Sq81Q%3D%3D");
//		List<Gym> dataList = JsonParser.parseData(jsonResponse);
//		gymService.saveAll(dataList);
//	}

}
