package example.services.Subject;

import example.entities.Subject;
import org.springframework.ui.Model;

import java.util.List;

public interface ISubjectService {
    public List<Subject> subjectFindAll();

    public Subject subjectFindById(Long id);

    public void subjectSave(Subject obj);

    public void subjectDeleteById(Long id);
}
