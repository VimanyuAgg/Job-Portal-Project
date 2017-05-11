package edu.cmpe275.termproject.dao;

import org.springframework.data.repository.CrudRepository;

import edu.cmpe275.termproject.model.User;

public interface UserDAO extends CrudRepository<User,String> {

}
