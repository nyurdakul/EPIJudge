package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if(tree == null) return true;
    return traverse(tree, tree);
  }

  private static boolean traverse(BinaryTreeNode<Integer> tree1, BinaryTreeNode<Integer> tree2){
    if(tree1 == null && tree2 == null) return true;
    if(tree1 == null || tree2 == null) return false;
    if(tree1.data != tree2.data) return false;
    return traverse(tree1.left, tree2.right) && traverse(tree1.right, tree2.left);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
