package com.fraud.fraudms;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fraud_check_history")
public class FraudCheckHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cin;

    @Column(nullable = false)
    private boolean isFraudster;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public FraudCheckHistory( Long cin, boolean isFraudster, LocalDateTime createdAt) {

        this.cin = cin;
        this.isFraudster = isFraudster;
        this.createdAt = createdAt;
    }
}
