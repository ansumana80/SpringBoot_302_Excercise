package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController
{
    @Autowired
    TODORepository TODORepository;

    @RequestMapping("/")
    public String listform(Model model)
    {
        model.addAttribute("todos",TODORepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String todoform(Model model)
    {
        model.addAttribute("todo", new TODO());
        return "todoform";
    }

    @PostMapping("/process")
    public String processTvForm(@Valid TODO todo,
                                BindingResult result)
    {
        if(result.hasErrors())
        {
            return "todoform";
        }
        TODORepository.save(todo);
        return "redirect:/";
    }
}
