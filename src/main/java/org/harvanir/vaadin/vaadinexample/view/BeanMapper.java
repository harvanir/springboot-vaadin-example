package org.harvanir.vaadin.vaadinexample.view;

import org.harvanir.vaadin.vaadinexample.entity.dto.UsersRequestDto;
import org.harvanir.vaadin.vaadinexample.entity.dto.UsersResponseDto;
import org.mapstruct.Mapper;

/**
 * @author Harvan Irsyadi
 */
@Mapper(componentModel = "spring")
public interface BeanMapper {

  UsersRequestDto toUpdateDto(UsersResponseDto responseDto);
}
