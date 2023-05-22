package com.sehoon.account.domain;

import com.sehoon.account.type.AccountStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;
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
@Entity
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "unregistered_at IS NULL")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "account_user_id")
    AccountUser accountUserId;

    String accountNumber;

    @Enumerated(EnumType.STRING)
    AccountStatus accountStatus;

    Long balance;

    @CreatedDate
    LocalDateTime registeredAt;

    LocalDateTime unregisteredAt;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;

    public void unregister() {
        this.accountStatus = AccountStatus.UNREGISTERED;
        this.unregisteredAt = LocalDateTime.now();
    }
}
