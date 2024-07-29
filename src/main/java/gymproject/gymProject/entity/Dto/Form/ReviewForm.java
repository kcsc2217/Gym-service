package gymproject.gymProject.entity.Dto.Form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewForm {

    private String content; // 내용

    private int rating; // 별점

    private MultipartFile multipartFile; // 멀티펄트 파일


    public ReviewForm(String content, int rating, MultipartFile multipartFile) {
        this.content = content;
        this.rating = rating;
        this.multipartFile = multipartFile;
    }

    public ReviewForm() {
    }
}
