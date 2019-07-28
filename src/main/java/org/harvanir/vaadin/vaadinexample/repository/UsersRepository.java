package org.harvanir.vaadin.vaadinexample.repository;

import java.util.List;
import org.harvanir.vaadin.vaadinexample.entity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Harvan Irsyadi
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

  List<Users> findByLastNameStartsWithIgnoreCase(String filter);
}