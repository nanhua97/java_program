package linkBinaryTree;

public class BinaryTree {
    private TreeNode root;

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void frontGet() {
        root.frontGet();
    }

    public void midGet() {
        root.midGet();
    }

    public void afterGet() {
        root.afterGet();
    }

    /**
     * 后序查找
     * @param num
     * @return
     */
    public TreeNode afterFind(Integer num) {
        return root.afterFind(num);
    }

    //二叉树遍历的另一种形式
    public void midGet(TreeNode root) {
        if (root.getLeftNode()!=null){
            midGet(root.getLeftNode());
        }
        System.out.println(root.getValue());
        if (root.getRightNode()!=null){
            midGet(root.getRightNode());
        }
    }
}
