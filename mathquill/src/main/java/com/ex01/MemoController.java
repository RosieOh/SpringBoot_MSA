package com.ex01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class MemoController {

    @GetMapping("/math")
    public String mathForm(){
        return "math";
    }
}
