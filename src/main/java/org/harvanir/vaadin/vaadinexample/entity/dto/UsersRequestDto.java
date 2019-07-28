package org.harvanir.vaadin.vaadinexample.entity.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Harvan Irsyadi
 */
@Getter
@Setter
public class UsersRequestDto implements Serializable {

  private static final long serialVersionUID = -4274048269994633350L;

  private String firstName;

  private String lastName;

  private Integer id;

  private Long version;
}