import org.junit.Test;

/**
 * @author wangtingting
 * @date 2021/5/14
 */
public class BuildBinaryTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int value) {
            this.val = value;
        }
    }

    private TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        if (preorder.length != inorder.length) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        TreeNode rootNode = new TreeNode();
        rootNode.val = preorder[0];


        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                rootIndex = i;
            }
        }
        if (rootIndex == 0) {
            rootNode.left = null;
        } else {
            int[] newPreorder = new int[rootIndex];
            for (int k = 1; k <= rootIndex; k++) {
                newPreorder[k - 1] = preorder[k];
            }

            int[] newInorder = new int[rootIndex];
            for (int j = 0; j < rootIndex; j++) {
                newInorder[j] = inorder[j];
            }

            rootNode.left = buildTree(newPreorder, newInorder);
        }

        if (rootIndex == inorder.length - 1) {
            rootNode.right = null;
        } else {
            int[] newPreorder = new int[inorder.length - 1 - rootIndex];
            int[] newInorder = new int[inorder.length - 1 - rootIndex];

            int newIndex = 0;
            for (int k = rootIndex + 1; k < inorder.length; k++) {
                newPreorder[newIndex] = preorder[k];
                newInorder[newIndex] = inorder[k];
                newIndex++;
            }

            rootNode.right = buildTree(newPreorder, newInorder);
        }

        return rootNode;
    }


    @Test
    public void testBuildTree() throws Exception {
        TreeNode treeNode = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});

        // 边界
        TreeNode treeNode1 = buildTree(new int[]{}, new int[]{});
        TreeNode treeNode2 = buildTree(new int[]{1,2}, new int[]{1,2});


        System.out.println("hello world !!");
    }


}
