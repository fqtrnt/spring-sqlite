/**
 *
 * Copyright 2014 VIMICRO. All rights reserved.
 */
package org.study;

import static com.google.common.collect.FluentIterable.from;

import java.util.List;

import org.study.entity.Knowledge;

import com.google.common.base.Function;

/**
 * @author Yang.Xiangfu [2014年6月12日]
 * @since 1.0.0.0
 */
public class KnowledgeTreeNode implements TreeNode {

    private Knowledge node;

    /**
     * @param node
     */
    public KnowledgeTreeNode(Knowledge node) {
        this.node = node;
    }

    @Override
    public String getId() {
        return node.getId();
    }

    @Override
    public String getText() {
        return getText();
    }

    @Override
    public String getState() {
        return "closed";
    }
    @Override
    public boolean isLeaf() {
        return true;
    }
    @Override
    public List<TreeNode> getChildren() {
        return from(node.getChildren()).transform(new Function<Knowledge, TreeNode>() {
            @Override public TreeNode apply(Knowledge input) {
                return new KnowledgeTreeNode(input);
            }
        }).toList();
    }

}
