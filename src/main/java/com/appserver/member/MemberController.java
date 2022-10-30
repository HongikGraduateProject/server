package com.appserver.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        if (!form.getPassword().equals(form.getPasswordCheck())){
            return "members/createMemberForm";
        }

        Member member = Member.builder()
                .username(form.getUsername())
                .email(form.getEmail())
                .password(form.getPassword())
                .role(Role.USER)
                .build();

        try {
            memberService.join(member);
        } catch (IllegalStateException e) {
            result.addError(new FieldError("memberForm", "email", e.getMessage()));
            return "members/createMemberForm";
        }
        return "redirect:/main";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
