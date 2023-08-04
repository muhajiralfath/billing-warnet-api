package com.alfath.warnet.service.impl;

import com.alfath.warnet.entity.Billing;
import com.alfath.warnet.entity.Computer;
import com.alfath.warnet.entity.Customer;
import com.alfath.warnet.model.request.BillingRequest;
import com.alfath.warnet.model.response.BillingResponse;
import com.alfath.warnet.repository.BillingRepository;
import com.alfath.warnet.service.BillingService;
import com.alfath.warnet.service.ComputerService;
import com.alfath.warnet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PrePersist;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {
    private final BillingRepository billingRepository;
    private final ComputerService computerService;
    private final CustomerService customerService;
    @Transactional(rollbackOn = Exception.class)
    @PrePersist
    @Override
    public BillingResponse create(BillingRequest request) {
        Customer customerFound = customerService.getCustomerById(request.getComputerId());
        Computer computerFound = computerService.getCompById(request.getComputerId());
        long startBilling = System.currentTimeMillis() / 1000;
        long endTime = startBilling + request.getRentalMinutes() * 60;

        Billing billing = Billing.builder()
                .customer(customerFound)
                .computer(computerFound)
                .rentalMinutes(request.getRentalMinutes())
                .start(startBilling)
                .end(endTime)
                .isUsed(true)
                .startAt(new Timestamp(System.currentTimeMillis()))
                .endAt(new Timestamp(System.currentTimeMillis() + request.getRentalMinutes() * 60_000))
                .build();
        billingRepository.saveAndFlush(billing);

        return BillingResponse.builder()
                .customerName(customerFound.getName())
                .computerName(computerFound.getName())
                .minutes(request.getRentalMinutes())
                .isUsed(billing.getIsUsed())
                .build();
    }

    @Override
    public Page<BillingResponse> getAllBillingisUsed(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Billing> billings = billingRepository.findAllByIsUsedTrue(pageable);

        List<Billing> billingList = new ArrayList<>(billings.getContent());
        List<BillingResponse> billingResponses = billingList.stream().map(billing -> BillingResponse.builder()
                .id(billing.getId())
                .customerName(billing.getCustomer().getName())
                .computerName(billing.getComputer().getName())
                .minutes(billing.getRentalMinutes())
                .isUsed(billing.getIsUsed())
                .build()).collect(Collectors.toList());

        return new PageImpl<>(billingResponses, pageable, billings.getTotalElements());

    }

    @Override
    public void setBillingOffbyPcId(String pcId) {
        Billing billing = findByIdAndUsedTrueOrThrowNotFound(pcId);
        billing.setIsUsed(true);
        billingRepository.saveAndFlush(billing);

    }

    @Override
    public void deleteBillingById(String id) {
        Billing billing = findByIdAndUsedTrueOrThrowNotFound(id);
        billingRepository.deleteById(billing.getId());
    }

    @Override
    public Billing getBillingById(String id) {
        return billingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Billing not found"));
    }

    @Override
    @Scheduled(cron = "*/1 * * * * *") // Cron expression for running every second
    public void updateIsUsedBasedOnTime() {
//        long currentEpochTime = System.currentTimeMillis() / 1000; // Convert current time to epoch time in seconds
        List<Billing> usedBillings = billingRepository.findAllByEndAtLessThan(new Timestamp(System.currentTimeMillis()));
//        List<String> usedBillingIds = usedBillings.stream().map(Billing::getId).collect(Collectors.toList());
//        billingRepository.updateIsUsedField(usedBillingIds, false);
        usedBillings.forEach(billing -> {
            billing.setIsUsed(false);
            billingRepository.save(billing);
        });

    }

    private Billing findByIdAndUsedTrueOrThrowNotFound(String id) {
        return billingRepository.findByIdAndIsUsedTrue(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Billing not found"));
    }

}
