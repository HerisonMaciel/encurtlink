package com.example.encurtlink.repository;

import com.example.encurtlink.entity.EncurtadorGerado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncurtadorRepository extends MongoRepository<EncurtadorGerado, String> {

}
