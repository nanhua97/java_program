package linkBinaryTree;

public class TreeTest {
    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(1);

        TreeNode node2 = new TreeNode(2);

        TreeNode node3 = new TreeNode(3);

        TreeNode node4 = new TreeNode(4);

        TreeNode node5 = new TreeNode(5);

        TreeNode node6 = new TreeNode(6);

        TreeNode node7 = new TreeNode(7);

        node1.setLeftNode(node2);

        node1.setRightNode(node3);

        node2.setLeftNode(node4);

        node2.setRightNode(node5);

        node3.setLeftNode(node6);

        node3.setRightNode(node7);

        BinaryTree binaryTree = new BinaryTree(node1);

        //二叉树的前序遍历
        //binaryTree.frontGet();

        //二叉树的中序遍历
        //binaryTree.midGet();

        //二叉树的后序遍历
        //binaryTree.afterGet();

        //遍历指定树
        //binaryTree.leftGet(node1);

        //二叉树的后序查找
        TreeNode treeNode = binaryTree.afterFind(2);
        System.out.println(treeNode);

    }
}
