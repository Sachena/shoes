package com.example.shoes_back.controller;

import com.example.shoes_back.UserResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
public class MainController {


    @GetMapping("/")
    public String main() {

        return "index";
    }

    @PostMapping(value = "/upload")
    public String uploadFile(@RequestPart MultipartFile file, Model model) throws Exception {

        byte[] imageByteArray = file.getBytes();
        StringBuilder sb = new StringBuilder();



        sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(imageByteArray)));

        URI uri = UriComponentsBuilder.fromUriString("http://127.0.0.1:5000")
                .path("/check").encode().build().toUri();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, sb, UserResponse.class);



        model.addAttribute("first_brand", response.getBody().getFirst_brand());
        model.addAttribute("first_image", "/images/"+response.getBody().getFirst_brand()+".jpg");
        model.addAttribute("second_brand", response.getBody().getSecond_brand());
        model.addAttribute("second_image", "/images/"+response.getBody().getSecond_brand()+".jpg");
        model.addAttribute("third_brand", response.getBody().getThird_brand());
        model.addAttribute("third_image", "/images/"+response.getBody().getThird_brand()+".jpg");


        return "output";

    }

}
