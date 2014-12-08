package org.example.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a person.
 */
@Entity
@Table(name = "person")
public class Person extends Model
{
  @Column(name = "name")
  @NotNull
  private String name;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person", orphanRemoval = true)
  private List<PersonRegistration> registrations;

  /**
   * Default constructor.
   */
  Person()
  {
  }

  /**
   * Sets the name.
   *
   * @param name The person name.
   */
  public Person(String name)
  {
    this.name = name;
  }

  /**
   * Adds a registration to the person.
   *
   * @param registration The registration to add.
   * @param value        The registration value.
   */
  public void addRegistration(Registration registration, String value)
  {
    if (registration != null)
    {
      if (this.registrations == null)
      {
        this.registrations = new ArrayList<>();
      }

      PersonRegistration personRegistration = null;
      for (final PersonRegistration mapping : this.registrations)
      {
        if ((mapping != null) && mapping.getRegistration().equals(registration))
        {
          personRegistration = mapping;
          break;
        }
      }

      if (personRegistration == null)
      {
        personRegistration = new PersonRegistration(this, registration, value);
        this.registrations.add(personRegistration);
      }
      else
      {
        personRegistration.setValue(value);
      }
    }
  }

  /**
   * Gets the person name.
   *
   * @return The person name.
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * Gets the registrations applicable to the person.
   *
   * @return The registrations applicable to the person.
   */
  public List<PersonRegistration> getRegistrations()
  {
    return this.registrations;
  }
}
