package com.example.point.member;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.point.domain.Member;
import com.example.point.exception.NoSearchException;
import com.example.point.model.member.MemberRequestDTO;
import com.example.point.model.member.MemberResponseDTO;
import com.example.point.service.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@DisplayName("회원 관련기능 테스트")
public class MemberServiceTest {
    
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("조건에 따라 회원목록 조회 테스트")
    public void getMemberList(){
        // 성공 : 회원목록 정보 출력
        // 실패 : exceiption의 메세지 로그 출력
        try{
            MemberRequestDTO memberRequestDTO = new MemberRequestDTO();
            //memberRequestDTO.setSortType(orderValue.VIEWCNT);
            Pageable pageable = PageRequest.of(0,5);
            
            Page<MemberResponseDTO> memberList = memberService.getMemberList(memberRequestDTO, pageable);
            log.info(memberList.toString());
        } catch(NoSearchException e) {
            log.warn(e.getMessage());
            // 메세지 : 회원목록이 존재하지 않습니다.
        }
    }

    @Test
    @DisplayName("회원 1건 조회 테스트 / 낙관적 락 테스트")
    public void getMemberDetail() throws InterruptedException{
        // 테스트 : 1번 ID를 가진 회원 조회

        int numberOfUsers = 10;

		ExecutorService executorService = Executors.newFixedThreadPool(numberOfUsers);
		CountDownLatch latch = new CountDownLatch(numberOfUsers);

        for (int i = 0; i < numberOfUsers; i++) {
            executorService.submit(() -> {
                try{
                    MemberResponseDTO result = memberService.getMemberDetail(1L);
                    log.info(result.toString());
                } catch(NoSearchException e) { // 회원을 찾을 수 없는 경우
                    log.warn(e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        MemberResponseDTO result = memberService.getMemberDetail(1L);
        System.out.println("최종 조회수: " + result.getViewCount());
    }
}
