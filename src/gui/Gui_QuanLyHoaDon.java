package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.Dao_HoaDon;
import dao.Dao_Phim;
import entity.HoaDon;
import entity.KhachHang;
import entity.Phim;

public class Gui_QuanLyHoaDon extends JFrame implements ActionListener, MenuListener {
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
	private JButton btnThucPham;
	private JRadioButton radMa;
	private JTextField txtTimMa;
	private JButton btnTim;
	private DefaultTableModel tableModel;
	private JTable table;
	private Container bCenter;
	private JButton btnXoa;
	private JButton btnXemChiTiet;
	private JRadioButton radSapXepNgayLap;
	private JRadioButton radSapXepTien;
	private JButton btnSapXep;
	private JLabel lblTimTheoTenNV;
	private JTextField txtMaNhanVien;
	private JButton btnTimKiemMaNV;
	private JLabel lblTimTheoMaHD;
	private JTextField txtTimTheoMaHD;
	private JButton btnTimKiemTheoMaHD;
	private JRadioButton radSapXepTen;
	private Object String;
	private java.lang.String maHoaDon;
	private JButton btnTaiKhoan;
	private Dao_HoaDon dshd = new Dao_HoaDon();
	private JButton btnDoanhThu;
	private KhachHang kh;

	public Gui_QuanLyHoaDon() {
		setTitle("Quản lý hóa đơn");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(104, 109, 224));

		// Thêm các JMenu
		quanLyMenu = new JMenu("Quản lý");
		banHangMenu = new JMenu("Bán hàng");
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
//		menuBar.add(banHangMenu);
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
		bWest1.add(lblTitle = new JLabel("Tra cứu hóa đơn"));
		lblTitle.setForeground(new Color(104, 109, 224));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest2 = Box.createHorizontalBox());
		bWest2.add(lblTimTheoTenNV = new JLabel("Tìm kiếm theo mã nhân viên: "));
		bWest2.add(txtMaNhanVien = new JTextField());
		bWest2.add(btnTimKiemMaNV = new JButton("Tìm"));
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest3 = Box.createHorizontalBox());
		bWest3.add(lblTimTheoMaHD = new JLabel("Tìm kiếm theo mã hóa đơn:    "));
		bWest3.add(txtTimTheoMaHD = new JTextField());
		bWest3.add(btnTimKiemTheoMaHD = new JButton("Tìm"));
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest4 = Box.createHorizontalBox());
		JLabel lblSapXep;
		bWest4.add(lblSapXep = new JLabel("Sắp xếp hóa đơn theo tiêu chí"));
		lblSapXep.setForeground(new Color(104, 109, 224));
		lblSapXep.setFont(new Font("Arial", Font.BOLD, 20));
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest5 = Box.createHorizontalBox());
		bWest5.add(radSapXepNgayLap = new JRadioButton("Sắp xếp theo ngày lập hóa đơn"));
		bWest5.add(Box.createHorizontalStrut(90));
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest6 = Box.createHorizontalBox());
		bWest6.add(radSapXepTien = new JRadioButton("Sắp xếp theo tổng tiền"));
		bWest6.add(Box.createHorizontalStrut(135));
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest7 = Box.createHorizontalBox());
		bWest7.add(radSapXepTen = new JRadioButton("Sắp xếp theo tên nhân viên"));
		bWest7.add(Box.createHorizontalStrut(110));
		ButtonGroup groupSapXep = new ButtonGroup();
		groupSapXep.add(radSapXepNgayLap);
		groupSapXep.add(radSapXepTien);
		groupSapXep.add(radSapXepTen);
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest8 = Box.createHorizontalBox());
		bWest8.add(btnSapXep = new JButton("Sắp xếp"));

		jpWest.add(bWest);
		jpWest.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(jpWest, BorderLayout.WEST);

		JPanel jpCenter = new JPanel();
		jpCenter.setBackground(Color.white);
		Box bCenter = Box.createVerticalBox();
		bCenter.setBackground(Color.blue);
		Box bCenter1, bCenter2;
		bCenter.add(Box.createVerticalStrut(30));

		String[] row = { "STT", "Mã hóa đơn", "Ngày lập", "Mã nhân viên", "Mã khách hàng", "Tên khách hàng","Số điện thoại","Địa chỉ","Tổng tiền" };
		tableModel = new DefaultTableModel(row, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setPreferredScrollableViewportSize(new Dimension(1050, 550));
		bCenter.add(bCenter1 = Box.createHorizontalBox());
		bCenter1.add(Box.createHorizontalStrut(30));
		bCenter1.add(scroll);
		bCenter.add(Box.createVerticalStrut(30));

		bCenter.add(bCenter2 = Box.createHorizontalBox());
		bCenter2.add(btnXemChiTiet = new JButton("Xem chi tiết hóa đơn"));
		bCenter2.add(Box.createHorizontalStrut(20));
		bCenter2.add(btnXoa = new JButton("Xóa"));

		jpCenter.add(bCenter);
		add(jpCenter, BorderLayout.CENTER);

		btnPhim.addActionListener(this);
		btnSuatChieu.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnVe.addActionListener(this);

		btnXemChiTiet.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTimKiemMaNV.addActionListener(this);
		btnTimKiemTheoMaHD.addActionListener(this);
		btnSapXep.addActionListener(this);

		quanLyMenu.addMenuListener(this);
		banHangMenu.addMenuListener(this);
		thongKeMenu.addMenuListener(this);
		dangXuatMenu.addMenuListener(this);
		
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
	private void createChiTietHoaDonGUI() {
	    // Lấy dòng được chọn trên table hóa đơn
	    int selectedRow = table.getSelectedRow();

	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để xem chi tiết!");
	        return;
	    }

	    // Lấy dữ liệu từ bảng
	    String maHD = table.getValueAt(selectedRow, 1).toString();
	    String ngayLap = table.getValueAt(selectedRow, 2).toString();
	    String maNV = table.getValueAt(selectedRow, 3).toString();
	    String tenNV = table.getValueAt(selectedRow, 4).toString();
	    String maKH = table.getValueAt(selectedRow, 5).toString();
	    String tenKH = table.getValueAt(selectedRow, 6).toString();
	    String sdt = table.getValueAt(selectedRow, 7).toString();
	    String diaChi = table.getValueAt(selectedRow, 8).toString();
	    String tongTien = table.getValueAt(selectedRow, 9).toString();

	    // Tạo cửa sổ hiển thị chi tiết hóa đơn
	    JFrame detailFrame = new JFrame("Chi tiết hóa đơn");
	    detailFrame.setSize(500, 400);
	    detailFrame.setLayout(new BorderLayout());
	    detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    // Tạo vùng hiển thị nội dung
	    JTextArea txtChiTiet = new JTextArea();
	    txtChiTiet.setEditable(false);
	    txtChiTiet.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 14));
	    txtChiTiet.setMargin(new java.awt.Insets(10, 10, 10, 10));

	    // Dựng nội dung chi tiết hóa đơn
	    StringBuilder sb = new StringBuilder();
	    sb.append("             CHI TIẾT HÓA ĐƠN XEM PHIM\n");
	    sb.append("--------------------------------------------------\n");
	    sb.append("Mã hóa đơn:      ").append(maHD).append("\n");
	    sb.append("Ngày lập:        ").append(ngayLap).append("\n");
	    sb.append("Mã nhân viên:    ").append(maNV).append("\n");
	    sb.append("Tên nhân viên:    ").append(tenNV).append("\n");
	    sb.append("Mã khách hàng:   ").append(maKH).append("\n");
	    sb.append("Tên khách hàng:  ").append(tenKH).append("\n");
	    sb.append("Số điện thoại:   ").append(sdt).append("\n");
	    sb.append("Địa chỉ:         ").append(diaChi).append("\n");
	    sb.append("--------------------------------------------------\n");
	    sb.append(((java.lang.String) String).format("TỔNG TIỀN:       %s VNĐ\n", tongTien));
	    sb.append("--------------------------------------------------\n");
	    sb.append("Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi!");

	    txtChiTiet.setText(sb.toString());

	    // Thêm vào frame
	    detailFrame.add(new JScrollPane(txtChiTiet), BorderLayout.CENTER);
	    detailFrame.setLocationRelativeTo(null); // căn giữa
	    detailFrame.setVisible(true);
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
		if (o.equals(btnXemChiTiet)) {
			createChiTietHoaDonGUI(); // Bạn có thể lấy mã hóa đơn từ bảng nếu cần
		}
		if (o.equals(btnDoanhThu)) {
			new Gui_ThongKeDoanhThu().setVisible(true);
			this.setVisible(false);
		}
//		if (o.equals(btnSanPham)) {
//			new Gui_ThongKeSanPham().setVisible(true);
//			this.setVisible(false);
//		}
//		if (o.equals(btnKhachHang)) {
//			new Gui_ThongKeKhachHang().setVisible(true);
//			this.setVisible(false);
//		}
		if (o.equals(btnXoa))
			actionXoa();
		if (o.equals(btnTimKiemMaNV))
			actionTimTheoMaNV();
		if (o.equals(btnTimKiemTheoMaHD))
			actionTimTheoMaHD();
		if (o.equals(btnSapXep))
			actionSapXep();
	
	}

	private void actionSapXep() {
	    ArrayList<HoaDon> list = null;

	    if (radSapXepNgayLap.isSelected()) {
	        list = dshd.sapXepTheoMa(); // Sắp theo mã HD (gần đúng ngày lập)
	    }
	    if (radSapXepTien.isSelected()) {
	        list = dshd.sapXepTheoTien();
	    }
	    if (radSapXepTen.isSelected()) {
	        list = dshd.sapXepTheoTen();
	    }

	    if (list != null && !list.isEmpty()) {
	        XoaDataTrenModel();
	        int i = 0;
	        for (HoaDon hoaDon : list) {
	            tableModel.addRow(new Object[] { 
	                i + 1, 
	                hoaDon.getMaHoaDon(), 
	                hoaDon.getNgayLap() != null ? hoaDon.getNgayLap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
	                hoaDon.getNhanVien().getMaNhanVien(),
	                hoaDon.getKhachHang().getMaKhachHang(),
	                hoaDon.getKhachHang().getHoTen(),
	                hoaDon.getKhachHang().getSoDienThoai(),
	                hoaDon.getKhachHang().getDiaChi(),
	                new DecimalFormat("#,###").format(hoaDon.getTongTien())
	            });
	            i++;
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Không có dữ liệu để sắp xếp!");
	    }
	}
	private void actionTimTheoMaNV() {
	    String timMaNV = txtMaNhanVien.getText().trim().toUpperCase();
	    
	    // Nếu để trống → hiện toàn bộ
	    if (timMaNV.isEmpty()) {
	        DocDuLieuDatabaseVaoTable();
	        return;
	    }

	    ArrayList<HoaDon> list = dshd.timHoaDonTheoMaNV(timMaNV);
	    XoaDataTrenModel(); // Xóa bảng cũ trước

	    // KIỂM TRA KÍCH THƯỚC DANH SÁCH, KHÔNG PHẢI null
	    if (list.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên có mã: " + timMaNV);
	        txtMaNhanVien.requestFocus();
	        txtMaNhanVien.selectAll();
	        return;
	    }

	    // ĐÚNG 9 CỘT – KHÔNG THIẾU, KHÔNG SAI
	    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    DecimalFormat mf = new DecimalFormat("#,###");
	    int stt = 1;
	    for (HoaDon hd : list) {
	        tableModel.addRow(new Object[] {
	            stt++,
	            hd.getMaHoaDon(),
	            hd.getNgayLap() != null ? hd.getNgayLap().format(df) : "",
	            hd.getNhanVien().getMaNhanVien(),
	            hd.getKhachHang().getMaKhachHang(),
	            hd.getKhachHang().getHoTen(),
	            hd.getKhachHang().getSoDienThoai(),
	            hd.getKhachHang().getDiaChi(),
	            mf.format(hd.getTongTien())
	        });
	    }
	}
	
	

	private void actionTimTheoMaHD() {
	    String ma = txtTimTheoMaHD.getText().trim().toUpperCase();
	    if (ma.isEmpty()) {
	        DocDuLieuDatabaseVaoTable();
	        return;
	    }

	    ArrayList<HoaDon> list = dshd.timHoaDonTheoMaHD(ma);
	    XoaDataTrenModel();

	    if (list.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy: " + ma);
	    } else {
	        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        DecimalFormat mf = new DecimalFormat("#,###");
	        int stt = 1;
	        for (HoaDon hd : list) {
	            tableModel.addRow(new Object[] {
	                stt++,
	                hd.getMaHoaDon(),
	                hd.getNgayLap().format(df),
	                hd.getNhanVien().getMaNhanVien(),
	                hd.getKhachHang().getMaKhachHang(),
	                hd.getKhachHang().getHoTen(),
	                hd.getKhachHang().getSoDienThoai(),
	                hd.getKhachHang().getDiaChi(),
	                mf.format(hd.getTongTien())
	            });
	        }
	    }
	}

	private void actionXoa() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if (row != -1) {
			String maHoaDon = (String) table.getModel().getValueAt(row, 1);
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa? ", "Chú ý",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION)
				if (dshd.xoaHoaDon(maHoaDon)) {
					tableModel.removeRow(row);
				}
		}
	}

	private void DocDuLieuDatabaseVaoTable() {
		 DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		    tableModel.setRowCount(0); // Xóa dữ liệu cũ

		    ArrayList<HoaDon> danhSach = dshd.layDanhSachHoaDon1();

		    if (danhSach.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Không có hóa đơn để hiển thị!");
		        return;
		    }

		    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    DecimalFormat moneyFormatter = new DecimalFormat("#,###");
		    int stt = 1;
		    for (HoaDon hd : danhSach) {
		        Object[] rowData = new Object[] {
		            stt++,
		            hd.getMaHoaDon(),
		            hd.getNgayLap() != null ? hd.getNgayLap().format(dateFormatter) : "",
		            hd.getNhanVien().getMaNhanVien(),
		            hd.getKhachHang().getMaKhachHang(),
		            hd.getKhachHang().getHoTen(),
		            hd.getKhachHang().getSoDienThoai(),
		            hd.getKhachHang().getDiaChi(),
		            moneyFormatter.format(hd.getTongTien())
		        };
		        tableModel.addRow(rowData);
		    }
		    table.repaint();
		    table.revalidate();
	}
	public void XoaDataTrenModel() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.getDataVector().removeAllElements();
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
//			toolBar.add(btnKhachHang = createControlButton("Khách hàng", "image//customer-icon.png", 40, 40));
//			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

			toolBar.revalidate();
			toolBar.repaint();
			btnDoanhThu.addActionListener(this);

//			btnKhachHang.addActionListener(this);
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
		new Gui_QuanLyHoaDon().setVisible(true);
	}
}