package com.medication.app.dao.base;

import com.medication.app.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseDAO<T  extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {


}
