package org.example.login.service;

import org.example.login.entity.UserOperator;
import org.example.login.entity.UserOperatorLevel;
import org.example.login.repository.UserOperatorLevelsRepository;
import org.example.login.entity.UserMaterials;
import org.example.login.repository.UserMaterialsRepository;
import org.example.login.repository.UserOperatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserOperatorsService {

    @Autowired
    private UserOperatorsRepository userOperatorsRepository;

    @Autowired
    private UserOperatorLevelsRepository userOperatorLevelsRepository;

    @Autowired
    private UserMaterialsRepository userMaterialsRepository;

    public List<UserOperator> findUserOperatorsByUsername(String username) {
        return userOperatorsRepository.findByUsername(username);
    }

    public List<UserOperatorLevel> findUserOperatorLevelsByUsernameAndOperatorName(String username, String operatorName) {
        return userOperatorLevelsRepository.findByUsernameAndOperatorName(username, operatorName);
    }

    public int getOperatorToken(String username, String operatorName) {
        UserOperator userOperator = userOperatorsRepository.findByUsernameAndOperatorName(username, operatorName);
        return userOperator.getOperatorToken();
    }

    @Transactional
    public boolean upgradeLevel(String username, String operatorName, int newLevel) {
        List<UserOperatorLevel> operatorLevels = userOperatorLevelsRepository.findByUsernameAndOperatorName(username, operatorName);
        if (operatorLevels.isEmpty()) {
            return false; // No operator level found
        }

        UserOperatorLevel operatorLevel = operatorLevels.get(0);
        UserMaterials userMaterials = userMaterialsRepository.findById(username).orElse(null);

        if (operatorLevel == null || userMaterials == null) {
            return false;
        }

        int coinsRequired = newLevel * 100;
        int recordsRequired = newLevel * 10;

        if (userMaterials.getDragonGateCoin() < coinsRequired || userMaterials.getBattleRecord() < recordsRequired) {
            return false;
        }

        userMaterials.setDragonGateCoin(userMaterials.getDragonGateCoin() - coinsRequired);
        userMaterials.setBattleRecord(userMaterials.getBattleRecord() - recordsRequired);
        userMaterialsRepository.save(userMaterials);

        operatorLevel.setLevel(newLevel);
        userOperatorLevelsRepository.save(operatorLevel);

        return true;
    }

    @Transactional
    public boolean increasePotential(String username, String operatorName) {
        UserOperator userOperator = userOperatorsRepository.findByUsernameAndOperatorName(username, operatorName);
        if (userOperator == null || userOperator.getOperatorToken() <= 0) {
            return false; // No operator token found or no tokens left
        }

        List<UserOperatorLevel> operatorLevels = userOperatorLevelsRepository.findByUsernameAndOperatorName(username, operatorName);
        if (operatorLevels.isEmpty()) {
            return false; // No operator level found
        }

        UserOperatorLevel operatorLevel = operatorLevels.get(0);
        if (operatorLevel.getPotential() >= 6) {
            return false; // Max potential reached
        }

        userOperator.setOperatorToken(userOperator.getOperatorToken() - 1);
        userOperatorsRepository.save(userOperator);

        operatorLevel.setPotential(operatorLevel.getPotential() + 1);
        userOperatorLevelsRepository.save(operatorLevel);

        return true;
    }

    @Transactional
    public boolean breakthrough(String username, String operatorName, int eliteLevel) {
        List<UserOperatorLevel> operatorLevels = userOperatorLevelsRepository.findByUsernameAndOperatorName(username, operatorName);
        if (operatorLevels.isEmpty()) {
            return false; // No operator level found
        }

        UserOperatorLevel operatorLevel = operatorLevels.get(0);
        UserMaterials userMaterials = userMaterialsRepository.findById(username).orElse(null);

        if (operatorLevel == null || userMaterials == null) {
            return false;
        }

        if (userMaterials.getDragonGateCoin() < 50) {
            return false;
        }

        userMaterials.setDragonGateCoin(userMaterials.getDragonGateCoin() - 50);
        userMaterialsRepository.save(userMaterials);

        operatorLevel.setEliteLevel(eliteLevel + 1);
        operatorLevel.setLevel(1);
        userOperatorLevelsRepository.save(operatorLevel);

        return true;
    }

}
