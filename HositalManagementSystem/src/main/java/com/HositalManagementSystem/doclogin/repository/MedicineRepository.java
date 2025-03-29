package com.HositalManagementSystem.doclogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HositalManagementSystem.doclogin.Entity.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {


	

}
