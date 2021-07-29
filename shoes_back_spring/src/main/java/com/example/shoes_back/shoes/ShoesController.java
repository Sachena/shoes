package com.example.shoes_back.shoes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ShoesController {

    private final ShoesRepository shoesRepository;


    @GetMapping("/shoes/{shoesName}")
    public String viewShoes(@PathVariable String shoesName, Model model){
        System.out.println(shoesName);
        model.addAttribute(shoesName);

        return "shoes/viewShoes";
    }


}
