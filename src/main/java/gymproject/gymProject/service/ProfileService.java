package gymproject.gymProject.service;

import gymproject.gymProject.File.FileStore;
import gymproject.gymProject.File.UploadFile;
import gymproject.gymProject.entity.Dto.Form.ProfileForm;
import gymproject.gymProject.entity.Dto.Form.ProfileUpdateForm;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.entity.Profile;
import gymproject.gymProject.entity.exception.MemberNotFoundException;
import gymproject.gymProject.repogitory.MemberRepository;
import gymproject.gymProject.repogitory.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;
    private final FileStore fileStore;

    @Transactional
    public Long saveProfile(Profile profile){
        Profile saveProfile= profileRepository.save(profile);

        return saveProfile.getId();

    }

    @Transactional
    public void updateProfile(Long id, ProfileUpdateForm profileUpdateForm) throws IOException {
        Member member = memberRepository.findByIdWithProfile(id).orElseThrow(() -> new MemberNotFoundException("해당 멤버가 없습니다"));// 패치 조인으로 가져옴

        UploadFile uploadFile = fileStore.storeFile(profileUpdateForm.getMultipartFile());

        log.info("업데이트 프로필 계층까지 오는데 성공");

        profileUpdateForm.setStoredFileName(uploadFile.getStoreFileName());
        profileUpdateForm.setUploadFileName(uploadFile.getUploadFileName());
        log.info(profileUpdateForm.getStoredFileName());
        log.info(profileUpdateForm.getUploadFileName());

        member.updateProfile(profileUpdateForm);
    }




    // profile 로 바꾸기
    public Profile alterProfile(ProfileForm profileForm, UploadFile uploadFile){
        return new Profile(profileForm, uploadFile);
    }




}
