package com.alfath.warnet.service;

import com.alfath.warnet.entity.Billing;
import com.alfath.warnet.model.request.BillingRequest;
import com.alfath.warnet.model.response.BillingResponse;
import org.springframework.data.domain.Page;

public interface BillingService {
    BillingResponse create(BillingRequest billing);
    Page<BillingResponse> getAllBillingisUsed(Integer page, Integer size);
    void setBillingOffbyPcId(String pcId);
    void deleteBillingById(String id);

    Billing getBillingById(String id);

}
