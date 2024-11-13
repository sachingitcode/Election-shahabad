package com.example.demo.Controller;

import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepo;
import com.example.demo.service.CountryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CountryController {
    private static final Logger logger = LogManager.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @Autowired
    CountryRepo countryRepo;

    @GetMapping("/start")
    public String getCountries(Model model) {
        List<String> countries = countryService.getAllTehsil();
        model.addAttribute("countries", countries);
        return "countries";
    }

    @GetMapping("/block/{tehsilName}")
    public String getStates(@PathVariable String tehsilName, Model model) {
        List<String> a = countryService.getBlockByTehsilName(tehsilName);
        model.addAttribute("states", a);
        return "states";
    }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        List<String> a = countryService.getBlockByTehsilName();
        model.addAttribute("block", a);
        return "add-employee";
    }


    @GetMapping("/details/{block}")
    public String getCities(@PathVariable String block, Model model) {
        List<Country> b = countryService.getDetailsGroupByBlock(block);
        model.addAttribute("cities", b);
        logger.info("Model  + {}",model);
        return "cities";
    }


    @GetMapping("/addEmployee")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Country());
        List<String> a = countryService.getBlockByTehsilName();
        model.addAttribute("block", a);
        return "add-employee";
    }


    @GetMapping("/editEmployee/{id}")
    public String showEditEmployeeForm(@PathVariable("id") Long id, Model model) {
        logger.info("id ", id);
        Country employee = countryRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        List<String> a = countryService.getBlockByTehsilName();
        model.addAttribute("block", a);
        logger.info("Model details {} ", employee);
        return "add-employee";
    }


    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute Country employee) {
        System.out.println("emmp: " + employee);
        employee.setTehsil("Shahbad");
        countryService.save(employee);
        return "redirect:/start";
    }


    @PostMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") Long id, Model model) {
        Country employee = countryRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        var block = employee.getBlock();
        countryRepo.delete(employee);
        List<Country> b = countryService.getDetailsGroupByBlock(block);
        model.addAttribute("cities", b);
        logger.info("Model  + {}",model);
        return "cities";
    }

}