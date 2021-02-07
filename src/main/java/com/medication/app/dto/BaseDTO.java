package com.medication.app.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

//@MappedSuperclass
public abstract class BaseDTO<ID extends Serializable>{

    @Id
    @GeneratedValue()
    private ID id;

    public BaseDTO(){

    }

    public BaseDTO(Long id) {
        this.id = (ID) id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
