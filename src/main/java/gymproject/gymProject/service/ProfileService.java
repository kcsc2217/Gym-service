package gymproject.gymProject.service;

import gymproject.gymProject.File.UploadFile;
import gymproject.gymProject.entity.Dto.Form.ProfileForm;
import gymproject.gymProject.entity.Profile;
import gymproject.gymProject.repogitory.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public Long saveProfile(Profile profile){
        Profile saveProfile= profileRepository.save(profile);

        return saveProfile.getId();

    }


    // profile 로 바꾸기
    public Profile alterProfile(ProfileForm profileForm, UploadFile uploadFile){
        return new Profile(profileForm, uploadFile);
    }




}
