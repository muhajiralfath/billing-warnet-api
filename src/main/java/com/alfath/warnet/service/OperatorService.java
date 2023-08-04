package com.alfath.warnet.service;

import com.alfath.warnet.entity.Operator;

public interface OperatorService {
    Operator create(Operator operator);
    void deleteOperatorById(String id);

    Operator getOperatorById(String id);
}
