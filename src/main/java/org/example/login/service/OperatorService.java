package org.example.login.service;

import org.example.login.entity.Operator;
import org.example.login.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class OperatorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getOperatorImage(String operatorName) {
        String sql = "SELECT 干员姓名, 普通立绘, 精二立绘 FROM 干员图像信息 WHERE 干员姓名 = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{operatorName}, (ResultSet rs, int rowNum) -> {
            Map<String, Object> result = new HashMap<>();
            result.put("operatorName", rs.getString("干员姓名"));
            byte[] normalImageBytes = rs.getBytes("普通立绘");
            byte[] eliteImageBytes = rs.getBytes("精二立绘");
            if (normalImageBytes != null) {
                result.put("normalImage", Base64.getEncoder().encodeToString(normalImageBytes));
            }
            if (eliteImageBytes != null) {
                result.put("eliteImage", Base64.getEncoder().encodeToString(eliteImageBytes));
            }
            return result;
        });
    }
    @Autowired
    private OperatorRepository operatorRepository;

    public Operator getOperatorByName(String operatorName) {
        return operatorRepository.findByOperatorName(operatorName);
    }
}
