package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    int n0Count = 0;
    int n1Count = 0;
    BinaryTree<Integer> dummyNode0 = new BinaryTree<>(node0.data, node0.left, node0.right, node0.parent);
    BinaryTree<Integer> dummyNode1 = new BinaryTree<>(node1.data, node1.left, node1.right, node1.parent);
    while(dummyNode0.parent != null){
      n0Count++;
      dummyNode0 = dummyNode0.parent;
    }
    while(dummyNode1.parent != null){
      n1Count++;
      dummyNode1 = dummyNode1.parent;
    }
    BinaryTree<Integer> lowNode = n1Count >= n0Count ? node1 : node0;
    BinaryTree<Integer> highNode = n1Count < n0Count ? node1 : node0;
    for(int i = 0; i < Math.abs(n1Count - n0Count); i++) lowNode = lowNode.parent;
    while(lowNode != highNode){
      lowNode = lowNode.parent;
      highNode = highNode.parent;
    }
    return lowNode;
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
