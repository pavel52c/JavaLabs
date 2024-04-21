package example.services.Student;

import example.entities.Student;

import java.util.List;

public interface IStudentService {
    public List<Student> studentFindAll();

    public Student studentFindById(Long id);

    public boolean studentSave(Student obj);

    public void studentDeleteById(Long id);
}
