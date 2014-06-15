/**
 *
 * Copyright 2014 VIMICRO. All rights reserved.
 */
package org.study;

import java.util.List;

/**
 * @author Yang.Xiangfu [2014年6月12日]
 * @since 1.0.0.0
 */
public interface TreeNode {
    String getId();
    String getText();
    String getState();
    boolean isLeaf();
    List<TreeNode> getChildren();
}
