package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")

  /*
    The optimal solution for repeated reversals is to use a lookup table for every 16-bit word.
   */

  public static long reverseBits(long x) {
    for(int i = 0; i < 32; i++){
      if(((x >>> i) & 1) != ((x >>> (63 - i)) & 1)){
        long mask = (1L << i) | (1L << (63 - i));
        x ^= mask;
      }
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
