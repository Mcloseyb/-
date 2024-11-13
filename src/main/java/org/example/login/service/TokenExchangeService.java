package org.example.login.service;

import org.example.login.entity.OperatorTokenDTO;
import org.example.login.entity.UserOperator;
import org.example.login.entity.UserOperatorLevel;
import org.example.login.entity.UserMaterials;
import org.example.login.repository.UserOperatorsRepository;
import org.example.login.repository.UserOperatorLevelsRepository;
import org.example.login.repository.UserMaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TokenExchangeService {

    @Autowired
    private UserOperatorsRepository userOperatorsRepository;

    @Autowired
    private UserOperatorLevelsRepository userOperatorLevelsRepository;

    @Autowired
    private UserMaterialsRepository userMaterialsRepository;

    public List<OperatorTokenDTO> getYellowTokens(String username) {
        return userOperatorsRepository.findByUsername(username).stream()
                .filter(op -> {
                    UserOperatorLevel level = userOperatorLevelsRepository.findByUsernameAndOperatorName(username, op.getOperatorName()).get(0);
                    return op.getOperator().getRarity() >= 5 && level.getPotential() == 6;
                })
                .map(op -> new OperatorTokenDTO(op.getOperatorName(), op.getOperatorToken(), op.getOperator().getRarity()))
                .collect(Collectors.toList());
    }

    public List<OperatorTokenDTO> getGreenTokens(String username) {
        return userOperatorsRepository.findByUsername(username).stream()
                .filter(op -> {
                    UserOperatorLevel level = userOperatorLevelsRepository.findByUsernameAndOperatorName(username, op.getOperatorName()).get(0);
                    return op.getOperator().getRarity() <= 4 && level.getPotential() == 6;
                })
                .map(op -> new OperatorTokenDTO(op.getOperatorName(), op.getOperatorToken(), op.getOperator().getRarity()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Map<String, Object> exchangeToken(String username, String type) {
        Map<String, Object> response = new HashMap<>();
        UserMaterials userMaterials = userMaterialsRepository.findById(username).orElse(null);
        if (userMaterials == null) {
            response.put("success", false);
            return response;
        }

        List<OperatorTokenDTO> operatorTokens = type.equals("yellow") ? getYellowTokens(username) : getGreenTokens(username);
        int totalTokens = 0;
        for (OperatorTokenDTO tokenDTO : operatorTokens) {
            int tokens = tokenDTO.getQuantity();
            totalTokens += tokens * (type.equals("yellow") ? (tokenDTO.getRarity() == 6 ? 10 : 5) :
                    (tokenDTO.getRarity() == 4 ? 10 : 5));
            UserOperator userOperator = userOperatorsRepository.findByUsernameAndOperatorName(username, tokenDTO.getOperatorName());
            userOperator.setOperatorToken(0);
            userOperatorsRepository.save(userOperator);
        }

        if (type.equals("yellow")) {
            userMaterials.setYellowTicket(userMaterials.getYellowTicket() + totalTokens);
        } else {
            userMaterials.setGreenTicket(userMaterials.getGreenTicket() + totalTokens);
        }

        userMaterialsRepository.save(userMaterials);
        response.put("success", true);
        response.put("quantity", totalTokens);
        return response;
    }
}
