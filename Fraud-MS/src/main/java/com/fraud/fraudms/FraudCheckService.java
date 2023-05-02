package com.fraud.fraudms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Service
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository, RabbitTemplate rabbitTemplate) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

//    @RabbitListener(queues = "reservations")
//    public void checkFraud(String message) {
//        String[] parts = message.split(",");
//        Long cin = Long.parseLong(parts[0]);
//        Long reservationId = Long.parseLong(parts[1]);
//
//        if (isFraudulentClient(cin)) {
//            log.info("Fraudulent client detected: cancelling reservation with ID {}", reservationId);
//            rabbitTemplate.convertAndSend("fraudExchange", "cancelReservation", reservationId);
//        } else {
//            log.info("Client is not fraudulent: allowing reservation with ID {}", reservationId);
//        }
//    }

    public boolean isFraudulentClient(Long cin, Long reservationId) {
        List<FraudCheckHistory> fraudCheckHistories = fraudCheckHistoryRepository.findAllByCin(cin);

        if (fraudCheckHistories.isEmpty()) {
            fraudCheckHistoryRepository.save(new FraudCheckHistory(cin, false, LocalDateTime.now(ZoneId.of("Africa/Tunis"))));
            return false;
        }
        LocalDateTime lastFraudCheck = fraudCheckHistories.get(0).getCreatedAt();
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Africa/Tunis"));
        if (Duration.between(lastFraudCheck, now).toMinutes() < 60) {
            fraudCheckHistoryRepository.save(new FraudCheckHistory(cin, true, now));
            rabbitTemplate.convertAndSend("fraudExchange", "cancelReservation", reservationId);
            return true;
        } else {      fraudCheckHistoryRepository.save(new FraudCheckHistory(cin, false, now));
            return false;
        }
    }

}