package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.Dao_NhanVien;
import entity.NhanVien;

public class Gui_QuanLyNhanVien extends JFrame implements ActionListener, MenuListener, MouseListener{
	private JMenu quanLyMenu;
	private JMenu banHangMenu;
	private JMenu thongKeMenu;
	private JMenu dangXuatMenu;
	private JToolBar toolBar = new JToolBar();
	private JButton btnPhim;
	private JButton btnSuatChieu;

	private JButton btnKhachHang;
	private JButton btnNhanVien;
	private JButton btnHoaDon;
	private JButton btnVe;
	private JRadioButton radMa;
	private JTextField txtTimMa;
	private JButton btnTim;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtTuoi;
	private JTextField txtDiaChi;
	private JTextField txtSoDienThoai;
	private JTextField txtEmail;
	private JTextField txtChucVu;
	private JTextField txtLuong;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnXoaRong;
	private JRadioButton radSapXepMa;
	private JRadioButton radSapXepTen;
	private JButton btnSapXep;
	private JTextField txtNgaySinh;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JLabel lblTimTheoMa;
	private JLabel lblTimTheoTen;
	private JTextField txtTimTen;
	private JButton btnTimTheoMa;
	private JButton btnTimTheoTen;
	private JRadioButton radSapXepTuoi;
	private JComboBox<String> cboChucVu;
	private JDateChooser ngaySinh;
	Dao_NhanVien dsnv = new Dao_NhanVien();
	private AbstractButton radSapXepLuong;
	private JButton btnDoanhThu;

	public Gui_QuanLyNhanVien() {
		setTitle("Quản lý nhân viên");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(104, 109, 224));

		// Thêm các JMenu
		quanLyMenu = new JMenu("Quản lý");
		banHangMenu = new JMenu("Nhân viên bán vé");
		thongKeMenu = new JMenu("Thống kê");
		dangXuatMenu = new JMenu("Đăng xuất");

		quanLyMenu.setPreferredSize(new Dimension(150, 40));
		quanLyMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
		quanLyMenu.setBackground(Color.white);
		quanLyMenu.setForeground(Color.black);
		quanLyMenu.setOpaque(true);

//		banHangMenu.setPreferredSize(new Dimension(150, 40));
//		banHangMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
//		banHangMenu.setForeground(Color.white);

		thongKeMenu.setPreferredSize(new Dimension(150, 40));
		thongKeMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
		thongKeMenu.setForeground(Color.white);

		dangXuatMenu.setPreferredSize(new Dimension(150, 40));
		dangXuatMenu.setBorder(new EmptyBorder(0, 25, 0, 50));
		dangXuatMenu.setForeground(Color.white);
		dangXuatMenu.setIcon(resizeImage("image//dangXuat-icon.jpg", 40, 40));

		menuBar.add(quanLyMenu);
		menuBar.add(thongKeMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(dangXuatMenu);

		setJMenuBar(menuBar);

		// Tạo JPanel cố định cho các nút điều khiển

		// Thêm các nút điều khiển
		toolBar.add(btnPhim = createControlButton("Phim", "image//movie.jpg", 40, 40));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		toolBar.add(btnSuatChieu = createControlButton("Suất chiếu", "image/suatchieu.jpg", 40, 40));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		toolBar.add(btnKhachHang = createControlButton("Khách hàng", "image//customer.jpg", 40, 40));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		toolBar.add(btnNhanVien = createControlButton("Nhân viên", "image/employee.jpg", 40, 40));
		btnNhanVien.setBackground(new Color(104, 109, 224));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		toolBar.add(btnHoaDon = createControlButton("Hóa đơn", "image/bill.jpg", 40, 40));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		toolBar.add(btnVe = createControlButton("Vé", "image/tiket.jpg", 40, 40));

		// Đặt controlPanel ở phía trên cùng (giống như thanh công cụ cố định)
		add(toolBar, BorderLayout.NORTH);

		// Thêm logo Cineplex
		JPanel jpWest = new JPanel();
		Box bWest = Box.createVerticalBox();
		Box bWest1, bWest2, bWest3, bWest4, bWest5, bWest6, bWest7, bWest8;
		bWest.setBackground(Color.lightGray);
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest1 = Box.createHorizontalBox());
		JLabel lblTitle;
		bWest1.add(lblTitle = new JLabel("Tra cứu nhân viên"));
		lblTitle.setForeground(new Color(104, 109, 224));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest2 = Box.createHorizontalBox());
		bWest2.add(lblTimTheoMa = new JLabel("Tìm nhân viên theo mã:  "));
//		bWest2.add(Box.createHorizontalStrut(85));
		bWest2.add(txtTimMa = new JTextField());
		bWest2.add(btnTimTheoMa = new JButton("Tìm"));
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest3 = Box.createHorizontalBox());
		bWest3.add(lblTimTheoTen = new JLabel("Tìm nhân viên theo tên: "));
//		bWest3.add(Box.createHorizontalStrut(85));
		bWest3.add(txtTimTen = new JTextField());
		bWest3.add(btnTimTheoTen = new JButton("Tìm"));
		bWest.add(Box.createVerticalStrut(20));
		
//		lblTimTheoMa.setPreferredSize(lblTimTheoTen.getPreferredSize());

//		bWest.add(bWest4 = Box.createHorizontalBox());
//		bWest4.add(btnTim = new JButton("Tìm"));
//		bWest.add(Box.createVerticalStrut(30));
		
		bWest.add(bWest4 = Box.createHorizontalBox());
		JLabel lblSapXep;
		bWest4.add(lblSapXep = new JLabel("Sắp xếp nhân viên theo tiêu chí"));
		lblSapXep.setForeground(new Color(104, 109, 224));
		lblSapXep.setFont(new Font("Arial", Font.BOLD, 20));
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest5 = Box.createHorizontalBox());
		bWest5.add(radSapXepTen = new JRadioButton("Sắp xếp theo tên nhân viên"));
		bWest5.add(Box.createHorizontalStrut(100));
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest6 = Box.createHorizontalBox());
		bWest6.add(radSapXepLuong = new JRadioButton("Sắp xếp theo lương nhân viên"));
		bWest6.add(Box.createHorizontalStrut(90));
		ButtonGroup groupSapXep = new ButtonGroup();
		groupSapXep.add(radSapXepTen);
		groupSapXep.add(radSapXepLuong);
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest7 = Box.createHorizontalBox());
		bWest7.add(btnSapXep = new JButton("Sắp xếp"));

		jpWest.add(bWest);
		jpWest.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(jpWest, BorderLayout.WEST);

		JPanel jpCenter = new JPanel();
		jpCenter.setBackground(Color.white);
		Box bCenter = Box.createVerticalBox();
		bCenter.setBackground(Color.blue);
		Box bCenter1, bCenter2, bCenter3, bCenter4, bCenter5, bCenter6;
		bCenter.add(Box.createVerticalStrut(30));
 
		bCenter.add(bCenter1 = Box.createHorizontalBox());
		bCenter1.add(Box.createHorizontalStrut(30));
		JLabel lblMa;
		bCenter1.add(lblMa = new JLabel("Mã nhân viên:  "));
		bCenter1.add(txtMa = new JTextField(25));
		bCenter1.add(Box.createHorizontalStrut(30));
		JLabel lblTen;
		bCenter1.add(lblTen = new JLabel("Tên nhân viên: "));
		bCenter1.add(txtTen = new JTextField());
		bCenter1.add(Box.createHorizontalStrut(10));
		bCenter1.add(lblPassword = new JLabel("Password: "));
		bCenter1.add(txtPassword = new JTextField());
		bCenter.add(Box.createVerticalStrut(30));

		bCenter.add(bCenter2 = Box.createHorizontalBox());
		bCenter2.add(Box.createHorizontalStrut(30));
		JLabel lblNgaySinh;
		bCenter2.add(lblNgaySinh = new JLabel("Ngày sinh:  "));
		ngaySinh = new JDateChooser();
		ngaySinh.setDateFormatString("yyyy-MM-dd");
		ngaySinh.setPreferredSize(new Dimension(38,5));
		bCenter2.add(ngaySinh);
		bCenter2.add(Box.createHorizontalStrut(30));
		JLabel lblDiaChi;
		bCenter2.add(lblDiaChi = new JLabel("Địa chỉ: "));
		bCenter2.add(txtDiaChi = new JTextField());
		bCenter.add(Box.createVerticalStrut(30));

		bCenter.add(bCenter3 = Box.createHorizontalBox());
		bCenter3.add(Box.createHorizontalStrut(30));
		JLabel lblSoDienThoai;
		bCenter3.add(lblSoDienThoai = new JLabel("Số điện thoại: "));
		bCenter3.add(txtSoDienThoai = new JTextField(28));
		txtSoDienThoai.setMaximumSize(new Dimension(2250, 20));
		bCenter3.add(Box.createHorizontalStrut(30));
		JLabel lblEmail;
		bCenter3.add(lblEmail = new JLabel("Email: "));
		bCenter3.add(txtEmail = new JTextField());
		txtEmail.setMaximumSize(new Dimension(2500, 20));
		bCenter3.add(Box.createHorizontalStrut(30));
		JLabel lblGioiTinh;
		bCenter3.add(lblGioiTinh = new JLabel("Giới tính:  "));
		bCenter3.add(radNam = new JRadioButton("Nam"));
		bCenter3.add(Box.createHorizontalStrut(30));
		bCenter3.add(radNu = new JRadioButton("Nữ"));
		bCenter3.add(Box.createHorizontalStrut(30));
		ButtonGroup group1 = new ButtonGroup();
		group1.add(radNam);
		group1.add(radNu);
		bCenter.add(Box.createVerticalStrut(30));
		
		bCenter.add(bCenter4 = Box.createHorizontalBox());
		bCenter4.add(Box.createHorizontalStrut(30));
		JLabel lblLuong;
		bCenter4.add(lblLuong = new JLabel("Lương: "));
		bCenter4.add(txtLuong = new JTextField(38));
		txtLuong.setMaximumSize(new Dimension(2500, 20));
		bCenter4.add(Box.createHorizontalStrut(30));
		JLabel lblChucVu;
		bCenter4.add(lblChucVu = new JLabel("Chức vụ:  "));
		bCenter4.add(cboChucVu = new JComboBox<String>());
		cboChucVu.addItem("Nhân viên bán vé");
		cboChucVu.addItem("Quản lý");
		bCenter.add(Box.createVerticalStrut(30));
		

		lblNgaySinh.setPreferredSize(lblMa.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblMa.getPreferredSize());
		lblChucVu.setPreferredSize(lblMa.getPreferredSize());

		lblDiaChi.setPreferredSize(lblTen.getPreferredSize());
		lblEmail.setPreferredSize(lblTen.getPreferredSize());
		lblLuong.setPreferredSize(lblTen.getPreferredSize());
		
		bCenter.add(bCenter5 = Box.createHorizontalBox());
		bCenter5.add(btnThem = new JButton("Thêm"));
		bCenter5.add(Box.createHorizontalStrut(20));
		bCenter5.add(btnCapNhat = new JButton("Cập nhật"));
		bCenter5.add(Box.createHorizontalStrut(20));
		bCenter5.add(btnXoa = new JButton("Xóa"));
		bCenter.add(Box.createVerticalStrut(30));

		bCenter.add(bCenter6 = Box.createHorizontalBox());
		String[] row = { "STT", "Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Số điện thoại", "Email", "Chức vụ", "Lương","Password" };
		tableModel = new DefaultTableModel(row, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setPreferredScrollableViewportSize(new Dimension(1050, 350));
//		bCenter.add(bCenter5 = Box.createHorizontalBox());
		bCenter6.add(Box.createHorizontalStrut(30));
		bCenter6.add(scroll);
		

		

		jpCenter.add(bCenter);
		add(jpCenter, BorderLayout.CENTER);

		btnPhim.addActionListener(this);
		btnSuatChieu.addActionListener(this);

		btnKhachHang.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnVe.addActionListener(this);

		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTimTheoTen.addActionListener(this);
		btnTimTheoMa.addActionListener(this);
		btnSapXep.addActionListener(this);

		quanLyMenu.addMenuListener(this);
		banHangMenu.addMenuListener(this);
		thongKeMenu.addMenuListener(this);
		dangXuatMenu.addMenuListener(this);
		
		table.addMouseListener(this);
		DocDuLieuDatabaseVaoTable();
	}

	private static void chinhMauMenu(JMenu menu1, JMenu menu2) {
		menu2.setForeground(Color.white);
		menu2.setBackground(new Color(104, 109, 224));
		menu2.setOpaque(true);

		menu1.setBackground(Color.white);
		menu1.setForeground(Color.black);
		menu1.setOpaque(true);
	}

	private static JButton createControlButton(String text, String iconPath, int width, int height) {
		JButton button = new JButton(text);
		ImageIcon icon = new ImageIcon(iconPath);
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImg);
		button.setIcon(icon); // Đường dẫn tới file icon
		button.setFocusable(false);
		return button;
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
		if (o.equals(btnPhim)) {
			new Gui_QuanLyPhim().setVisible(true);
			this.setVisible(false);
		}
		if (o.equals(btnSuatChieu)) {
			new Gui_QuanLySuatChieu().setVisible(true);
			this.setVisible(false);
		}
	
		if (o.equals(btnKhachHang)) {
			new Gui_QuanLyKhachHang().setVisible(true);
			this.setVisible(false);
		}
		if (o.equals(btnNhanVien)) {
			new Gui_QuanLyNhanVien().setVisible(true);
			this.setVisible(false);
		}
		if (o.equals(btnHoaDon)) {
			new Gui_QuanLyHoaDon().setVisible(true);
			this.setVisible(false);
		}
		if (o.equals(btnVe)) {
			new Gui_QuanLyVe().setVisible(true);
			this.setVisible(false);
		}
		if (o.equals(btnDoanhThu)) {
			new Gui_ThongKeDoanhThu().setVisible(true);
			this.setVisible(false);
		}

//		if (o.equals(btnTKKhachHang)) {
//			new Gui_ThongKeKhachHang().setVisible(true);
//			this.setVisible(false);
//		}
		
		if (o.equals(btnThem))
			actionThem();
		if (o.equals(btnCapNhat))
			actionCapNhat();
		if (o.equals(btnXoa))
			actionXoa();
		if (o.equals(btnTimTheoMa))
			actionTimTheoMa();
		if (o.equals(btnTimTheoTen))
			actionTimTheoTen();
		if (o.equals(btnSapXep))
			actionSapXep();
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		JMenu sourceMenu = (JMenu) e.getSource();
		toolBar.removeAll();
		if (sourceMenu.getText().equals("Quản lý")) {
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
			toolBar.add(btnVe = createControlButton("Vé", "image//ticket-icon.png", 40, 40));

			toolBar.revalidate();
			toolBar.repaint();

			btnPhim.addActionListener(this);
			btnSuatChieu.addActionListener(this);

			
			btnKhachHang.addActionListener(this);
			btnNhanVien.addActionListener(this);
			btnHoaDon.addActionListener(this);
			btnVe.addActionListener(this);
		}
//		if (sourceMenu.getText().equals("Nhân viên bán vé")) {
//			chinhMauMenu(banHangMenu, thongKeMenu, quanLyMenu);
//			toolBar.add(btnPhim = createControlButton("Phim", "image//movie-icon.png", 40, 40));
//			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
//			toolBar.add(createControlButton("Suất chiếu", "image//suatChieu-icon.png", 40, 40));
//			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
//
//			toolBar.revalidate();
//			toolBar.repaint();
//
//			btnPhim.addActionListener(this);
//		}
		if (sourceMenu.getText().equals("Thống kê")) {
			chinhMauMenu(thongKeMenu, quanLyMenu);
			toolBar.add(btnPhim = createControlButton("Phim", "image//movie-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
			toolBar.add(createControlButton("Suất chiếu", "image//suatChieu-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

			toolBar.revalidate();
			toolBar.repaint();

			btnPhim.addActionListener(this);
		}
		if (sourceMenu.getText().equals("Đăng xuất")) {
			new Gui_DangNhap().setVisible(true);
			this.setVisible(false);
		}
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		JMenu sourceMenu = (JMenu) e.getSource();
		toolBar.removeAll();
		if (sourceMenu.getText().equals("Quản lý")) {
			chinhMauMenu(quanLyMenu, thongKeMenu);
			toolBar.add(btnPhim = createControlButton("Phim", "image//movie-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
			toolBar.add(btnSuatChieu = createControlButton("Suất chiếu", "image//suatChieu-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
			toolBar.add(btnKhachHang = createControlButton("Khách hàng", "image//customer-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
			toolBar.add(btnNhanVien = createControlButton("Nhân viên", "image//employee-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
			toolBar.add(btnHoaDon = createControlButton("Hóa đơn", "image//hoaDon-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
			toolBar.add(btnVe = createControlButton("Vé", "image//ticket-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

			toolBar.revalidate();
			toolBar.repaint();

			btnPhim.addActionListener(this);
			btnSuatChieu.addActionListener(this);

			btnKhachHang.addActionListener(this);
			btnNhanVien.addActionListener(this);
			btnHoaDon.addActionListener(this);
			btnVe.addActionListener(this);
		}
		if (sourceMenu.getText().equals("Thống kê")) {
			chinhMauMenu(thongKeMenu, quanLyMenu);
			toolBar.add(btnDoanhThu = createControlButton("Doanh thu", "image//doanhthu-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

//			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
//			toolBar.add(btnTKKhachHang = createControlButton("Khách hàng", "image//customer-icon.png", 40, 40));
//			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

			toolBar.revalidate();
			toolBar.repaint();

			btnDoanhThu.addActionListener(this);

//			btnTKKhachHang.addActionListener(this);
		}
//		if (sourceMenu.getText().equals("Đăng xuất")) {
//			new Gui_DangNhap().setVisible(true);
//			this.setVisible(false);
//		}
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}


	
	private void DocDuLieuDatabaseVaoTable() {	
		ConnectDB db = ConnectDB.getInstance();
        db.connect();
		ArrayList<NhanVien> list = dsnv.layDanhSachNhanVien();
		int i = 1;
		for (NhanVien nv : list) {
			String gt = "Nam";
			String cv = "Quản lý";
			if(!nv.isGioiTinh()) {
				gt = "Nữ";
			}
			if(!nv.isChucVu()) {
				cv = "Nhân viên bán vé";
			}
			tableModel.addRow(new Object[] { i, nv.getMaNhanVien(), nv.getHoTen(),nv.getNgaySinh(),
							gt, nv.getDiaChi(), nv.getSoDienThoai(), nv.getEmail(), cv, nv.getLuong(), nv.getPassword() });
			i++;
		}
		table.setModel(tableModel);
	}

	public void XoaDataTrenModel() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.getDataVector().removeAllElements();
	}
	
	private void actionXoa() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if (row != -1) {
			String maNhanVien = (String) table.getModel().getValueAt(row, 1);
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa? ", "Chú ý",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION)
				if (dsnv.xoaNhanVien(maNhanVien)) {
					tableModel.removeRow(row);
				}
		}
	}

	private void actionCapNhat() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		NhanVien nhanVien = taoNhanVienMoi();
		if (row >= 0) {
			if (dsnv.capNhatNhanVien(nhanVien)) {
				table.setValueAt(nhanVien.getHoTen(), row, 2);
				table.setValueAt(nhanVien.getNgaySinh(), row, 3);
				table.setValueAt(nhanVien.isGioiTinh() ? "Nam" : "Nữ", row, 4);
				table.setValueAt(nhanVien.getDiaChi(), row, 5);
				table.setValueAt(nhanVien.getEmail(), row, 7);
				table.setValueAt(nhanVien.getSoDienThoai(), row, 6);
				table.setValueAt(nhanVien.isChucVu() ? "Quản lý" : "Nhân Viên bán vé", row, 8);
				table.setValueAt(nhanVien.getLuong(), row, 9);
				table.setValueAt(nhanVien.getPassword(), row, 10);
				JOptionPane.showMessageDialog(null, "Cập nhật thành công.");
				actionXoaTrang();
			}
		}
	}

	private void actionThem() {
		// TODO Auto-generated method stub
		dsnv.layDanhSachNhanVien();
		if (kiemTraDuLieu()) {
			NhanVien nv = taoNhanVienMoi();
			if (dsnv.themNhanVien(nv)) {
				String gt = "Nam";
				String cv = "Quản lý";
				if(!nv.isGioiTinh()) {
					gt = "Nữ";
				}
				if(!nv.isChucVu()) {
					cv = "Nhân viên bán vé";
				}
				tableModel.addRow(new Object[] { tableModel.getRowCount()+1, nv.getMaNhanVien(), nv.getHoTen(),nv.getNgaySinh(),
						gt, nv.getDiaChi(), nv.getSoDienThoai(), nv.getEmail(), cv, nv.getLuong(), nv.getPassword() });
				actionXoaTrang();
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã nhân viên.");
				txtMa.requestFocus();
				txtMa.selectAll();
			}
		}
	}

	private void actionXoaTrang() {
		// TODO Auto-generated method stub
		txtMa.setText("");
		txtTen.setText("");
		txtDiaChi.setText("");
		ngaySinh.setDate(null);
		txtSoDienThoai.setText("");
		txtEmail.setText("");
		radNu.setSelected(true);
		txtLuong.setText("");
		cboChucVu.setSelectedIndex(0);
		txtPassword.setText("");
	}

	private NhanVien taoNhanVienMoi() {
		String maNhanVien = txtMa.getText().trim();
		String tenNhanVien = txtTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		LocalDate ngaySinh1 = ngaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String soDienThoai = txtSoDienThoai.getText().trim();
		String email = txtEmail.getText().trim();
		boolean gioiTinh = radNam.isSelected() ? true : false;
		double luong = Double.parseDouble(txtLuong.getText());
		String chucVu = cboChucVu.getSelectedItem().toString();
		boolean cv = false;
		if (chucVu.equals("Quản lý")){
			cv = true;
		}
		String password = txtPassword.getText().trim();
		return new NhanVien(maNhanVien, tenNhanVien, password, ngaySinh1, diaChi, soDienThoai, email, cv, gioiTinh, luong);
	}
	
	private boolean kiemTraDuLieu() {
		String maNhanVien = txtMa.getText().trim();
		String tenNhanVien = txtTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		LocalDate ngaySinh1 = null;
		String soDienThoai = txtSoDienThoai.getText().trim();
		String email = txtEmail.getText().trim();
		String password = txtPassword.getText().trim();
		double luong;

		if (!maNhanVien.matches("(NV){1}[0-9]{3}")) {
			JOptionPane.showMessageDialog(null, "Mã nhân viên có dạng NV theo sau 3 số.");
			txtMa.selectAll();
			txtMa.requestFocus();
			return false;
		}

		if (!tenNhanVien.matches("[\\w\\s\\']+")) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không chứa ký tự đặc biệt.");
			txtTen.selectAll();
			txtTen.requestFocus();
			return false;
		}

		if (!diaChi.matches("[\\w\\s\\/\\,\\.']+")) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không chứa ký tự đặc biệt.");
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			return false;
		}

		try {
			if (txtLuong.getText().trim().isBlank()) {
				JOptionPane.showMessageDialog(null, "Lương không được rỗng.");
				txtLuong.selectAll();
				txtLuong.requestFocus();
				return false;
			} else {
				luong = Double.parseDouble(txtLuong.getText());
				if (luong < 5000) {
					JOptionPane.showMessageDialog(null, "Lương phải lớn hơn 5.000.");
					txtLuong.selectAll();
					txtLuong.requestFocus();
					return false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Lương phải là số.");
			txtLuong.selectAll();
			txtLuong.requestFocus();
			return false;
		}

		if (ngaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() == null) {
			JOptionPane.showMessageDialog(null, "Ngày khởi chiếu không được rỗng.");
			return false;
		} else {
			ngaySinh1 = ngaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (ngaySinh1.isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(null, "Ngày sinh phải trước ngày hiện tại.");
				return false;
			}
		}

		if(!soDienThoai.matches("(03|05|07|08|09)\\d{8}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu 03, 05, 07, 08, 09 theo sau 8 số.");
			txtSoDienThoai.selectAll();
			txtSoDienThoai.requestFocus();
			return false;
		}
		
		if (!email.matches("[\\w\\.]+@(gmail|yahoo|outlook){1}(.com){1}")) {
			JOptionPane.showMessageDialog(null, "Email có dạng example@gmail.com hoặc yahoo.com hoặc outlook.com");
			txtEmail.selectAll();
			txtEmail.requestFocus();
			return false;
		}
		if(password.length() < 4) {
			JOptionPane.showMessageDialog(null, "Password phải hơn 4 lí tự");
			txtPassword.selectAll();
			txtPassword.requestFocus();
			return false;
		}

		return true;
	}
	private void actionSapXep() {
		// TODO Auto-generated method stub
		if (radSapXepTen.isSelected()) {
			XoaDataTrenModel();
			ArrayList<NhanVien> list = dsnv.sapXepTheoTen();
			int i = 1;
			for (NhanVien nv : list) {
				String gt = "Nam";
				String cv = "Quản lý";
				if(!nv.isGioiTinh()) {
					gt = "Nữ";
				}
				if(!nv.isChucVu()) {
					cv = "Nhân viên bán vé";
				}
				tableModel.addRow(new Object[] { i, nv.getMaNhanVien(), nv.getHoTen(),nv.getNgaySinh(),
								gt, nv.getDiaChi(), nv.getSoDienThoai(), nv.getEmail(), cv, nv.getLuong(), nv.getPassword() });
				i++;
			}
		}
		if (radSapXepLuong.isSelected()) {
			XoaDataTrenModel();
			ArrayList<NhanVien> list = dsnv.sapXepTheoLuong();
			int i = 1;
			for (NhanVien nv : list) {
				String gt = "Nam";
				String cv = "Quản lý";
				if(!nv.isGioiTinh()) {
					gt = "Nữ";
				}
				if(!nv.isChucVu()) {
					cv = "Nhân viên bán vé";
				}
				tableModel.addRow(new Object[] { i, nv.getMaNhanVien(), nv.getHoTen(),nv.getNgaySinh(),
								gt, nv.getDiaChi(), nv.getSoDienThoai(), nv.getEmail(), cv, nv.getLuong(), nv.getPassword() });
				i++;
			}
		}
	}

	private void actionTimTheoMa() {
		// TODO Auto-generated method stub
		
		String timMaNV = txtTimMa.getText().trim();
		ArrayList<NhanVien> list = dsnv.timNhanVienTheoMa(timMaNV);
		if(list.size() > 0){
			XoaDataTrenModel();
			int i = 1;
			for (NhanVien nv : list) {
				String gt = "Nam";
				String cv = "Quản lý";
				if(!nv.isGioiTinh()) {
					gt = "Nữ";
				}
				if(!nv.isChucVu()) {
					cv = "Nhân viên bán vé";
				}
				tableModel.addRow(new Object[] { i, nv.getMaNhanVien(), nv.getHoTen(),nv.getNgaySinh(),
								gt, nv.getDiaChi(), nv.getSoDienThoai(), nv.getEmail(), cv, nv.getLuong(), nv.getPassword() });
				i++;

			}
		}else {
			JOptionPane.showMessageDialog(null, "Không tìm thấy.");
			txtTimMa.requestFocus();
			txtTimMa.selectAll();
		}
	}

	private void actionTimTheoTen() {
		// TODO Auto-generated method stub
		String timTenNV = txtTimTen.getText().trim();
		ArrayList<NhanVien> list = dsnv.timNhanVienTheoTen(timTenNV);
		System.err.println(timTenNV);
		if(list.size() > 0){
			XoaDataTrenModel();
			int i = 1;
			for (NhanVien nv : list) {
				String gt = "Nam";
				String cv = "Quản lý";
				if(!nv.isGioiTinh()) {
					gt = "Nữ";
				}
				if(!nv.isChucVu()) {
					cv = "Nhân viên bán vé";
				}
				tableModel.addRow(new Object[] { i, nv.getMaNhanVien(), nv.getHoTen(),nv.getNgaySinh(),
								gt, nv.getDiaChi(), nv.getSoDienThoai(), nv.getEmail(), cv, nv.getLuong(), nv.getPassword() });
				i++;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Không tìm thấy.");
			txtTimTen.requestFocus();
			txtTimTen.selectAll();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
	    
	    // Thiết lập giá trị cho các trường
	    txtMa.setText(tableModel.getValueAt(row, 1).toString());
	    txtTen.setText(tableModel.getValueAt(row, 2).toString());
	    ngaySinh.setDate(Date.valueOf(tableModel.getValueAt(row, 3).toString()));
	    if(tableModel.getValueAt(row, 4).toString().equals("Nam")) {
	    	radNam.setSelected(true);
	    }
	    if(tableModel.getValueAt(row, 4).toString().equals("Nữ")) {
	    	radNu.setSelected(true);
	    }
	    txtDiaChi.setText(tableModel.getValueAt(row, 5).toString());
	    txtSoDienThoai.setText(tableModel.getValueAt(row, 6).toString());
	    txtEmail.setText(tableModel.getValueAt(row, 7).toString());
	    if(tableModel.getValueAt(row, 8).toString().equals("Nhân viên bán vé")) {
	    	cboChucVu.setSelectedItem("Nhân viên bán vé");
	    }
	    if(tableModel.getValueAt(row, 8).toString().equals("Quản lý")) {
	    	cboChucVu.setSelectedItem("Quản lý");
	    }
	    txtLuong.setText(tableModel.getValueAt(row, 9).toString());
	    txtPassword.setText(tableModel.getValueAt(row, 10).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

	public static void main(String[] args) {
		new Gui_QuanLyNhanVien().setVisible(true);
	}

}
