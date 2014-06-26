import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class MainFrame {

  private JFrame _frame;

  Interpreter interpreter;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          MainFrame window = new MainFrame();
          window._frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public MainFrame() {
    initialize();
  }

  private void initialize() {
    _frame = new JFrame();
    _frame.setTitle("Brainfuck Interpreter");
    _frame.setResizable(false);
    _frame.setBounds(100, 100, 450, 259);
    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    _frame.getContentPane().setLayout(null);

    final JTextArea code = new JTextArea();
    code.setLineWrap(true);
    code.setBounds(10, 31, 414, 26);

    JLabel lblCode = new JLabel("Code:");
    lblCode.setBounds(10, 11, 46, 14);
    _frame.getContentPane().add(lblCode);

    JScrollPane scrollPane = new JScrollPane(code);
    scrollPane.setBounds(10, 31, 414, 49);
    _frame.getContentPane().add(scrollPane);

    final JTextArea output = new JTextArea();
    output.setEditable(false);
    output.setBounds(10, 187, 414, 26);

    JLabel lblOutput = new JLabel("Output:");
    lblOutput.setBounds(10, 162, 46, 14);
    _frame.getContentPane().add(lblOutput);

    final JTextArea input = new JTextArea();
    input.setBounds(10, 116, 414, 26);
    _frame.getContentPane().add(input);

    JLabel lblInput = new JLabel("Input:");
    lblInput.setBounds(10, 91, 46, 14);
    _frame.getContentPane().add(lblInput);

    JButton btnExecute = new JButton("Execute");
    btnExecute.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Execute code
        interpreter = new Interpreter(code.getText(), input.getText());
        output.setText(interpreter.getText());
      }
    });
    btnExecute.setBounds(335, 153, 89, 23);
    _frame.getContentPane().add(btnExecute);

    JTextPane textPane = new JTextPane();
    textPane.setBounds(10, 31, 78, 26);
    _frame.getContentPane().add(textPane);

    JScrollPane scrollPane_1 = new JScrollPane(output);
    scrollPane_1.setBounds(10, 187, 414, 26);
    _frame.getContentPane().add(scrollPane_1);
  }
}
