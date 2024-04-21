package example.controllers.entities;

import example.entities.Subject;
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
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @RequestMapping("/subjects")
    public String showSubjects(Model model) {
        model.addAttribute("subjects", subjectService.subjectFindAll());
        model.addAttribute("subject", new Subject());
        return "views/subjects";
    }


    @PostMapping("/editSubject")
    public String editSubject(@ModelAttribute("subject") Subject subjectModel, Model model) {
        Subject subject = subjectService.subjectFindById(subjectModel.getId());
        model.addAttribute("subject", subject);
        model.addAttribute("title", "Редактировать предмет:");
        model.addAttribute("action", "/saveSubjectConfirm");
        return "/views/subjectEdit";
    }

    @PostMapping("/addSubject")
    public String addSubject(Model model) {
        model.addAttribute("subject", new Subject());
        model.addAttribute("title", "Добавить предмет");
        model.addAttribute("action", "/saveSubjectConfirm");
        return "views/subjectEdit";
    }

    @PostMapping("saveSubjectConfirm")
    public String confirmStudent(@ModelAttribute("subject") @Valid Subject subjectForm, BindingResult bindingResult, Model model) {
        subjectService.subjectSave(subjectForm);
        return "redirect:/subjects";
    }

    @PostMapping("/deleteSubjects")
    public String deleteSubject(@ModelAttribute("subject") Subject subject) {
        subjectService.subjectDeleteById(subject.getId());
        return "redirect:/subjects";
    }
}
