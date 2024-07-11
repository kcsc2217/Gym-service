package gymproject.gymProject.File;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir +filename;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {

        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            UploadFile uploadFile = storeFile(multipartFile);

            uploadFiles.add(uploadFile);
        }
        return uploadFiles;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {

        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename(); //파일명
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFilename, storeFileName);
    }


    // 서버에 저장 할 파일명 생성
    private String createStoreFileName(String originalFilename){

        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + ext;

    }

    // 뒤에 확장자만 따오기
    private String extractExt(String originalFileName){
        int pos = originalFileName.lastIndexOf(".");

        return originalFileName.substring(pos+1);
    }


}
