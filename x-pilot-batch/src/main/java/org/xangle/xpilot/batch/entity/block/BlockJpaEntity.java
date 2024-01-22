package org.xangle.xpilot.batch.entity.block;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "blocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockJpaEntity {

    @Id
    private Long number;
    private LocalDateTime time;
    private String hash;
    private String parentHash;
    private String miner;
    private int size;
}
