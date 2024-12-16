package ru.mirea.pkmn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.pkmn.entity.CardEntity;
import ru.mirea.pkmn.entity.StudentEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    Optional<CardEntity> findByName(String name);
    Optional<CardEntity> findByPokemonOwnerId(UUID ownerId);
}