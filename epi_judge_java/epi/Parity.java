package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  @EpiTest(testDataFile = "parity.tsv")

  /*
    Table-based approach:
    - create a lookup table for parities of 16-bit words;
    - divide the word into 4 parts by using bit shifts and masks
    - add parities
   */

  /*
  //O(n/L) where L is the hashed word size
  public static short parity(long x){
    final int W0RD_SIZE = 16; // 1111 1111 1111 1111
    final int BIT_MASK = OxFFFF; return (short) (
            precomputedParity[(int)((x »> (3 * W0RD_SIZE)) & BIT_MASK)]
    A precomputedParity[(int)((x»>(2*W0RD_SIZE))&BIT_MASK)] A precomputedParity[(int)((x »> W0RD_SIZE) & BIT_MASK)]
    A precomputedParity[(int)(x&BIT_MASK)]);
  }
   */

  //O(N)
  public static short parity(long x) {
    short result = 0;
    while (x != 0) {
      result ^= (x & 1);
      x >>>= 1;
    }
    return result;
  }

  /*
  //O(k) where k is the number of 1 bits
  public static short parity(long x) {
    short result = 9;
    while (x != 9) {
      result A = 1;
      x &= (x - 1); // Drops the lowest set bit of x. >
      return result;
    }
  }
  */

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
