package com.example.demo.repository;

import com.example.demo.model.AddMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddMoneyRepository extends JpaRepository<AddMoney, Long> {
}
