package com.example.payment.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.payment.Enum.ResponseFailCode;
import com.example.payment.domain.Member;
import com.example.payment.exception.ConflictException;
import com.example.payment.exception.NoSearchException;
import com.example.payment.model.member.MemberRequestDTO;
import com.example.payment.model.member.MemberResponseDTO;
import com.example.payment.repository.member.MemberRepository;

import jakarta.persistence.OptimisticLockException;

@Service
public class MemberService {
    
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 조회
     * 
     * @param sort
     * @param pageable
     * @return Page<MemberResponseDTO>
     */
    public Page<MemberResponseDTO> getMemberList(MemberRequestDTO memberRequestDTO, Pageable pageable){
        
        Page<MemberResponseDTO> memberResult = memberRepository.getMemberList(memberRequestDTO, pageable);
        if(memberResult.isEmpty()){
            throw new NoSearchException(ResponseFailCode.MEMBER_EMPTY);
        }

        return memberResult;
    }   

    /**
     * 특정 회원 조회 (조회 성공시 조회수 1증가)
     * 
     * @param memberid
     * @return Member
     */
    @Transactional
    public Member getMemberDetail(Long memberid){
        Member member = memberRepository.findById(memberid)
                            .orElseThrow(() -> new NoSearchException(ResponseFailCode.NO_SEARCH_MEMBER));
        try{
            member.plusViewCount();
            memberRepository.save(member);
            return member;
        } catch (OptimisticLockException e){ // 낙관적 잠금 충돌감지
            throw new ConflictException(ResponseFailCode.DATABASE_CONFLICT);
        }
    }
}
