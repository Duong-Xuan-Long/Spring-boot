package com.example.person.controller;

import com.example.person.model.Person;
import com.example.person.repository.PersonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonMethods personMethods;

    @GetMapping(value="/1")
    public String getAll(Model model){
    model.addAttribute("persons",personMethods.getAll());
            return "allpersons";
    }
    @GetMapping(value="/2")
    public String sortPeopleByFullName(Model model) {
        model.addAttribute("persons",personMethods.sortPeopleByFullName(personMethods.getAll()));
        return "allpersons";
    }
    @GetMapping(value="/3")
    public String getSortedJobs(Model model) {
        model.addAttribute("persons",personMethods.getSortedJobs(personMethods.getAll()));
        return "allpersons";
    }

    @GetMapping(value="/4")
    public String getSortedCities(Model model) {
        model.addAttribute("persons",personMethods.getSortedCities(personMethods.getAll()));
        return "allpersons";
    }

    @GetMapping(value="/5")
    @ResponseBody
    public HashMap groupPeopleByCity(Model model) {
        //model.addAttribute("persons",personMethods.groupPeopleByCity(personMethods.getAll()));
        //"allpersons"
        return personMethods.groupPeopleByCity(personMethods.getAll());
    }

    @GetMapping(value="/6")
    @ResponseBody
    public HashMap<String, Integer> groupJobByCount(Model model) {
//        model.addAttribute("persons",personMethods.groupJobByCount(personMethods.getAll()));
//        "allpersons"
        return personMethods.groupJobByCount(personMethods.getAll()) ;
    }

    @GetMapping(value="/7")
    @ResponseBody
    public Map findTop5Jobs(Model model) {
//        model.addAttribute("persons",personMethods.findTop5Jobs(personMethods.getAll()));
//        "allpersons"
        return personMethods.findTop5Jobs(personMethods.getAll());
    }

    @GetMapping(value="/8")
    @ResponseBody
    public Map findTop5Cities(Model model) {
//        model.addAttribute("persons",personMethods.findTop5Cities(personMethods.getAll()));
//        "allpersons"
        return personMethods.findTop5Cities(personMethods.getAll());
    }

    @GetMapping(value="/9")
    @ResponseBody
    public HashMap findTopJobInCity(Model model) {
//        model.addAttribute("persons",personMethods.findTopJobInCity(personMethods.getAll(),"Zbrosławice"));
////        "allpersons"
        return personMethods.findTopJobInCity(personMethods.getAll(),"Zbrosławice");
    }

    @GetMapping(value="/10")
    @ResponseBody
    public List find5CitiesHaveMostSpecificJob(Model model) {
//        model.addAttribute("persons",personMethods.find5CitiesHaveMostSpecificJob("Nurse",personMethods.getAll()));
//        "allpersons"
        return personMethods.find5CitiesHaveMostSpecificJob("Nurse",personMethods.getAll());
    }
}
