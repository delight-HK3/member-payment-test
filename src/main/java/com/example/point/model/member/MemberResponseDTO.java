package com.example.point.model.member;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MemberResponseDTO {
    
    private final Long id;                  // 회원 아이디
    private final String name;              // 회원이름
    private final int viewCount;            // 프로필 상세 조회수
    private final int amountPoint;          // 회원이 소지하고 있는 포인트

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createDt;   // 등록 시간

    public MemberResponseDTO(Long id, String name, int viewCount, int amountPoint, LocalDateTime createDt){
        this.id = id;
        this.name = name;
        this.viewCount = viewCount;
        this.amountPoint = amountPoint;
        this.createDt = createDt;
    }
}
