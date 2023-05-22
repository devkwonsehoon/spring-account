package com.sehoon.account.domain;

import com.sehoon.account.type.TransactionResultType;
import com.sehoon.account.type.TransactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    TransactionResultType transactionResultType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account accountId;

    Long amount;

    Long balanceSnapshot;

    String transactionId;

    @CreatedDate
    LocalDateTime transactedAt;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;

    public Transaction toEntity() {
        return Transaction
                .builder()
                .transactionType(this.transactionType)
                .transactionResultType(this.transactionResultType)
                .accountId(this.accountId)
                .amount(this.amount)
                .balanceSnapshot(this.balanceSnapshot)
                .transactionId(this.transactionId)
                .build();
    }
}
