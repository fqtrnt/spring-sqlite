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
@DatabaseTable(tableName = "subject")
public class Subject {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String title;
    @DatabaseField
    private int pageSize;
    @DatabaseField
    private boolean disabled;
    @ForeignCollectionField
    private ForeignCollection<Knowledge> knowledges;
    /**
     * @return the id
     */
    public int getId() {
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
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the knowledges
     */
    public ForeignCollection<Knowledge> getKnowledges() {
        return knowledges;
    }
    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }
    /**
     * @return the disabled
     */
    public boolean isDisabled() {
        return disabled;
    }
    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
