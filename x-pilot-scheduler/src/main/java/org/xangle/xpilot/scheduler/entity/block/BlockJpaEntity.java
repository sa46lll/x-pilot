package org.xangle.xpilot.scheduler.entity.block;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "blocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockJpaEntity {

    @Id
    private Long number;
    private Instant time;
    private byte[] hash;
    private byte[] parentHash;
    private byte[] miner;
    private int size;
}
