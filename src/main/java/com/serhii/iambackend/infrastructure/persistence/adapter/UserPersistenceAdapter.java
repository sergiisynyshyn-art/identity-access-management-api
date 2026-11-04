package com.serhii.iambackend.infrastructure.persistence.adapter;

import com.serhii.iambackend.domain.model.User;
import com.serhii.iambackend.domain.ports.UserRepositoryPort;
import com.serhii.iambackend.infrastructure.mapper.UserMapper;
import com.serhii.iambackend.infrastructure.persistence.entity.UserEntity;
import com.serhii.iambackend.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public UserPersistenceAdapter(
            UserJpaRepository userJpaRepository,
            UserMapper userMapper) {

        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {

        UserEntity entity = userMapper.toEntity(user);

        UserEntity savedEntity =
                userJpaRepository.save(entity);

        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {

        return userJpaRepository
                .findById(id)
                .map(userMapper::toDomain);
    }


    @Override
    public Optional<User> findByEmail(String email) {

        return userJpaRepository
                .findByEmail(email)
                .map(userMapper::toDomain);
    }


    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
}