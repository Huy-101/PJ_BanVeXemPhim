package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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

import connectDB.ConnectDB;
import dao.Dao_Phim;
import dao.Dao_PhongChieu;
import dao.Dao_SuatChieu;
import entity.*;

public class Gui_QuanLySuatChieu extends JFrame implements ActionListener, MenuListener, MouseListener {
	private JToolBar toolBar = new JToolBar();
	private static final Dimension LABEL_SIZE = new Dimension(140, 30);
	private static final Dimension FIELD_SIZE = new Dimension(200, 30);
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
	private JLabel lblTimTheoMaSuatChieu;
	private JTextField txtTimMa;
	private JButton btnTimKiemMa;
	private JLabel lblTimTenPhim;
	private JTextField txtTimTenPhim;
	private JButton btnTimTenPhim;
	private JLabel lblTrangThai;
	private JComboBox<String> cboTrangThai;
	private JButton btnTim;
	private JDateChooser dateChooserTuNgay;
	private JDateChooser dateChooserDenNgay;
	private JRadioButton radSapXepTen;
	private JRadioButton radSapXepTheoTGChieu;
	private JRadioButton radSapXepSoGhe;
	private JButton btnSapXep;
	private DefaultTableModel tableModel;
	private JTable table;
	private AbstractButton btnTaiKhoan;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnXoaRong;
	private JTextField txtMa;
	private JTextField txtHinhThucChieu;
	private JTextField txtThoiGianBatDau;
	private JTextField txtThoiGianKetThuc;
	private JTextField txtSoGheTrong;
	private JRadioButton radChuaChieu;
	private JRadioButton radDangChieu;
	private JRadioButton radDaChieu;
	private JComboBox<String> cboMaPhim;
	private JComboBox<String> cboMaPhongChieu;
	private JDateChooser ngayKhoiChieu;

	private Dao_SuatChieu dssc = new Dao_SuatChieu();
	private Dao_Phim dsp = new Dao_Phim();
	private Dao_PhongChieu dspc = new Dao_PhongChieu();
	private JLabel lblTimTheoMaPhong;
	private JTextField txtTimMaPhong;
	private JButton btnTimKiemMaPhong;
	private JButton btnDoanhThu;
	private JLabel lbltimkiemtheotenphim;
	private JLabel lbltimkiemtheomaphong;
	private JLabel lbltungay;
	private JLabel lbldenngay;
	private Object[][] duLieuGoc = null;
	
	public Gui_QuanLySuatChieu() {
		setTitle("Quản lý phim");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(104, 109, 224));

		// Thêm các JMenu
		quanLyMenu = new JMenu("Quản lý");
		thongKeMenu = new JMenu("Thống kê");
		dangXuatMenu = new JMenu("Đăng xuất");

//		quanLyMenu.setPreferredSize(new Dimension(150, 40));
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


	    add(toolBar, BorderLayout.NORTH);

	    // ===== PANEL TRÁI (WEST) =====
	    JPanel jpWest = new JPanel();
	    Box bWest = Box.createVerticalBox();
	    bWest.add(Box.createVerticalStrut(20));

	    JLabel lblTitle = new JLabel("Tra cứu suất chiếu");
	    lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
	    lblTitle.setForeground(new Color(104, 109, 224));
	    Box bWest1 = Box.createHorizontalBox();
	    bWest1.add(lblTitle);
	    bWest.add(bWest1);
	    bWest.add(Box.createVerticalStrut(15));

	    // --- Tìm kiếm ---
	    Box bWest2 = Box.createHorizontalBox();
	    bWest2.add(lbltimkiemtheomaphong = new JLabel("Tìm kiếm theo mã phòng: "));
	    bWest2.add(txtTimMaPhong = new JTextField());
	    bWest2.add(btnTimKiemMaPhong = new JButton("Tìm"));
	    bWest.add(bWest2);
	    bWest.add(Box.createVerticalStrut(10));

//	    Box bWest3 = Box.createHorizontalBox();
//	    bWest3.add(lbltimkiemtheotenphim = new JLabel("Tìm kiếm theo tên phim: "));
//	    bWest3.add(txtTimTenPhim = new JTextField());
//	    bWest3.add(btnTimTenPhim = new JButton("Tìm"));
//	    bWest.add(bWest3);
//	    bWest.add(Box.createVerticalStrut(20));
//	    
//	    lbltimkiemtheotenphim.setPreferredSize(lbltimkiemtheomaphong.getPreferredSize());

	    // --- Trạng thái suất chiếu ---
	    bWest.add(new JLabel("Tìm kiếm theo trạng thái suất chiếu"));
	    cboTrangThai = new JComboBox<>();
	    cboTrangThai.addItem("Sắp chiếu");
	    cboTrangThai.addItem("Đang chiếu");
	    Box bWest4 = Box.createHorizontalBox();
	    bWest4.add(cboTrangThai);
	    bWest.add(bWest4);
	    bWest.add(Box.createVerticalStrut(20));

	    // --- Tìm nâng cao ---
	    Box bWest7 = Box.createHorizontalBox();
	    bWest7.add(lbltungay = new JLabel("Từ ngày: "));
	    dateChooserTuNgay = new JDateChooser();
	    dateChooserTuNgay.setDateFormatString("dd-MM-yyyy");
	    bWest7.add(dateChooserTuNgay);
	    bWest.add(bWest7);
	    bWest.add(Box.createVerticalStrut(10));

	    Box bWest8 = Box.createHorizontalBox();
	    bWest8.add(lbldenngay = new JLabel("Đến ngày: "));
	    dateChooserDenNgay = new JDateChooser();
	    dateChooserDenNgay.setDateFormatString("dd-MM-yyyy");
	    bWest8.add(dateChooserDenNgay);
	    bWest.add(bWest8);
	    bWest.add(Box.createVerticalStrut(20));

	    lbltungay.setPreferredSize(lbldenngay.getPreferredSize());
	    
	    Box bWest9 = Box.createHorizontalBox();
	    bWest9.add(btnTim = new JButton("Tìm"));
	    bWest.add(bWest9);
	    bWest.add(Box.createVerticalStrut(20));

	    // --- Sắp xếp ---
	    JLabel lblSapXep = new JLabel("Sắp xếp suất chiếu theo tiêu chí");
	    lblSapXep.setFont(new Font("Arial", Font.BOLD, 18));
	    lblSapXep.setForeground(new Color(104, 109, 224));
	    bWest.add(lblSapXep);
	    bWest.add(Box.createVerticalStrut(10));

	    ButtonGroup groupSapXep = new ButtonGroup();
	    radSapXepTen = new JRadioButton("Theo tên phim");
	    radSapXepTheoTGChieu = new JRadioButton("Theo thời gian chiếu");
	    radSapXepSoGhe = new JRadioButton("Theo số ghế");
	    groupSapXep.add(radSapXepTen);
	    groupSapXep.add(radSapXepTheoTGChieu);
	    groupSapXep.add(radSapXepSoGhe);

	    bWest.add(radSapXepTen);
	    bWest.add(radSapXepTheoTGChieu);
	    bWest.add(radSapXepSoGhe);
	    bWest.add(Box.createVerticalStrut(15));
	    bWest.add(btnSapXep = new JButton("Sắp xếp"));

	    jpWest.add(bWest);
	    jpWest.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	    add(jpWest, BorderLayout.WEST);

	    // ===== PANEL (CENTER)=====
	    JPanel jpCenter = new JPanel();
	    jpCenter.setBackground(Color.WHITE);
	    Box bCenter = Box.createVerticalBox();
	    bCenter.setBorder(BorderFactory.createTitledBorder(
	        BorderFactory.createLineBorder(new Color(104, 109, 224), 2),
	        "Thông tin suất chiếu", TitledBorder.LEFT, TitledBorder.TOP,
	        new Font("Arial", Font.BOLD, 16), new Color(104, 109, 224)
	    ));
	    bCenter.add(Box.createVerticalStrut(20));

	    // --- Form nhập ---
	    Box b1 = Box.createHorizontalBox();
	    JLabel lblMa = new JLabel("Mã suất chiếu:");
	    lblMa.setPreferredSize(LABEL_SIZE);
	    b1.add(lblMa);
	    b1.add(txtMa = new JTextField());
	    txtMa.setPreferredSize(FIELD_SIZE);
	    b1.add(Box.createHorizontalStrut(30));

	    JLabel lblHinhThuc = new JLabel("Hình thức chiếu:");
	    lblHinhThuc.setPreferredSize(LABEL_SIZE);
	    b1.add(lblHinhThuc);
	    b1.add(txtHinhThucChieu = new JTextField());
	    txtHinhThucChieu.setPreferredSize(FIELD_SIZE);
	    bCenter.add(b1);
	    bCenter.add(Box.createVerticalStrut(15));

	    // ---

	    Box b2 = Box.createHorizontalBox();
	    JLabel lblNgayKC = new JLabel("Ngày khởi chiếu:");
	    lblNgayKC.setPreferredSize(LABEL_SIZE);
	    b2.add(lblNgayKC);
	    ngayKhoiChieu = new JDateChooser();
	    ngayKhoiChieu.setDateFormatString("yyyy-MM-dd");
	    ngayKhoiChieu.setPreferredSize(FIELD_SIZE);
	    b2.add(ngayKhoiChieu);
	    b2.add(Box.createHorizontalStrut(30));

	    JLabel lblTGBD = new JLabel("Thời gian bắt đầu:");
	    lblTGBD.setPreferredSize(LABEL_SIZE);
	    b2.add(lblTGBD);
	    b2.add(txtThoiGianBatDau = new JTextField());
	    txtThoiGianBatDau.setPreferredSize(FIELD_SIZE);
	    bCenter.add(b2);
	    bCenter.add(Box.createVerticalStrut(15));
	    
	    // ---

	    Box b3 = Box.createHorizontalBox();
	    JLabel lblMaPhim = new JLabel("Mã phim:");
	    lblMaPhim.setPreferredSize(LABEL_SIZE);
	    b3.add(lblMaPhim);
	    b3.add(cboMaPhim = new JComboBox<>());
	    cboMaPhim.setPreferredSize(FIELD_SIZE);
	    b3.add(Box.createHorizontalStrut(30));

	    JLabel lblMaPhong = new JLabel("Mã phòng chiếu:");
	    lblMaPhong.setPreferredSize(LABEL_SIZE);
	    b3.add(lblMaPhong);
	    b3.add(cboMaPhongChieu = new JComboBox<>());
	    cboMaPhongChieu.setPreferredSize(FIELD_SIZE);
	    bCenter.add(b3);
	    bCenter.add(Box.createVerticalStrut(20));

	    // --- Nút thao tác ---
	    Box b4 = Box.createHorizontalBox();
	    b4.add(btnThem = new JButton("Thêm"));
	    b4.add(Box.createHorizontalStrut(15));
	    b4.add(btnCapNhat = new JButton("Cập nhật"));
	    b4.add(Box.createHorizontalStrut(15));
	    b4.add(btnXoa = new JButton("Xóa"));
	    b4.add(Box.createHorizontalStrut(15));
	    b4.add(btnXoaRong = new JButton("Xóa trắng"));
	    bCenter.add(b4);
	    bCenter.add(Box.createVerticalStrut(30));

	    // --- Bảng dữ liệu ---
	    String[] cols = { "STT", "Mã suất chiếu", "Mã phim", "Mã phòng chiếu", "Tên phim",
	                      "Ngày khởi chiếu", "Hình thức chiếu", "Thời gian bắt đầu",
	                      "Thời lượng", "Số ghế trống", "Trạng thái" };
	    tableModel = new DefaultTableModel(cols, 0);
	    table = new JTable(tableModel);
	    JScrollPane scroll = new JScrollPane(table);
	    scroll.setPreferredSize(new Dimension(1050, 350));
	    bCenter.add(scroll);

	    jpCenter.add(bCenter);
	    add(jpCenter, BorderLayout.CENTER);

	    // ===== SỰ KIỆN =====
	    btnPhim.addActionListener(this);
	    btnSuatChieu.addActionListener(this);
	    btnKhachHang.addActionListener(this);
	    btnNhanVien.addActionListener(this);
	    btnHoaDon.addActionListener(this);
	    btnVe.addActionListener(this);

	    btnThem.addActionListener(this);
	    btnCapNhat.addActionListener(this);
	    btnXoa.addActionListener(this);
	    btnXoaRong.addActionListener(this);
	    btnTimKiemMaPhong.addActionListener(this);
//	    btnTimTenPhim.addActionListener(this);
	    btnTim.addActionListener(this);
	    btnSapXep.addActionListener(this);
	    cboTrangThai.addActionListener(this);

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
		if (o.equals(btnThem))
			actionThem();
		if (o.equals(btnTimKiemMaPhong))
			actionTimTheoMaPhong();
//		if (o.equals(btnTimTenPhim))
//			actionTimKiemTenPhim();
		if (o.equals(btnTim))
			actionTimKiemTrangThai();
		if (o.equals(btnXoa)) 
			actionXoa();
		if (o.equals(btnCapNhat)) 
			actionCapNhat();
		if (o.equals(btnXoaRong)) 
			actionXoaTrang();
		if (o.equals(btnSapXep)) 
			actionSapXep();
	}

	private void actionTimKiemTrangThai() {
		// TODO Auto-generated method stub
		String trangThaiChon = (String) cboTrangThai.getSelectedItem();
	    boolean isDangChieu = trangThaiChon.equals("Đang chiếu");

	    ArrayList<SuatChieu> danhSach = dssc.layDanhSachSuatChieu();
	    ArrayList<SuatChieu> ketQua = new ArrayList<>();

	    for (SuatChieu sc : danhSach) {
	        boolean laDangChieu = sc.getNgayKhoiChieu().equals(LocalDate.now()) && sc.isTrangThai();
	        if (isDangChieu && laDangChieu) {
	            ketQua.add(sc);
	        } else if (!isDangChieu && !laDangChieu) {
	            ketQua.add(sc);
	        }
	    }

	    // Nếu chọn "Đang chiếu" mà không có → hiện thông báo
	    if (isDangChieu && ketQua.isEmpty()) {
	        JOptionPane.showMessageDialog(this, 
	            "Hiện tại không có suất chiếu nào đang diễn ra!", 
	            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }

	    // Cập nhật bảng
	    XoaDataTrenModel();
	    int stt = 1;
	    for (SuatChieu sc : ketQua) {
	        String tt = sc.isTrangThai() ? "Đang chiếu" : "Sắp chiếu";
	        tableModel.addRow(new Object[] {
	            stt++,
	            sc.getMaSuatChieu(),
	            sc.getPhim().getMaPhim(),
	            sc.getPhongChieu().getMaPhong(),
	            sc.getPhim().getTenPhim(),
	            sc.getNgayKhoiChieu(),
	            sc.getHinhThucChieu(),
	            sc.getThoiGianBatDau(),
	            sc.getPhim().getThoiLuong(),
	            sc.getPhongChieu().getSoGheTrong(),
	            tt
	        });
	    }
	}

	private void actionTimKiemTenPhim() {
	    
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

			toolBar.revalidate();
			toolBar.repaint();

			btnDoanhThu.addActionListener(this);
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
		new Gui_QuanLySuatChieu().setVisible(true);
	}

	private void DocDuLieuDatabaseVaoTable() {
	    ConnectDB db = ConnectDB.getInstance();
	    db.connect();
	    ArrayList<Phim> list_dsp = dsp.layDanhSachPhim();
	    ArrayList<PhongChieu> list_dspc = dspc.layDanhSachPhongChieu();
	    
	    // Load ComboBox
	    for (int i = 0; i < list_dsp.size(); i++) {
	        cboMaPhim.addItem(list_dsp.get(i).getMaPhim());
	    }
	    for (int i = 0; i < list_dspc.size(); i++) {
	        cboMaPhongChieu.addItem(list_dspc.get(i).getMaPhong());
	    }

	    ArrayList<SuatChieu> list = dssc.layDanhSachSuatChieu();
	    
	    // Xóa bảng cũ
	    while (tableModel.getRowCount() > 0) {
	        tableModel.removeRow(0);
	    }

	    int i = 1;
	    String trangThai = "";
	    
	    // Tạo mảng tạm để lưu dữ liệu
	    Object[][] tempData = new Object[list.size()][11];

	    for (SuatChieu sc : list) {
	        if (sc.isTrangThai()) {
	            trangThai = "Đang chiếu";
	        } else {
	            trangThai = "Sắp chiếu";
	        }

	        Object[] row = new Object[] { 
	            i, sc.getMaSuatChieu(), sc.getPhim().getMaPhim(),
	            sc.getPhongChieu().getMaPhong(), sc.getPhim().getTenPhim(), 
	            sc.getNgayKhoiChieu(), sc.getHinhThucChieu(), 
	            sc.getThoiGianBatDau(), sc.getPhim().getThoiLuong(),
	            sc.getPhongChieu().getSoGheTrong(), trangThai 
	        };

	        tempData[i-1] = row;
	        tableModel.addRow(row);
	        i++;
	    }

	    // LƯU DỮ LIỆU GỐC
	    duLieuGoc = tempData;
	    
	    table.setModel(tableModel);
	}

	public void XoaDataTrenModel() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.getDataVector().removeAllElements();
	}

	private void actionThem() {
	    ConnectDB db = ConnectDB.getInstance();
	    db.connect();

	    Dao_SuatChieu daoSC = new Dao_SuatChieu();
	    ArrayList<SuatChieu> danhSach = daoSC.layDanhSachSuatChieu();

	    if (!kiemTraDuLieu()) {
	        return;
	    }

	    // Tạo suất chiếu mới
	    SuatChieu scMoi = taoSuatChieuMoi();

	    // Kiểm tra trùng mã suất chiếu
	    for (SuatChieu sc : danhSach) {
	        if (sc.getMaSuatChieu().equalsIgnoreCase(scMoi.getMaSuatChieu())) {
	            JOptionPane.showMessageDialog(this, "Mã suất chiếu đã tồn tại!");
	            txtMa.requestFocus();
	            txtMa.selectAll();
	            return;
	        }
	    }

	    // Kiểm tra trùng thời gian chiếu trong cùng ngày và cùng phòng
	    for (SuatChieu sc : danhSach) {
	        if (sc.getNgayKhoiChieu().equals(scMoi.getNgayKhoiChieu()) &&
	            sc.getPhongChieu().getMaPhong().equals(scMoi.getPhongChieu().getMaPhong())) {

	            int thoiLuongCu = sc.getPhim().getThoiLuong();
	            int thoiLuongMoi = scMoi.getPhim().getThoiLuong();

	            int batDauCu = chuyenDoiPhut(sc.getThoiGianBatDau());
	            int ketThucCu = batDauCu + thoiLuongCu + 30;

	            int batDauMoi = chuyenDoiPhut(scMoi.getThoiGianBatDau());
	            int ketThucMoi = batDauMoi + thoiLuongMoi + 30;

	            // Nếu giờ bắt đầu mới nằm trong khoảng giờ chiếu cũ => trùng
	            if (!(ketThucMoi <= batDauCu || batDauMoi >= ketThucCu)) {
	                JOptionPane.showMessageDialog(this, 
	                    "Phòng " + scMoi.getPhongChieu().getMaPhong() + 
	                    " đã có phim chiếu từ " + sc.getThoiGianBatDau() + " đến " +
	                    formatTime(ketThucCu) + ". Vui lòng chọn giờ khác!");
	                txtThoiGianBatDau.requestFocus();
	                return;
	            }
	        }
	    }

	    // Thêm vào DB
	    if (daoSC.themSuatChieu(scMoi)) {
	        String trangThai = scMoi.isTrangThai() ? "Đang chiếu" : "Sắp chiếu";

	        tableModel.addRow(new Object[] {
	            tableModel.getRowCount(), 
	            scMoi.getMaSuatChieu(),
	            scMoi.getPhim().getMaPhim(),
	            scMoi.getPhongChieu().getMaPhong(),
	            scMoi.getPhim().getTenPhim(),
	            scMoi.getNgayKhoiChieu(),
	            scMoi.getHinhThucChieu(),
	            scMoi.getThoiGianBatDau(),
	            scMoi.getPhim().getThoiLuong(),
	            scMoi.getPhongChieu().getSoGheTrong(),
	            trangThai
	        });

	        JOptionPane.showMessageDialog(this, "Thêm suất chiếu thành công!");
	        actionXoaTrang();
	    } else {
	        JOptionPane.showMessageDialog(this, "Lỗi khi thêm suất chiếu!");
	    }
	}
	// Hàm chuyển "hh:mm:ss" thành phút
	private int chuyenDoiPhut(String thoiGian) {
	    String[] parts = thoiGian.split(":");
	    int gio = Integer.parseInt(parts[0]);
	    int phut = Integer.parseInt(parts[1]);
	    return gio * 60 + phut;
	}

	// Định dạng lại giờ phút
	private String formatTime(int tongPhut) {
	    int gio = tongPhut / 60;
	    int phut = tongPhut % 60;
	    return String.format("%02d:%02d", gio, phut);
	}
	
	private boolean kiemTraDuLieu() {
		// TODO Auto-generated method stub
		String maSuatChieu = txtMa.getText().trim();
		String hinhThucChieu = txtHinhThucChieu.getText().trim();
		LocalDate ngayKC = null;
		String thoiGianBatDau = txtThoiGianBatDau.getText().trim();

		if (!maSuatChieu.matches("(SC){1}[0-9]{3}")) {
			JOptionPane.showMessageDialog(null, "Mã suất chiếu có dạng SC theo sau 3 số.");
			txtMa.selectAll();
			txtMa.requestFocus();
			return false;
		}

		if (!hinhThucChieu.equals("") && !hinhThucChieu.equals("2D") && !hinhThucChieu.equals("3D")) {
			JOptionPane.showMessageDialog(null, "Hình thức chiếu chỉ có thể là rỗng, '2D' hoặc '3D'.");
			txtHinhThucChieu.selectAll();
			txtHinhThucChieu.requestFocus();
			return false;
		}

		if (ngayKhoiChieu.getDate() == null) {
		    JOptionPane.showMessageDialog(null, "Ngày khởi chiếu không được rỗng.");
		    return false;
		} else {
		    ngayKC = ngayKhoiChieu.getDate().toInstant()
		            .atZone(ZoneId.systemDefault())
		            .toLocalDate();

		    LocalDate ngayHienTai = LocalDate.now();

		    // Kiểm tra ngày suất chiếu không được nhỏ hơn hôm nay
		    if (ngayKC.isBefore(ngayHienTai)) {
		        JOptionPane.showMessageDialog(null, 
		            "Ngày khởi chiếu phải sau hoặc bằng ngày hiện tại (" + ngayHienTai + ").");
		        return false;
		    }

		    ConnectDB db = ConnectDB.getInstance();
		    db.connect();

		    ArrayList<Phim> list_dsp = dsp.layDanhSachPhim();
		    Phim p = null;
		    String maPhim = (String) cboMaPhim.getSelectedItem();
		    for (Phim phim : list_dsp) {
		        if (phim.getMaPhim().equals(maPhim)) {
		            p = phim;
		            break;
		        }
		    }

		    // Kiểm tra ngày suất chiếu không được trước ngày phát hành phim
		    if (p != null && ngayKC.isBefore(p.getNgayKhoiChieu())) {
		        JOptionPane.showMessageDialog(null, 
		            "Ngày khởi chiếu phải sau hoặc bằng ngày phát hành phim (" + p.getNgayKhoiChieu() + ").");
		        return false;
		    }
		}

		if (!thoiGianBatDau.matches("^(\\d{1,2}):(\\d{2})$")) {
			JOptionPane.showMessageDialog(null, "Thời gian bắt đầu phải theo định dạng giờ:phút");
			txtThoiGianBatDau.selectAll();
			txtThoiGianBatDau.requestFocus();
			return false;
		}
		String[] parts = thoiGianBatDau.split(":");
		int gio = Integer.parseInt(parts[0]);
		int phut = Integer.parseInt(parts[1]);

		if (gio < 0 || gio > 23 || phut < 0 || phut > 59) {
			JOptionPane.showMessageDialog(null, "Giờ phải nằm trong khoảng 0-23 và phút phải nằm trong khoảng 0-59.");
			txtThoiGianBatDau.selectAll();
			txtThoiGianBatDau.requestFocus();
			return false;
		}

		return true;
	}

	private SuatChieu taoSuatChieuMoi() {
		String maSc = txtMa.getText().trim();
		String maPhim = (String) cboMaPhim.getSelectedItem();
		String maPhongChieu = (String) cboMaPhongChieu.getSelectedItem();
		LocalDate ngayKC = ngayKhoiChieu.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String tgbd = txtThoiGianBatDau.getText().trim();
		String hinhThucchieu = txtHinhThucChieu.getText().trim();
		Phim p = null;
		PhongChieu pc = null;
		ConnectDB db = ConnectDB.getInstance();
		db.connect();
		ArrayList<Phim> list_dsp = dsp.layDanhSachPhim();

		for (Phim phim : list_dsp) {
			if (phim.getMaPhim().equals(maPhim)) {
				p = phim;
			}
		}
		ArrayList<PhongChieu> list_dspc = dspc.layDanhSachPhongChieu();

		for (PhongChieu phongChieu : list_dspc) {
			if (phongChieu.getMaPhong().equals(maPhongChieu)) {
				pc = phongChieu;
			}
		}
		boolean trangThai = false;
		if (ngayKC.isAfter(LocalDate.now())) {
			trangThai = false;
		}
		if (ngayKC.equals(LocalDate.now())) {
			trangThai = true;
		}

		return new SuatChieu(maSc, p, pc, ngayKC, tgbd, hinhThucchieu, trangThai);
	}

	private void actionSapXep() {
	    if (duLieuGoc == null || duLieuGoc.length == 0) {
	        JOptionPane.showMessageDialog(this, "Không có dữ liệu để sắp xếp!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    String trangThaiChon = (String) cboTrangThai.getSelectedItem();
	    boolean chiHienDangChieu = trangThaiChon.equals("Đang chiếu");

	    // Lọc dữ liệu theo trạng thái
	    ArrayList<Object[]> danhSachLoc = new ArrayList<>();
	    for (Object[] row : duLieuGoc) {
	        String trangThai = (String) row[10];
	        if (!chiHienDangChieu || trangThai.equals("Đang chiếu")) {
	            danhSachLoc.add(row.clone()); // clone để không ảnh hưởng gốc
	        }
	    }

	    // Nếu chọn "Đang chiếu" mà không có → bảng trống
	    if (chiHienDangChieu && danhSachLoc.isEmpty()) {
	        while (tableModel.getRowCount() > 0) {
	            tableModel.removeRow(0);
	        }
	        return;
	    }

	    // Sắp xếp
	    if (radSapXepTen.isSelected()) {
	        danhSachLoc.sort((r1, r2) -> ((String) r1[4]).compareToIgnoreCase((String) r2[4]));
	    } else if (radSapXepTheoTGChieu.isSelected()) {
	        danhSachLoc.sort((r1, r2) -> {
	            LocalDate d1 = (LocalDate) r1[5];
	            LocalDate d2 = (LocalDate) r2[5];
	            int cmp = d1.compareTo(d2);
	            if (cmp == 0) {
	                return ((String) r1[7]).compareTo((String) r2[7]);
	            }
	            return cmp;
	        });
	    } else if (radSapXepSoGhe.isSelected()) {
	        danhSachLoc.sort((r1, r2) -> Integer.compare((int) r2[9], (int) r1[9]));
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn tiêu chí sắp xếp!", "Lỗi", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // Cập nhật STT
	    for (int i = 0; i < danhSachLoc.size(); i++) {
	        danhSachLoc.get(i)[0] = i + 1;
	    }

	    // Xóa bảng cũ
	    while (tableModel.getRowCount() > 0) {
	        tableModel.removeRow(0);
	    }

	    // Thêm lại
	    for (Object[] row : danhSachLoc) {
	        tableModel.addRow(row);
	    }
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();

		// Thiết lập giá trị cho các trường
		txtMa.setText(table.getValueAt(row, 1).toString());
		txtHinhThucChieu.setText(table.getValueAt(row, 6).toString());
		ngayKhoiChieu.setDate(Date.valueOf(tableModel.getValueAt(row, 5).toString()));
		txtThoiGianBatDau.setText(table.getValueAt(row, 7).toString());
		cboMaPhim.setSelectedItem(table.getValueAt(row, 2).toString());
		cboMaPhongChieu.setSelectedItem(table.getValueAt(row, 3).toString());
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

	private void actionXoaTrang() {
		// TODO Auto-generated method stub
		txtMa.setText("");
		txtHinhThucChieu.setText("");
		ngayKhoiChieu.setDate(null);
		txtThoiGianBatDau.setText("");
		cboMaPhim.setSelectedIndex(0);
		cboMaPhongChieu.setSelectedIndex(0);
	}

	private void actionTimTheoMaPhong() {
		// TODO Auto-generated method stub
		String timMaPhong = txtTimMaPhong.getText().trim();
		ArrayList<SuatChieu> list = dssc.timSuatChieuTheoMaPhong(timMaPhong);
		System.err.println(list.size() + timMaPhong);
		if (list.size() > 0) {
			XoaDataTrenModel();
			int i = 0;
			String trangThai = "";
			for (SuatChieu sc : list) {
				if (sc.isTrangThai()) {
					trangThai = "Đang chiếu";
				}
				if (!sc.isTrangThai()) {
					trangThai = "Sắp chiếu";
				}
				tableModel.addRow(new Object[] { i, sc.getMaSuatChieu(), sc.getPhim().getMaPhim(),
						sc.getPhongChieu().getMaPhong(), sc.getPhim().getTenPhim(), sc.getNgayKhoiChieu(),
						sc.getHinhThucChieu(), sc.getThoiGianBatDau(), sc.getPhim().getThoiLuong(),
						sc.getPhongChieu().getSoGheTrong(), trangThai });
				i++;
			}
			table.setModel(tableModel);
		} else {
			JOptionPane.showMessageDialog(null, "Không tìm thấy.");
			txtTimTenPhim.requestFocus();
			txtTimTenPhim.selectAll();
		}

	}

	private void actionXoa() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if (row != -1) {
			String maSuatChieu = (String) table.getModel().getValueAt(row, 1);
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa? ", "Chú ý",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION)
				if (dssc.xoaSuatChieu(maSuatChieu)) {
					tableModel.removeRow(row);
				}
		}
	}
	private void actionCapNhat() {
	    int row = table.getSelectedRow();
	    if (row == -1) {
	        JOptionPane.showMessageDialog(this, "Chọn suất chiếu để cập nhật!");
	        return;
	    }

	    if (!kiemTraDuLieu()) return;

	    SuatChieu scMoi = taoSuatChieuMoi();
	    if (dssc.capNhatSuatChieu(scMoi)) {
	        String trangThai = scMoi.isTrangThai() ? "Đang chiếu" : "Sắp chiếu";

	        Object[] rowData = {
	            row + 1,
	            scMoi.getMaSuatChieu(),
	            scMoi.getPhim().getMaPhim(),
	            scMoi.getPhongChieu().getMaPhong(),
	            scMoi.getPhim().getTenPhim(),
	            scMoi.getNgayKhoiChieu(),
	            scMoi.getHinhThucChieu(),
	            scMoi.getThoiGianBatDau(),
	            scMoi.getPhim().getThoiLuong(),
	            scMoi.getPhongChieu().getSoGheTrong(),
	            trangThai
	        };

	        // CẬP NHẬT BẢNG
	        for (int i = 0; i < rowData.length; i++) {
	            tableModel.setValueAt(rowData[i], row, i);
	        }

	        // CẬP NHẬT duLieuGoc
	        duLieuGoc[row] = rowData.clone();

	        JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
	    }
	}
}
