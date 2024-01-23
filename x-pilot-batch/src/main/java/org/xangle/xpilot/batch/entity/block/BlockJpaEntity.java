package org.xangle.xpilot.batch.entity.block;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.xangle.xpilot.batch.entity.transaction.TransactionJpaEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "blocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockJpaEntity {

    @Id
    private Long number;
    private LocalDateTime time;
    private byte[] hash;
    private byte[] parentHash;
    private byte[] miner;
    private int size;
    @OneToMany(mappedBy = "blockNumber", fetch = FetchType.EAGER)
    private List<TransactionJpaEntity> transactions = new ArrayList<>();
}
