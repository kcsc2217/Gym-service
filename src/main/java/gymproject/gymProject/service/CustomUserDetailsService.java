package gymproject.gymProject.service;

import gymproject.gymProject.entity.CustomUserDetails;
import gymproject.gymProject.entity.Member;
import gymproject.gymProject.repogitory.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Member member = memberRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException((username)));

       return new CustomUserDetails(member);

    }

}
