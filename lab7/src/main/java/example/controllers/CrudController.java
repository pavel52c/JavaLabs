package example.controllers;

import example.entities.Credit;
import example.entities.Exam;
import example.entities.Student;
import example.entities.Subject;
import example.services.ApplicationServiceImpl;
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
public class CrudController {

    @Autowired
    ApplicationServiceImpl service;

    @Autowired
    SubjectService subjectService;

//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    Student
//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @RequestMapping("/students")
    public String showStudents(Model model) {
        model.addAttribute("students", service.studentFindAll());
        model.addAttribute("student", new Student());
        return "views/students";
    }

    @PostMapping("/editStudent")
    public String editStudent(@ModelAttribute("student") Student studentModel, Model model) {
        Student student = service.studentFindById(studentModel.getId());
        model.addAttribute("student", student);
        model.addAttribute("title", "Редактировать студента:");
        model.addAttribute("action", "/saveStudentConfirm");
        return "/views/studentEdit";
    }

    @PostMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("title", "Добавить студента");
        model.addAttribute("action", "/saveStudentConfirm");
        return "views/studentEdit";
    }

    @PostMapping("saveStudentConfirm")
    public String confirmStudent(@ModelAttribute("student") @Valid Student studentForm, BindingResult bindingResult, Model model) {
        if (service.studentSave(studentForm)) {
            return "redirect:/students";
        } else {
            model.addAttribute("title", "Редактировать студента:");
            model.addAttribute("error", "Вы не можете использовать номер " + studentForm.getNumberOfRecordBook());
            model.addAttribute("student", service.studentFindById(studentForm.getId()));
            return "views/studentEdit";
        }
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(@ModelAttribute("student") Student student) {
        service.studentDeleteById(student.getId());
        return "redirect:/students";
    }

//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    Subject
//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//    @RequestMapping("/subjects")
//    public String showSubjects(Model model) {
//        model.addAttribute("subjects", subjectService.subjectFindAll());
//        model.addAttribute("subject", new Subject());
//        return "views/subjects";
//    }
//
//
//    @PostMapping("/editSubject")
//    public String editSubject(@ModelAttribute("subject") Subject subjectModel, Model model) {
//        Subject subject = subjectService.subjectFindById(subjectModel.getId());
//        model.addAttribute("subject", subject);
//        model.addAttribute("title", "Редактировать предмет:");
//        model.addAttribute("action", "/saveSubjectConfirm");
//        return "/views/subjectEdit";
//    }
//
//    @PostMapping("/addSubject")
//    public String addSubject(Model model) {
//        model.addAttribute("subject", new Subject());
//        model.addAttribute("title", "Добавить предмет");
//        model.addAttribute("action", "/saveSubjectConfirm");
//        return "views/subjectEdit";
//    }
//
//    @PostMapping("saveSubjectConfirm")
//    public String confirmStudent(@ModelAttribute("subject") @Valid Subject subjectForm, BindingResult bindingResult, Model model) {
//        subjectService.subjectSave(subjectForm);
//        return "redirect:/subjects";
//    }
//
//    @PostMapping("/deleteSubjects")
//    public String deleteSubject(@ModelAttribute("subject") Subject subject) {
//        subjectService.subjectDeleteById(subject.getId());
//        return "redirect:/subjects";
//    }
//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    Exam
//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @RequestMapping("/exams")
    public String showExams(Model model) {
        model.addAttribute("credits", service.examFindAll());
        model.addAttribute("credit", new Exam());
        model.addAttribute("title", "Экзамены");
        return "views/credits";
    }

    @PostMapping("/editExam")
    public String editExam(@ModelAttribute("exam") Exam examModel, Model model) {
        Exam exam = service.examFindById(examModel.getId());
        model.addAttribute("credit", exam);
        model.addAttribute("title", "Редактировать экзамен:");
        initExamModel(model);
        return "/views/creditEdit";
    }

    @PostMapping("/addExam")
    public String addExam(Model model) {
        model.addAttribute("credit", new Exam());
        model.addAttribute("title", "Добавить экзамен");
        initExamModel(model);
        return "views/creditEdit";
    }

    @PostMapping("saveExamConfirm")
    public String confirmExam(@ModelAttribute("credit") @Valid Exam examForm, BindingResult bindingResult, Model model) {
        Subject subject = subjectService.subjectFindById(examForm.getSubject().getId());
        Student student = service.studentFindById(examForm.getStudent().getId());
        examForm.setStudent(student);
        examForm.setSubject(subject);
        service.examSave(examForm);
        return "redirect:/exams";
    }

    @PostMapping("/deleteExam")
    public String deleteExam(@ModelAttribute("credit") Exam exam) {
        service.examDeleteById(exam.getId());
        return "redirect:/exams";
    }

//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    Credit
//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @RequestMapping("/credits")
    public String showCredits(Model model) {
        model.addAttribute("credits", service.creditFindAll());
        model.addAttribute("credit", new Credit());
        model.addAttribute("title", "Зачеты");
        return "views/credits";
    }

    @PostMapping("/editCredit")
    public String editCredit(@ModelAttribute("credit") Credit creditModel, Model model) {
        Credit credit = service.creditFindById(creditModel.getId());
        model.addAttribute("credit", credit);
        model.addAttribute("title", "Редактировать зачет:");
        initCreditModel(model);
        return "/views/creditEdit";
    }

    @PostMapping("/addCredit")
    public String addCredit(Model model) {
        model.addAttribute("credit", new Credit());
        model.addAttribute("title", "Добавить зачет");
        initCreditModel(model);
        return "views/creditEdit";
    }

    @PostMapping("saveCreditConfirm")
    public String confirmCredit(@ModelAttribute("credit") @Valid Credit creditForm, BindingResult bindingResult, Model model) {
        Subject subject = subjectService.subjectFindById(creditForm.getSubject().getId());
        Student student = service.studentFindById(creditForm.getStudent().getId());
        creditForm.setStudent(student);
        creditForm.setSubject(subject);
        service.creditSave(creditForm);
        return "redirect:/credits";
    }

    @PostMapping("/deleteCredit")
    public String deleteCredit(@ModelAttribute("credit") Credit credit) {
        service.creditDeleteById(credit.getId());
        return "redirect:/credits";
    }

//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void initCreditModel(Model model) {
        initStudentAndSubject(model);
        String[] marks = new String[]{"зач", "незач"};
        model.addAttribute("marks", marks);
        model.addAttribute("action", "/saveCreditConfirm");
    }

    private void initExamModel(Model model) {
        initStudentAndSubject(model);
        String[] marks = new String[]{"отл", "хор", "удв", "неуд"};
        model.addAttribute("marks", marks);
        model.addAttribute("action", "/saveExamConfirm");
    }

    private void initStudentAndSubject(Model model) {
        model.addAttribute("students", service.studentFindAll());
        model.addAttribute("subjects", subjectService.subjectFindAll());
    }
}
