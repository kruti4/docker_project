package com.revature.cats.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.cats.models.Cat;
import com.revature.cats.models.User;
import com.revature.cats.repositories.CatRepository;
import com.revature.cats.repositories.UserRepository;

@RestController
public class TestController {

  @Autowired
  CatRepository catRepository;
  @Autowired
  UserRepository userRepository;

  // To get the underlying Session from Spring, autowire in an EntityManager
  @Autowired
  EntityManager em;

  @GetMapping("/testrepo")
  public Object testRepoResult() {
    return catRepository.count();
  }

  @GetMapping("/hbsession")
  public String hbSessionDemo() {
    String out = "";
    Session session = em.unwrap(Session.class);
    // begin DB transaction, good thing to start with.
    session.beginTransaction();

    // get Cat with id 1
    Cat fluffy = session.get(Cat.class, 1);

    // modify the persistent object retrieved, this will cause DB update on commit
    fluffy.setCuteness(55.55);

    // session.get is one option, it gets eagerly and returns null if not found
    // session.load is another, it gets lazily:
    Cat lazyfluffy = session.load(Cat.class, 1);

    out += lazyfluffy.toString();

    // We can add new Cat objects to the DB with save and persist.
    // save takes place immediately, persist makes the object persistent (it will update eventually,
    // but its part of the session cache as normal)

    Cat newCat = new Cat(null, "newhbcat II", 33.3);

    User user = session.get(User.class, 2);

    newCat.setOwner(user);

    session.save(newCat);

    // Some fancier ways to retrieve data from the DB: Criteria and Query
    // Note these are Hibernate 4 so you'll see them in the wild but they're deprecated in Hibernate
    // 5

    // Query is for HQL queries, which uses a query language like SQL but that deals with Objects
    // Criteria is a programmatic, type-safe way of specifying queries to the DB.
    // You should know what these are, but in practice we'll use repository CRUD methods and HQL or
    // SQL queries.
    // HQL is a benefit over SQL because it works with any DB.

    Set<String> catNames = new HashSet<String>();
    catNames.add("fluffy");
    catNames.add("whiskers");
    catNames.add("kibo");

    // add Restictions. Specify Restrictions using methods on org.hibernate.criterion.Restrictions,
    // a utility class for this purpose
    Criteria c = session.createCriteria(Cat.class).add(Restrictions.in("name", catNames));

    // we can add more restrictions on the end, or add "projections" which aggregate the results.

    List<Cat> catsMeetingCriteria = c.list();
    out += catsMeetingCriteria.toString();

    // HQL query, parameters start with :
    Query<Cat> q = session.createQuery("from Cat where cuteness > :param");
    // q.list gets results
    // set the param to 100.0 to retrieve cats with cuteness over 100
    List<Cat> cuteCats = q.setDouble("param", 100.0).list();

    // Once we've used the query to retrieve data we want, manipulate it using Java and Hibernate
    // will handle saving it to the DB.
    // All cute cats get cuter
    for (Cat cat : cuteCats) {
      cat.setCuteness(cat.getCuteness() + 20);
    }

    out += cuteCats.toString();

    // commit DB transaction
    session.getTransaction().commit();

    return out;
  }

}
