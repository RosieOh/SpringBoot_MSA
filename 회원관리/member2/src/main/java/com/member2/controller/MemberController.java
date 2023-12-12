package com.member2.controller;

import com.member2.domain.Member;
import com.member2.dto.MemberJoinDTO;
import com.member2.repository.MemberRepository;
import com.member2.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/join")
    public String joinGET(Model model){
        log.info("join get...");
        model.addAttribute("memberJoinDTO", new MemberJoinDTO());
        return "member/join";
    }

    @PostMapping("/join")
    public String joinPOST(@Valid MemberJoinDTO memberJoinDTO, BindingResult bindingResult, Model model){
        log.info("join post...");
        log.info(memberJoinDTO);

        String mid = memberJoinDTO.getMid();
        boolean exist = memberRepository.existsById(mid);
        if (exist) {
            bindingResult
                    .rejectValue("mid", "error.mid","사용이 불가한 아이디입니다.");
        }

        String email = memberJoinDTO.getEmail();
        Member existEmail = memberService.existByEmail(email);
        System.out.println(existEmail);
        if(existEmail!=null){
            bindingResult
                    .rejectValue("email", "error.email", "사용이 불가한 이메일입니다.");
        }



        if(bindingResult.hasErrors()){
          model.addAttribute("memberJoinDTO", memberJoinDTO);

            System.out.println("끝??");
            return "member/join";
        }

        try {
            memberService.join(memberJoinDTO);
            System.out.println("여기>>");
        } catch (MemberService.MidExistException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("memberJoinDTO", memberJoinDTO);
            System.out.println("gw:"+ e.getMessage());
            return "member/join";
        }

        return "redirect:/";

    }

    @GetMapping("/login")
    public String loginGet(){
        log.info("/login get ...........");
        return "member/login";
    }

}