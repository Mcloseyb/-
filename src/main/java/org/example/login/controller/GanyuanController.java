package org.example.login.controller;

import org.example.login.entity.Ganyuan;
import org.example.login.entity.GanyuanImage;
import org.example.login.service.GanyuanService;
import org.example.login.service.GanyuanImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class GanyuanController {

    @Autowired
    private GanyuanService ganyuanService;

    @Autowired
    private GanyuanImageService ganyuanImageService;

    @GetMapping("/add-ganyuan")
    public String showAddGanyuanForm(Model model) {
        model.addAttribute("ganyuan", new Ganyuan());
        return "add-ganyuan";
    }

    @PostMapping("/add-ganyuan")
    public @ResponseBody String addGanyuan(@ModelAttribute Ganyuan ganyuan,
                                           @RequestParam("normalImage") MultipartFile normalImageFile,
                                           @RequestParam("eliteImage") MultipartFile eliteImageFile) {
        ganyuanService.saveGanyuan(ganyuan);

        GanyuanImage ganyuanImage = new GanyuanImage();
        ganyuanImage.setGanyuanName(ganyuan.getGanyuanName());
        ganyuanImage.setGanyuan(ganyuan);

        try {
            ganyuanImage.setNormalImage(normalImageFile.getBytes());
            if (!eliteImageFile.isEmpty()) {
                ganyuanImage.setEliteImage(eliteImageFile.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }

        ganyuanImageService.saveGanyuanImage(ganyuanImage);

        return "上传成功";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "文件大小超过限制，请上传较小的文件！");
        return "redirect:/add-ganyuan";
    }
}
