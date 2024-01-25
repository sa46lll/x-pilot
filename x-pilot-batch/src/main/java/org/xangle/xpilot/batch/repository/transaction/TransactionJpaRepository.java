package org.xangle.xpilot.batch.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xangle.xpilot.batch.entity.transaction.TransactionJpaEntity;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpaEntity, String> {

}
