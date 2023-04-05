package com.fraud.fraudms;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.time.LocalDateTime;
@Service
@AllArgsConstructor
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentClient(Integer clientId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .clientId(clientId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
