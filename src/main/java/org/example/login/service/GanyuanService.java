package org.example.login.service;

import org.example.login.entity.Ganyuan;
import org.example.login.repository.GanyuanRepository;
import org.example.login.repository.GanyuanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GanyuanService {

    @Autowired
    private GanyuanRepository ganyuanRepository;

    public void saveGanyuan(org.example.login.entity.Ganyuan ganyuan) {
        ganyuanRepository.save(ganyuan);
    }
}
