package com.sync.userapp.repository;

import com.sync.userapp.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {
}
