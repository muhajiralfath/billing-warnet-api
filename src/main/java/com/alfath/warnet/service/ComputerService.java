package com.alfath.warnet.service;

import com.alfath.warnet.entity.Computer;
import com.alfath.warnet.model.ComputerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComputerService {
    Computer createPc(Computer computer);
    void deleteCompById(String id);
    Page<ComputerResponse> getAllComp(Integer page, Integer size);
    ComputerResponse updateComputer(Computer computer);
    Computer getCompById(String id);
}
