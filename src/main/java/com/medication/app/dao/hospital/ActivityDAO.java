package com.medication.app.dao.hospital;

import com.medication.app.dao.base.BaseDAO;
import com.medication.app.entity.hospital.Activity;
import org.springframework.stereotype.Repository;

public interface ActivityDAO extends BaseDAO<Activity, Long> {

    Activity findByName(String name);
}
