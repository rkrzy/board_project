package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("members/signup")
    public String newSignUpForm(){
        return "members/new";
    }

    @PostMapping("members/signup")
    public String join(MemberForm memberForm){
        Member member = memberForm.toEntity();

        memberRepository.save(member);
        return "redirect:/members/" + member.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){

        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);

        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){

        List<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberList);

        return "members/index";
    }
}
