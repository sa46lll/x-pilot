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
    public List<BlockJpaEntity> findAllByNumberAfter(Long number, int count) {
        return queryFactory.selectFrom(blockJpaEntity)
                .where(blockJpaEntity.number.goe(number))
                .orderBy(blockJpaEntity.number.asc())
                .limit(count)
                .fetch();
    }
}
