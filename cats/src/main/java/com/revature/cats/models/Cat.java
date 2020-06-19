package com.revature.cats.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Two JPA annotations to specify this class is mapped to a table
// Entity, which just marks this as a class Hibernate will manage, and Table which specifies the
// table
@Entity
@Table(schema = "cat", name = "cats")
public class Cat {

  // Each field needs at least one annotation to map it to a column in the DB
  // The primary key MUST be marked with @Id
  @Id
  @Column(name = "cat_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY) // this uses postgres for id generation
  private Integer catId;
  @Column(name = "name")
  private String name;
  @Column(name = "cuteness")
  private Double cuteness;

  // Multiplicity using Hibernate doesn't work the same way it does in our DB. Instead of specifying
  // the id of
  // an associated class in our Cat class, we specify the actual linked Object as a field.
  // Our Cats wont have an id of their owners, instead they'll have a reference to actual owner
  // objects
  // For multiplicty relationships, we use @JoinColumn and an annotation that specifies the
  // multiplicity
  @JoinColumn(name = "owner")
  @ManyToOne(fetch = FetchType.EAGER) // fetchtype eager means hibernate will automatically retrieve
                                      // the owner information for the db
  // whenever we retrieve the cat information from the db.
  // Use JsonIgnoreProperties here to specify which properties to ignore on the User represented by
  // owner.
  @JsonIgnoreProperties({"cats"})
  private User owner;

  // A no-args constructor is NECESSARY for Jackson to work!
  public Cat() {
    super();
  }

  public Cat(Integer catId, String name, Double cuteness) {
    super();
    this.catId = catId;
    this.name = name;
    this.cuteness = cuteness;
  }

  public Integer getCatId() {
    return catId;
  }

  public void setCatId(Integer catId) {
    this.catId = catId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getCuteness() {
    return cuteness;
  }

  public void setCuteness(Double cuteness) {
    this.cuteness = cuteness;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  @Override
  public String toString() {
    return "Cat [catId=" + catId + ", name=" + name + ", cuteness=" + cuteness + "]";
  }

}
