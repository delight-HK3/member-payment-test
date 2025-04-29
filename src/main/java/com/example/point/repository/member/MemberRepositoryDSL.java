package com.example.point.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.point.model.member.MemberRequestDTO;
import com.example.point.model.member.MemberResponseDTO;

@Repository
public interface MemberRepositoryDSL {
    
    // 회원 목록 조회
    public Page<MemberResponseDTO> getMemberList(MemberRequestDTO memberRequestDTO, Pageable pageable);

    // 특정 회원 조회
    public MemberResponseDTO getMemberDetail(Long memberid);
}
