package com.revature.cats.repositories;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.revature.cats.models.Cat;

// To have Spring Data JPA write CRUD methods for us, just extend JpaRepository
@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
  // we now have CRUD methods

  // This should find cats by their name in our db
  // We can write methods that follow Spring Data JPA's conventions
  Cat findByName(String name);

  // We can also write methods and annotate them with @Query, which lets us write queries to
  // implement a method
  // This uses HQL, which is Hibernate Query Language. Its like SQL except it refers to objects

  // This is a findAll that sorts by catId:
  @Query("select c from Cat c order by c.catId")
  List<Cat> findAllSorted();

  // Retrieve all cats with names in a Set:
  // equivalent SQL: SELECT * FROM cat.cats WHERE name in <set>
  // if you want your query to be native instead of HQL, set native=true in the annotation
  @Query("select c from Cat c where c.name in :catNames")
  List<Cat> findCatsWithNamesInSet(Set<String> catNames);
}
