package org.example.login.repository;

import org.example.login.entity.UserOperatorLevel;
import org.example.login.entity.UserOperatorLevelId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOperatorLevelsRepository extends JpaRepository<UserOperatorLevel, UserOperatorLevelId> {
    List<UserOperatorLevel> findByUsernameAndOperatorName(String username, String operatorName);

    //UserOperatorLevel findByUsernameAndOperatorName(String username ,String operatorName);
}
