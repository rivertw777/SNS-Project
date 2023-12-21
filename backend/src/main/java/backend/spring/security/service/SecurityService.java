package backend.spring.security.service;

import backend.spring.member.model.entity.Member;
import backend.spring.security.CustomUserDetails;
import backend.spring.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 이름을 가진 회원이 없습니다."));

        return new CustomUserDetails(member);
    }
}