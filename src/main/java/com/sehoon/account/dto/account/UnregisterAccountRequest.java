package com.sehoon.account.dto.account;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UnregisterAccountRequest {
    Long userId;
    String accountNumber;
}
