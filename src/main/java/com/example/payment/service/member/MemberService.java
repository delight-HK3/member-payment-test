package com.example.payment.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.payment.Enum.ResponseFailCode;
import com.example.payment.exception.NoSearchException;
import com.example.payment.model.member.MemberRequestDTO;
import com.example.payment.model.member.MemberResponseDTO;
import com.example.payment.repository.member.MemberRepository;

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
     * @return
     */
    public Page<MemberResponseDTO> getMemberList(MemberRequestDTO memberRequestDTO, Pageable pageable){
        
        Page<MemberResponseDTO> memberResult = memberRepository.getMemberList(memberRequestDTO, pageable);
        if(memberResult.isEmpty()){
            throw new NoSearchException(ResponseFailCode.MEMBER_EMPTY);
        }

        return memberResult;
    }   

}
