package org.xangle.xpilot.scheduler.repository.block.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;
import org.xangle.xpilot.scheduler.repository.block.CustomBlockJpaRepository;

import java.util.List;

import static org.xangle.xpilot.scheduler.entity.block.QBlockJpaEntity.blockJpaEntity;

@Repository
@RequiredArgsConstructor
public class CustomBlockJpaRepositoryImpl implements CustomBlockJpaRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BlockJpaEntity> findAllByNumberBetween(Long startNumber, Long endNumber) {
        return queryFactory.selectFrom(blockJpaEntity)
                .where(blockJpaEntity.number.between(startNumber, endNumber))
                .leftJoin(blockJpaEntity.transactions)
                .fetchJoin()
                .fetch();
    }
}
