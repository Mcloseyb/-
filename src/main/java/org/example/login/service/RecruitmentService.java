package org.example.login.service;

import org.example.login.repository.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecruitmentService {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    public Map<String, Object> callRecruitmentProcedure(String username, String keyword, int boxNumber) {
        return recruitmentRepository.callRecruitmentProcedure(username, keyword, boxNumber);
    }
}
