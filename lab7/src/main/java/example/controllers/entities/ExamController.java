package example.controllers.entities;

import example.entities.Exam;
import example.entities.Student;
import example.entities.Subject;
import example.services.ApplicationService.ApplicationService;
import example.services.Exam.ExamService;
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
public class ExamController {
    @Autowired
    ExamService examService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    StudentService studentService;
    @Autowired
    ApplicationService applicationService;

    @RequestMapping("/exams")
    public String showExams(Model model) {
        model.addAttribute("credits", examService.examFindAll());
        model.addAttribute("credit", new Exam());
        model.addAttribute("title", "Экзамены");
        return "views/credits";
    }

    @PostMapping("/editExam")
    public String editExam(@ModelAttribute("exam") Exam examModel, Model model) {
        Exam exam = examService.examFindById(examModel.getId());
        model.addAttribute("credit", exam);
        model.addAttribute("title", "Редактировать экзамен:");
        applicationService.initExamModel(model);
        return "/views/creditEdit";
    }

    @PostMapping("/addExam")
    public String addExam(Model model) {
        model.addAttribute("credit", new Exam());
        model.addAttribute("title", "Добавить экзамен");
        applicationService.initExamModel(model);
        return "views/creditEdit";
    }

    @PostMapping("saveExamConfirm")
    public String confirmExam(@ModelAttribute("credit") @Valid Exam examForm, BindingResult bindingResult, Model model) {
        Subject subject = subjectService.subjectFindById(examForm.getSubject().getId());
        Student student = studentService.studentFindById(examForm.getStudent().getId());
        examForm.setStudent(student);
        examForm.setSubject(subject);
        examService.examSave(examForm);
        return "redirect:/exams";
    }

    @PostMapping("/deleteExam")
    public String deleteExam(@ModelAttribute("credit") Exam exam) {
        examService.examDeleteById(exam.getId());
        return "redirect:/exams";
    }


}
