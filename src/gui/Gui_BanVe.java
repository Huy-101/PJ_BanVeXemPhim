package gui;

import java.util.Set;
import java.util.HashSet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.Dao_ChiTietHoaDon;
import dao.Dao_Ghe;
import dao.Dao_HoaDon;
import dao.Dao_KhachHang;
import dao.Dao_NhanVien;
import dao.Dao_Phim;
import dao.Dao_PhongChieu;
import dao.Dao_SuatChieu;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phim;
import entity.PhongChieu;
import entity.SuatChieu;

public class Gui_BanVe extends JFrame implements ActionListener, MenuListener {
	private JButton btnBanHang,btnXuatHoaDon;
	private JToolBar toolBar = new JToolBar();
	private JMenu quanLyMenu;
	private JMenu banHangMenu;
	private JMenu thongKeMenu;
	private JMenu dangXuatMenu;
	private JComboBox<String> cboPhim;
	private JComboBox<String> cboSuatChieu;
	private JComboBox<String> cboPhong;
	private JTextField txtGiaVe;
	private JLabel lblTongTien;
	private JSpinner spnSoLuongVe;
	private JPanel seatPanel;
	private List<String> selectedSeats = new ArrayList<>();
	private JTable veTable;
	private DefaultTableModel veTableModel;
	private DefaultTableModel thucPhamTableModel;
	private JComboBox<String> cboMaKH;
	private JTextField txtMaNV;
	private JComboBox<String> cboDoAn;
	private JSpinner spnSoLuongDoAn;
	private JTextField txtGiaDoAn;
	private JTable thucPhamTable;
	private Box bCenter5;

	private Dao_Phim dsp = new Dao_Phim();
	private Dao_PhongChieu dspc = new Dao_PhongChieu();
	private Dao_SuatChieu dssc = new Dao_SuatChieu();
	private Dao_Ghe dsg = new Dao_Ghe();
	private Dao_NhanVien dsnv = new Dao_NhanVien();
	private Dao_KhachHang dskh = new Dao_KhachHang();
	private Dao_HoaDon dshd = new Dao_HoaDon();
	private Dao_ChiTietHoaDon dscthd = new Dao_ChiTietHoaDon();
	private ArrayList<Phim> list_phim;
	private ArrayList<PhongChieu> list_phongChieu;
	private ArrayList<SuatChieu> list_SuatChieus;
	private ArrayList<KhachHang> list_khachHangs;
	private ArrayList<NhanVien> list_nhanVien;


	public Gui_BanVe(NhanVien nv) {
		setTitle("Bán vé và thực phẩm");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setupMenuBar();
		setupToolBar();
		add(createTicketPanel(nv), BorderLayout.WEST);
		add(createveTablePanel(), BorderLayout.CENTER);

	}

	private void setupMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(104, 109, 224));
		banHangMenu = new JMenu("Bán hàng");
		dangXuatMenu = new JMenu("Đăng xuất");

		banHangMenu.setPreferredSize(new Dimension(150, 40));
		banHangMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
		banHangMenu.setOpaque(true);
		banHangMenu.setBackground(Color.white);
		banHangMenu.setForeground(Color.black);

		dangXuatMenu.setPreferredSize(new Dimension(150, 40));
		dangXuatMenu.setBorder(new EmptyBorder(0, 25, 0, 50));
		dangXuatMenu.setForeground(Color.white);
		dangXuatMenu.setIcon(resizeImage("image//dangXuat-icon.jpg", 40, 40));

		menuBar.add(banHangMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(dangXuatMenu);

		setJMenuBar(menuBar);

		banHangMenu.addMenuListener(this);
		dangXuatMenu.addMenuListener(new MenuListener() {
	        @Override
	        public void menuSelected(MenuEvent e) {
	            int confirm = JOptionPane.showConfirmDialog(
	                Gui_BanVe.this,
	                "Bạn có chắc chắn muốn đăng xuất?",
	                "Đăng xuất",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE
	            );
	            if (confirm == JOptionPane.YES_OPTION) {
	                dispose(); // Đóng cửa sổ bán vé
	                new Gui_DangNhap().setVisible(true); // Mở trang đăng nhập
	            }
	        }
	        @Override public void menuDeselected(MenuEvent e) {}
	        @Override public void menuCanceled(MenuEvent e) {}
	    });
	}

	private void setupToolBar() {
		toolBar.add(btnBanHang = createControlButton("Vé phim", "image//banVe-icon.png", 60, 40));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		btnBanHang.setBackground(new Color(104, 109, 224));

		add(toolBar, BorderLayout.NORTH);
	}

	private JButton createControlButton(String title, String imagePath, int width, int height) {
		JButton button = new JButton(title);
		button.setIcon(resizeImage(imagePath, width, height));
		button.setFocusable(false);
		return button;
	}

	private JPanel createveTablePanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);

		Box bCenter = Box.createVerticalBox();
		Box bCenter1, bCenter2, bCenter3, bCenter4, bCenter6;

		bCenter.add(bCenter1 = Box.createHorizontalBox());
		JLabel titleLabelPhim = new JLabel("Vé phim", SwingConstants.CENTER);
		titleLabelPhim.setFont(new Font("Arial", Font.BOLD, 16));
		titleLabelPhim.setBorder(new EmptyBorder(10, 0, 10, 0));
		bCenter1.add(titleLabelPhim, BorderLayout.NORTH);	

		veTableModel = new DefaultTableModel(
				new Object[] { "Mã KH", "Mã NV", "Phim", "Suất Chiếu", "Phòng", "Số lượng", "Ghế", "Thành tiền" }, 0);
		veTable = new JTable(veTableModel);
		veTable.setAutoCreateRowSorter(true);
		veTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		veTable.setPreferredScrollableViewportSize(new Dimension(1050, 200));

		bCenter.add(bCenter2 = Box.createHorizontalBox());
		bCenter2.add(new JScrollPane(veTable));
		bCenter.add(Box.createVerticalStrut(20));

		bCenter.add(bCenter5 = Box.createHorizontalBox());
		JLabel lblTitleTongTien;
		bCenter5.add(lblTitleTongTien = new JLabel("Tổng tiền: "));
		lblTitleTongTien.setFont(new Font("Arial", Font.BOLD, 16));
		bCenter5.add(lblTongTien = new JLabel("0 VNĐ"));
		lblTongTien.setFont(new Font("Arial", Font.BOLD, 16));
		lblTongTien.setForeground(Color.red);
		bCenter.add(Box.createVerticalStrut(15));

		bCenter.add(bCenter6 = Box.createHorizontalBox());
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(255, 69, 0));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.addActionListener(e -> deleteSelectedRows());
		bCenter6.add(btnXoa);
		bCenter6.add(Box.createHorizontalStrut(10));

		// Nút Lưu
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setBackground(new Color(104, 109, 224));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.addActionListener(e -> actionLuuHoaDon());
		bCenter6.add(btnLuu);
		bCenter6.add(Box.createHorizontalStrut(10));

		btnXuatHoaDon = new JButton("In vé phim");
		btnXuatHoaDon.setBackground(new Color(60, 179, 113));
		btnXuatHoaDon.setForeground(Color.WHITE);
		btnXuatHoaDon.addActionListener(e -> {
				createReceiptGUI();
		});
		bCenter6.add(btnXuatHoaDon);
		
		

		panel.add(bCenter);

		return panel;
	}

	private void createReceiptGUI() {
	    int[] selectedRows = veTable.getSelectedRows();

	    if (selectedRows.length == 0) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một vé để xuất hóa đơn.");
	        return;
	    }

	    JFrame receiptFrame = new JFrame("Hóa Đơn");
	    receiptFrame.setSize(500, 400);
	    receiptFrame.setLayout(new BorderLayout());
	    receiptFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    JTextArea receiptArea = new JTextArea();
	    receiptArea.setEditable(false);
	    receiptArea.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 14));
	    receiptArea.setMargin(new java.awt.Insets(10, 10, 10, 10));

	    StringBuilder sb = new StringBuilder();
	    sb.append("                    VÉ XEM PHIM LOREM CINEMA              \n");
	  
	    // Giả sử mã KH và mã NV giống nhau cho tất cả vé
	    String maKH = veTableModel.getValueAt(selectedRows[0], 0).toString();
	    String maNV = veTableModel.getValueAt(selectedRows[0], 1).toString();

	    sb.append("Mã KH: ").append(maKH).append("\n");
	    sb.append("Mã NV: ").append(maNV).append("\n");
	    sb.append("------------------------------------------\n");
	    sb.append(String.format("%-20s %-10s %-10s %-10s\n", "Phim", "Suất", "Ghế", "Thành tiền"));
	    sb.append("------------------------------------------\n");

	    double tongCong = 0.0;

	    // Lặp qua các hàng được chọn
	    for (int row : selectedRows) {
	        String phim = veTableModel.getValueAt(row, 2).toString();
	        String suatChieu = veTableModel.getValueAt(row, 3).toString();
	        String ghe = veTableModel.getValueAt(row, 6).toString();
	        String tongTienStr = veTableModel.getValueAt(row, 7).toString();

	        // Xử lý lỗi null
	        if (tongTienStr == null || tongTienStr.isEmpty()) tongTienStr = "0";

	        double thanhTien = 0;
	        try {
	            thanhTien = Double.parseDouble(tongTienStr);
	        } catch (NumberFormatException e) {
	            thanhTien = 0;
	        }

	        tongCong += thanhTien;

	        sb.append(String.format("%-20s %-10s %-10s %-10.0f\n", phim, suatChieu, ghe, thanhTien));
	    }

	    sb.append("------------------------------------------\n");
	    sb.append(String.format("TỔNG CỘNG: %.0f VNĐ\n", tongCong));
	    sb.append("------------------------------------------\n");
	    sb.append("Cảm ơn quý khách đã sử dụng dịch vụ!\n");

	    receiptArea.setText(sb.toString());
	    receiptFrame.add(new JScrollPane(receiptArea), BorderLayout.CENTER);

	    // Căn giữa màn hình
	    receiptFrame.setLocationRelativeTo(null);
	    receiptFrame.setVisible(true);
	}


	private void deleteSelectedRows() {
	    int[] selectedRows1 = veTable.getSelectedRows();

	    if (selectedRows1.length > 0) {
	        // Hiển thị hộp thoại xác nhận
	        int confirm = JOptionPane.showConfirmDialog(
	            this,
	            "Bạn có chắc chắn muốn xóa các hàng đã chọn không?",
	            "Xác nhận xóa",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.WARNING_MESSAGE
	        );

	        if (confirm == JOptionPane.YES_OPTION) {
	            // Xóa từ dòng cuối lên để không bị lệch index
	            for (int i = selectedRows1.length - 1; i >= 0; i--) {
	                veTableModel.removeRow(selectedRows1[i]);
	            }
	            JOptionPane.showMessageDialog(this, "Đã xóa thành công!");
	        }

	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng để xóa.");
	    }
	}


	private JPanel createTicketPanel(NhanVien nv) {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));

		Box bWest = Box.createVerticalBox();
		Box bWest1, bWest2, bWest3, bWest4, bWest5, bWest6, bWest7, bWest8, bWest9, bWest10, bWest11, bWest12, bWest13,
				bWest14;

		bWest.add(Box.createVerticalStrut(10));
		bWest.add(bWest1 = Box.createHorizontalBox());
		JLabel lblKH;
		bWest1.add(lblKH = new JLabel("Mã khách hàng:"));
		cboMaKH = new JComboBox<String>();
		bWest1.add(cboMaKH);
		bWest.add(Box.createVerticalStrut(15));

		bWest.add(bWest2 = Box.createHorizontalBox());
		JLabel lblNV;
		bWest2.add(lblNV = new JLabel("Mã nhân viên:"));
		txtMaNV = new JTextField(10);
//		txtMaNV.setText(nv.getMaNhanVien());
		txtMaNV.setText("NV004");
		txtMaNV.setEditable(true);
		bWest2.add(txtMaNV);
		bWest.add(Box.createVerticalStrut(15));

		bWest.add(bWest3 = Box.createHorizontalBox());
		JLabel lblThemPhim;
		bWest3.add(lblThemPhim = new JLabel("Thêm vé phim"));
		lblThemPhim.setFont(new Font("Arial", Font.BOLD, 16));
		bWest.add(Box.createVerticalStrut(15));

		bWest.add(bWest4 = Box.createHorizontalBox());
		JLabel lblPhim;
		bWest4.add(lblPhim = new JLabel("Chọn phim: "));
		cboPhim = new JComboBox<>();
		bWest4.add(cboPhim);
		bWest.add(Box.createVerticalStrut(15));

		bWest.add(bWest5 = Box.createHorizontalBox());
		JLabel lblSC;
		bWest5.add(lblSC = new JLabel("Chọn suất chiếu: "));
		cboSuatChieu = new JComboBox<>();
		bWest5.add(cboSuatChieu);
		bWest.add(Box.createVerticalStrut(15));

		bWest.add(bWest6 = Box.createHorizontalBox());
		JLabel lblPhong;
		bWest6.add(lblPhong = new JLabel("Chọn Phòng: "));
		cboPhong = new JComboBox<>();
		bWest6.add(cboPhong);
		bWest.add(Box.createVerticalStrut(15));

		bWest.add(bWest7 = Box.createHorizontalBox());
		JLabel lblGVP;
		bWest7.add(lblGVP = new JLabel("Giá vé:"));
		txtGiaVe = new JTextField("75000 VNĐ");
		txtGiaVe.setForeground(new Color(34, 139, 34));
		txtGiaVe.setFont(new Font("Arial", Font.BOLD, 13));
		txtGiaVe.setEditable(false);
		bWest7.add(txtGiaVe);
		bWest.add(Box.createVerticalStrut(15));

		bWest.add(bWest8 = Box.createHorizontalBox());
		JLabel lblG;
		bWest8.add(lblG = new JLabel("Chọn ghế: "));
		seatPanel = createSeatPanel();
		bWest8.add(seatPanel);
		bWest.add(Box.createVerticalStrut(15));

		bWest.add(bWest9 = Box.createHorizontalBox());
		JButton btnThemVaoBang = new JButton("Thêm vào Bảng");
		btnThemVaoBang.setBackground(new Color(104, 109, 224));
		btnThemVaoBang.setForeground(Color.WHITE);
		btnThemVaoBang.setPreferredSize(new Dimension(100, 30));
		btnThemVaoBang.addActionListener(e -> addVeToTable());
		bWest9.add(btnThemVaoBang);
		bWest.add(Box.createVerticalStrut(15));


		lblKH.setPreferredSize(lblSC.getPreferredSize());
		lblNV.setPreferredSize(lblSC.getPreferredSize());
		lblPhim.setPreferredSize(lblSC.getPreferredSize());
		
		lblPhong.setPreferredSize(lblSC.getPreferredSize());
		lblG.setPreferredSize(lblSC.getPreferredSize());
		
		lblGVP.setPreferredSize(lblSC.getPreferredSize());


		list_phim = dsp.layDanhSachPhim();
		list_SuatChieus = dssc.layDanhSachSuatChieu();
		list_khachHangs = dskh.layDanhSachKhachHang();
		list_nhanVien = dsnv.layDanhSachNhanVien();

		for (Phim phim : list_phim) {
			cboPhim.addItem(phim.getTenPhim());
		}
		
		for (KhachHang khachHang : list_khachHangs) {
			cboMaKH.addItem(khachHang.getMaKhachHang());
		}
		
		cboPhim.addActionListener(e -> {
			cboSuatChieu.removeAllItems();
			for (SuatChieu suatChieu : list_SuatChieus) {
				if (suatChieu.getPhim().getTenPhim().equals(cboPhim.getSelectedItem())) {
					cboSuatChieu.addItem(suatChieu.getThoiGianBatDau());
				}
			}
		});
		
		cboSuatChieu.addActionListener(e -> {
			cboPhong.removeAllItems();
			for (SuatChieu suatChieu : list_SuatChieus) {
				if (suatChieu.getThoiGianBatDau().equals(cboSuatChieu.getSelectedItem()) && suatChieu.getPhim().getTenPhim().equals(cboPhim.getSelectedItem())) {
					cboPhong.addItem(suatChieu.getPhongChieu().getMaPhong());
				}
			}
		});
		panel.add(bWest);
		return panel;
	}
	private JPanel createSeatPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 5, 5, 5));
		for (int i = 1; i <= 25; i++) {
			JButton seat = new JButton("A" + i);
			seat.setBackground(Color.LIGHT_GRAY);
			seat.addActionListener(e -> selectSeat(seat));
			panel.add(seat);
		}
		return panel;
	}

	private void selectSeat(JButton seat) {
		if (seat.getBackground() == Color.LIGHT_GRAY) {
			seat.setBackground(Color.GREEN);
			selectedSeats.add(seat.getText());
		} else {
			seat.setBackground(Color.LIGHT_GRAY);
			selectedSeats.remove(seat.getText());
		}
//		updateTotalPrice();
	}


	private double tinhTongTien() {
		double tong = 0;
		int row1 = veTable.getRowCount();
		System.out.println(row1);
		for (int i = 0; i < row1; i++) {
			tong += Double.parseDouble((String) veTable.getValueAt(i, 7));
		}
		return tong;
	}

	private void addVeToTable() {
		String maKH = cboMaKH.getSelectedItem().toString();
		String maNV = txtMaNV.getText();
		String phim = (String) cboPhim.getSelectedItem();
		String suatChieu = (String) cboSuatChieu.getSelectedItem();
		String phong = (String) cboPhong.getSelectedItem();
		String text = txtGiaVe.getText();
		String[] parts = text.split(" ");
		String tongTien = parts[0];

		// Thêm mỗi ghế là một hàng vào bảng
		if (!cboPhim.getSelectedItem().equals("")) {
			for (String ghe : selectedSeats) {
				veTableModel.addRow(new Object[] { maKH, maNV, phim, suatChieu, phong, 1, ghe, tongTien });
			}
		}

		// Reset lại sau khi thêm vào bảng
		selectedSeats.clear();
		for (int i = 0; i < seatPanel.getComponentCount(); i++) {
			((JButton) seatPanel.getComponent(i)).setBackground(Color.LIGHT_GRAY);
		}
		lblTongTien.setText(String.valueOf(tinhTongTien()) + " VNĐ");
		cboMaKH.setSelectedIndex(0);
		cboPhim.setSelectedIndex(0);
		cboPhong.setSelectedIndex(0);
		cboSuatChieu.setSelectedIndex(0);
	}

//	private void actionLuuChiTietHoaDon() {
//		try {
//			int row1 = veTable.getRowCount();
//			if (row1 != 0) {
//				for (int i = 0; i < row1; i++) {
//					ChiTietHoaDon chiTietHoaDon = taoChiTietHoaDonMoi_VePhim(row1);
//					dscthd.themChiTietHoaDon(chiTietHoaDon);
//				}
//			} else if (row2 != 0) {
//				for (int i = 0; i < row2; i++) {
//					ChiTietHoaDon chiTietHoaDon = taoChiTietHoaDonMoi_ThucPham(row2);
//					dscthd.themChiTietHoaDon(chiTietHoaDon);
//				}
//			}
//			DefaultTableModel dtm = (DefaultTableModel) veTable.getModel();
//			dtm.getDataVector().removeAllElements();
//			dtm = (DefaultTableModel) thucPhamTable.getModel();
//			dtm.getDataVector().removeAllElements();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	private void actionLuuHoaDon() {
//		try {
			dshd.themHoaDon(taoHoaDonMoi());
			JOptionPane.showMessageDialog(null, "Lưu thành công.");
			veTableModel.setRowCount(0);
//		} catch (Exception e) {
//			// TODO: handle exception
//			JOptionPane.showMessageDialog(null, "Lỗi server.");
//		}
	}
	private String taoMaHoaDon() {
		ArrayList<HoaDon> list = dshd.layDanhSachHoaDon();
		int tongHoaDon = list.size();
		if (tongHoaDon < 10)
			return "HD00" + String.valueOf(tongHoaDon);
		if (tongHoaDon < 100)
			return "HD0" + String.valueOf(tongHoaDon);
		return "HD" + String.valueOf(tongHoaDon);
	}
	private HoaDon taoHoaDonMoi() {
		String maHD = taoMaHoaDon();
		LocalDate ngayLap = LocalDate.now();
		String text = lblTongTien.getText();
		String[] parts = text.split(" ");
		String numberPart = parts[0];
		double tongTien = Double.parseDouble(numberPart);
		String maNhanVien = txtMaNV.getText();
		String tenNhanVien = dsnv.timNhanVienTheoMa(maNhanVien).get(0).getHoTen();
		System.out.println(dsnv.timNhanVienTheoMa(maNhanVien).get(0).getHoTen());
		NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien);
		KhachHang khachHang = new KhachHang(cboMaKH.getSelectedItem().toString());
		return new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
	}

	private HoaDon taoHoaDonMoi_VePhim(int row) {
		String maHD = taoMaHoaDon();
		LocalDate ngayLap = LocalDate.now();
		String text = lblTongTien.getText();
		String[] parts = text.split(" ");
		String numberPart = parts[0];
		double tongTien = Double.parseDouble(numberPart);
		String maNhanVien = veTableModel.getValueAt(row, 1).toString();
		NhanVien nhanVien = dsnv.timNhanVienTheoMa(maNhanVien).get(0);
		String maKhachHang = veTableModel.getValueAt(row, 0).toString();
		KhachHang khachHang = dskh.timKhachHangTheoMa(maKhachHang).get(0);
		return new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
	}
//	private ChiTietHoaDon taoChiTietHoaDonMoi_ThucPham(int row) {
//		HoaDon hoaDon = new HoaDon(taoMaHoaDon());
//		String tenTP = thucPhamTable.getValueAt(row, 3).toString();
//		ThucPham thucPham = dstp.timThucPhamTheoTen(tenTP).get(0);
//		int soLuongSanPham = Integer.parseInt(thucPhamTable.getValueAt(row, 2).toString());
//		double thanhTien = Double.parseDouble(thucPhamTable.getValueAt(row, 5).toString());
//		return new ChiTietHoaDon(hoaDon, thucPham, soLuongSanPham, thanhTien);
//	}

//	private double updateFoodPrice() {
//		String selectedFood = cboDoAn.getSelectedItem().toString();
//		double price = 0;
//		ArrayList<ThucPham> list_tp = dstp.layDanhSachThucPham();
//		for (ThucPham thucPham : list_tp) {
//			if(selectedFood.equals(thucPham.getTenThucPham())) {
//				price = thucPham.getDonGia();
//				txtGiaDoAn.setText(price + " VNĐ");
//			}
//		}
//		return price;
//	}

	private ImageIcon resizeImage(String path, int width, int height) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	@Override
	public void menuDeselected(MenuEvent e) {
	}

	@Override
	public void menuCanceled(MenuEvent e) {
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		String phimDuocChon = (String) cboPhim.getSelectedItem();

		cboPhong.removeAllItems();
		list_phongChieu = dspc.layDanhSachPhongChieu();
		Set<String> uniquePhongChieu = new HashSet<>();

		for (SuatChieu suatChieu : list_SuatChieus) {
			if (suatChieu.getPhim().getTenPhim().equals(phimDuocChon)) {
				String maPhong = suatChieu.getPhongChieu().getMaPhong();
				if (uniquePhongChieu.add(maPhong)) {
					cboPhong.addItem(maPhong);
				}
			}
		}
		cboPhong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String phongDuocChon = (String) cboPhong.getSelectedItem();
				cboSuatChieu.removeAllItems();

				for (SuatChieu suatChieu : list_SuatChieus) {
					if (suatChieu.getPhongChieu().getMaPhong().equals(phongDuocChon)
							&& suatChieu.getPhim().getTenPhim().equals(phimDuocChon)) {
						cboSuatChieu.addItem(suatChieu.getThoiGianBatDau());
					}
				}
			}
		});
		
		

	


	}
	
	

	public static void main(String[] args) {
		
			NhanVien nv = new NhanVien();
			Gui_BanVe gui = new Gui_BanVe(nv);
			gui.setVisible(true);
	}
}