package org.example.domain;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents a mapping between a person and a registration.
 */
@Audited
@Entity
@Table(name = "person_registration")
public class PersonRegistration extends Model
{
  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
  @JoinColumn(name = "person_id", updatable = false)
  @ManyToOne
  @NotNull
  private Person person;

  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
  @JoinColumn(name = "registration_id", updatable = false)
  @ManyToOne
  @NotNull
  private Registration registration;

  @Column(name = "value")
  @NotNull
  private String value;

  /**
   * Default constructor.
   */
  PersonRegistration()
  {
  }

  /**
   * Sets the person and registration to map.
   *
   * @param person       A {@link Person}.
   * @param registration A {@link Registration}.
   * @param value        The registration value.
   */
  PersonRegistration(Person person, Registration registration, String value)
  {
    this.person = person;
    this.registration = registration;
    this.value = value;
  }

  /**
   * Gets the mapped person.
   *
   * @return An {@link Person}.
   */
  public Person getPerson()
  {
    return this.person;
  }

  /**
   * Gets the mapped registration.
   *
   * @return A {@link Registration}.
   */
  public Registration getRegistration()
  {
    return this.registration;
  }

  /**
   * Gets the registration value.
   *
   * @return The registration value.
   */
  public String getValue()
  {
    return this.value;
  }

  /**
   * Sets the registration value.
   *
   * @param value The registration value.
   */
  public void setValue(String value)
  {
    this.value = value;
  }
}
