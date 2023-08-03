package com.alfath.warnet.service;
import com.alfath.warnet.entity.Role;
import com.alfath.warnet.entity.constant.ERole;

public interface RoleService {
    Role getOrSave(ERole role);
}
