package com.damoralesr97.vacunas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damoralesr97.vacunas.entity.Dosis;

public interface DosisRepository extends JpaRepository<Dosis, Integer> {

}
