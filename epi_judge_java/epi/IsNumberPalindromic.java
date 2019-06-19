package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    if(x < 0) return false;

    int digitNum = (int) (Math.floor(Math.log10(x)));

    while(digitNum > 0){
      int leastSig = x % 10;
      int mostSig = x / (int)(Math.pow(10, digitNum));
      if (leastSig != mostSig) return false;

      x %= (int)(Math.pow(10, digitNum));
      x /= 10;
      digitNum -= 2;
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
