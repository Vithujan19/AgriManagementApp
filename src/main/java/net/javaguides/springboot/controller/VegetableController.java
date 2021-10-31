package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Vegetable;
import net.javaguides.springboot.service.VegetableService;

@Controller
public class VegetableController {

    @Autowired
    private VegetableService vegetableService;

    // display list of employees
    @GetMapping("/showVegetableList")
    public String viewHomePage(Model model) {
        model.addAttribute("listVegetables", vegetableService.getAllVegetables());
        return "display_vegetable";
    }

    @GetMapping("/showNewVegetableForm")
    public String showNewVegetableForm(Model model) {
        // create model attribute to bind form data
        Vegetable vegetable = new Vegetable();
        model.addAttribute("vegetable", vegetable);
        return "new_vegetable";
    }

    @PostMapping("/saveVegetable")
    public String saveVegetable(@ModelAttribute("vegetable") Vegetable vegetable) {
        // save employee to database
        vegetableService.saveVegetable(vegetable);
        return "redirect:/";
    }

    @GetMapping("/showFormVegForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get employee from the service
        Vegetable vegetable = vegetableService.getVegetableById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("vegetable", vegetable);
        return "update_vegetable";
    }

    @GetMapping("/deleteVegetable/{id}")
    public String deleteVegetable(@PathVariable (value = "id") long id) {

        // call delete employee method
        this.vegetableService.deleteVegetableById(id);
        return "redirect:/";
    }


//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model) {
//        int pageSize = 5;
//
//        Page<Vegetable> page = vegetableService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List<Vegetable> listVegetables = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("listVegetables", listVegetables);
//        return "index";
//    }
}
