public class Interpreter {

  MainFrame mf = new MainFrame();

  private char mainAr[] = new char[900];
  private int input[] = new int[900];
  private String text = "";

  public Interpreter(String codeIn, String input) {
    char[] code = codeIn.toCharArray();
    getInput(input);
    clearArray();
    translate(code);
  }

  private void translate(char[] code) {
    boolean loopOpen = false;
    int position = 0;
    int z = 0;
    // Main-Loop
    for (int i = 0; i < code.length; i++) {
      switch (code[i]) {
        case '<':
          // Decrement Field-Pointer
          position--;
          break;
        case '>':
          // Increment Field-Pointer
          position++;
          break;
        case '+':
          // Increment current field
          mainAr[position]++;
          break;
        case '-':
          // Decrement current field
          mainAr[position]--;
          break;
        case ',':
          // Read from input
          mainAr[position] = (char)input[z];
          z++;
          break;
        case '.':
          // Write value of current field
          if (mainAr[position] > (int)31) {
            text += Character.toString(mainAr[position]);
          } else {
            int a = mainAr[position];
            text += Integer.toString(a);
          }
          break;
        case '[':
          if (mainAr[position] == 0) {
            // Jump to closing brackets If brackets inside brackets jump over these too
            i++;
            int y = 0;
            for (int x = i; x < code.length; x++) {
              if (code[x] == '[') {
                y++;
              } else if (code[x] == ']' && y > 0) {
                y--;
              } else if (code[x] == ']' && y == 0) {
                i = x;
                break;
              }
            }
          } else {
            // Start loop
            loopOpen = true;
          }
          break;
        case ']':
          if (!loopOpen) {
            System.err.println("\nError: Trying to close loop without opening it (\']\' at position " + (i + 1) + ")");
          } else if (loopOpen && mainAr[position] != 0) {
            // Back to start of loop
            int y = 0;
            int x = i;
            while (x != 0) {
              x--;
              if (code[x] == ']') {
                y++;
              } else if (code[x] == '[' && y > 0) {
                y--;
              } else if (code[x] == '[' && y == 0) {
                i = x;
                break;
              }
            }
          } else if (loopOpen && mainAr[position] == 0) {
            // Loop ends
            loopOpen = false;
          }
          break;
      }
    }
  }

  public String getText() {
    return text;
  }

  private void clearArray() {
    for (int i = 0; i < mainAr.length; i++) {
      mainAr[i] = 0;
    }
  }

  private void getInput(String in) {
    char[] in2 = in.toCharArray();
    for (int i = 0; i < in2.length; i++) {
      input[i] = Character.getNumericValue(in2[i]);
    }
  }

}
