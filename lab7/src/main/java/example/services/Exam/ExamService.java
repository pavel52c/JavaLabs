package example.services.Exam;

import example.dao.entitiesDao.ExamDao;
import example.entities.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ExamService implements IExamService{
    @Autowired
    ExamDao examDao;
    @Transactional
    @Override
    public List<Exam> examFindAll() {
        return examDao.findAll();
    }

    @Transactional
    @Override
    public Exam examFindById(Long id) {
        return examDao.findById(id);
    }

    @Transactional
    @Override
    public void examSave(Exam obj) {
        examDao.save(obj);
    }

    @Transactional
    @Override
    public void examDeleteById(Long id) {
        examDao.deleteById(id);
    }

}
