package com.revature.cats.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.cats.exceptions.UserNotFoundException;
import com.revature.cats.models.User;
import com.revature.cats.repositories.UserRepository;

// Its good practice to make Interfaces for your services, but not strictly necessary. Just less
// modular
@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new UserNotFoundException();
    }
  }

  public User create(User user) {
    user.setUserId(0);
    return userRepository.save(user);
  }

  public User update(User user) {
    Optional<User> existingUser = userRepository.findById(user.getUserId());
    if (existingUser.isPresent()) {
      return userRepository.save(user);
    } else {
      throw new UserNotFoundException();
    }
  }

  public Boolean checkCredentials(String username, String password) {
    // we just check if this username and password exist in the db
    return userRepository.checkUsernamePassword(username, password).size() > 0;
  }

  public User createOrUpdate(User user) {
    return userRepository.save(user);
  }

  public void delete(Integer id) {
    userRepository.deleteById(id);
  }

}
