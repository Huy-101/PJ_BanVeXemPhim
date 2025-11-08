package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import dao.Dao_KhachHang;
import entity.KhachHang;

public class Gui_QuanLyKhachHang extends JFrame implements ActionListener, MenuListener,MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu quanLyMenu;
	private JMenu thongKeMenu;
	private JMenu banHangMenu;
	private JMenu dangXuatMenu;
	private JToolBar toolBar = new JToolBar();
	private JButton btnPhim;
	private JButton btnSuatChieu;
	private JButton btnThucPham;
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
	private JTextField txtEmail;
	private JTextField txtSoDienThoai;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnXoaRong;
	private JRadioButton radSapXepMa;
	private JRadioButton radSapXepTen;
	private JButton btnSapXep;
	private JLabel lblTimTenPhim;
	private JTextField txtTimTenPhim;
	private JButton btnTimTenPhim;
	private JButton btnTaiKhoan;
	private Dao_KhachHang dskh;
	private JButton btnDoanhThu;
	private JButton btnSanPham;
	private JButton btnTKKhachHang;
	private JDateChooser ngaySinh;
	private JTextField txtTim;
	public Gui_QuanLyKhachHang() {
		dskh = new Dao_KhachHang();

		setTitle("Quản lý khách hàng");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(104, 109, 224));

		// Thêm các JMenu
		quanLyMenu = new JMenu("Quản lý");
		thongKeMenu = new JMenu("Thống kê");
		dangXuatMenu = new JMenu("Đăng xuất");

		quanLyMenu.setPreferredSize(new Dimension(150, 40));
		quanLyMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
		quanLyMenu.setBackground(Color.white);
		quanLyMenu.setForeground(Color.black);
		quanLyMenu.setOpaque(true);

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
		// Thêm các nút điều khiển
				toolBar.add(btnPhim = createControlButton("Phim", "image//movie-icon.png", 40, 40));
				toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
				toolBar.add(btnSuatChieu = createControlButton("Suất chiếu", "image//suatChieu-icon.png", 40, 40));
				toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
				toolBar.add(btnThucPham = createControlButton("Thực phẩm", "image//bap-icon.png", 40, 40));
				toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
				toolBar.add(btnKhachHang = createControlButton("Khách hàng", "image//customer-icon.png", 40, 40));
				btnKhachHang.setBackground(new Color(104, 109, 224));
				toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
				toolBar.add(btnNhanVien = createControlButton("Nhân viên", "image//employee-icon.png", 40, 40));
				toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
				toolBar.add(btnHoaDon = createControlButton("Hóa đơn", "image//hoaDon-icon.png", 40, 40));
				toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
				toolBar.add(btnVe = createControlButton("Vé", "image//ticket-icon.png", 40, 40));
			
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
				bWest1.add(lblTitle = new JLabel("Tra cứu khách hàng"));
				lblTitle.setForeground(new Color(104, 109, 224));
				lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
				bWest.add(Box.createVerticalStrut(10));

				bWest.add(bWest3 = Box.createHorizontalBox());
				JLabel lblTim;
				bWest3.add(lblTim = new JLabel("Tìm kiếm theo tên khách hàng: "));
				bWest3.add(txtTim = new JTextField());
				bWest3.add(btnTim = new JButton("Tìm"));
				bWest.add(Box.createVerticalStrut(20));

				bWest.add(bWest5 = Box.createHorizontalBox());
				JLabel lblSapXep;
				bWest5.add(lblSapXep = new JLabel("Sắp xếp khách hàng theo tiêu chí"));
				lblSapXep.setForeground(new Color(104, 109, 224));
				lblSapXep.setFont(new Font("Arial", Font.BOLD, 20));
				bWest.add(Box.createVerticalStrut(10));

				bWest.add(bWest6 = Box.createHorizontalBox());
				bWest6.add(radSapXepMa = new JRadioButton("Sắp xếp theo mã khách hàng"));
				bWest6.add(Box.createHorizontalStrut(90));
				bWest.add(Box.createVerticalStrut(10));

				bWest.add(bWest7 = Box.createHorizontalBox());
				bWest7.add(radSapXepTen = new JRadioButton("Sắp xếp theo tên khách hàng"));
				bWest7.add(Box.createHorizontalStrut(90));
				ButtonGroup groupSapXep = new ButtonGroup();
				groupSapXep.add(radSapXepMa);
				groupSapXep.add(radSapXepTen);
				bWest.add(Box.createVerticalStrut(20));

				bWest.add(bWest8 = Box.createHorizontalBox());
				bWest8.add(btnSapXep = new JButton("Sắp xếp"));

				jpWest.add(bWest);
				add(jpWest, BorderLayout.WEST);

				JPanel jpCenter = new JPanel();
				jpCenter.setBackground(Color.white);
				Box bCenter = Box.createVerticalBox();
				bCenter.setBackground(Color.blue);
				Box bCenter1, bCenter2, bCenter3, bCenter4, bCenter5;
				bCenter.add(Box.createVerticalStrut(30));

				bCenter.add(bCenter1 = Box.createHorizontalBox());
				bCenter1.add(Box.createHorizontalStrut(30));
				JLabel lblMa;
				bCenter1.add(lblMa = new JLabel("Mã khách hàng: "));
				bCenter1.add(txtMa = new JTextField());
				bCenter1.add(Box.createHorizontalStrut(30));
				JLabel lblTen;
				bCenter1.add(lblTen = new JLabel("Tên khách hàng: "));
				bCenter1.add(txtTen = new JTextField());
				bCenter.add(Box.createVerticalStrut(30));

				bCenter.add(bCenter2 = Box.createHorizontalBox());
				bCenter2.add(Box.createHorizontalStrut(30));
				JLabel lblNgaySinh;
				bCenter2.add(lblNgaySinh = new JLabel("Ngày sinh:  "));
				ngaySinh = new JDateChooser();
				ngaySinh.setDateFormatString("yyyy-MM-dd");
				bCenter2.add(ngaySinh);
				bCenter2.add(Box.createHorizontalStrut(30));
				JLabel lblDiaChi;
				bCenter2.add(lblDiaChi = new JLabel("Địa chỉ: "));
				bCenter2.add(txtDiaChi = new JTextField());
				txtDiaChi.setPreferredSize(new Dimension(85, 20));
				bCenter.add(Box.createVerticalStrut(30));

				bCenter.add(bCenter3 = Box.createHorizontalBox());
				bCenter3.add(Box.createHorizontalStrut(30));
				JLabel lblSoDienThoai;
				bCenter3.add(lblSoDienThoai = new JLabel("Số điện thoại: "));
				bCenter3.add(txtSoDienThoai = new JTextField());
				bCenter3.add(Box.createHorizontalStrut(30));
				JLabel lblEmail;
				bCenter3.add(lblEmail = new JLabel("Email: "));
				bCenter3.add(txtEmail = new JTextField());
				bCenter.add(Box.createVerticalStrut(30));

				lblNgaySinh.setPreferredSize(lblMa.getPreferredSize());
				lblSoDienThoai.setPreferredSize(lblMa.getPreferredSize());

				lblDiaChi.setPreferredSize(lblTen.getPreferredSize());
				lblEmail.setPreferredSize(lblTen.getPreferredSize());

				bCenter.add(bCenter5 = Box.createHorizontalBox());
				bCenter5.add(Box.createHorizontalStrut(10));
				bCenter5.add(btnThem = new JButton("Thêm"));
				bCenter5.add(Box.createHorizontalStrut(20));
				bCenter5.add(btnCapNhat = new JButton("Cập nhật"));
				bCenter5.add(Box.createHorizontalStrut(20));
				bCenter5.add(btnXoa = new JButton("Xóa"));
				bCenter5.add(Box.createHorizontalStrut(20));
				bCenter.add(Box.createVerticalStrut(30));

				String[] row = { "STT", "Mã khách hàng", "Tên khách hàng", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email" };
				tableModel = new DefaultTableModel(row, 0);
				JScrollPane scroll = new JScrollPane();
				scroll.setViewportView(table = new JTable(tableModel));
				table.setAutoCreateRowSorter(true);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
				table.setPreferredScrollableViewportSize(new Dimension(1050, 400));
				bCenter.add(bCenter4 = Box.createHorizontalBox());
				bCenter4.add(Box.createHorizontalStrut(30));
				bCenter4.add(scroll);
				bCenter.add(Box.createVerticalStrut(30));

				jpCenter.add(bCenter);
				add(jpCenter, BorderLayout.CENTER);

				btnPhim.addActionListener(this);
				btnSuatChieu.addActionListener(this);
				btnThucPham.addActionListener(this);
				btnKhachHang.addActionListener(this);
				btnNhanVien.addActionListener(this);
				btnHoaDon.addActionListener(this);
				btnVe.addActionListener(this);

				btnThem.addActionListener(this);
				btnCapNhat.addActionListener(this);
				btnXoa.addActionListener(this);
				btnTim.addActionListener(this);
				btnSapXep.addActionListener(this);

				quanLyMenu.addMenuListener(this);
				thongKeMenu.addMenuListener(this);
				dangXuatMenu.addMenuListener(this);

				table.addMouseListener(this);

				DocDuLieuDatabaseVaoTable();

	}
	private static ImageIcon resizeImage(String iconPath, int width, int height) {
		ImageIcon icon = new ImageIcon(iconPath);
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImg);
		return icon;
	}
	private JButton createControlButton(String text, String iconPath, int width, int height) {
		JButton button = new JButton(text);
		ImageIcon icon = new ImageIcon(iconPath);
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImg);
		button.setIcon(icon); // Đường dẫn tới file icon
		button.setFocusable(false);
		return button;
	}
	private void DocDuLieuDatabaseVaoTable() {
		ArrayList<KhachHang> list = dskh.layDanhSachKhachHang();
		int i = 0;
		for (KhachHang khachHang : list) {
			tableModel.addRow(new Object[] { i + 1, khachHang.getMaKhachHang(), khachHang.getHoTen(),
					khachHang.getNgaySinh(), khachHang.getDiaChi(), khachHang.getSoDienThoai(), khachHang.getEmail() });
			i += 1;
		}
		table.setModel(tableModel);
	}
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
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
		if (o.equals(btnThem))
			actionThem();
		if (o.equals(btnCapNhat))
			actionCapNhat();
		if (o.equals(btnXoa))
			actionXoa();
		if (o.equals(btnTim))
			actionTim();
		if (o.equals(btnSapXep))
			actionSapXep();
	}
	private void actionSapXep() {
		// TODO Auto-generated method stub
		
	}
	private void actionTim() {
		// TODO Auto-generated method stub
		
	}
	private void actionXoa() {
		// TODO Auto-generated method stub
		
	}
	private void actionCapNhat() {
		// TODO Auto-generated method stub
		
	}
	private void actionThem() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		new Gui_QuanLyKhachHang().setVisible(true);
	}

}
