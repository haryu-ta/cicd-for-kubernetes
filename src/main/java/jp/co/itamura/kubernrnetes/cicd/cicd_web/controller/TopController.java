package jp.co.itamura.kubernrnetes.cicd.cicd_web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopController {
    @GetMapping("/")
    public String sayJapanese(){
        return "こんにちは";
    }

    @GetMapping("/hello")
    public String sayEnglish(){
        return "Hello";

    }

}
