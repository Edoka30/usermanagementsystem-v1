package com.UserMgtSystem.UserMgtSystem.Repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.UserMgtSystem.UserMgtSystem.Entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

public	User findByUserId(String userid);

public User findByEmail(String email);

public List<User> findByCountryId(int cid);

public Page<User> findAll(Pageable pageable);

//public com.UserMgtSystem.UserMgtSystem.ServiceImpl.Page<User> findByCountryId(Integer countryId, Pageable pageable);

public Page<User> findByCountryId(Integer countryId, Pageable pageable);

}
                                                                                                      