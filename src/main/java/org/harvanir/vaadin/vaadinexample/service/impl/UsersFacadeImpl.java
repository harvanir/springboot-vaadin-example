package org.harvanir.vaadin.vaadinexample.service.impl;

import java.util.List;
import org.harvanir.vaadin.vaadinexample.entity.model.Users;
import org.harvanir.vaadin.vaadinexample.entity.dto.UsersRequestDto;
import org.harvanir.vaadin.vaadinexample.entity.dto.UsersResponseDto;
import org.harvanir.vaadin.vaadinexample.repository.UsersRepository;
import org.harvanir.vaadin.vaadinexample.service.DtoBeanMapper;
import org.harvanir.vaadin.vaadinexample.service.UsersFacade;
import org.harvanir.vaadin.vaadinexample.service.VersioningUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Harvan Irsyadi
 */
@Service
public class UsersFacadeImpl implements UsersFacade {

  private UsersRepository usersRepository;

  private DtoBeanMapper mapper;

  @Autowired
  public void setUsersRepository(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Autowired
  public void setMapper(DtoBeanMapper mapper) {
    this.mapper = mapper;
  }

  @Transactional
  @Override
  public UsersResponseDto save(UsersRequestDto dto) {
    if (dto.getId() == null) {
      return mapper.toResponseDto(usersRepository.save(mapper.toModel(dto)));
    }
    Users users = usersRepository.getOne(dto.getId());
    VersioningUtils.checkVersion(dto.getVersion(), users.getVersion(), users, users.getId());
    mapper.copyForUpdate(users, dto);

    return mapper.toResponseDto(usersRepository.save(users));
  }

  @Override
  public void delete(UsersRequestDto usersRequestDto) {
    usersRepository.delete(mapper.toModel(usersRequestDto));
  }

  @Override
  public List<UsersResponseDto> findAll() {
    return mapper.toResponseDtoList(usersRepository.findAll());
  }

  @Override
  public List<UsersResponseDto> findByLastName(String filterText) {
    return mapper.toResponseDtoList(usersRepository.findByLastNameStartsWithIgnoreCase(filterText));
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  @Override
  public UsersRequestDto findById(Integer id) {
    return mapper.toUpdateDto(usersRepository.getOne(id));
  }
}