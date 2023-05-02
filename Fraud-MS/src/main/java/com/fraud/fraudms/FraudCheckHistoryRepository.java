package com.fraud.fraudms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Long> {
    List<FraudCheckHistory> findAllByCin(Long cin);
}


