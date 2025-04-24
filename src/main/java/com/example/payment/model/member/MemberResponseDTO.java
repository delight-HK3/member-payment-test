package com.example.payment.model.member;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberResponseDTO {
    
    private final Long id;                  // 회원 아이디
    private final String name;              // 회원이름
    private final int viewCount;            // 프로필 상세 조회수
    private final LocalDateTime createDt;   // 등록 시간

    public MemberResponseDTO(Long id, String name, int viewCount, LocalDateTime createDt){
        this.id = id;
        this.name = name;
        this.viewCount = viewCount;
        this.createDt = createDt;
    }
}
