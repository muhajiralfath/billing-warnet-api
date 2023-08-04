package com.alfath.warnet.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BillingRequest {
    private String customerId;
    private String computerId;
    private Long rentalMinutes;
}
