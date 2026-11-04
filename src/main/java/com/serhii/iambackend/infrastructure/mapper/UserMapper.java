package com.serhii.iambackend.infrastructure.mapper;

import com.serhii.iambackend.domain.model.User;
import com.serhii.iambackend.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}