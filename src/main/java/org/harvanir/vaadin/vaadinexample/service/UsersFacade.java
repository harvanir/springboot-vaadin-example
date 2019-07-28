package org.harvanir.vaadin.vaadinexample.service;

import java.util.List;
import org.harvanir.vaadin.vaadinexample.entity.dto.UsersRequestDto;
import org.harvanir.vaadin.vaadinexample.entity.dto.UsersResponseDto;

/**
 * @author Harvan Irsyadi
 */
public interface UsersFacade {

  UsersResponseDto save(UsersRequestDto usersCreateDto);

  void delete(UsersRequestDto usersRequestDto);

  List<UsersResponseDto> findAll();

  List<UsersResponseDto> findByLastName(String filterText);

  UsersRequestDto findById(Integer id);
}