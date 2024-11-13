package org.example.login.repository;

import org.example.login.entity.UserMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserMaterialsRepository extends JpaRepository<UserMaterials, String> {
    UserMaterials findByUsername(String username);

}
