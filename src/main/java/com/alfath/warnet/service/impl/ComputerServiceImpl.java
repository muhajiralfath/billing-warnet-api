package com.alfath.warnet.service.impl;

import com.alfath.warnet.entity.Computer;
import com.alfath.warnet.repository.ComputerRepository;
import com.alfath.warnet.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService {
    private final ComputerRepository computerRepository;
    @Override
    public Computer createPc(Computer computer) {
        try {

            return computerRepository.save(computer);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "category already exist");
        }
    }

    @Override
    public void deleteCompById(String id) {

    }

    @Override
    public List<Computer> getAllComp() {
        return null;
    }

    @Override
    public Computer updateComputer(Computer computer) {
        return null;
    }
}
