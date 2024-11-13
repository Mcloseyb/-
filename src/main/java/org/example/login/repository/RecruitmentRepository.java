package org.example.login.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RecruitmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> callRecruitmentProcedure(String username, String keyword, int boxNumber) {
        // 调用存储过程
        jdbcTemplate.update("EXEC InsertRecruitmentResult @p_username = ?, @p_keyword = ?, @p_recruit_table_number = ?",
                username, keyword, boxNumber);

        // 获取招募结果
        return getRecruitmentResult(username, boxNumber);
    }

    public Map<String, Object> getRecruitmentResult(String username, int boxNumber) {
        // 查询招募结果，确保只返回一条最新的记录
        String sql = "SELECT TOP 1 干员名, 干员图像信息.普通立绘 AS operatorImage " +
                "FROM 公招结果 " +
                "JOIN 干员图像信息 ON 公招结果.干员名 = 干员图像信息.干员姓名 " +
                "WHERE 公招结果.用户名 = ? AND 公招结果.公招号 = ? " +
                "ORDER BY 公招时间 DESC";
        return jdbcTemplate.queryForObject(sql, new Object[]{username, boxNumber}, (rs, rowNum) -> {
            Map<String, Object> result = new HashMap<>();
            result.put("operatorName", rs.getString("干员名"));
            result.put("operatorImage", rs.getBytes("operatorImage"));
            return result;
        });
    }
}
