package com.alfath.warnet.service.impl;

import com.alfath.warnet.entity.Role;
import com.alfath.warnet.entity.constant.ERole;
import com.alfath.warnet.repository.RoleRepository;
import com.alfath.warnet.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findByRoleName(role.name()).orElseGet(() -> roleRepository.save(Role.builder().role(role).build()));

    }
}
