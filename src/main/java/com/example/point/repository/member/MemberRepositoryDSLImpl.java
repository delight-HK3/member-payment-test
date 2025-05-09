package com.example.point.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.example.point.Enum.orderValue;
import com.example.point.domain.Member;
import com.example.point.model.member.MemberRequestDTO;
import com.example.point.model.member.MemberResponseDTO;
import com.querydsl.core.types.Order;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.example.point.domain.QMember.member;
import static com.example.point.domain.QPoint.point;

import java.util.List;

@Repository
public class MemberRepositoryDSLImpl implements MemberRepositoryDSL{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryDSLImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    // 특정 회원 조회
    @Override
    public MemberResponseDTO getMemberDetail(Long memberid) {
        MemberResponseDTO result = queryFactory
                                        .select(Projections.constructor(MemberResponseDTO.class,
                                            member.id
                                            , member.name
                                            , member.viewcount
                                            , point.amountpoint
                                            , member.createdAt
                                        ))
                                        .from(member).leftJoin(point).on(member.id.eq(point.member.id))
                                        .where(member.id.eq(memberid))
                                        .fetchOne();
        
        return result;
    }

    // 회원 목록 조회
    @Override
    public Page<MemberResponseDTO> getMemberList(MemberRequestDTO memberRequestDTO, Pageable pageable) {

        List<MemberResponseDTO> result = queryFactory
                                    .select(Projections.constructor(MemberResponseDTO.class,
                                        member.id
                                        , member.name
                                        , member.viewcount
                                        , point.amountpoint
                                        , member.createdAt
                                    ))
                                    .from(member).leftJoin(point).on(member.id.eq(point.member.id))
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

        // 만약 입력된 파라미터중에 orderValue가 지정되지 않았으면 DEFAULT로 설정합니다.
        orderValue orderValueNew = (sortType == null) ? orderValue.DEFAULT : sortType;

        return switch (orderValueNew) {
            case NAME -> new OrderSpecifier<>(Order.ASC, member.name);           // 회원이름 가나다순
            case VIEWCNT -> new OrderSpecifier<>(Order.DESC, member.viewcount);  // 회원 조회수순
            case CREATEDT -> new OrderSpecifier<>(Order.DESC, member.createdAt); // 회원 등록 최신순
            default -> new OrderSpecifier<>(Order.DESC, member.id);              // 회원 ID 내림차순
        };
    }

}
