package org.example.login.service;

import org.example.login.entity.UserMaterials;
import org.example.login.repository.UserMaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class DrawCardService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMaterialsRepository userMaterialsRepository;

    @Transactional
    public void performSingleDraw(String username, String sixStar, String fiveStar1, String fiveStar2) {
        UserMaterials userMaterials = userMaterialsRepository.findByUsername(username);
        if (userMaterials.getSyntheticJade() < 1) {
            throw new IllegalArgumentException("Not enough Synthetic Jade for a single draw");
        }
        userMaterials.setSyntheticJade(userMaterials.getSyntheticJade() - 1);
        userMaterialsRepository.save(userMaterials);

        String sql = "EXEC drawCard @p_username = ?, @p_six_star = ?, @p_five_star1 = ?, @p_five_star2 = ?";
        jdbcTemplate.update(sql, username, sixStar, fiveStar1, fiveStar2);
    }

    @Transactional
    public void performTenDraws(String username, String sixStar, String fiveStar1, String fiveStar2) {
        for (int i = 0; i < 10; i++) {
            performSingleDraw(username, sixStar, fiveStar1, fiveStar2);
        }
    }

    public List<String> getSixStarOperators() {
        String sql = "SELECT 干员姓名 FROM six_op";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<String> getFiveStarOperators() {
        String sql = "SELECT 干员姓名 FROM five_op";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<Map<String, Object>> getLatestSingleDrawResult(String username) {
        String sql = "SELECT TOP 1 干员名 AS operatorName, 稀有度 AS rarity, 抽卡时间 AS drawTime, 普通立绘 AS image " +
                "FROM 抽卡结果 " +
                "JOIN 干员图像信息 ON 抽卡结果.干员名 = 干员图像信息.干员姓名 " +
                "WHERE 用户名 = ? " +
                "ORDER BY 抽卡时间 DESC";
        return jdbcTemplate.query(sql, new Object[]{username}, (ResultSet rs, int rowNum) -> {
            Map<String, Object> result = new HashMap<>();
            result.put("operatorName", rs.getString("operatorName"));
            result.put("rarity", rs.getInt("rarity"));
            result.put("drawTime", rs.getTimestamp("drawTime"));
            byte[] imageBytes = rs.getBytes("image");
            if (imageBytes != null) {
                result.put("image", Base64.getEncoder().encodeToString(imageBytes));
            } else {
                result.put("image", null);
            }
            return result;
        });
    }

    public List<Map<String, Object>> getLatestTenDrawResults(String username) {
        String sql = "SELECT TOP 10 干员名 AS operatorName, 稀有度 AS rarity, 抽卡时间 AS drawTime, 普通立绘 AS image " +
                "FROM 抽卡结果 " +
                "JOIN 干员图像信息 ON 抽卡结果.干员名 = 干员图像信息.干员姓名 " +
                "WHERE 用户名 = ? " +
                "ORDER BY 抽卡时间 DESC";
        return jdbcTemplate.query(sql, new Object[]{username}, (ResultSet rs, int rowNum) -> {
            Map<String, Object> result = new HashMap<>();
            result.put("operatorName", rs.getString("operatorName"));
            result.put("rarity", rs.getInt("rarity"));
            result.put("drawTime", rs.getTimestamp("drawTime"));
            byte[] imageBytes = rs.getBytes("image");
            if (imageBytes != null) {
                result.put("image", Base64.getEncoder().encodeToString(imageBytes));
            } else {
                result.put("image", null);
            }
            return result;
        });
    }
}
