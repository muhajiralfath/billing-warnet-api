package com.alfath.warnet.service.impl;

import com.alfath.warnet.entity.Computer;
import com.alfath.warnet.model.ComputerResponse;
import com.alfath.warnet.repository.ComputerRepository;
import com.alfath.warnet.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService {
    private final ComputerRepository computerRepository;
    @Override
    public Computer createPc(Computer computer) {
        try {
            return computerRepository.save(computer);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "computer already exist");
        }
    }

    @Override
    public void deleteCompById(String id) {
            getCompById(id);
            computerRepository.deleteComputerById(id);
    }

    @Override
    public Page<ComputerResponse> getAllComp(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Computer> computers = computerRepository.findAllComputersWithPagination(pageable);

        List<Computer> computerList = new ArrayList<>(computers.getContent());
        List<ComputerResponse> computerResponseList = computerList.stream().map(computer -> ComputerResponse.builder()
                .computerName(computer.getName())
                .pricePerHour(computer.getPrice())
                .build()).collect(Collectors.toList());

        return new PageImpl<>(computerResponseList, pageable, computers.getTotalElements());
    }

    @Override
    public ComputerResponse updateComputer(Computer computer) {
        try {
            Computer compById = getCompById(computer.getId());
            if (compById != null){
                computerRepository.updateComputer(computer.getId(), computer.getName(), computer.getPrice());
                return ComputerResponse.builder()
                        .computerName(computer.getName())
                        .pricePerHour(computer.getPrice())
                        .build();
            }
            return null;
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Computer getCompById(String id) {
        return computerRepository.findComputerById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found"));
    }
}
