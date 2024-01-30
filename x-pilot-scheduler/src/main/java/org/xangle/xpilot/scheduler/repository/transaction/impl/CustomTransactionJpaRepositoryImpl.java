package org.xangle.xpilot.scheduler.repository.transaction.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;
import org.xangle.xpilot.scheduler.entity.transaction.QTransactionJpaEntity;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.scheduler.repository.transaction.CustomTransactionJpaRepository;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.xangle.xpilot.scheduler.entity.block.QBlockJpaEntity.blockJpaEntity;
import static org.xangle.xpilot.scheduler.entity.transaction.QTransactionJpaEntity.transactionJpaEntity;

@Repository
@RequiredArgsConstructor
public class CustomTransactionJpaRepositoryImpl implements CustomTransactionJpaRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TransactionJpaEntity> findAllByBlockTimeAndBlockNumber(Instant blockTime, Long blockNumber) {
        return queryFactory.selectFrom(transactionJpaEntity)
                .where(transactionJpaEntity.blockTime.between(blockTime.minusSeconds(1), blockTime.plusSeconds(1)))
                .where(transactionJpaEntity.blockNumber.eq(blockNumber))
                .fetch();
    }
}
