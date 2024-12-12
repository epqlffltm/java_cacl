import javax.swing.*; // 자바 스윙 패키지 임포트
import java.awt.*; // AWT 패키지 임포트
import java.awt.event.ActionEvent; // 액션 이벤트 임포트
import java.awt.event.ActionListener; // 액션 리스너 임포트
import javax.script.ScriptEngine; // 스크립트 엔진 임포트
import javax.script.ScriptEngineManager; // 스크립트 엔진 매니저 임포트
import javax.script.ScriptException; // 스크립트 예외 임포트

// GridLayoutEx 클래스 정의
class GridLayoutEx extends JFrame {
    private JTextField expressionDisplay; // 수식 입력 필드
    private JTextField resultDisplay; // 결과 출력 필드

    // 생성자 정의
    public GridLayoutEx() {
        setTitle("Calculator"); // 윈도우 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우 닫기 동작 설정
        Container contentPane = getContentPane(); // 컨텐트 패인 가져오기
        contentPane.setLayout(new BorderLayout()); // 레이아웃을 BorderLayout으로 설정

        expressionDisplay = new JTextField(""); // 수식 입력 필드 초기화
        resultDisplay = new JTextField(""); // 결과 출력 필드 초기화

        JPanel displayPanel = new JPanel(); // 디스플레이 패널 생성
        displayPanel.setLayout(new GridLayout(1, 2)); // 한 행에 두 개의 칸 생성
        displayPanel.add(expressionDisplay); // 왼쪽에 수식 입력 필드 추가
        displayPanel.add(resultDisplay); // 오른쪽에 결과 출력 필드 추가

        contentPane.add(displayPanel, BorderLayout.NORTH); // 디스플레이 패널을 북쪽에 추가

        JPanel buttonPanel = new JPanel(); // 버튼 패널 생성
        buttonPanel.setLayout(new GridLayout(4, 4, 1, 1)); // 4x4 그리드 레이아웃 설정

        String[] buttons = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "AC", "=", "/"}; // 버튼 배열 생성

        for (String text : buttons) {
            JButton button = new JButton(text); // 버튼 생성
            button.addActionListener(new Action()); // 버튼에 액션 리스너 추가
            buttonPanel.add(button); // 버튼 패널에 버튼 추가
        }

        contentPane.add(buttonPanel, BorderLayout.CENTER); // 버튼 패널을 중앙에 추가

        setSize(300, 400); // 윈도우 크기 설정
        setVisible(true); // 윈도우를 보이게 설정
    }

    // Action 클래스 정의
    private class Action implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource(); // 클릭된 버튼 가져오기
            String buttonText = button.getText(); // 버튼 텍스트 가져오기

            if (buttonText.equals("=")) { // "=" 버튼 클릭 시
                try {
                    String expression = expressionDisplay.getText(); // 수식 입력 필드의 텍스트 가져오기
                    String result = eval(expression); // 수식 계산
                    resultDisplay.setText(result); // 결과 출력 필드에 결과 설정
                } catch (ScriptException ex) {
                    // 예외 발생 시
                }
            } else if (buttonText.equals("AC")) { // "AC" 버튼 클릭 시
                expressionDisplay.setText(""); // 수식 입력 필드 초기화
                resultDisplay.setText(""); // 결과 출력 필드 초기화
            } else {
                expressionDisplay.setText(expressionDisplay.getText() + buttonText); // 버튼 텍스트를 수식 입력 필드에 추가
            }
        }

        // 수식을 계산하는 메서드
        private String eval(String expression) throws ScriptException {
            ScriptEngineManager manager = new ScriptEngineManager(); // 스크립트 엔진 매니저 생성
            ScriptEngine engine = manager.getEngineByName("JavaScript"); // 자바스크립트 엔진 가져오기
            return engine.eval(expression).toString(); // 수식 계산 결과 반환
        }
    }
}

// Main 클래스 정의
public class Main {
    public static void main(String[] args) {
        new GridLayoutEx(); // GridLayoutEx 인스턴스 생성
    }
}
