package org.example.login.service;

import org.example.login.entity.User;
import org.example.login.entity.UserMaterials;
import org.example.login.entity.UserOperator;
import org.example.login.repository.UserMaterialsRepository;
import org.example.login.repository.UserOperatorsRepository;
import org.example.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMaterialsRepository userMaterialsRepository;

    @Autowired
    private UserOperatorsRepository userOperatorsRepository;

    @Autowired
    private OperatorService operatorService; // 添加 OperatorService

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public User registerUser(String username, String account, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.findByAccount(account) != null) {
            throw new IllegalArgumentException("Account already exists");
        }
        User user = new User(username, account, password);
        userRepository.save(user);

        // 初始化用户材料
        UserMaterials userMaterials = new UserMaterials();
        userMaterials.setUsername(username);
        userMaterials.setSyntheticJade(100);
        userMaterials.setDragonGateCoin(100);
        userMaterials.setBattleRecord(100);
        userMaterials.setAccelerationTicket(100);
        userMaterials.setYellowTicket(100);
        userMaterials.setGreenTicket(100);
        userMaterials.setPublicRecruitmentTicket(100);
        userMaterialsRepository.save(userMaterials);

        return user;
    }

    public UserMaterials getUserMaterials(String username) {
        return userMaterialsRepository.findByUsername(username);
    }

    // 获取用户干员及其图像
    public List<Map<String, Object>> getUserOperatorsWithImages(String username) {
        return userOperatorsRepository.findByUsername(username).stream()
                .map(userOperator -> {
                    Map<String, Object> operatorImage = operatorService.getOperatorImage(userOperator.getOperatorName());
                    operatorImage.put("operatorName", userOperator.getOperatorName());
                    return operatorImage;
                })
                .collect(Collectors.toList());
    }
}
