package com.medication.app.service.base;

import com.medication.app.dao.base.BaseDAO;
import com.medication.app.entity.base.BaseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class CrudService<T extends BaseEntity<ID>, ID extends Serializable>{

    public BaseDAO<T, ID> baseDAO;

    public CrudService(BaseDAO<T, ID> baseDAO){
        this.baseDAO = baseDAO;
    }

    public T getById(ID id){
        return baseDAO.findById(id).get();
    }

    public BaseDAO<T, ID> getBaseDAO(){
        return baseDAO;
    }

    public List<T> findAll(){
        return baseDAO.findAll();
    }

    public void deleteById(ID id){
         baseDAO.deleteById(id);
    }





}
