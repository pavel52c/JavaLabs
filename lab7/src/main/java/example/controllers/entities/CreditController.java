package example.controllers.entities;

import example.entities.Credit;
import example.entities.Student;
import example.entities.Subject;
import example.services.ApplicationService.ApplicationService;
import example.services.Credit.CreditService;
import example.services.Student.StudentService;
import example.services.Subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class CreditController {
    @Autowired
    CreditService creditService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    StudentService studentService;

    @RequestMapping("/credits")
    public String showCredits(Model model) {
        model.addAttribute("credits", creditService.creditFindAll());
        model.addAttribute("credit", new Credit());
        model.addAttribute("title", "Зачеты");
        return "views/credits";
    }

    @PostMapping("/editCredit")
    public String editCredit(@ModelAttribute("credit") Credit creditModel, Model model) {
        Credit credit = creditService.creditFindById(creditModel.getId());
        model.addAttribute("credit", credit);
        model.addAttribute("title", "Редактировать зачет:");
        applicationService.initCreditModel(model);
        return "/views/creditEdit";
    }

    @PostMapping("/addCredit")
    public String addCredit(Model model) {
        model.addAttribute("credit", new Credit());
        model.addAttribute("title", "Добавить зачет");
        applicationService.initCreditModel(model);
        return "views/creditEdit";
    }

    @PostMapping("saveCreditConfirm")
    public String confirmCredit(@ModelAttribute("credit") @Valid Credit creditForm, BindingResult bindingResult, Model model) {
        Subject subject = subjectService.subjectFindById(creditForm.getSubject().getId());
        Student student = studentService.studentFindById(creditForm.getStudent().getId());
        creditForm.setStudent(student);
        creditForm.setSubject(subject);
        creditService.creditSave(creditForm);
        return "redirect:/credits";
    }

    @PostMapping("/deleteCredit")
    public String deleteCredit(@ModelAttribute("credit") Credit credit) {
        creditService.creditDeleteById(credit.getId());
        return "redirect:/credits";
    }
}
