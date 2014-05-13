/**
 *
 * Copyright 2014 VIMICRO. All rights reserved.
 */
package org.study;

import static com.google.common.collect.Lists.newArrayList;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.study.entity.Knowledge;
import org.study.entity.Subject;

import com.j256.ormlite.dao.Dao;

/**
 * @author fqtrnt [2014年4月24日]
 * @since 1.0.0.0
 */
@Component
@Transactional(readOnly = true)
public class CardService extends AbstractService {
	@Resource
	protected Dao<Subject, String> subjectDao;
	@Resource
	protected Dao<Knowledge, String> knowledgeDao;
	
	/**
	 * @param subject
	 * @return
	 */
	@Transactional(readOnly = false)
	public int save(Subject subject) {
		try {
		    subjectDao.createOrUpdate(subject);
			return subject.getId();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @return
	 */
	public List<Subject> loadAllSubjects() {
		try {
		    return subjectDao.queryBuilder().orderBy("id", false).query();
		} catch (SQLException e) {
			return newArrayList();
		}
	}
}
