package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import dao.Dao_BanVe;
import entity.VePhim;

public class Gui_QuanLyVe extends JFrame implements ActionListener, MenuListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu quanLyMenu;
	private JMenu banHangMenu;
	private JMenu thongKeMenu;
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
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnInVe;
	private JButton btnXoa;
	private JRadioButton radSapXepMa;
	private JRadioButton radSapXepTien;
	private JButton btnSapXep;
	private JLabel lblTimTheoMa;
	private JButton btnTimTheoMa;
	private AbstractButton btnTaiKhoan;
	private Dao_BanVe dbv = new Dao_BanVe();
	
	private JButton btnDoanhThu;

	public Gui_QuanLyVe() {
		setTitle("Quản lý vé phim");
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
		btnVe.setBackground(new Color(104, 109, 224));

		// Đặt controlPanel ở phía trên cùng (giống như thanh công cụ cố định)
		add(toolBar, BorderLayout.NORTH);

		// Thêm logo Cineplex
		JPanel jpWest = new JPanel();
		Box bWest = Box.createVerticalBox();
		Box bWest1, bWest2, bWest3, bWest4, bWest5, bWest6;
		bWest.setBackground(Color.lightGray);
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest1 = Box.createHorizontalBox());
		JLabel lblTitle;
		bWest1.add(lblTitle = new JLabel("Tra cứu vé phim"));
		lblTitle.setForeground(new Color(104, 109, 224));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest2 = Box.createHorizontalBox());
		bWest2.add(lblTimTheoMa = new JLabel("Tìm vé phim theo mã:  "));
//		bWest2.add(Box.createHorizontalStrut(85));
		bWest2.add(txtTimMa = new JTextField());
		bWest2.add(btnTimTheoMa = new JButton("Tìm"));
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest3 = Box.createHorizontalBox());
		JLabel lblSapXep;
		bWest3.add(lblSapXep = new JLabel("Sắp xếp vé phim theo tiêu chí"));
		lblSapXep.setForeground(new Color(104, 109, 224));
		lblSapXep.setFont(new Font("Arial", Font.BOLD, 20));
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest4 = Box.createHorizontalBox());
		bWest4.add(radSapXepMa = new JRadioButton("Sắp xếp theo mã vé phim"));
		bWest4.add(Box.createHorizontalStrut(100));
		bWest.add(Box.createVerticalStrut(10));

		bWest.add(bWest5 = Box.createHorizontalBox());
		bWest5.add(radSapXepTien = new JRadioButton("Sắp xếp theo đơn giá"));
		bWest5.add(Box.createHorizontalStrut(124));
		ButtonGroup groupSapXep = new ButtonGroup();
		groupSapXep.add(radSapXepMa);
		groupSapXep.add(radSapXepTien);
		bWest.add(Box.createVerticalStrut(20));

		bWest.add(bWest6 = Box.createHorizontalBox());
		bWest6.add(btnSapXep = new JButton("Sắp xếp"));

		jpWest.add(bWest);
		add(jpWest, BorderLayout.WEST);

		JPanel jpCenter = new JPanel();
		jpCenter.setBackground(Color.white);
		Box bCenter = Box.createVerticalBox();
		bCenter.setBackground(Color.blue);
		Box bCenter1, bCenter2;
		bCenter.add(Box.createVerticalStrut(30));

		String[] row = { "STT", "Mã vé phim", "Mã phòng chiếu", "Tên phim", "Ngày chiếu", "Suất chiếu", "Mã ghế", "Giá",
				"Mô tả" };
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
		bCenter2.add(Box.createHorizontalStrut(10));
		bCenter2.add(btnInVe = new JButton("In vé phim"));
		bCenter2.add(Box.createHorizontalStrut(20));
		bCenter2.add(btnXoa = new JButton("Xóa"));
		bCenter2.add(Box.createHorizontalStrut(10));

		jpCenter.add(bCenter);
		add(jpCenter, BorderLayout.CENTER);

		btnPhim.addActionListener(this);
		btnSuatChieu.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnVe.addActionListener(this);

		btnInVe.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTimTheoMa.addActionListener(this);
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

	//@Override
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
		if (o.equals(btnInVe)) {
		inVePhim();
		}
		if (o.equals(btnDoanhThu)) {
			new Gui_ThongKeDoanhThu().setVisible(true);
			this.setVisible(false);
		}

		if (o.equals(btnXoa))
			actionXoa();
		if (o.equals(btnTimTheoMa))
			actionTimKiem();
		if (o.equals(btnSapXep))
			actionSapXep();
	}

	private void actionTimKiem() {
	    String maTim = txtTimMa.getText().trim();

	    if (maTim.isEmpty()) {
	        hienThiCanhBao("Vui lòng nhập mã vé để tìm!");
	        txtTimMa.requestFocus();
	        return;
	    }

	    ArrayList<VePhim> ketQua = dbv.timVePhimTheoMa(maTim);

	    if (ketQua.isEmpty()) {
	        hienThiThongBao("Không tìm thấy vé có mã: " + maTim);
	        txtTimMa.selectAll();
	        return;
	    }

	    capNhatBang(ketQua);
	    hienThiThongBao("Tìm thấy " + ketQua.size() + " vé phù hợp!");
	}
	private void actionSapXep() {
	    if (!radSapXepMa.isSelected() && !radSapXepTien.isSelected()) {
	        hienThiCanhBao("Vui lòng chọn tiêu chí sắp xếp!");
	        return;
	    }

	    ArrayList<VePhim> danhSach;

	    if (radSapXepMa.isSelected()) {
	        danhSach = dbv.sapXepTheoMa();
	    } else {
	        danhSach = dbv.sapXepTheoGia(); // hoặc theo ngày nếu không có giá
	    }

	    capNhatBang(danhSach);
	}
	private void actionXoa() {
	    int row = table.getSelectedRow();
	    if (row == -1) {
	        hienThiCanhBao("Vui lòng chọn một vé để xóa!");
	        return;
	    }

	    String maVe = layGiaTriTai(row, 1);
	    String tenPhim = layGiaTriTai(row, 3);

	    int xacNhan = JOptionPane.showConfirmDialog(
	        this,
	        String.format("Xóa vé phim:\nMã: %s\nPhim: %s\n\nBạn có chắc chắn?", maVe, tenPhim),
	        "Xác nhận xóa",
	        JOptionPane.YES_NO_OPTION,
	        JOptionPane.QUESTION_MESSAGE
	    );

	    if (xacNhan != JOptionPane.YES_OPTION) return;

	    if (dbv.xoaVePhim(maVe)) {
	        tableModel.removeRow(row);
	        capNhatSTT();
	        hienThiThongBao("Xóa vé thành công!");
	    } else {
	        hienThiLoi("Xóa thất bại! Vé có thể đang được sử dụng trong hóa đơn.");
	    }
	}
	private void themVeVaoBang(VePhim ve, int stt) {
	    Object[] row = {
	        stt,
	        ve.getMaVe(),
	        ve.getPc().getMaPhong(),
	        ve.getPhim().getTenPhim(),
	        ve.getSuatChieu().getNgayKhoiChieu(),
	        ve.getSuatChieu().getThoiGianBatDau(),
	        ve.getGhe(),
	        "75,000", // Có thể thay bằng ve.getDonGia() nếu có
	        ve.getPhim().getMoTa()
	    };
	    tableModel.addRow(row);
	}
	private void capNhatSTT() {
	    for (int i = 0; i < tableModel.getRowCount(); i++) {
	        tableModel.setValueAt(i + 1, i, 0);
	    }
	}
	private void capNhatBang(ArrayList<VePhim> danhSach) {
	    tableModel.setRowCount(0); // Xóa hết
	    int stt = 1;
	    for (VePhim ve : danhSach) {
	        themVeVaoBang(ve, stt++);
	    }
	}
	private String layGiaTriTai(int row, int col) {
	    Object value = tableModel.getValueAt(row, col);
	    return value != null ? value.toString() : "";
	}
	private void hienThiThongBao(String msg) {
	    JOptionPane.showMessageDialog(this, msg, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

	private void hienThiCanhBao(String msg) {
	    JOptionPane.showMessageDialog(this, msg, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
	}

	private void hienThiLoi(String msg) {
	    JOptionPane.showMessageDialog(this, msg, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}
	
//	private void actionSapXep() {
//		// TODO Auto-generated method stub
//		if (radSapXepMa.isSelected()) {
//			ArrayList<VePhim> list = dsvp.sapXepVePhimTheoMa();
//			XoaDataTrenModel();
//			int i = 0;
//			for (VePhim vePhim : list) {
//				tableModel.addRow(new Object[] { i + 1, vePhim.getMaSanPham(), vePhim.getTenSanPham(),
//						vePhim.getPhim().getTenPhim(), vePhim.getPhongChieu().getTenPhong(), vePhim.getGhe().getMaGhe(),
//						vePhim.getSuatChieu().getMaSuatChieu(), vePhim.getDonGia(), vePhim.getPhim().getMoTa() });
//			}
//		}
//		if (radSapXepTien.isSelected()) {
//			ArrayList<VePhim> list = dsvp.sapXepVePhimGia();
//			XoaDataTrenModel();
//			int i = 0;
//			for (VePhim vePhim : list) {
//				tableModel.addRow(new Object[] { i + 1, vePhim.getMaSanPham(), vePhim.getTenSanPham(),
//						vePhim.getPhim().getTenPhim(), vePhim.getPhongChieu().getTenPhong(), vePhim.getGhe().getMaGhe(),
//						vePhim.getSuatChieu().getMaSuatChieu(), vePhim.getDonGia(), vePhim.getPhim().getMoTa() });
//			}
//		}
//	}
//	
//	private void actionTim() {
//		// TODO Auto-generated method stub
//		String timMaVP = txtTimMa.getText().trim();
//		ArrayList<VePhim> list = dsvp.timVePhimTheoMa(timMaVP);
//		if (timMaVP.isBlank()) {
//			DocDuLieuDatabaseVaoTable();
//		} else {
//			if (list != null) {
//				XoaDataTrenModel();
//				int i = 0;
//				for (VePhim vePhim : list) {
//					tableModel.addRow(new Object[] { i + 1, vePhim.getMaSanPham(), vePhim.getTenSanPham(),
//							vePhim.getPhim().getTenPhim(), vePhim.getPhongChieu().getTenPhong(),
//							vePhim.getGhe().getMaGhe(), vePhim.getSuatChieu().getMaSuatChieu(), vePhim.getDonGia(),
//							vePhim.getPhim().getMoTa() });
//				}
//			} else {
//				JOptionPane.showMessageDialog(null, "Không tìm thấy.");
//				txtTimMa.requestFocus();
//				txtTimMa.selectAll();
//			}
//		}
//	}
//
//	private void actionXoa() {
//		// TODO Auto-generated method stub
//		int row = table.getSelectedRow();
//		if (row != -1) {
//			String maVePhim = (String) table.getModel().getValueAt(row, 1);
//			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa? ", "Chú ý",
//					JOptionPane.YES_NO_OPTION);
//			if (hoiNhac == JOptionPane.YES_OPTION)
//				if (dsvp.xoaVePhim(maVePhim)) {
//					tableModel.removeRow(row);
//				}
//		}
//	}
//
//	private void DocDuLieuDatabaseVaoTable() {
//		Dao_ThucPham dsvp = new Dao_ThucPham();
//		ArrayList<VePhim> list = dsvp.layDanhSachVePhim();
//		int i = 0;
//		for (VePhim vePhim : list) {
//			tableModel.addRow(new Object[] { i + 1, vePhim.getMaSanPham(), vePhim.getTenSanPham(),
//					vePhim.getPhim().getTenPhim(), vePhim.getPhongChieu().getTenPhong(), vePhim.getGhe().getMaGhe(),
//					vePhim.getSuatChieu().getMaSuatChieu(), vePhim.getDonGia(), vePhim.getPhim().getMoTa() });
//		}
//		table.setModel(tableModel);
//	}
//
//	public void XoaDataTrenModel() {
//		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
//		dtm.getDataVector().removeAllElements();
//	}
	private void DocDuLieuDatabaseVaoTable() {
	    // Xóa dữ liệu cũ trong table trước khi load mới
	    tableModel.setRowCount(0);
	    // Lấy danh sách vé phim từ DAO
	    ArrayList<VePhim> dsVePhim = dbv.layDanhSachVePhim();
	    int i = 1;
	    // Đưa từng vé phim vào bảng
	    for (VePhim ve : dsVePhim) {
	        Object[] rowData = {
	        		i,
	            ve.getMaVe(),
	            ve.getPc().getMaPhong(),
	            ve.getPhim().getTenPhim(),
	            ve.getSuatChieu().getNgayKhoiChieu(),
	            ve.getSuatChieu().getThoiGianBatDau(),
	            ve.getGhe(),
	            "75000",
	            ve.getPhim().getMoTa(),
	        };
	        tableModel.addRow(rowData);
	        i += 1;
	    }
	    // Cập nhật hiển thị
	    table.setModel(tableModel);
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
			btnThucPham.addActionListener(this);
			btnKhachHang.addActionListener(this);
			btnNhanVien.addActionListener(this);
			btnHoaDon.addActionListener(this);
			btnVe.addActionListener(this);
		}
//		if (sourceMenu.getText().equals("Bán hàng")) {
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
			toolBar.add(btnDoanhThu = createControlButton("Doanh thu", "image//doanhthu-icon.png", 40, 40));
			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
//			toolBar.add(btnSanPham = createControlButton("Sản phẩm", "image//sanpham-icon.png", 40, 40));
//			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
//			toolBar.add(btnKhachHang = createControlButton("Khách hàng", "image//customer-icon.png", 40, 40));
//			toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

			toolBar.revalidate();
			toolBar.repaint();

			btnDoanhThu.addActionListener(this);
//			btnSanPham.addActionListener(this);
//			btnKhachHang.addActionListener(this);
		}
		if (sourceMenu.getText().equals("Đăng xuất")) {
			new Gui_DangNhap().setVisible(true);
			this.setVisible(false);
		}
	}

	private void inVePhim() {
	    int selectedRow = table.getSelectedRow();

	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một vé phim để in!");
	        return;
	    }

	    // Lấy thông tin từ bảng
	    String maVe = tableModel.getValueAt(selectedRow, 1).toString();
	    String maPhong = tableModel.getValueAt(selectedRow, 2).toString();
	    String tenPhim = tableModel.getValueAt(selectedRow, 3).toString();
	    String ngayChieu = tableModel.getValueAt(selectedRow, 4).toString();
	    String gioChieu = tableModel.getValueAt(selectedRow, 5).toString();
	    String ghe = tableModel.getValueAt(selectedRow, 6).toString();
	    String gia = tableModel.getValueAt(selectedRow, 7).toString();
	    String moTa = tableModel.getValueAt(selectedRow, 8).toString();

	    // Tạo khung in vé
	    JFrame frame = new JFrame("In vé phim");
	    frame.setSize(450, 500);
	    frame.setLayout(new BorderLayout());
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    JTextArea area = new JTextArea();
	    area.setEditable(false);
	    area.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 14));
	    area.setMargin(new java.awt.Insets(10, 10, 10, 10));

	    // Tạo nội dung vé
	    StringBuilder sb = new StringBuilder();
	    sb.append("                LOREM CINEMA               \n");
	    sb.append("=============================================\n");
	    sb.append("                 VÉ XEM PHIM\n");
	    sb.append("=============================================\n");
	    sb.append(String.format("Mã vé:          %s\n", maVe));
	    sb.append(String.format("Phòng chiếu:    %s\n", maPhong));
	    sb.append(String.format("Tên phim:       %s\n", tenPhim));
	    sb.append(String.format("Ngày chiếu:     %s\n", ngayChieu));
	    sb.append(String.format("Giờ chiếu:      %s\n", gioChieu));
	    sb.append(String.format("Ghế:            %s\n", ghe));
	    sb.append("---------------------------------------------\n");
	    sb.append(String.format("Giá vé:         %s VNĐ\n", gia));
	    sb.append("---------------------------------------------\n");
	    sb.append("Mô tả phim:\n");
	    sb.append(moTa + "\n");
	    sb.append("=============================================\n");
	    sb.append("  Cảm ơn quý khách đã chọn Lorem Cinema!\n");
	    sb.append("=============================================\n");

	    area.setText(sb.toString());
	    frame.add(new JScrollPane(area), BorderLayout.CENTER);

	    // Căn giữa
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
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
		new Gui_QuanLyVe().setVisible(true);
	}

	
	
}