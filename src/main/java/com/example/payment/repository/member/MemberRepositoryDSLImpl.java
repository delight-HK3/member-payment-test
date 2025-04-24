package com.example.payment.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.example.payment.Enum.orderValue;
import com.example.payment.domain.Member;
import com.example.payment.model.member.MemberRequestDTO;
import com.example.payment.model.member.MemberResponseDTO;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.Order;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.example.payment.domain.QMember.member;

import java.util.List;

@Repository
public class MemberRepositoryDSLImpl implements MemberRepositoryDSL{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryDSLImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<MemberResponseDTO> getMemberList(MemberRequestDTO memberRequestDTO, Pageable pageable) {

        List<MemberResponseDTO> result = queryFactory
                                    .select(Projections.constructor(MemberResponseDTO.class,
                                        member.id
                                        , member.name
                                        , member.viewcount
                                        , member.createdAt
                                    ))
                                    .from(member)
                                    .orderBy(createOrderSpecifier(memberRequestDTO.getSort()))
                                    .offset(pageable.getOffset())
                                    .limit(pageable.getPageSize())
                                    .fetch();
        
        JPQLQuery<Member> count = queryFactory.selectFrom(member);

        // getPage(결과목록, 페이지네이션 설정, 조건에 맞는 데이터 총 개수)
        // 성능 최적화를 위해 PageImpl대신 PageableExecutionUtils사용
        // (PageImpl에서 DB 3번 호출할 때 PageableExecutionUtils는 2번 호출)
        return PageableExecutionUtils.getPage(result, pageable, () -> count.fetchCount());
    }
   
    private OrderSpecifier<?> createOrderSpecifier(orderValue sortType) {

        orderValue orderValueNew = (sortType == null) ? orderValue.DEFAULT : sortType;

        return switch (orderValueNew) {
            case NAME -> new OrderSpecifier<>(Order.ASC, member.name);           // 회원이름 가나다순
            case VIEWCNT -> new OrderSpecifier<>(Order.DESC, member.viewcount);  // 회원 조회수순
            case CREATEDT -> new OrderSpecifier<>(Order.DESC, member.createdAt); // 회원 등록 최신순
            default -> new OrderSpecifier<>(Order.DESC, member.id);              // 회원 ID 내림차순
        };
    }

}
