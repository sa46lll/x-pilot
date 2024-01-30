package org.xangle.xpilot.scheduler.entity.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long blockNumber;
    private byte[] blockHash;
    private Long gasPrice;
    private Long gasUsed;
    private Long index;
}
