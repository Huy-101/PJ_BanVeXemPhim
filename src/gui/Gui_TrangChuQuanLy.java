package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Gui_TrangChuQuanLy extends JFrame implements ActionListener, MenuListener{
	
	private JButton btnPhim;
	private JToolBar toolBar = new JToolBar();
	private JButton btnSuatChieu;
	private JMenu quanLyMenu;
	private JMenu banHangMenu;
	private JMenu thongKeMenu;    
	private JMenu dangXuatMenu;
	private JButton btnKhachHang;
	private JButton btnNhanVien;
	private JButton btnHoaDon;
	private JButton btnVe;
	private AbstractButton btnTaiKhoan; 

	public Gui_TrangChuQuanLy() {
		setTitle("Trang chủ");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Thanh menu xanh đậm
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(78,133,248)); // xanh nhạt
		menuBar.setBorderPainted(false);

		// Tạo các menu
		quanLyMenu = new JMenu("Quản lý");
		thongKeMenu = new JMenu("Thống kê");
		dangXuatMenu = new JMenu("Đăng xuất");

		// Font và màu chữ menu
		Font fontMenu = new Font("Segoe UI", Font.BOLD, 16);
		Color mauChu = Color.WHITE;

		quanLyMenu.setFont(fontMenu);
		thongKeMenu.setFont(fontMenu);
		dangXuatMenu.setFont(fontMenu);

		quanLyMenu.setForeground(mauChu);
		thongKeMenu.setForeground(mauChu);
		dangXuatMenu.setForeground(mauChu);

		quanLyMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
		thongKeMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
		dangXuatMenu.setBorder(new EmptyBorder(0, 25, 0, 50));
		dangXuatMenu.setIcon(resizeImage("image//dangXuat-icon.jpg", 40, 40));

		// Thêm menu vào thanh menu
		menuBar.add(quanLyMenu);
		menuBar.add(thongKeMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(dangXuatMenu);

		setJMenuBar(menuBar);

		//Thanh công cụ xanh nhạt
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(new Color(78,133,248)); // xanh lam nhạt
		toolBar.setBorder(new EmptyBorder(10, 20, 10, 20));

		// Các nút công cụ
		toolBar.add(btnPhim = createControlButton("Phim", "image//movie.jpg", 40, 40));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		toolBar.add(btnSuatChieu = createControlButton("Suất chiếu", "image/suatchieu.jpg", 40, 40));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		toolBar.add(btnKhachHang = createControlButton("Khách hàng", "image/customer.jpg", 40, 40));
		btnKhachHang.setBackground(new Color(104, 109, 224));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		toolBar.add(btnNhanVien = createControlButton("Nhân viên", "image//employee.jpg", 40, 40));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		toolBar.add(btnHoaDon = createControlButton("Hóa đơn", "image/bill.jpg", 40, 40));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		toolBar.add(btnVe = createControlButton("Vé", "image/tiket.jpg", 40, 40));

		add(toolBar, BorderLayout.NORTH);

		// 🖼️ Logo ở trung tâm – nền trắng
		JLabel logoLabel = new JLabel(resizeImage("image//cinema-logo.jpg", 800, 500), SwingConstants.CENTER);
		logoLabel.setBackground(Color.WHITE);
		logoLabel.setOpaque(true);
		add(logoLabel, BorderLayout.CENTER);

		// Sự kiện
		btnPhim.addActionListener(this);
		btnSuatChieu.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnVe.addActionListener(this);

		quanLyMenu.addMenuListener(this);
		//banHangMenu.addMenuListener(this);
		thongKeMenu.addMenuListener(this);
		dangXuatMenu.addMenuListener(this);


	}
	
	private static void chinhMauMenu(JMenu menu1, JMenu menu2) {
		menu2.setForeground(Color.white);
		menu2.setBackground(new Color(104, 109, 224));
		menu2.setOpaque(true);
		
//		menu3.setForeground(Color.white);
//		menu3.setBackground(new Color(104, 109, 224));
//		menu3.setOpaque(true);
        
		menu1.setBackground(Color.white);
		menu1.setForeground(Color.black);
		menu1.setOpaque(true);
    }
	
	private JButton createControlButton(String text, String iconPath, int width, int height) {
	    JButton btn = new JButton(text, resizeImage(iconPath, width, height));

	    // Font và căn chỉnh
	    btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
	    btn.setHorizontalTextPosition(SwingConstants.RIGHT);
	    btn.setIconTextGap(10);
	    btn.setFocusPainted(false);

	    //Nền xanh sáng (65,165,238) + chữ trắng
	    btn.setBackground(new Color(65, 165, 238));
	    btn.setForeground(Color.WHITE);
	    btn.setBorder(BorderFactory.createLineBorder(new Color(41, 128, 185), 2, true)); // xanh đậm viền nhẹ
	    btn.setPreferredSize(new Dimension(140, 50));

	    //Hiệu ứng hover – xanh đậm hơn một chút
	    btn.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            btn.setBackground(new Color(41, 128, 185)); // hover: xanh đậm hơn
	        }

	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            btn.setBackground(new Color(65, 165, 238)); // trở lại xanh sáng
	        }
	    });

	    return btn;
	}

	
	private static ImageIcon resizeImage(String iconPath, int width, int height) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);
        return icon;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnPhim)) {
			new Gui_QuanLyPhim().setVisible(true);
			this.setVisible(false);
		}
		if(o.equals(btnSuatChieu)) {
			new Gui_QuanLySuatChieu().setVisible(true);
			this.setVisible(false);
		}
		if (o.equals(btnKhachHang)) {
			new Gui_QuanLyKhachHang().setVisible(true);
			this.setVisible(false);
		}
		if(o.equals(btnNhanVien)) {
			new Gui_QuanLyNhanVien().setVisible(true);
			this.setVisible(false);
		}
		if(o.equals(btnHoaDon)) {
			new Gui_QuanLyHoaDon().setVisible(true);
			this.setVisible(false);
		}
		if(o.equals(btnVe)) {
			new Gui_QuanLyVe().setVisible(true);
			this.setVisible(false);
		}
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		JMenu sourceMenu = (JMenu) e.getSource();
		toolBar.removeAll();
		if(sourceMenu.getText().equals("Quản lý")) {
			chinhMauMenu(quanLyMenu, thongKeMenu);
	        toolBar.add(btnPhim = createControlButton("Phim", "image//movie-icon.png", 40, 40));
	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        toolBar.add(btnSuatChieu = createControlButton("Suất chiếu", "image//suatChieu-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
			toolBar.add(btnKhachHang = createControlButton("Khách hàng", "image//customer-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        toolBar.add(btnNhanVien = createControlButton("Nhân viên", "image//employee-icon.png", 40, 40));
	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        toolBar.add(btnHoaDon = createControlButton("Hóa đơn", "image//hoaDon-icon.png", 40, 40));
	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        toolBar.add(btnVe = createControlButton("Vé ", "image//ticket-icon.png", 40, 40));


            toolBar.revalidate();
            toolBar.repaint();
            
    		btnPhim.addActionListener(this);
            btnSuatChieu.addActionListener(this);
			btnKhachHang.addActionListener(this);
            btnNhanVien.addActionListener(this);
            btnHoaDon.addActionListener(this);
            btnVe.addActionListener(this);
		}
//		if(sourceMenu.getText().equals("Bán hàng")) {
//			chinhMauMenu(banHangMenu, thongKeMenu, quanLyMenu);
//	        toolBar.add(btnPhim = createControlButton("Phim", "image//movie-icon.png", 40, 40));
//	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
//	        toolBar.add(createControlButton("Suất chiếu", "image//suatChieu-icon.png", 40, 40));
//	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
//	        
//	        toolBar.revalidate();
//            toolBar.repaint();
//            
//            btnPhim.addActionListener(this);
//            btnSuatChieu.addActionListener(this);
//		}
		if(sourceMenu.getText().equals("Thống kê")) {
			chinhMauMenu(thongKeMenu  , quanLyMenu);
			toolBar.add(btnPhim = createControlButton("Phim", "image//movie-icon.png", 40, 40));
	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        toolBar.add(createControlButton("Suất chiếu", "image//suatChieu-icon.png", 40, 40));
	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        
	        toolBar.revalidate();
            toolBar.repaint();
            
            btnPhim.addActionListener(this);
            btnSuatChieu.addActionListener(this);
		}
		if(sourceMenu.getText().equals("Đăng xuất")) {
			new Gui_DangNhap().setVisible(true);
			this.setVisible(false);
		}
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new Gui_TrangChuQuanLy().setVisible(true);
	}
}
