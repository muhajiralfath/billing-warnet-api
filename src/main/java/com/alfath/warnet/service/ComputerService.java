package com.alfath.warnet.service;

import com.alfath.warnet.entity.Computer;
import com.alfath.warnet.model.request.ComputerRequest;
import com.alfath.warnet.model.response.ComputerResponse;
import org.springframework.data.domain.Page;

public interface ComputerService {
    Computer createPc(ComputerRequest computer);
    void deleteCompById(String id);
    Page<ComputerResponse> getAllComp(Integer page, Integer size);
    ComputerResponse updateComputer(Computer computer);
    Computer getCompById(String id);
}
