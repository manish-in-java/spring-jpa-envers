package org.example.data;

import org.example.domain.Person;
import org.example.domain.Registration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

/**
 * Unit tests for {@link PersonRepository}.
 */
@ContextConfiguration(locations = "classpath:springContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class PersonRepositoryTest
{
  private static final String[] registrations = { "Social Security Number", "Passport Number", "Driving License Number" };

  @Autowired
  PersonRepository       personRepository;
  @Autowired
  RegistrationRepository registrationRepository;

  /**
   * Sets up test data.
   */
  @Before
  public void before()
  {
    for (final String registration : registrations)
    {
      Registration reg = this.registrationRepository.findByName(registration);
      if (reg == null)
      {
        reg = new Registration(registration);
        this.registrationRepository.save(reg);
      }
    }

    final String name = this.getName();
    final Person person = new Person(name);

    for (final String registration : registrations)
    {
      person.addRegistration(this.registrationRepository.findByName(registration), name);
    }

    this.personRepository.save(person);
  }

  /**
   * Tests that auditing works on updates.
   */
  @Test
  public void testUpdate()
  {
    final Person person = this.personRepository.findAll().iterator().next();

    Assert.assertNotNull(person);

    for (int i = 1; i <= this.getInt(); ++i)
    {
      final String name = this.getName();

      for (final String registration : registrations)
      {
        person.addRegistration(this.registrationRepository.findByName(registration), name);
      }

      this.personRepository.save(person);
    }
  }

  /**
   * Gets a random integer from 1 to 3.
   *
   * @return A random integer from 1 to 3.
   */
  private int getInt()
  {
    return 1 + new Random().nextInt(3);
  }

  /**
   * Gets a random string to use as a name for running tests.
   *
   * @return A random string to use as a name for running tests.
   */
  private String getName()
  {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
