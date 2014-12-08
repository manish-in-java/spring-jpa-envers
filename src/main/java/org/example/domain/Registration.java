package org.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents a statutory registration.
 */
@Entity
@Table(name = "registration")
public class Registration extends Model
{
  @Column(name = "name")
  @NotNull
  private String name;

  /**
   * Default constructor.
   */
  Registration()
  {
  }

  /**
   * Sets the registration name.
   *
   * @param name The registration name.
   */
  public Registration(String name)
  {
    this.name = name;
  }

  /**
   * Gets the registration name.
   *
   * @return The registration name.
   */
  public String getName()
  {
    return this.name;
  }
}
