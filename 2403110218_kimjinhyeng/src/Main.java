import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class GridLayoutEx extends JFrame {
    private JTextField expressionDisplay;
    private JTextField resultDisplay;

    public GridLayoutEx() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        expressionDisplay = new JTextField("");
        resultDisplay = new JTextField("");

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(1, 2)); // 한 행에 두 개의 칸 생성
        displayPanel.add(expressionDisplay); // 왼쪽에 위치
        displayPanel.add(resultDisplay); // 오른쪽에 위치

        contentPane.add(displayPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 1, 1));

        String[] buttons = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "AC", "=", "/"};

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new Action());
            buttonPanel.add(button);
        }

        contentPane.add(buttonPanel, BorderLayout.CENTER);

        setSize(300, 400);
        setVisible(true);
    }

    private class Action implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();

            if (buttonText.equals("=")) {
                try {
                    String expression = expressionDisplay.getText();
                    String result = eval(expression);
                    resultDisplay.setText(result);
                    //JOptionPane.showMessageDialog(GridLayoutEx.this, "Expression: " + expression + "\nResult: " + result);
                } catch (ScriptException ex) {
                    //JOptionPane.showMessageDialog(GridLayoutEx.this, "Error in expression");
                }
            } else if (buttonText.equals("AC")) {
                expressionDisplay.setText("");
                resultDisplay.setText("");
            } else {
                expressionDisplay.setText(expressionDisplay.getText() + buttonText);
            }
        }

        private String eval(String expression) throws ScriptException {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return engine.eval(expression).toString();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new GridLayoutEx();
    }
}
