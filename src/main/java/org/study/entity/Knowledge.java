/**
 *
 * Copyright 2014 fqtrnt. All rights reserved.
 */
package org.study.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author fqtrnt [2014年1月21日]
 * @since 1.0.0.0
 */
@DatabaseTable(tableName = "knowledge")
public class Knowledge {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String title;
    @DatabaseField
    private int proficiency;
    @DatabaseField
    private int pageNumber;
    @DatabaseField
    private int type;
    @DatabaseField
    private int ordered;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "subject_id")
    private Subject subject;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "parent_id")
    private Knowledge parent;
    @ForeignCollectionField
    private ForeignCollection<Knowledge> chindren;
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }
    /**
     * @param subject the subject to set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    /**
     * @return the chindren
     */
    public ForeignCollection<Knowledge> getChindren() {
        return chindren;
    }
    /**
     * @return the parent
     */
    public Knowledge getParent() {
        return parent;
    }
    /**
     * @param parent the parent to set
     */
    public void setParent(Knowledge parent) {
        this.parent = parent;
    }
    /**
     * @return the proficiency
     */
    public int getProficiency() {
        return proficiency;
    }
    /**
     * @return the pageNumber
     */
    public int getPageNumber() {
        return pageNumber;
    }
    /**
     * @return the type
     */
    public int getType() {
        return type;
    }
    /**
     * @return the order
     */
    public int getOrdered() {
        return ordered;
    }
    /**
     * @param proficiency the proficiency to set
     */
    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }
    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
    /**
     * @param order the order to set
     */
    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }
}
