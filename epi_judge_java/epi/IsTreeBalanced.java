package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    int result = traverse(tree);
    return result != -1;
  }

  private static int traverse(BinaryTreeNode<Integer> node){
    if(node == null) return 0;

    int left = traverse(node.left) + 1;
    if(left == 0) return -1;
    int right = traverse(node.right) + 1;
    if(right == 0) return -1;
    if(Math.abs(right - left) > 1) return -1;
    return Math.max(left, right);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
