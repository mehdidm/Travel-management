package com.fraud.fraudms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/checkFraud")
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @Autowired
    public FraudCheckController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    @GetMapping("/{cin}/{reservationId}")
    public ResponseEntity<String> checkFraud(@PathVariable Long cin,@PathVariable Long reservationId ) {

        boolean isFraudulent = fraudCheckService.isFraudulentClient(cin,reservationId);
        if (isFraudulent) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Client with CIN " + cin + " is fraudulent");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Client with CIN " + cin + " is not fraudulent");
        }
    }
}

