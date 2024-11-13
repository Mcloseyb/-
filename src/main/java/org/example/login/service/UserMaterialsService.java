package org.example.login.service;

import org.example.login.entity.UserMaterials;
import org.example.login.repository.UserMaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserMaterialsService {

    @Autowired
    private UserMaterialsRepository userMaterialsRepository;

    public UserMaterials getUserMaterials(String username) {
        return userMaterialsRepository.findByUsername(username);
    }

    @Transactional
    public void deductMaterials(String username, int recruitmentTicket, int dragonGateCoin) {
        UserMaterials userMaterials = userMaterialsRepository.findByUsername(username);
        if (userMaterials != null) {
            userMaterials.setPublicRecruitmentTicket(userMaterials.getPublicRecruitmentTicket() - recruitmentTicket);
            userMaterials.setDragonGateCoin(userMaterials.getDragonGateCoin() - dragonGateCoin);
            userMaterialsRepository.save(userMaterials);
        }
    }
}
