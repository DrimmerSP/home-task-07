package ru.homework.hometask07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.dao.RoleRepository;
import ru.homework.hometask07.dao.entity.RoleEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    public RoleEntity getRoleByID(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public RoleEntity createRole(RoleEntity body) {
        return roleRepository.save(body);
    }

    public RoleEntity updateRole(Long id, RoleEntity body) {
        body.setId(id);
        return roleRepository.save(body);
    }

    public void deleteRoleByID(Long id) {
        roleRepository.deleteById(id);
    }

}
