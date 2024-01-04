package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {


    private MemberService memberService;
    @Autowired //스프링에서 클래스에 대한 종속성을 자동으로 연결해줌.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/member/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/member/new")
    public String create(MemberForm form){
        Member member=new Member();
        member.setName((form.getName()));

        try {
            memberService.join(member);
        }catch(IllegalStateException e){
            System.out.println("이미 존재");
        }
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
