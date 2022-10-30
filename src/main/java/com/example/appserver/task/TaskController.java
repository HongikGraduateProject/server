package com.example.appserver.task;

import com.example.appserver.controller.SessionConst;
import com.example.appserver.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks/new")
    public String createForm(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        return "tasks/createTaskForm";
    }

    @PostMapping("/tasks/new")
    public String create(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, @Valid TaskForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "tasks/createTaskForm";
        }
        taskService.save(loginMember.getId(), form.getContents());
        return "redirect:/";
    }

    @GetMapping("/tasks/{id}")
    public String list(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, @PathVariable Long id, Model model) {
        List<Tasks> tasklist = taskService.findMemberTasks(loginMember.getId());
        model.addAttribute("tasks", tasklist);
        return "/tasks/taskList";
    }
}
