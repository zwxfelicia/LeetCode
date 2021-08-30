# 112. Path Sum

Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

## Example 1

![image-20210824163453660](/Users/zhengwenxuan/Desktop/Leetcode/DFS/image-20210824163453660.png)

```
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
```

## Solution

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.val == targetSum && root.right == null && root.left == null) {
            return true;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
```

# 113. Path Sum II

Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

## Example 1

![image-20210824165824908](/Users/zhengwenxuan/Desktop/Leetcode/DFS/image-20210824165824908.png)

```
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
```

## Solution

```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<Integer>();
        findPathsRecursive(root, targetSum, currentPath, allPaths);
        return allPaths;
    }

    private void findPathsRecursive(TreeNode currentNode, int sum, List<Integer> currentPath,  List<List<Integer>> allPaths) {
        if (currentNode == null) {
            return;
        }
      	// add the current node to the path
        currentPath.add(currentNode.val);

        if (currentNode.val == sum && currentNode.left == null && currentNode.right == null) {
            allPaths.add(new ArrayList<Integer>(currentPath));
        } else {
            findPathsRecursive(currentNode.left, sum - currentNode.val, currentPath, allPaths);
            findPathsRecursive(currentNode.right, sum - currentNode.val, currentPath, allPaths);
        }
        // remove the current node from the path to backtrack, 
    		// we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
    }
}
```

# 129. Sum Root to Leaf Numbers

You are given the `root` of a binary tree containing digits from `0` to `9` only.

Each root-to-leaf path in the tree represents a number.

- For example, the root-to-leaf path `1 -> 2 -> 3` represents the number `123`.

Return *the total sum of all root-to-leaf numbers*. Test cases are generated so that the answer will fit in a **32-bit** integer.

A **leaf** node is a node with no children.

## Example 1

![image-20210824175325292](/Users/zhengwenxuan/Desktop/Leetcode/DFS/image-20210824175325292.png)

```
Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
```

## Solution

```java
class Solution {
    public int sumNumbers(TreeNode root) {
        return findRootToLeafPathNumbers(root, 0);
    }
    
    private int findRootToLeafPathNumbers(TreeNode currentNode, int pathSum) {
        if (currentNode == null) {
            return 0;
        }
        
        // calculate the path number of the current node
        pathSum = 10 * pathSum + currentNode.val;
        
        if (currentNode.left == null && currentNode.right == null) {
            return pathSum;
        }
        
        return findRootToLeafPathNumbers(currentNode.left, pathSum) + findRootToLeafPathNumbers(currentNode.right, pathSum);
    }
}
```



# 437. Path Sum III

Given the `root` of a binary tree and an integer `targetSum`, return *the number of paths where the sum of the values along the path equals* `targetSum`.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

## Example 1

![image-20210824182629495](/Users/zhengwenxuan/Desktop/Leetcode/DFS/image-20210824182629495.png)

```
Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.
```

## Solution

```java
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        List<Integer> currentPath = new LinkedList<>();
        return countPathsRecursive(root, targetSum, currentPath);
    }
    
    private int countPathsRecursive(TreeNode currentNode, int targetSum, List<Integer> currentPath) {
        if (currentNode == null) {
            return 0;
        }
        
        // add the current node to the path
        currentPath.add(currentNode.val);
        int pathCount = 0;
        int pathSum = 0;
        
        // find the sums of all sub-paths in the current path list
        ListIterator<Integer> pathIterator = currentPath.listIterator(currentPath.size());
        while (pathIterator.hasPrevious()) {
            pathSum += pathIterator.previous();
             // if the sum of any sub-path is equal to 'S' we increment our path count.
            if (pathSum == targetSum) {
                pathCount++;
            }
        }
        
        // traverse the left sub-tree
        pathCount += countPathsRecursive(currentNode.left, targetSum, currentPath);
        // traverse the right sub-tree
        pathCount += countPathsRecursive(currentNode.right, targetSum, currentPath);
        
        // remove the current node from the path to backtrack, 
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
        
        return pathCount;
    }
}
```

# 543. Tree Diameter

Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

## Example 1

![image-20210824184837513](/Users/zhengwenxuan/Desktop/Leetcode/DFS/image-20210824184837513.png)

```
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
```

## Solution

```java
class Solution {
    private int treeDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return treeDiameter;
    }

    private int calculateHeight(TreeNode currentNode) {
        if (currentNode == null) {
            return 0;
        }

        // if the current node doesn't have a left or right subtree, we can't have
        // a path passing through it, since we need a leaf node on each side
        int leftHeight = calculateHeight(currentNode.left);
        int rightHeight = calculateHeight(currentNode.right);

        // diameter at the current node will be equal to the height of left subtree +
        // the height of right sub-trees + '1' for the current node
        treeDiameter = Math.max(leftHeight + rightHeight, treeDiameter);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}

```

# 124. Binary Tree Maximum Path Sum

A **path** in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence **at most once**. Note that the path does not need to pass through the root.

The **path sum** of a path is the sum of the node's values in the path.

Given the `root` of a binary tree, return *the maximum **path sum** of any path*.

## **Example 1:**

![image-20210825155636589](/Users/zhengwenxuan/Desktop/Leetcode/DFS/image-20210825155636589.png)

```
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
```

## Solution

```java
class Solution {
    private int globalMax = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        findMaxPathSumRecursive(root);
        return globalMax;
    }
    
    private int findMaxPathSumRecursive(TreeNode currentNode) {
        if (currentNode == null) {
            return 0;
        }
        
        // ignore paths with negative sums, since we need to find the maximum sum we should
        // ignore any path which has an overall negative sum.
        int leftMax = findMaxPathSumRecursive(currentNode.left);
        int rightMax = findMaxPathSumRecursive(currentNode.right);
        
        leftMax = Math.max(leftMax, 0);
        rightMax = Math.max(rightMax, 0);
        
        // maximum path sum at the current node will be equal to the sum from the left subtree +
        // the sum from right subtree + val of current node
        globalMax = Math.max(leftMax + rightMax + currentNode.val, globalMax);
        
        // maximum sum of any path from the current node will be equal to the maximum of 
        // the sums from left or right subtrees plus the value of the current node
        return Math.max(leftMax, rightMax) + currentNode.val;
    }
}
```

# 257. Binary Tree Paths

Given the `root` of a binary tree, return *all root-to-leaf paths in **any order***.

A **leaf** is a node with no children.

## **Example 1:**

![image-20210825165746755](/Users/zhengwenxuan/Desktop/Leetcode/DFS/image-20210825165746755.png) 

```
Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]
```

## Solution

```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        StringBuilder currentPath = new StringBuilder();
        findPaths(root, result, currentPath);
        return result;
    }
    
    
    private void findPaths(TreeNode currentNode, List<String> result, StringBuilder currentPath) {
        if (currentNode == null) {
            return;
        }
        
        if (currentNode.left == null && currentNode.right == null) {
            String temp = currentNode.val + "";
            currentPath.append(temp);
            result.add(currentPath.toString());
            currentPath.delete(currentPath.length() - temp.length(), currentPath.length());
            return;
        }

        String temp = currentNode.val + "->";
        currentPath.append(temp);
        
        findPaths(currentNode.left, result, currentPath);
        findPaths(currentNode.right, result, currentPath);
        currentPath.delete(currentPath.length() - temp.length(), currentPath.length());
        
    }
}
```

# 98. Validate Binary Search Tree

Given the `root` of a binary tree, *determine if it is a valid binary search tree (BST)*.

A **valid BST** is defined as follows:

- The left subtree of a node contains only nodes with keys **less than** the node's key.
- The right subtree of a node contains only nodes with keys **greater than** the node's key.
- Both the left and right subtrees must also be binary search trees.

 ## **Example 1:**

![image-20210825174753796](/Users/zhengwenxuan/Desktop/Leetcode/DFS/image-20210825174753796.png)

```
Input: root = [2,1,3]
Output: true
```

## Solution

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode currentNode, long min, long max) {
        if (currentNode == null) {
            return true;
        }

        if (currentNode.val <= min || currentNode.val >= max) {
            return false;
        }

        return validate(currentNode.left, min, currentNode.val) && validate(currentNode.right, currentNode.val, max);
    }
}
```

# 94. Binary Tree Inorder Traversal

Given the root of a binary tree, return the inorder traversal of its nodes' values.

## Example 1

![image-20210827110001466](/Users/zhengwenxuan/Desktop/LeetCode/DFS/image-20210827110001466.png)

```
Input: root = [1,null,2,3]
Output: [1,3,2]
```

## Solution

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode currentNode, List<Integer> result) {
        if (currentNode == null) {
            return;
        }
        inorder(currentNode.left, result);
        result.add(currentNode.val);
        inorder(currentNode.right, result);
    }
}
```

# 111. Minimum Depth of Binary Tree

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

## Example

![image-20210827175951881](/Users/zhengwenxuan/Desktop/LeetCode/DFS/image-20210827175951881.png)

```
Input: root = [3,9,20,null,null,15,7]
Output: 2
```

## Solution

```java
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        if (root.left == null && root.right == null) {
            return 1;
        }

        int min = Integer.MAX_VALUE; 
        if (root.left != null) {
            min = Math.min(minDepth(root.left), min);
        }
        if (root.right != null) {
            min = Math.min(minDepth(root.right), min);
        }
        return min + 1;
    }
}
```

# 144. Binary Tree Preorder Traversal

Given the root of a binary tree, return the preorder traversal of its nodes' values.

## Example

![image-20210827183940400](/Users/zhengwenxuan/Desktop/LeetCode/DFS/image-20210827183940400.png)



```
Input: root = [1,null,2,3]
Output: [1,2,3]
```

## Solution

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode node,  List<Integer> result) {
        if (node == null) {
            return;
        }

        result.add(node.val);
        preorder(node.left, result);
        preorder(node.right, result);
    }
}
```

# 145. Binary Tree Postorder Traversal

Given the root of a binary tree, return the postorder traversal of its nodes' values.

## Example 1:

![image-20210827184521125](/Users/zhengwenxuan/Desktop/LeetCode/DFS/image-20210827184521125.png)

```
Input: root = [1,null,2,3]
Output: [3,2,1]
```

## Solution

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.val);
    }
}
```

# 222. Count Complete Tree Nodes

Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

## Example 1:

![image-20210827185032681](/Users/zhengwenxuan/Desktop/LeetCode/DFS/image-20210827185032681.png)

```
Input: root = [1,2,3,4,5,6]
Output: 6
```

## Solution 1: DFS

```java
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }
}
```

## Solution 2: Complete Binary Tree

完全二叉树的定义：**它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。**

如果满二叉树的层数为h，则总节点数为：2^h - 1.
那么我们来对 root 节点的左右子树进行高度统计，分别记为 left 和 right，有以下两种结果：

left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。所以左子树的节点总数我们可以直接得到，是 2^left - 1，加上当前这个 root 节点，则正好是 2^left。再对右子树进行递归统计。
left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。同理，右子树节点 +root 节点，总数为 2^right。再对左子树进行递归查找。

```java
// a << b => a*b
// a >> b => a/b

class Solution {
    public int countNodes(TreeNode root) {
        if(root == null){
           return 0;
        } 
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if (left == right) {
           /*
            * 最后一个节点在右子树上
            * +1 是加上当前节点
            * 左子树是满二叉树，节点数=(1 << left) - 1
            * 再递归计算右子树
            */
            return countNodes(root.right) + (1<<left);
        } else {
           /*
            * 最后一个节点在左子树上
            * +1 是加上当前节点
            * 右子树是满二叉树，节点数=(1 << right) - 1
            * 再递归计算左子树
            */
            return countNodes(root.left) + (1<<right);
        }
    }
    private int countLevel(TreeNode root) {
        int level = 0;
        while(root != null) {
            level++;
            root = root.left;
        }
        return level;
    }
}

```

# 226. Invert Binary Tree

Given the `root` of a binary tree, invert the tree, and return *its root*.

## Example

 ![image-20210830151026558](/Users/zhengwenxuan/Desktop/LeetCode/DFS/image-20210830151026558.png)

```
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
```

## Solution

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        helper(root);
        return root;
    }

    private void helper(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        helper(node.left);
        helper(node.right);
    }
}
```

# 310. Minimum Height Trees

A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

## **Example 1:**

![image-20210830152655654](/Users/zhengwenxuan/Desktop/LeetCode/DFS/image-20210830152655654.png)

```java
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
```



