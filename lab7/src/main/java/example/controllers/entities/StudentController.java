package example.controllers.entities;

import example.entities.Student;
import example.services.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/students")
    public String showStudents(Model model) {
        model.addAttribute("students", studentService.studentFindAll());
        model.addAttribute("student", new Student());
        return "views/students";
    }

    @PostMapping("/editStudent")
    public String editStudent(@ModelAttribute("student") Student studentModel, Model model) {
        Student student = studentService.studentFindById(studentModel.getId());
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
        if (studentService.studentSave(studentForm)) {
            return "redirect:/students";
        } else {
            model.addAttribute("title", "Редактировать студента:");
            model.addAttribute("error", "Вы не можете использовать номер " + studentForm.getNumberOfRecordBook());
            model.addAttribute("student", studentService.studentFindById(studentForm.getId()));
            return "views/studentEdit";
        }
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(@ModelAttribute("student") Student student) {
        studentService.studentDeleteById(student.getId());
        return "redirect:/students";
    }
}
