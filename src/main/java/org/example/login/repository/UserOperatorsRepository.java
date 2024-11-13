package org.example.login.repository;

import org.example.login.entity.UserOperator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOperatorsRepository extends JpaRepository<UserOperator, String> {
    List<UserOperator> findByUsername(String username);


    UserOperator findByUsernameAndOperatorName(String username, String operatorName);
}
