package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

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
import javax.swing.table.TableColumnModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import com.toedter.calendar.JDateChooser;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import dao.Dao_Phim;
import entity.Phim;

public class Gui_QuanLyPhim extends JFrame implements ActionListener, MouseListener, MenuListener{

	
	private JToolBar toolBar = new JToolBar();
	private JMenu quanLyMenu;
	private JMenu banHangMenu;
	private JMenu thongKeMenu;
	private JMenu dangXuatMenu;
	private JButton btnPhim;
	private JButton btnSuatChieu;
	private JButton btnThucPham;
	private JButton btnKhachHang;
	private JButton btnNhanVien;
	private JButton btnHoaDon;
	private JButton btnVe;
	private JRadioButton radTen;
	private JTextField txtTimTen;
	private JRadioButton radNgay;
	private JDateChooser dateChooserTuNgay;
	private JDateChooser dateChooserDenNgay;
	private JButton btnTim;
	private JRadioButton radSapXepMa;
	private JRadioButton radSapXepTen;
	private JButton btnSapXep;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtDaoDien;
	private JTextField txtThoiLuong;
	private JDateChooser ngayKhoiChieu;
	private JTextField txtQuocGia;
	private JDateChooser ngayKetThuc;
	private JTextField txtMoTa;
	private JTextField txtTheLoai;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel lblTimTenPhim;
	private JTextField txtTimTenPhim;
	private JButton btnTimTenPhim;
	private JLabel lblTimNgayChieu;
	private JButton btnTaiKhoan;
	private JButton btnTimTheoNgay;

	private Dao_Phim dsp;
	private JButton btnDoanhThu;
	private JButton btnSanPham;
	private JButton btnTKKhachHang;

	public Gui_QuanLyPhim() {
		dsp = new Dao_Phim();

		setTitle("Quản lý phim");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(104, 109, 224));

		// Thêm các JMenu
		quanLyMenu = new JMenu("Quản lý");
		thongKeMenu = new JMenu("Thống kê");
		dangXuatMenu = new JMenu("Đăng xuất");
		
		quanLyMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
		quanLyMenu.setBackground(Color.white);
		quanLyMenu.setForeground(Color.black);
		quanLyMenu.setOpaque(true);

//		thongKeMenu.setPreferredSize(new Dimension(150, 40));
		thongKeMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
		thongKeMenu.setForeground(Color.white);

//		dangXuatMenu.setPreferredSize(new Dimension(150, 40));
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
		setJMenuBar(menuBar);
		// Thêm các nút điều khiển
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

		// Đặt controlPanel ở phía trên cùng (giống như thanh công cụ cố định)
		add(toolBar, BorderLayout.NORTH);

		// Thêm logo Cineplex
		JPanel jpWest = new JPanel();
		Box bWest = Box.createVerticalBox();
		Box bWest1, bWest2, bWest3, bWest4, bWest5, bWest6, bWest7, bWest8, bWest9, bWest10, bWest11, bWest12, bWest13;
		bWest.setBackground(Color.lightGray);
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest1 = Box.createHorizontalBox());
		JLabel lblTraCuu;
		bWest1.add(lblTraCuu = new JLabel("Tra cứu phim"));
		lblTraCuu.setForeground(new Color(104, 109, 224));
		lblTraCuu.setFont(new Font("Arial", Font.BOLD, 20));
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest3 = Box.createHorizontalBox());
		bWest3.add(lblTimTenPhim = new JLabel("Tìm kiếm theo tên phim: "));
		bWest3.add(txtTimTenPhim = new JTextField());
		bWest3.add(btnTimTenPhim = new JButton("Tìm"));
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest4 = Box.createHorizontalBox());
		bWest4.add(lblTimNgayChieu = new JLabel("Tìm kiếm theo ngày công chiếu"));
		bWest.add(Box.createVerticalStrut(5));

		bWest.add(bWest5 = Box.createHorizontalBox());
		JLabel lblNgayBatDau;
		bWest5.add(lblNgayBatDau = new JLabel("Từ ngày: "));
		dateChooserTuNgay = new JDateChooser();
		dateChooserTuNgay.setDateFormatString("yyyy-MM-dd");
		bWest5.add(dateChooserTuNgay);
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest6 = Box.createHorizontalBox());
		JLabel lblNgayKetThuc;
		bWest6.add(lblNgayKetThuc = new JLabel("Đến ngày: "));
		dateChooserDenNgay = new JDateChooser();
		dateChooserDenNgay.setDateFormatString("yyyy-MM-dd");
		bWest6.add(dateChooserDenNgay);
		bWest.add(Box.createVerticalStrut(10));

		lblNgayBatDau.setPreferredSize(lblNgayKetThuc.getPreferredSize());

		bWest.add(bWest7 = Box.createHorizontalBox());
		bWest7.add(btnTimTheoNgay = new JButton("Tìm"));
		bWest.add(Box.createVerticalStrut(30));

		bWest.add(bWest8 = Box.createHorizontalBox());
		JLabel lblSapXep;
		bWest8.add(lblSapXep = new JLabel("Sắp xếp phim theo tiêu chí"));
		lblSapXep.setForeground(new Color(104, 109, 224));
		lblSapXep.setFont(new Font("Arial", Font.BOLD, 20));
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest9 = Box.createHorizontalBox());
		bWest9.add(radSapXepMa = new JRadioButton("Sắp xếp theo mã phim"));
		radSapXepMa.setSelected(true);
		bWest9.add(Box.createHorizontalStrut(100));
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest10 = Box.createHorizontalBox());
		bWest10.add(radSapXepTen = new JRadioButton("Sắp xếp theo tên phim"));
		bWest10.add(Box.createHorizontalStrut(100));
		ButtonGroup groupSapXep = new ButtonGroup();
		groupSapXep.add(radSapXepMa);
		groupSapXep.add(radSapXepTen);
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest13 = Box.createHorizontalBox());
		bWest13.add(btnSapXep = new JButton("Sắp xếp"));

		jpWest.add(bWest);
		jpWest.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(jpWest, BorderLayout.WEST);
//		jpWest.setVisible(false);

		JPanel jpCenter = new JPanel();
		jpCenter.setBackground(Color.white);
		Box bCenter = Box.createVerticalBox();
//		bCenter.setBackground(Color.blue);
		Box bCenter1, bCenter2, bCenter3, bCenter4, bCenter5, bCenter6, bCenter7;
		bCenter.add(Box.createVerticalStrut(30));

		bCenter.add(bCenter1 = Box.createHorizontalBox());
		bCenter1.add(Box.createHorizontalStrut(30));
		JLabel lblMa;
		bCenter1.add(lblMa = new JLabel("Mã phim: "));
		bCenter1.add(txtMa = new JTextField());
		bCenter1.add(Box.createHorizontalStrut(30));
		JLabel lblTen;
		bCenter1.add(lblTen = new JLabel("Tên phim: "));
		bCenter1.add(txtTen = new JTextField());
		bCenter.add(Box.createVerticalStrut(30));

		bCenter.add(bCenter2 = Box.createHorizontalBox());
		bCenter2.add(Box.createHorizontalStrut(30));
		JLabel lblDaoDien;
		bCenter2.add(lblDaoDien = new JLabel("Đạo diễn: "));
		bCenter2.add(txtDaoDien = new JTextField());
		bCenter2.add(Box.createHorizontalStrut(30));
		JLabel lblThoiLuong;
		bCenter2.add(lblThoiLuong = new JLabel("Thời lượng: "));
		bCenter2.add(txtThoiLuong = new JTextField());
		bCenter.add(Box.createVerticalStrut(30));

		bCenter.add(bCenter3 = Box.createHorizontalBox());
		bCenter3.add(Box.createHorizontalStrut(30));
		JLabel lblNgayChieu;
		bCenter3.add(lblNgayChieu = new JLabel("Ngày khởi chiếu: "));
		ngayKhoiChieu = new JDateChooser();
		ngayKhoiChieu.setDateFormatString("dd-MM-yyyy");
		ngayKhoiChieu.setPreferredSize(new Dimension(0, 20));
		bCenter3.add(ngayKhoiChieu);
		bCenter3.add(Box.createHorizontalStrut(30));
		JLabel lblQuocGia;
		bCenter3.add(lblQuocGia = new JLabel("Quốc gia: "));
		bCenter3.add(txtQuocGia = new JTextField());
		bCenter.add(Box.createVerticalStrut(30));

		bCenter.add(bCenter4 = Box.createHorizontalBox());
		bCenter4.add(Box.createHorizontalStrut(30));
		bCenter4.add(lblNgayKetThuc = new JLabel("Ngày kết thúc: "));
		ngayKetThuc = new JDateChooser();
		ngayKetThuc.setDateFormatString("dd-MM-yyyy");
		bCenter4.add(ngayKetThuc);
		bCenter4.add(Box.createHorizontalStrut(635));
		bCenter.add(Box.createVerticalStrut(30));

		bCenter.add(bCenter5 = Box.createHorizontalBox());
		bCenter5.add(Box.createHorizontalStrut(30));
		JLabel lblMoTa;
		bCenter5.add(lblMoTa = new JLabel("Mô tả: "));
		bCenter5.add(txtMoTa = new JTextField(48));
		txtMoTa.setMaximumSize(new Dimension(2500, 20));
		bCenter5.add(Box.createHorizontalStrut(30));
		JLabel lblTheLoai;
		bCenter5.add(lblTheLoai = new JLabel("Thể loại: "));
		bCenter5.add(txtTheLoai = new JTextField(48));
		txtTheLoai.setMaximumSize(new Dimension(2000, 20));
		bCenter.add(Box.createVerticalStrut(30));

		lblMa.setPreferredSize(lblNgayChieu.getPreferredSize());
		lblDaoDien.setPreferredSize(lblNgayChieu.getPreferredSize());
		lblMoTa.setPreferredSize(lblNgayChieu.getPreferredSize());
		lblNgayKetThuc.setPreferredSize(lblNgayChieu.getPreferredSize());

		lblTen.setPreferredSize(lblThoiLuong.getPreferredSize());
		lblQuocGia.setPreferredSize(lblThoiLuong.getPreferredSize());
		lblTheLoai.setPreferredSize(lblThoiLuong.getPreferredSize());

		bCenter.add(bCenter6 = Box.createHorizontalBox());
//		bCenter6.setBorder(new TitledBorder("Chọn tác vụ"));
		bCenter6.add(Box.createHorizontalStrut(10));
		bCenter6.add(btnThem = new JButton("Thêm"));
		bCenter6.add(Box.createHorizontalStrut(30));
		bCenter6.add(btnCapNhat = new JButton("Cập nhật"));
		bCenter6.add(Box.createHorizontalStrut(30));
		bCenter6.add(btnXoa = new JButton("Xóa"));
		bCenter6.add(Box.createHorizontalStrut(10));
		bCenter.add(Box.createVerticalStrut(30));

		bCenter.add(bCenter7 = Box.createVerticalBox());
		String[] row = { "STT", "Mã phim", "Tên phim", "Đạo diễn", "Thời lượng", "Ngày khởi chiếu", "Ngày kết thúc"
			, "Quốc gia", "Thể loại", "Mô tả" };
		tableModel = new DefaultTableModel(row, 0);
		JScrollPane scroll = new JScrollPane();
		
		
		scroll.setViewportView(table = new JTable(tableModel));
		table.setAutoCreateRowSorter(true);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		
		table.setPreferredScrollableViewportSize(new Dimension(1050, 300));
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(30);
		columnModel.getColumn(1).setPreferredWidth(50);
		columnModel.getColumn(2).setPreferredWidth(160);
		columnModel.getColumn(3).setPreferredWidth(120);
		columnModel.getColumn(4).setPreferredWidth(65);
		columnModel.getColumn(5).setPreferredWidth(110);
		columnModel.getColumn(6).setPreferredWidth(110);
		columnModel.getColumn(7).setPreferredWidth(90);
		columnModel.getColumn(8).setPreferredWidth(120);
		columnModel.getColumn(9).setPreferredWidth(380);
		bCenter.add(bCenter5 = Box.createHorizontalBox());
		bCenter7.add(Box.createHorizontalStrut(30));
		bCenter7.add(scroll);

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
		btnTimTenPhim.addActionListener(this);
		btnTimTheoNgay.addActionListener(this);
		btnSapXep.addActionListener(this);

		quanLyMenu.addMenuListener(this);
		thongKeMenu.addMenuListener(this);
		dangXuatMenu.addMenuListener(this);

		table.addMouseListener(this);

		DocDuLieuDatabaseVaoTable();

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

	
private static ImageIcon resizeImage(String iconPath, int width, int height) {
	ImageIcon icon = new ImageIcon(iconPath);
	Image img = icon.getImage();
	Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	icon = new ImageIcon(scaledImg);
	return icon;

	}
	private void DocDuLieuDatabaseVaoTable() {
		Dao_Phim dsp = new Dao_Phim();
		ArrayList<Phim> list = dsp.layDanhSachPhim();
		int i = 0;
		for (Phim phim : list) {
			tableModel.addRow(new Object[] { i + 1, phim.getMaPhim(), phim.getTenPhim(), phim.getDaoDien(),
					phim.getThoiLuong(), phim.getNgayKhoiChieu(), phim.getNgayKetThuc(),
					phim.getQuocGia(), phim.getTheLoai(), phim.getMoTa() });
			i += 1;
		}
		table.setModel(tableModel);

		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMa.setText(tableModel.getValueAt(row, 1).toString());
		txtTen.setText(tableModel.getValueAt(row, 2).toString());
		txtDaoDien.setText(tableModel.getValueAt(row, 3).toString());
		txtThoiLuong.setText(tableModel.getValueAt(row, 4).toString());
		ngayKhoiChieu.setDate(Date.valueOf(tableModel.getValueAt(row, 5).toString()));
		ngayKetThuc.setDate(Date.valueOf(tableModel.getValueAt(row, 6).toString()));
		txtQuocGia.setText(tableModel.getValueAt(row, 8).toString());
		txtTheLoai.setText(tableModel.getValueAt(row, 9).toString());
		txtMoTa.setText(tableModel.getValueAt(row, 10).toString());

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
	@Override
	public void actionPerformed(ActionEvent e) {
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
		if (o.equals(btnTimTenPhim))
			actionTimTheoTen();
		if (o.equals(btnTimTheoNgay))
			actionTimTheoNgay();
		if (o.equals(btnSapXep))
			actionSapXep();

		
	}
	private void actionSapXep() {
		// TODO Auto-generated method stub
		
	}
	private void actionTimTheoNgay() {
		// TODO Auto-generated method stub
		
	}
	private void actionTimTheoTen() {
		// TODO Auto-generated method stub
		
	}
	private void actionXoa() {
		int row = table.getSelectedRow();
		if (row != -1) {
			String maPhim = (String) table.getModel().getValueAt(row, 1);
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa? ", "Chú ý",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION)
				if (dsp.xoaPhim(maPhim)) {
					tableModel.removeRow(row);
				}
		}

		
	}
	
	private void actionCapNhat() {
		// TODO Auto-generated method stub
		
	}
	private void actionThem() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void menuSelected(MenuEvent e) {
		
		
	}
	private void chinhMauMenu(JMenu quanLyMenu2, JMenu thongKeMenu2) {
		quanLyMenu2.setForeground(Color.white);
		quanLyMenu2.setBackground(new Color(104, 109, 224));
		quanLyMenu2.setOpaque(true);

		thongKeMenu2.setBackground(Color.white);
		thongKeMenu2.setForeground(Color.black);
		thongKeMenu2.setOpaque(true);

		
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
		new Gui_QuanLyPhim().setVisible(true);
	}

}
