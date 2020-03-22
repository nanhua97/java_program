package linkBinaryTree;

public class TreeNode {
    private Integer value;
    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public void frontGet() {
        System.out.println(this.value);
        if (leftNode!=null){
            leftNode.frontGet();
        }
        if (rightNode!=null){
            rightNode.frontGet();
        }
    }

    public void midGet() {
        if (leftNode!=null){
            leftNode.frontGet();
        }
        System.out.println(this.value);
        if (rightNode!=null){
            rightNode.frontGet();
        }
    }

    /**
     * 后序遍历
     */
    public void afterGet() {
        if (leftNode!=null){
            leftNode.afterGet();
        }
        if (rightNode!=null){
            rightNode.afterGet();
        }
        System.out.println(this.value);
    }

    /**
     * 后序查找
     * @param num
     * @return
     */
    public TreeNode afterFind(Integer num) {
        if (value == num){
            return this;
        }else {
            if (leftNode!=null){
                return leftNode.afterFind(num);
            }
            if(rightNode!=null){
                return rightNode.afterFind(num);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }
}
