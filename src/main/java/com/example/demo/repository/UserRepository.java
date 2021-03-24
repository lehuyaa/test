package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User  findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<User> findById(int id);

//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE users u SET u.moneyNumber = :moneyNumber WHERE u.id = :userID")
//    int updateMoney(@Param("userID") Long  userID, @Param("moneyNumber") Long  moneyNumber);
}