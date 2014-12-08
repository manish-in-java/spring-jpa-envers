package org.example.domain;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents an audit revision.
 */
@Entity
@RevisionEntity
@Table(name = "revision")
public class Revision
{
  @Column(name = "revision")
  @Id
  @GeneratedValue
  @RevisionNumber
  private int revision;

  @Column(name = "revision_timestamp")
  @RevisionTimestamp
  private Date timestamp;
}
