package ru.mirea.pkmn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.pkmn.entity.StudentEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    Optional<StudentEntity> findByFirstNameAndFamilyNameAndSurName(String firstName, String familyName, String surName);
}