package com.alfath.warnet.service.impl;

import com.alfath.warnet.entity.Operator;
import com.alfath.warnet.repository.OperatorRepository;
import com.alfath.warnet.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {
    private final OperatorRepository operatorRepository;
    @Override
    public Operator create(Operator operator) {
        try {
            return operatorRepository.save(operator);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "username already exist");
        }
    }
}