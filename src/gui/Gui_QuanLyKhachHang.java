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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.Dao_KhachHang;
import entity.KhachHang;
import entity.Phim;

public class Gui_QuanLyKhachHang extends JFrame implements ActionListener, MenuListener, MouseListener {
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

		// Tạo JPanel cố định cho các nút điều khiển

		// Thêm các nút điều khiển
		toolBar.add(btnPhim = createControlButton("Phim", "image//phim-icon.png", 40, 40));
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
        toolBar.add(btnSuatChieu = createControlButton("Suất chiếu", "image//suatChieu-icon.png", 40, 40));
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
        toolBar.add(btnKhachHang = createControlButton("Khách hàng", "image//khachhang-icon.png", 40, 40));
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
        toolBar.add(btnNhanVien = createControlButton("Nhân viên", "image//nhanvien-icon.png", 40, 40));
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
        toolBar.add(btnHoaDon = createControlButton("Hóa đơn", "image//hoadon-icon.png", 40, 40));
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
        toolBar.add(btnVe = createControlButton("Vé", "image//ve-icon.png", 40, 40));
        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

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

	private static void chinhMauMenu(JMenu menu1, JMenu menu2) {
		menu2.setForeground(Color.white);
		menu2.setBackground(new Color(104, 109, 224));
		menu2.setOpaque(true);

		menu1.setBackground(Color.white);
		menu1.setForeground(Color.black);
		menu1.setOpaque(true);
	}

	private static JButton createControlButton(String text, String iconPath, int width, int height) {
        ImageIcon icon = resizeImage(iconPath, width, height);
        
        JButton btn = new JButton(text, icon);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setIconTextGap(10);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(65, 165, 238));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(41, 128, 185), 2, true));
        btn.setPreferredSize(new Dimension(140, 50));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { 
                btn.setBackground(new Color(41, 128, 185)); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { 
                btn.setBackground(new Color(65, 165, 238)); 
            }
        });

        return btn;
    }

    private static ImageIcon resizeImage(String iconPath, int width, int height) {
        try {
            // Sửa lỗi: // → /, xóa khoảng trắng
            String fixedPath = iconPath.replace("//", "/").replace(" ", "");
            
            // Dùng ImageIcon với đường dẫn đã sửa
            ImageIcon originalIcon = new ImageIcon(fixedPath);
            
            // Kiểm tra xem ảnh có tồn tại không
            if (originalIcon.getIconWidth() == -1 || originalIcon.getIconHeight() == -1) {
                System.err.println("Không tìm thấy ảnh: " + fixedPath);
                return createPlaceholderIcon(width, height); // Icon mặc định
            }

            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);

        } catch (Exception e) {
            System.err.println("Lỗi load ảnh: " + iconPath + " | " + e.getMessage());
            return createPlaceholderIcon(width, height); // Tránh crash
        }
    }
    private static ImageIcon createPlaceholderIcon(int width, int height) {
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
            width, height, java.awt.image.BufferedImage.TYPE_INT_RGB
        );
        java.awt.Graphics2D g2d = img.createGraphics();
        
        // Nền xám
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRect(0, 0, width, height);
        
        // Viền đen
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, width - 1, height - 1);
        
        // Chữ "?"
        g2d.setFont(new Font("Arial", Font.BOLD, width / 3));
        g2d.drawString("?", width / 3, height / 2 + 10);
        
        g2d.dispose();
        return new ImageIcon(img);
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
//		if (o.equals(btnSanPham)) {
//			new Gui_ThongKeSanPham().setVisible(true);
//			this.setVisible(false);
//		}
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

	public void XoaDataTrenModel() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.getDataVector().removeAllElements();
	}

	private void actionSapXep() {
		// TODO Auto-generated method stub
		if (radSapXepMa.isSelected()) {
			XoaDataTrenModel();
			ArrayList<KhachHang> list = dskh.sapXepTheoMa();
			int i = 0;
			for (KhachHang khachHang : list) {
				tableModel.addRow(
						new Object[] { i + 1, khachHang.getMaKhachHang(), khachHang.getHoTen(), khachHang.getNgaySinh(),
								khachHang.getDiaChi(), khachHang.getSoDienThoai(), khachHang.getEmail() });
				i += 1;
			}
		}
		if (radSapXepTen.isSelected()) {
			XoaDataTrenModel();
			ArrayList<KhachHang> list = dskh.sapXepTheoHoTen();
			int i = 0;
			for (KhachHang khachHang : list) {
				tableModel.addRow(
						new Object[] { i + 1, khachHang.getMaKhachHang(), khachHang.getHoTen(), khachHang.getNgaySinh(),
								khachHang.getDiaChi(), khachHang.getSoDienThoai(), khachHang.getEmail() });
				i += 1;
			}
		}
	}

	private void actionTim() {
		// TODO Auto-generated method stub
		String timTenKH = txtTim.getText().trim();
		ArrayList<KhachHang> list = dskh.timKhachHangTheoTen(timTenKH);
		if(list.size() > 0) {
			XoaDataTrenModel();
			int i = 0;
			for (KhachHang khachHang : list) {
				tableModel.addRow(new Object[] { i + 1, khachHang.getMaKhachHang(), khachHang.getHoTen(),
						khachHang.getNgaySinh(), khachHang.getDiaChi(), khachHang.getSoDienThoai(),
						khachHang.getEmail() });
				i++;
			}
		}else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy.");
				txtTimTenPhim.requestFocus();
				txtTimTenPhim.selectAll();
		}
	}

	private void actionXoa() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if (row != -1) {
			String maKhachHang = (String) table.getModel().getValueAt(row, 1);
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa? ", "Chú ý",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION)
				if (dskh.xoaKhachHang(maKhachHang)) {
					tableModel.removeRow(row);
				}
		}
	}

	private void actionCapNhat() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if (kiemTraDuLieu()) {
			KhachHang khachHang = taoKhachHangMoi();
			if (row >= 0) {
				if (dskh.capNhatKhachHang(khachHang)) {
					table.setValueAt(khachHang.getHoTen(), row, 2);
					table.setValueAt(khachHang.getNgaySinh(), row, 3);
					table.setValueAt(khachHang.getDiaChi(), row, 4);
					table.setValueAt(khachHang.getSoDienThoai(), row, 5);
					table.setValueAt(khachHang.getEmail(), row, 6);
					JOptionPane.showMessageDialog(null, "Cập nhật thành công.");
					actionXoaTrang();
				}
			}
		} 
	}

	private void actionThem() {
		// TODO Auto-generated method stub
		if (kiemTraDuLieu()) {
			KhachHang khachHang = taoKhachHangMoi();
			if (dskh.themKhachHang(khachHang)) {
				tableModel.addRow(new Object[] { dskh.tinhTongKhachHang()+1, khachHang.getMaKhachHang(),
						khachHang.getHoTen(), khachHang.getNgaySinh(), khachHang.getDiaChi(),
						khachHang.getSoDienThoai(), khachHang.getEmail() });
				actionXoaTrang();
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã khách hàng.");
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
	}

	private KhachHang taoKhachHangMoi() {
		// TODO Auto-generated method stub
		String maKhachHang = txtMa.getText().trim();
		String tenKhachHang = txtTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		LocalDate ngaySinh1 = ngaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String soDienThoai = txtSoDienThoai.getText().trim();
		String email = txtEmail.getText().trim();
		return new KhachHang(maKhachHang, tenKhachHang, ngaySinh1, diaChi, soDienThoai, email);
	}

	private boolean kiemTraDuLieu() {
		// TODO Auto-generated method stub
		String maKhachHang = txtMa.getText().trim();
		String tenKhachHang = txtTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		LocalDate ngaySinh1 = null;
		String soDienThoai = txtSoDienThoai.getText().trim();
		String email = txtEmail.getText().trim();

		if (!maKhachHang.matches("(KH){1}[0-9]{3}")) {
			JOptionPane.showMessageDialog(null, "Mã khách hàng có dạng KH theo sau 3 số.");
			txtMa.selectAll();
			txtMa.requestFocus();
			return false;
		}

		if (!tenKhachHang.matches("[\\w\\s]+")) {
			JOptionPane.showMessageDialog(null, "Tên khách hàng không chứa ký tư đặc biệt.");
			txtTen.selectAll();
			txtTen.requestFocus();
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

		if (!diaChi.matches("[\\w\\s\\,\\/\\.]+")) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không chứa các ký tự @,#,$,%,^,...");
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			return false;
		}

		if (!soDienThoai.matches("(03|05|07|08|09)\\d{8}")) {
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
		return true;
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		JMenu sourceMenu = (JMenu) e.getSource();
		toolBar.removeAll();
		if (sourceMenu.getText().equals("Quản lý")) {
			chinhMauMenu(quanLyMenu, thongKeMenu);
			toolBar.add(btnPhim = createControlButton("Phim", "image//phim-icon.png", 40, 40));
	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        toolBar.add(btnSuatChieu = createControlButton("Suất chiếu", "image//suatChieu-icon.png", 40, 40));
	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        toolBar.add(btnKhachHang = createControlButton("Khách hàng", "image//khachhang-icon.png", 40, 40));
	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        toolBar.add(btnNhanVien = createControlButton("Nhân viên", "image//nhanvien-icon.png", 40, 40));
	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        toolBar.add(btnHoaDon = createControlButton("Hóa đơn", "image//hoadon-icon.png", 40, 40));
	        toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
	        toolBar.add(btnVe = createControlButton("Vé", "image//ve-icon.png", 40, 40));
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
//			toolBar.add(btnSanPham = createControlButton("Sản phẩm", "image//sanpham-icon.png", 40, 40));
//			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
//			toolBar.add(btnTKKhachHang = createControlButton("Khách hàng", "image//customer-icon.png", 40, 40));
//			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

			toolBar.revalidate();
			toolBar.repaint();

			btnDoanhThu.addActionListener(this);
//			btnSanPham.addActionListener(this);
//			btnTKKhachHang.addActionListener(this);
		}
		if (sourceMenu.getText().equals("Đăng xuất")) {
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
		new Gui_QuanLyKhachHang().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMa.setText(tableModel.getValueAt(row, 1).toString());
		txtTen.setText(tableModel.getValueAt(row, 2).toString());
		ngaySinh.setDate(Date.valueOf(tableModel.getValueAt(row, 3).toString()));
		txtDiaChi.setText(tableModel.getValueAt(row, 4).toString());
		txtSoDienThoai.setText(tableModel.getValueAt(row, 5).toString());
		txtEmail.setText(tableModel.getValueAt(row, 6).toString());
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
}
