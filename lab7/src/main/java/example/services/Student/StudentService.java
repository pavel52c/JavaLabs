package example.services.Student;

import example.dao.entitiesDao.StudentDao;
import example.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    StudentDao studentDao;

    @Override
    public List<Student> studentFindAll() {
        return studentDao.findAll();
    }

    @Override
    public Student studentFindById(Long id) {
        return studentDao.findById(id);
    }

    @Transactional
    @Override
    public boolean studentSave(Student obj) {
        if (obj.getId() == null) {
            generateNumberOfRecordBook(obj);
        } else {
            Student studentFromDB = studentDao.getStudentNumber(obj);
            if (studentFromDB != null) {
                long idFromDB = studentFromDB.getId();
                long idFromForm = obj.getId();
                if (idFromDB != idFromForm) {
                    return false;
                } else {
                    studentFromDB.setStudentName(obj.getStudentName());
                    studentFromDB.setStudentSurname(obj.getStudentSurname());
                    studentFromDB.setNumberOfRecordBook(obj.getNumberOfRecordBook());
                    studentDao.save(studentFromDB);
                    return true;
                }
            }
        }
        studentDao.save(obj);
        return true;
    }

    private void generateNumberOfRecordBook(Student obj) {
        Long lastNumber = studentDao.getLastNumber();
        if (lastNumber != null) {
            obj.setNumberOfRecordBook(lastNumber + 1);
        } else {
            obj.setNumberOfRecordBook(10000000L);
        }
    }


    @Transactional
    @Override
    public void studentDeleteById(Long id) {
        studentDao.deleteById(id);
    }

    public Long studentGetLast() {
        return studentDao.getLastNumber();
    }
}
