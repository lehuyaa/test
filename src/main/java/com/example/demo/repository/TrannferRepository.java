package com.example.demo.repository;

import com.example.demo.model.TransferPhone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrannferRepository   extends JpaRepository<TransferPhone, Long> {
}
