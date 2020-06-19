package com.revature.cats.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(schema = "cat", name = "users")
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId;
  @Column(name = "username")
  private String username;
  // JPA uses merge when id is 0 rather than persist, so cascading merge will create cats
  @OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE)
  @JsonIgnoreProperties({"owner"}) // ignores the property owner on each cat
  private List<Cat> cats;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<Cat> getCats() {
    return cats;
  }

  public void setCats(List<Cat> cats) {
    this.cats = cats;
    // Make each cat in the incoming list of cats have this user as the owner
    for (Cat c : cats) {
      c.setOwner(this);
    }
  }

  @Override
  public String toString() {
    return "User [userId=" + userId + ", username=" + username + "]";
  }

}
