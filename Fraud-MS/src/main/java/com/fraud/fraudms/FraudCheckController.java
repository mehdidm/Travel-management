package com.fraud.fraudms;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudCheckController {
    private final  FraudCheckService fraudCheckService;
    @GetMapping(path = "{clientId}")
    public FraudCheckResponse isFraudster(

    @PathVariable("clientId") Integer clientId){
    boolean isFraudulentClient = fraudCheckService.isFraudulentClient(clientId);
    log.info("fraud check request");
    return new FraudCheckResponse(isFraudulentClient);
    }
}
