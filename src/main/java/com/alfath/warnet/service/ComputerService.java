package com.alfath.warnet.service;

import com.alfath.warnet.entity.Computer;

import java.util.List;

public interface ComputerService {
    Computer createPc(Computer computer);
    void deleteCompById(String id);
    List<Computer> getAllComp();
    Computer updateComputer(Computer computer);
}
