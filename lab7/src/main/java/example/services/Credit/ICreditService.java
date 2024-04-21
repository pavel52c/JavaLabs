package example.services.Credit;

import example.entities.Credit;

import java.util.List;

public interface ICreditService {
    public List<Credit> creditFindAll();

    public Credit creditFindById(Long id);

    public void creditSave(Credit obj);

    public void creditDeleteById(Long id);
}
