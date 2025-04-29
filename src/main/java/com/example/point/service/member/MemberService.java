package com.example.point.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.point.Enum.ResponseFailCode;
import com.example.point.domain.Member;
import com.example.point.exception.ConflictException;
import com.example.point.exception.NoSearchException;
import com.example.point.model.member.MemberRequestDTO;
import com.example.point.model.member.MemberResponseDTO;
import com.example.point.repository.member.MemberRepository;

import jakarta.persistence.OptimisticLockException;

/**
 * 회원관련 Service
 */
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
     * 특정 회원 조회
     * 
     * @param memberid
     * @return MemberResponseDTO
     */
    public MemberResponseDTO getMemberDetail(Long memberid){
        return memberRepository.getMemberDetail(memberid);
    }

    /**
     * 조회 성공시 조회수 1증가
     * 
     * @param memberid
     * @return Member
     */
    @Transactional
    public void memberViewCntUpdt(Long memberid){
        Member member = memberRepository.findById(memberid)
                            .orElseThrow(() -> new NoSearchException(ResponseFailCode.NO_SEARCH_MEMBER));
        try{
            member.plusViewCount();
            memberRepository.save(member);
        } catch (OptimisticLockException e){ // 낙관적 잠금 충돌감지
            throw new ConflictException(ResponseFailCode.DATABASE_CONFLICT);
        }
    }
}
