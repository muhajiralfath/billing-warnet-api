package com.alfath.warnet.service.impl;

import com.alfath.warnet.entity.Operator;
import com.alfath.warnet.model.request.OperatorRequest;
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
    public Operator create(OperatorRequest request) {
        try {
            Operator operator = Operator.builder()
                    .username(request.getUsername())
                    .name(request.getName())
                    .telepon(request.getTelepon())
                    .build();
            return operatorRepository.save(operator);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "username already exist");
        }
    }

    @Override
    public void deleteOperatorById(String id) {
        getOperatorById(id);
        operatorRepository.deleteById(id);
    }

    @Override
    public Operator getOperatorById(String id) {
        return operatorRepository.findOperatorById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Operator not found"));
    }
}
