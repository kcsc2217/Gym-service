package gymproject.gymProject.entity.value;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class StoredFile {

    private String fileName;

    public StoredFile(String fileName) {
        this.fileName = fileName;
    }

    public StoredFile() {
    }
}
