package org.example.login.service;

import org.example.login.entity.UserShop;
import org.example.login.entity.UserMaterials;
import org.example.login.repository.UserShopRepository;
import org.example.login.repository.UserMaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserShopService {

    @Autowired
    private UserShopRepository userShopRepository;

    @Autowired
    private UserMaterialsRepository userMaterialsRepository;

    public UserShop getUserShop(String username) {
        return userShopRepository.findById(username).orElse(null);
    }

    public UserMaterials getUserMaterials(String username) {
        return userMaterialsRepository.findById(username).orElse(null);
    }

    @Transactional
    public boolean purchaseMaterial(String username, String materialType, String ticketType, int ticketQuantity, int resultQuantity) {
        UserShop userShop = userShopRepository.findById(username).orElse(null);
        UserMaterials userMaterials = userMaterialsRepository.findById(username).orElse(null);

        if (userShop == null || userMaterials == null) {
            return false;
        }

        // Check and update user's tickets
        if (ticketType.equals("yellow")) {
            if (userMaterials.getYellowTicket() < ticketQuantity) {
                return false;
            }
            userMaterials.setYellowTicket(userMaterials.getYellowTicket() - ticketQuantity);
        } else if (ticketType.equals("green")) {
            if (userMaterials.getGreenTicket() < ticketQuantity) {
                return false;
            }
            userMaterials.setGreenTicket(userMaterials.getGreenTicket() - ticketQuantity);
        }

        // Update user's materials
        switch (materialType) {
            case "合成玉":
                userMaterials.setSyntheticJade(userMaterials.getSyntheticJade() + resultQuantity);
                break;
            case "龙门币":
                userMaterials.setDragonGateCoin(userMaterials.getDragonGateCoin() + resultQuantity);
                break;
            case "作战录像":
                userMaterials.setBattleRecord(userMaterials.getBattleRecord() + resultQuantity);
                break;
            case "公招券":
                userMaterials.setPublicRecruitmentTicket(userMaterials.getPublicRecruitmentTicket() + resultQuantity);
                break;
        }

        // Update shop's materials
        switch (materialType) {
            case "合成玉":
                if (userShop.getSyntheticJade() < resultQuantity) {
                    return false;
                }
                userShop.setSyntheticJade(userShop.getSyntheticJade() - resultQuantity);
                break;
            case "龙门币":
                if (userShop.getDragonGateCoin() < resultQuantity) {
                    return false;
                }
                userShop.setDragonGateCoin(userShop.getDragonGateCoin() - resultQuantity);
                break;
            case "作战录像":
                if (userShop.getBattleRecord() < resultQuantity) {
                    return false;
                }
                userShop.setBattleRecord(userShop.getBattleRecord() - resultQuantity);
                break;
            case "公招券":
                if (userShop.getPublicRecruitmentTicket() < resultQuantity) {
                    return false;
                }
                userShop.setPublicRecruitmentTicket(userShop.getPublicRecruitmentTicket() - resultQuantity);
                break;
        }

        userMaterialsRepository.save(userMaterials);
        userShopRepository.save(userShop);

        return true;
    }
}
