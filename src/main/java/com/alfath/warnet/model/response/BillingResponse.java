package com.alfath.warnet.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BillingResponse {
    private String id;
    private String customerName;
    private Long minutes;
    private Boolean isUsed;
}
