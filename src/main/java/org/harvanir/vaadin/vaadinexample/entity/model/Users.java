package org.harvanir.vaadin.vaadinexample.entity.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Harvan Irsyadi
 */
@EntityListeners(AuditingEntityListener.class)
@Data
@Proxy(lazy = false)
@Entity
@Table(name = "users")
@DynamicUpdate
public class Users {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date")
  private Date createdDate;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_date")
  private Date updatedDate;

  @Version
  @Column(name = "version")
  private Long version;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Users users = (Users) o;
    return Objects.equals(id, users.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}