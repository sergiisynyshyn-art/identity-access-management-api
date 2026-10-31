package infrastructure.persistence.adapter;

import domain.model.User;
import domain.ports.UserRepositoryPort;
import infrastructure.mapper.UserMapper;
import infrastructure.persistence.entity.UserEntity;
import infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

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