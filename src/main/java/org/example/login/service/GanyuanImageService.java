package org.example.login.service;

import org.example.login.entity.GanyuanImage;
import org.example.login.repository.GanyuanRepository;
import org.example.login.repository.GanyuanImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GanyuanImageService {

    @Autowired
    private GanyuanImageRepository ganyuanImageRepository;

    public void saveGanyuanImage(org.example.login.entity.GanyuanImage ganyuanImage) {
        ganyuanImageRepository.save(ganyuanImage);
    }
}
