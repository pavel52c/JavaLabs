package example.services.ApplicationService;

import example.services.Student.StudentService;
import example.services.Subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ApplicationService implements IApplicationService {
    @Autowired
    StudentService studentService;
    @Autowired
    SubjectService subjectService;

    @Override
    public void initCreditModel(Model model) {
        initStudentAndSubject(model);
        String[] marks = new String[]{"зач", "незач"};
        model.addAttribute("marks", marks);
        model.addAttribute("action", "/saveCreditConfirm");
    }
    @Override
    public void initExamModel(Model model) {
        initStudentAndSubject(model);
        String[] marks = new String[]{"отл", "хор", "удв", "неуд"};
        model.addAttribute("marks", marks);
        model.addAttribute("action", "/saveExamConfirm");
    }

    private void initStudentAndSubject(Model model) {
        model.addAttribute("students", studentService.studentFindAll());
        model.addAttribute("subjects", subjectService.subjectFindAll());
    }
}
