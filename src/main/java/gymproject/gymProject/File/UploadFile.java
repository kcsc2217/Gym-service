package gymproject.gymProject.File;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@Embeddable
public class UploadFile {

    private String uploadFileName; // 업로드 할 파일 이름


    private String storeFileName; // 저장 할 파일 이름

    public UploadFile() {
    }


    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
