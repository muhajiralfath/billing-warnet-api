package com.alfath.warnet.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BillingResponse {
    private String id;
    private String username;
    private String customerName;
    private String computerName;
    private Long minutes;
    private Boolean isUsed;
    private Timestamp startAt;
    private Timestamp EndAt;
}
