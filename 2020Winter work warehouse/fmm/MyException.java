package fmm;

import javax.swing.JOptionPane;

public class MyException extends Exception {

	String string;

	public MyException(String string) {
		super(string);
		this.string = string;
	}

	public void printStackTrace() {
		JOptionPane.showMessageDialog(null, string, "����", JOptionPane.ERROR_MESSAGE);// �Ի�����ʾ�ڴ������룬
	}
}