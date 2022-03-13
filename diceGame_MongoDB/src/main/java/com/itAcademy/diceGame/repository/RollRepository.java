package com.itAcademy.diceGame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.itAcademy.diceGame.entity.Roll;

@Repository
public interface RollRepository extends MongoRepository<Roll, String> {

}
