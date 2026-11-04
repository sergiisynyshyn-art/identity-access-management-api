package com.serhii.iambackend.domain.ports;

import com.serhii.iambackend.domain.model.Role;

import java.util.Optional;

public interface RoleRepositoryPort {

    Optional<Role> findByName(String name);

    Role save(Role role);

}