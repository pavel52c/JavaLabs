package example.services.Exam;

import example.entities.Exam;

import java.util.List;

public interface IExamService {
    public List<Exam> examFindAll();

    public Exam examFindById(Long id);

    public void examSave(Exam obj);

    public void examDeleteById(Long id);
}
