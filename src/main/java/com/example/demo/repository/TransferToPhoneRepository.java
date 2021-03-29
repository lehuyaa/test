package com.example.demo.repository;


import com.example.demo.model.TransferToPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferToPhoneRepository extends JpaRepository<TransferToPhone, Long> {
}
