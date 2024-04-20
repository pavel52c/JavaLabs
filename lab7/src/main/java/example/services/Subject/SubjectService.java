package example.services.Subject;

import example.dao.entitiesDao.SubjectDao;
import example.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService implements ISubjectService{
    @Autowired
    SubjectDao subjectDao;
    @Transactional
    @Override
    public List<Subject> subjectFindAll() {
        return subjectDao.findAll();
    }

    @Transactional
    @Override
    public Subject subjectFindById(Long id) {
        return subjectDao.findById(id);
    }

    @Transactional
    @Override
    public void subjectSave(Subject obj) {
        subjectDao.save(obj);
    }

    @Transactional
    @Override
    public void subjectDeleteById(Long id) {
        subjectDao.deleteById(id);
    }
}
