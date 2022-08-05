package com.example.Thymeleafdemo.Controller;

import com.example.Thymeleafdemo.Model.Person;
import com.example.Thymeleafdemo.repository.PersonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {
    @Autowired
    private PersonMethods personMethods;

    @GetMapping(value="/1")
    public String getALl(Model model){
        model.addAttribute("persons",personMethods.getAll());
        return "allpersons1";
    }
    @RequestMapping("/allpersons")
    public String allpersons(){
        return "allpersons1";
    }
//    @GetMapping(value="/2")
//    public String add(){
//        personMethods.getAll().add(personMethods.createPerson("ok","ok","ok"));
//
//    }
}
