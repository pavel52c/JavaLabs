package example.services.Credit;

import example.dao.entitiesDao.CreditDao;
import example.entities.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreditService implements ICreditService{
    @Autowired
    CreditDao creditDao;
    @Override
    public List<Credit> creditFindAll() {
        return creditDao.findAll();
    }

    @Override
    public Credit creditFindById(Long id) {
        return creditDao.findById(id);
    }

    @Transactional
    @Override
    public void creditSave(Credit obj) {
        creditDao.save(obj);
    }

    @Transactional
    @Override
    public void creditDeleteById(Long id) {
        creditDao.deleteById(id);
    }
}
