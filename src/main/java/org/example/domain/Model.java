package org.example.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

/**
 * Represents an entity in the domain model.
 */
@MappedSuperclass
public abstract class Model
{
  @Column(name = "id")
  @Generated(GenerationTime.INSERT)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  /**
   * Gets the unique identifier for this entity instance.
   *
   * @return The unique identifier for this entity instance.
   */
  public Long getID()
  {
    return this.id;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object o)
  {
    if (o == null)
    {
      // Cannot compare with a null reference.
      return false;
    }

    if (this == o)
    {
      // References to the same object are equal.
      return true;
    }

    final Class<?> thatClass = o.getClass();
    final Class<?> thisClass = this.getClass();
    if (!thisClass.equals(thatClass) // Both instances being compared are of the same type.
        && !thisClass.getSuperclass().equals(thatClass)
        // This instance is a proxied instance but its actual type is the same as that of the other instance.
        && !thisClass.equals(thatClass.getSuperclass())
        // The other instance is a proxied instance but its actual type is the same as that of this instance.
        && !thisClass.getSuperclass().equals(thatClass.getSuperclass())
      // Both instances are proxied instances but have the same actual type.
        )
    {
      // Cannot compare incompatible objects.
      return false;
    }

    final Model that = (Model) o;

    // Objects of the same type and with the same identifier are equal.
    return (this.id != null) && (that.id != null) && this.id.equals(that.id);
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    return this.id != null ? this.id.hashCode() : super.hashCode();
  }
}
