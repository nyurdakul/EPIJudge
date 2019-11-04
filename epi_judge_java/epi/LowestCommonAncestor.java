package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    return findLCA(tree, node0, node1);
  }

  private static BinaryTreeNode<Integer> findLCA(BinaryTreeNode<Integer> root,
                                                 BinaryTreeNode<Integer> node0,
                                                 BinaryTreeNode<Integer> node1) {
    if(root == null) return null;

    BinaryTreeNode<Integer> left = findLCA(root.left, node0, node1);
    BinaryTreeNode<Integer> right = findLCA(root.right, node0, node1);
    if(left != null && right != null) return root;
    if((left != null || right != null) && (root.equals(node0) || root.equals(node1))) return root;
    if(left != null) return left;
    if(right != null) return right;
    if(root.equals(node0) || root.equals(node1)) return root;
    return null;
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
