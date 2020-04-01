package fmm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyCalculator extends JFrame implements ActionListener {

	// �����Ա����
	JButton[] nums = new JButton[10]; // ����0-9
	String[] op = new String[] { ".", "+", "-", "*", "/", "%", "^", "C", "Back", "=" };// ����
	JButton[] operator = new JButton[10]; // ������
	JPanel jPanel;// ����������ּ��ͷ��ż��ȵ����
	JTextField textField;// ,�ı������ڴ洢������
	Font font;// ��������
	String nowButton;// ���ڰ��µļ�

	// ���췽��
	public MyCalculator() {

		setBounds(400, 200, 350, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		textField = new JTextField();
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setFont(new Font("����", Font.BOLD, 23));
		textField.setEditable(false);// �����ı����ɱ༭
		font = new Font("����", Font.BOLD, 17);

		Container c = getContentPane();// ����getContentPane()��������ʼ��������������������

		textField.setPreferredSize(new Dimension(350, 55));// �������ı����С����
		c.setLayout(new BorderLayout());// ���ò���Ϊ�߽粼��
		c.add(textField, BorderLayout.NORTH);
		jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(5, 4, 3, 3));// ���������Ϊ��񲼾֣�����������ּ��ͷ��ż�
		c.add(jPanel, BorderLayout.CENTER);// ����������������ڴ�������Ĳ���
		setTitle("������");

		// ������ּ��������
		for (int i = 0; i < 10; i++) {
			nums[i] = new JButton(i + "");
			nums[i].addActionListener(this);
			nums[i].setFont(font);
		}
		// ��ӷ��ż��������
		for (int i = 0; i < 10; i++) {
			operator[i] = new JButton(op[i]);
			operator[i].addActionListener(this);
			operator[i].setFont(font);
		}

		jPanel.add(operator[4]);
		jPanel.add(operator[6]);
		jPanel.add(operator[7]);
		jPanel.add(operator[8]);
		jPanel.add(nums[7]);
		jPanel.add(nums[8]);
		jPanel.add(nums[9]);
		jPanel.add(operator[1]);
		jPanel.add(nums[4]);
		jPanel.add(nums[5]);
		jPanel.add(nums[6]);
		jPanel.add(operator[2]);
		jPanel.add(nums[1]);
		jPanel.add(nums[2]);
		jPanel.add(nums[3]);
		jPanel.add(operator[3]);
		jPanel.add(operator[5]);
		jPanel.add(nums[0]);
		jPanel.add(operator[0]);
		jPanel.add(operator[9]);

		setVisible(true);// ���ô���ɼ�
	}

	@Override
	// ʵ�ֽӿ�����Ҫʵ�ֵķ���
	// getActionCommand()�����ڰ�ť�ϵ��ַ������õ����Ǳ�ǩ
	public void actionPerformed(ActionEvent event) {

		nowButton = event.getActionCommand();
		if (nowButton != "Back" && nowButton != "=") {
			// �������λ��=�Ͳ�ִ����
			textField.setText(textField.getText() + nowButton);// ��ʾ�ı����е����ݺ͵�ǰ���¼��е�����
		}

		if (nowButton.equals("=")) {
			// ����ǵ��ںţ��͵��ú���������
			if (calculator(textField.getText()).equals("��������Ϊ�㣬��������")) {
				textField.setText("");
			} else {
				textField.setText(calculator(textField.getText()));// ����calculator���������ν��룬���ؽ��
			}

		}

		if (nowButton.equals("Back")) {
			// ����һ���ַ�
			StringBuffer sb = new StringBuffer(textField.getText());
			textField.setText(sb.substring(0, sb.length() - 1));
		}

		if (nowButton.equals("C")) {// ���
			textField.setText("");// �ı���������Ϊ��
		}
	}

	// ��������ķ��������ν��룬����String����
	public String calculator(String string) {
		StringBuffer sb = new StringBuffer(string);
		int operatorCount = 0;// ��������

		int j = 0;// �������������м���
		// �����ж��ٸ������������n+1�����֣���Ϊ�����������Ǳ�����������1
		for (j = 0; j < sb.length(); j++) {
			if (sb.charAt(j) <= '9' && sb.charAt(j) >= '0' || sb.charAt(j) == '.') {
				continue;
			} else {
				operatorCount++;
			}
		}

		// ��ʼ����������
		char[] fuhao = new char[operatorCount];
		// ��ʼ���������飨���ַ�����ʾ��
		String[] num = new String[operatorCount + 1];
		for (j = 0; j < num.length; j++) {
			num[j] = "";
		}

		/*
		 * ��ÿ�����ִ���������飬ÿ�����Ŵ���������� ��ΪҪ��֤���ű�����������ֵĺ��棬���Կ��ƴ洢λ�õ�k�ı仯�������˷��Ŵ洢�Ĵ�����У���Ϊ�Ȱ������ּ�
		 * ���ַ���ֱ��ת��Ϊ�ַ����鲻���㣬��Ϊ�û����µ��ַ������������з��ţ������з����жϵȲ���ʱ�������������ȡ ���Բ���charAt()�������б����洢
		 */

		int k = 0;
		for (j = 0; j < sb.length(); j++) {
			if (sb.charAt(j) <= '9' && sb.charAt(j) >= '0' || sb.charAt(j) == '.') {
				num[k] += sb.charAt(j);// �ַ�����ƴ�Ӳ�������Ϊ�ڳ�ʼ����ʱ��Ϊ""
				continue;
			} else {
				fuhao[k] = sb.charAt(j);
				k++;
			}
		}
		// ����
		double result = 0;
		String isZero = "";// �����Ƿ�Ϊ���־
		String is = "����";
		for (int i = 0; i < operatorCount; i++) {

			// ȡǰ���������͵�һ��������������
			double num1 = Double.parseDouble(num[i]);// ���ַ�������ת��Ϊdouble���ͣ�ʹ��Double��װ��
			double num2 = Double.parseDouble(num[i + 1]);
			char ch = fuhao[i];

			// ����
			switch (ch) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				if (num2 == 0) {
					isZero = "��������Ϊ�㣬��������";
					try {
						if (!isZero.equals(is)) {
							throw new MyException("�쳣��" + isZero);
						}
						System.out.println(num1 / num2);
					} catch (MyException e) {
						e.printStackTrace();
					}
					result = 0;
					break;
				} else {
					result = num1 / num2;
					break;
				}
			case '%':// ����C���ԣ�ȡģ����
				result = (int) num1 % (int) num2;
				break;
			case '^':
				result = Math.pow(num1, num2);
				break;
			default:
				break;
			}
			num[i + 1] = String.valueOf(result);// ������ǿ��ת��Ϊ�ַ������ͣ���ʽת��������Ϊ��һ�μ����num1
		}

		if (isZero.equals("��������Ϊ�㣬��������"))
			return isZero;
		else
			return String.valueOf(result);// �����ַ������͵Ľ����������ǿ��ת��Ϊ�ַ������ͣ�
	}

	public static void main(String[] args) {
		new MyCalculator();
	}
}
