package ru.homework.hometask07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.dao.RoleRepository;
import ru.homework.hometask07.dao.entity.RoleEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    public Map<Integer, String> getAllRolesAsMap() {
        return roleRepository.findAll().stream().collect(Collectors.toMap(RoleEntity::getId, RoleEntity::getTitle));
    }

    public RoleEntity getRolesByID(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    public RoleEntity createRole(RoleEntity body) {
        return roleRepository.save(body);
    }

    public RoleEntity updateRole(Integer id, RoleEntity body) {
        body.setId(id);
        return roleRepository.save(body);
    }

    public void deleteRoleByID(Integer id) {
        roleRepository.deleteById(id);
    }

}
