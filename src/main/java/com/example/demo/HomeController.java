package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    DutyRepository dutyRepository;

    @RequestMapping("/")
    public String listDuty(Model model){
        model.addAttribute("duties", dutyRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String dutyForm(Model model){

        model.addAttribute("duty", new Duty());
        return "listform";

    }

    @PostMapping("/process")
    public String processForm(@Valid Duty duty, BindingResult result) {

        if (result.hasErrors()) {

            return "listform";
        }

        dutyRepository.save(duty);
        return "redirect:/";
    }

@RequestMapping("/detail/{id}")
    public String showDuty(@PathVariable("id") long id, Model model){
        model.addAttribute("duty",dutyRepository.findById(id).get());
        return "show";

}

    @RequestMapping("/update/{id}")
    public String updateDuty(@PathVariable("id") long id, Model model){
        model.addAttribute("duty",dutyRepository.findById(id));
        return "listform";

    }




    @RequestMapping("/delete/{id}")
    public String delDuty(@PathVariable("id") long id, Model model){

        dutyRepository.deleteById(id);
        return "redirect:/";

    }

}
