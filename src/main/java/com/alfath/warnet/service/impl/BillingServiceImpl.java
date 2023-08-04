package com.alfath.warnet.service.impl;

import com.alfath.warnet.entity.Billing;
import com.alfath.warnet.model.BillingResponse;
import com.alfath.warnet.service.BillingService;
import org.springframework.data.domain.Page;

public class BillingServiceImpl implements BillingService {
    @Override
    public BillingResponse create(Billing billing) {
        return null;
    }

    @Override
    public Page<BillingResponse> getAllBilling(Integer page, Integer size) {
        return null;
    }

    @Override
    public BillingResponse setBillingOff(Billing billing) {
        return null;
    }

    @Override
    public void deleteBillingById(String id) {

    }

    @Override
    public BillingResponse updateBilling(Billing billing) {
        return null;
    }
}
