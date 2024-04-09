package at.ac.htlstp.et.adresstadenbank.controller;

import at.ac.htlstp.et.adresstadenbank.dto.FormDto;
import at.ac.htlstp.et.adresstadenbank.dto.LandDto;
import at.ac.htlstp.et.adresstadenbank.service.DataService;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {

    @Autowired
    DataService dataService;

    @RequestMapping("/")
    public String rootPath(Model model) {
        model.addAttribute("land", new LandDto(0,"","",""));

        try {
            model.addAttribute("tables", dataService.tables());
        }catch (Exception ex){
            model.addAttribute("msg","Fehler beim DB-Zugriff!");
            return "error";
        }
        return "welcome";
    }


    @PostMapping("/form")
    public String formular(
            @Valid @ModelAttribute("land") LandDto land,
            Model model
    ) {
        System.out.println(land.toString());
        dataService.speicherLand(land);

        return "redirect";
    }

    // localhost:8080/delete?id=17

    @GetMapping("/delete")
    public String delete(
            Model model,
            @RequestParam int id
    ) {

        dataService.loescheLand(id);

        return "redirect";
    }




}
