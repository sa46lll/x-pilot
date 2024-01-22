package org.xangle.xpilot.batch.entity.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionJpaEntity {

    @Id
    private String hash;
    private LocalDateTime blockTime;
    private String from;
    private String to;
    private Long blockNumber;
    private String blockHash;
    private Long gasPrice;
    private Long gasUsed;
    private Long index;
}
