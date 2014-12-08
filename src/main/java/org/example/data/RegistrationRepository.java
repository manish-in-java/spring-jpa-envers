package org.example.data;

import org.example.domain.Registration;

/**
 * Contract for data access operations on {@link Registration}s.
 */
public interface RegistrationRepository extends ModelRepository<Registration>
{
  /**
   * Finds a registration type by name.
   *
   * @param name The registration type name to find.
   * @return A {@link Registration}.
   */
  Registration findByName(String name);
}
