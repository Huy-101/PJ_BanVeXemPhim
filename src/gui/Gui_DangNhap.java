package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import dao.Dao_NhanVien;
import entity.NhanVien;

public class Gui_DangNhap extends JFrame implements ActionListener, KeyListener {
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JTextField txtShowPassword; // Hiển thị mật khẩu
    private JButton btnLogin;
    private JButton btnToggle;
    private Box b4; // Box chứa txtPassword hoặc txtShowPassword
    private ImageIcon eyeClosedImg = new ImageIcon("image//eye-close.png");
    private ImageIcon eyeOpenImg = new ImageIcon("image//eye-open.png");
    private boolean isPasswordVisible = false;
    private Dao_NhanVien dsnv = new Dao_NhanVien();

    public Gui_DangNhap() {
        setTitle("Đăng nhập");
        setSize(500, 240);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // === PHÍA TRÁI - HÌNH ẢNH ===
        JPanel jpWest = new JPanel();
        jpWest.setBackground(Color.WHITE);
        ImageIcon imgLogin = new ImageIcon("image//login.png");
        Image img = imgLogin.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);
        JLabel lblImgLogin = new JLabel(new ImageIcon(img));
        jpWest.add(lblImgLogin);
        add(jpWest, BorderLayout.WEST);

        // === PHÍA GIỮA - FORM ===
        Box b = Box.createVerticalBox();
        Box b1, b2, b3;

        b.add(Box.createVerticalStrut(20));

        // --- DÒNG 1: TÀI KHOẢN ---
        b.add(b1 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        JLabel lblUser = new JLabel("Tài khoản: ");
        txtUser = new JTextField();
        txtUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtUser.getPreferredSize().height + 10));
        b1.add(lblUser);
        b1.add(txtUser);
        b1.add(Box.createHorizontalStrut(20));

        // --- DÒNG 2: MẬT KHẨU + NÚT HIỆN/ẨN ---
        b.add(b2 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        JLabel lblPassword = new JLabel("Mật khẩu:  ");
        b4 = Box.createHorizontalBox(); // Khởi tạo b4 tại đây
        txtPassword = new JPasswordField();
        txtShowPassword = new JTextField();
        txtShowPassword.setVisible(false); // Ẩn ban đầu

        // Đặt kích thước đồng bộ
        txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtPassword.getPreferredSize().height + 10));
        txtShowPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtShowPassword.getPreferredSize().height + 10));

        // Thêm vào b4
        b4.add(txtPassword);

        // Nút toggle
        btnToggle = new JButton(resizeIcon(eyeClosedImg, 20, 20));
        btnToggle.setPreferredSize(new Dimension(30, 30));
        btnToggle.setContentAreaFilled(false);
        btnToggle.setBorderPainted(false);
        btnToggle.setFocusPainted(false);

        b2.add(lblPassword);
        b2.add(b4);
        b2.add(btnToggle);
        b2.add(Box.createHorizontalStrut(20));

        // --- DÒNG 3: NÚT ĐĂNG NHẬP ---
        b.add(b3 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(10));
        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(0, 70, 160), 2, true));
        b3.add(btnLogin);

        b.add(Box.createVerticalStrut(20));
        add(b, BorderLayout.CENTER);

        // === ĐỒNG BỘ KÍCH THƯỚC LABEL ===
        lblPassword.setPreferredSize(lblUser.getPreferredSize());

        // === VIỀN CHO TEXTFIELD ===
        Border border = BorderFactory.createLineBorder(new Color(180, 180, 180));
        txtUser.setBorder(border);
        txtPassword.setBorder(border);
        txtShowPassword.setBorder(border);

        // === MÀU CHỮ LABEL ===
        Color blueText = new Color(0, 51, 153);
        lblUser.setForeground(blueText);
        lblPassword.setForeground(blueText);

        // === SỰ KIỆN ===
        btnLogin.addActionListener(this);
        btnToggle.addActionListener(this);
        txtUser.addKeyListener(this);
        txtPassword.addKeyListener(this);
        txtShowPassword.addKeyListener(this);
        btnLogin.addKeyListener(this);
    }

    // === PHÓNG TO ICON ===
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            actionLogin();
        } else if (e.getSource() == btnToggle) {
            actionToggle();
        }
    }

    private void actionToggle() {
        if (b4 == null) return;

        b4.removeAll();

        if (isPasswordVisible) {
            // Ẩn → hiện JPasswordField
            txtPassword.setText(txtShowPassword.getText());
            b4.add(txtPassword);
            btnToggle.setIcon(resizeIcon(eyeClosedImg, 20, 20));
            txtShowPassword.setVisible(false);
            txtPassword.setVisible(true);
        } else {
            // Hiện → hiển thị JTextField
            txtShowPassword.setText(new String(txtPassword.getPassword()));
            b4.add(txtShowPassword);
            btnToggle.setIcon(resizeIcon(eyeOpenImg, 20, 20));
            txtPassword.setVisible(false);
            txtShowPassword.setVisible(true);
        }

        isPasswordVisible = !isPasswordVisible;
        b4.revalidate();
        b4.repaint();
    }

    private void actionLogin() {
        String user = txtUser.getText().trim();
        String password = isPasswordVisible
                ? txtShowPassword.getText().trim()
                : new String(txtPassword.getPassword()).trim();

        if (user.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản và mật khẩu!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ArrayList<NhanVien> nhanVien = dsnv.layDanhSachNhanVien();
        for (NhanVien nv : nhanVien) {
            if (nv.getMaNhanVien().equals(user) && nv.getPassword().equals(password)) {
                this.dispose();
                if (nv.isChucVu()) {
                    new Gui_TrangChuQuanLy().setVisible(true);
                } else {
                    new Gui_BanVe(nv).setVisible(true);
                }
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu sai!", "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
        txtUser.selectAll();
        txtUser.requestFocus();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLogin.doClick();
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Gui_DangNhap().setVisible(true));
    }
}