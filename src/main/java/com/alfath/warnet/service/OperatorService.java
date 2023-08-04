package com.alfath.warnet.service;

import com.alfath.warnet.entity.Operator;
import com.alfath.warnet.model.request.OperatorRequest;

public interface OperatorService {
    Operator create(OperatorRequest operator);
    void deleteOperatorById(String id);

    Operator getOperatorById(String id);
}
