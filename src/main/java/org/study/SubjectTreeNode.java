/**
 *
 * Copyright 2014 VIMICRO. All rights reserved.
 */
package org.study;

import static com.google.common.collect.FluentIterable.from;

import java.util.List;

import org.study.entity.Knowledge;
import org.study.entity.Subject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Function;

/**
 * @author Yang.Xiangfu [2014年6月12日]
 * @since 1.0.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectTreeNode implements TreeNode {

    private Subject subject;

    /**
     * @param subject
     */
    public SubjectTreeNode(Subject subject) {
        this.subject = subject;
    }
    public String getId() {
        return String.valueOf(subject.getId());
    }
    public String getState() {
        return "open";
    }
    public String getText() {
        return subject.getTitle();
    }
    public List<TreeNode> getChildren() {
        return from(subject.getKnowledges()).transform(new Function<Knowledge, TreeNode>() {
            @Override public TreeNode apply(Knowledge input) {
                return new KnowledgeTreeNode(input);
            }
        }).toList();
    }
}
