package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.Dao_NhanVien;
import entity.NhanVien;

public class Gui_DangNhap extends JFrame implements ActionListener, KeyListener {
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JButton btnToggle;
	private JButton btnForget;
	private Box b4;
	private ImageIcon eyeClosedImg = new ImageIcon("image//eye-close.png");
	private ImageIcon eyeOpenImg = new ImageIcon("image//eye-open.png");
	private boolean isPasswordVisible = false;
	private JTextField txtShowPassword = new JTextField();
	private Dao_NhanVien dsnv = new Dao_NhanVien();

	public Gui_DangNhap() {
		setTitle("Đăng nhập");
		setSize(500, 240);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.gray);

		JPanel jpWest = new JPanel();
		ImageIcon imgLogin = new ImageIcon("image//login.png");
		Image img = imgLogin.getImage();
		Image newImg = img.getScaledInstance(170, 170, Image.SCALE_SMOOTH);
		imgLogin = new ImageIcon(newImg);
		JLabel lblImgLogin;
		jpWest.add(lblImgLogin = new JLabel(imgLogin));
		add(jpWest, BorderLayout.WEST);

		Box b = Box.createVerticalBox();
		Box b1, b2, b3;

		b.add(Box.createVerticalStrut(20));
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		JLabel lblUser;
		b1.add(lblUser = new JLabel("Tài khoản: "));
		b1.add(txtUser = new JTextField());
		txtUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtUser.getPreferredSize().height + 10));
		b1.add(Box.createHorizontalStrut(20));

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		JLabel lblPassword;
		b2.add(lblPassword = new JLabel("Mật khẩu: "));
		b2.add(b4 = Box.createHorizontalBox());
		b4.add(txtPassword = new JPasswordField());
		txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtPassword.getPreferredSize().height + 10));
		txtShowPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtPassword.getPreferredSize().height + 10));
		Image img1 = eyeClosedImg.getImage();
		Image newImg1 = img1.getScaledInstance(10, 20, Image.SCALE_SMOOTH);
		eyeClosedImg = new ImageIcon(newImg1);
		b2.add(btnToggle = new JButton(eyeClosedImg));
		b2.add(Box.createHorizontalStrut(20));

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(btnLogin = new JButton("ĐĂNG NHẬP"));
		btnLogin.setBackground(new Color(104, 109, 224));
		btnLogin.setForeground(Color.white);
		b.add(Box.createVerticalStrut(20));

		lblPassword.setPreferredSize(lblUser.getPreferredSize());

		add(b, BorderLayout.CENTER);

		btnLogin.addActionListener(this);
		btnToggle.addActionListener(this);

		txtUser.addKeyListener(this);
		txtPassword.addKeyListener(this);
		txtShowPassword.addKeyListener(this);
		btnLogin.addKeyListener(this);
		btnToggle.addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnLogin))
			actionLogin();
		if (o.equals(btnToggle))
			actionToggle();
	}

	private void actionToggle() {
		// TODO Auto-generated method stub
		if (isPasswordVisible) {
			txtPassword.setText(txtShowPassword.getText());
			b4.remove(txtShowPassword);
			b4.add(txtPassword, 0);
			btnToggle.setIcon(eyeClosedImg);
		} else {
			txtShowPassword.setText(new String(txtPassword.getPassword()));
			b4.remove(txtPassword);
			b4.add(txtShowPassword, 0);
			Image img2 = eyeOpenImg.getImage();
			Image newImg2 = img2.getScaledInstance(10, 20, Image.SCALE_SMOOTH);
			eyeOpenImg = new ImageIcon(newImg2);
			btnToggle.setIcon(eyeOpenImg);
		}
		isPasswordVisible = !isPasswordVisible;
		b4.revalidate();
		b4.repaint();
	}

	private void actionLogin() {
		// TODO Auto-generated method stub
		String user = txtUser.getText().trim();
		String password;
		if (isPasswordVisible) {
			password = new String(txtPassword.getPassword()).trim();
		} else {
			password = new String(txtPassword.getPassword()).trim();
		}
		ArrayList<NhanVien> nhanVien = dsnv.layDanhSachNhanVien();
		boolean found = false;
		for (NhanVien nv : nhanVien) {
			if (nv.getMaNhanVien().equals(user) && nv.getPassword().equals(password)) {
				found = true;
				if (nv.isChucVu()) {
					new Gui_TrangChuQuanLy().setVisible(true);
				} else {
					new Gui_BanVe(nv).setVisible(true);
				}
				this.setVisible(false);
				break;
			}
		}
		if (!found) {
			JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu sai.");
			txtUser.selectAll();
			txtUser.requestFocus();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			btnLogin.doClick();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new Gui_DangNhap().setVisible(true);
	}

}
