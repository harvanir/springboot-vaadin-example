package org.harvanir.vaadin.vaadinexample.entity.dto;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Harvan Irsyadi
 */
@Getter
@Setter
public class UsersResponseDto {

  private Integer id;

  private String firstName;

  private String lastName;

  private ZonedDateTime createdDate;

  private ZonedDateTime updatedDate;

  private Long version;
}