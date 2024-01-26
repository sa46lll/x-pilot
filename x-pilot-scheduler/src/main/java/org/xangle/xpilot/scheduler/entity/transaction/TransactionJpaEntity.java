package org.xangle.xpilot.scheduler.entity.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.xangle.xpilot.scheduler.entity.block.BlockJpaEntity;

import java.time.Instant;

@Entity
@Getter
@Table(name = "transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionJpaEntity {

    @Id
    private byte[] hash;
    private Instant blockTime;
    private byte[] from;
    private byte[] to;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_number")
    private BlockJpaEntity blockNumber;
    private byte[] blockHash;
    private Long gasPrice;
    private Long gasUsed;
    private Long index;
}