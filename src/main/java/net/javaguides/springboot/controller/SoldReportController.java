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

        import net.javaguides.springboot.model.SoldReport;
        import net.javaguides.springboot.service.SoldReportService;

@Controller
public class SoldReportController {

    @Autowired
    private SoldReportService soldReportService;

    // display list of employees
    @GetMapping("/showSoldReportList")
    public String viewHomePage(Model model) {
        model.addAttribute("listSoldReports", soldReportService.getAllSoldReport());
        return "display_soldReport";
    }

    @GetMapping("/showNewSoldReportForm")
    public String showNewSoldReportForm(Model model) {
        // create model attribute to bind form data
        SoldReport soldReport = new SoldReport();
        model.addAttribute("soldReport", soldReport);
        return "new_soldReport";
    }

    @PostMapping("/saveSoldReport")
    public String saveSoldReport(@ModelAttribute("soldReport") SoldReport soldReport) {
        // save employee to database
        soldReportService.saveSoldReport(soldReport);
        return "redirect:/";
    }

    @GetMapping("/showFormReportForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get employee from the service
        SoldReport soldReport = soldReportService.getSoldReportById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("soldReport", soldReport);
        return "update_soldReport";
    }

    @GetMapping("/deleteSoldReport/{id}")
    public String deleteSoldReport(@PathVariable (value = "id") long id) {

        // call delete employee method
        this.soldReportService.deleteSoldReportById(id);
        return "redirect:/";
    }
}
