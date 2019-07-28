package org.harvanir.vaadin.vaadinexample.service;

import java.util.List;
import org.harvanir.vaadin.vaadinexample.entity.dto.UsersRequestDto;
import org.harvanir.vaadin.vaadinexample.entity.dto.UsersResponseDto;
import org.harvanir.vaadin.vaadinexample.entity.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Harvan Irsyadi
 */
@Mapper(componentModel = "spring")
public interface DtoBeanMapper {

  Users toModel(UsersRequestDto usersRequestDto);

  UsersResponseDto toResponseDto(Users users);

  UsersRequestDto toUpdateDto(Users users);

  List<UsersResponseDto> toResponseDtoList(List<Users> all);

  @Mapping(target = "id", ignore = true)
  void copyForUpdate(@MappingTarget Users users, UsersRequestDto dto);
}