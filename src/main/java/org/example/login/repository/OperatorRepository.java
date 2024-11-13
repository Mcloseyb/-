package org.example.login.repository;

import org.example.login.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepository extends JpaRepository<Operator, String> {
    Operator findByOperatorName(String operatorName);
}
