/**
 *
 * Copyright 2014 fqtrnt. All rights reserved.
 */
package org.study;

import static com.j256.ormlite.table.TableUtils.createTable;
import static com.j256.ormlite.table.TableUtils.dropTable;
import static java.lang.String.valueOf;
import static org.apache.commons.lang.StringUtils.leftPad;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.study.entity.Knowledge;
import org.study.entity.Subject;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.misc.TransactionManager;

/**
 * @author fqtrnt [2014年1月6日]
 * @since 1.0.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context*.xml" })
@ActiveProfiles("test")
public class SQLiteTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    protected JdbcConnectionSource connectionSource;
    @Autowired
    private TransactionManager transactionManager;
    @Resource(name = "subjectDao")
    protected Dao<Subject, Integer> subjectDao;
    @Resource(name = "knowledgeDao")
    protected Dao<Knowledge, String> knowledgeDao;

    @Test
    public void test() throws Exception {
        final Subject subject = new Subject();
        subject.setTitle("Test title");
        subjectDao.create(subject);
        // 有事务和没有事务，在更新的速度上有很大的差别
        transactionManager.callInTransaction(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Knowledge knowledge = new Knowledge();
                knowledge.setId("000");
                knowledge.setSubject(subject);
                knowledge = knowledgeDao.createIfNotExists(knowledge);
                for (int i = 0; i < 100; i++) {
                    Knowledge subKnowledge = new Knowledge();
                    subKnowledge.setId(leftPad(valueOf(i), 6, "0"));
                    subKnowledge.setSubject(subject);
                    subKnowledge.setParent(knowledge);
                    subKnowledge.setOrdered(i + 1);
                    knowledgeDao.create(subKnowledge);
                }
                return new Object();
            }
        });

        Subject retSub = subjectDao.queryForId(subject.getId());
        assertNotNull(retSub);
        System.out.println(retSub.getId());
        System.out.println(retSub.getTitle());
        assertNotNull(retSub.getKnowledges());
        assertFalse(retSub.getKnowledges().isEmpty());
        Knowledge retKnowledge = (Knowledge) retSub.getKnowledges().toArray()[0];
        System.out.println(retKnowledge.getId());
        assertNotNull(retKnowledge.getChindren());
        assertFalse(retKnowledge.getChindren().isEmpty());
        System.out.println(retKnowledge.getChindren().size());

        // 生成随机列表
        final List<Knowledge> list = new ArrayList<Knowledge>(retKnowledge.getChindren());
        Set<Integer> cache = new HashSet<Integer>();
        for (int i = 0; i < 100; i++) {
            list.get(i).setOrdered(nextInt(cache, 100));
        }
        transactionManager.callInTransaction(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Knowledge subKnowledge = list.get(i);
                    knowledgeDao.update(subKnowledge);
                }
                return new Object();
            }
        });

        for (Object iterable_element : retKnowledge.getChindren()) {
            Knowledge subKnow = (Knowledge) iterable_element;
            System.out.println(subKnow.getId() + "\t" + subKnow.getOrdered());
        }
    }

    /**
     * @param cache
     * @param i
     * @return
     */
    private int nextInt(Set<Integer> cache, int i) {
        int nextInt = RandomUtils.nextInt(i);
        if (cache.contains(nextInt)) return nextInt(cache, i);
        cache.add(nextInt);
        return nextInt;
    }

    @Before
    public void before() throws Exception {
        createTable(connectionSource, Subject.class);
        createTable(connectionSource, Knowledge.class);
    }

    @After
    public void after() throws Exception {
        dropTable(connectionSource, Subject.class, true);
        dropTable(connectionSource, Knowledge.class, true);
    }
}
