package org.xangle.xpilot.scheduler.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpaEntity, String> {

}
