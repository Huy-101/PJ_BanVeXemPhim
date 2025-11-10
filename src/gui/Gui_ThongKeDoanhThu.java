package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import dao.Dao_DoanhThu;
import dao.Dao_HoaDon;
import entity.DoanhThu;
import entity.HoaDon;

public class Gui_ThongKeDoanhThu extends JFrame implements ActionListener, MenuListener {

    private JButton btnDoanhThu;
    private JToolBar toolBar = new JToolBar();
    private JButton btnSuatChieu;
    private JMenu quanLyMenu;
    private JMenu thongKeMenu;
    private JMenu dangXuatMenu;
    private JButton btnKhachHang;
    private JButton btnNhanVien;
    private JButton btnHoaDon;
    private JButton btnVe;
    private JButton btnPhim;
    private JCheckBox checkBoxNam;
    private JCheckBox checkBoxThang;
    private JButton btnThongKe;
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> cbNam;
    private JComboBox<String> cbThang;
    private JLabel lblTongDoanhThu;
    private JLabel lblTong;
    private TreeMap<Integer, Double> doanhThu = new TreeMap<>();
    private JLabel lblHoaDon;
    private JLabel lblBieuDo;
    private Dao_DoanhThu dsdt = new Dao_DoanhThu();
    private Dao_HoaDon dshd = new Dao_HoaDon();
    private Box bWest7;
    private JPanel jpCenter;
    private JPanel chartPanel;

    public Gui_ThongKeDoanhThu() {
    	setTitle("Thống kê doanh thu");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(104, 109, 224));

		// Thêm các JMenu
		quanLyMenu = new JMenu("Quản lý");
		thongKeMenu = new JMenu("Thống kê");
		dangXuatMenu = new JMenu("Đăng xuất");

		quanLyMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
		quanLyMenu.setForeground(Color.white);

		thongKeMenu.setBorder(new EmptyBorder(0, 45, 0, 50));
		thongKeMenu.setBackground(Color.white);
		thongKeMenu.setForeground(Color.black);
		thongKeMenu.setOpaque(true);

		dangXuatMenu.setBorder(new EmptyBorder(0, 25, 0, 50));
		dangXuatMenu.setForeground(Color.white);
		dangXuatMenu.setIcon(resizeImage("image//dangXuat-icon.jpg", 40, 40));

		menuBar.add(quanLyMenu);
		menuBar.add(thongKeMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(dangXuatMenu);

		setJMenuBar(menuBar);

		// Thêm các nút điều khiển
		toolBar.add(btnDoanhThu = createControlButton("Doanh thu", "image//doanhthu-icon.png", 40, 40));
		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
		btnDoanhThu.setBackground(new Color(104, 109, 224));
//		toolBar.add(btnSanPham = createControlButton("Sản phẩm", "image//sanpham-icon.png", 40, 40));
//		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));
//		toolBar.add(btnKhachHang = createControlButton("Khách hàng", "image//customer-icon.png", 40, 40));
//		toolBar.add(Box.createRigidArea(new Dimension(20, 0)));

		// Đặt controlPanel ở phía trên cùng (giống như thanh công cụ cố định)
		add(toolBar, BorderLayout.NORTH);

        // ======= PANEL WEST =======
        JPanel jpWest = new JPanel();
        jpWest.setBackground(Color.WHITE);
        Box bWest = Box.createVerticalBox();
        bWest.add(Box.createVerticalStrut(20));

        Box bWest1 = Box.createHorizontalBox();
        JLabel lblTitle = new JLabel("Thống kê doanh thu");
        lblTitle.setForeground(new Color(65, 165, 238));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        bWest1.add(lblTitle);
        bWest.add(bWest1);
        bWest.add(Box.createVerticalStrut(10));

        Box bWest2 = Box.createHorizontalBox();
        bWest2.add(checkBoxNam = new JCheckBox("Thống kê doanh thu theo năm"));
        bWest.add(bWest2);
        bWest.add(Box.createVerticalStrut(10));

        //SAU KHI TẠO cbNam
        Box bWest3 = Box.createHorizontalBox();
        bWest3.add(cbNam = new JComboBox<String>());
        cbNam.addItem("Tất cả");
        for (Integer nam : dshd.layCacNamCoHoaDon()) {
            cbNam.addItem("Năm " + nam);
        }
        bWest.add(bWest3);
        bWest.add(Box.createVerticalStrut(20));

        Box bWest4 = Box.createHorizontalBox();
        bWest4.add(checkBoxThang = new JCheckBox("Thống kê doanh thu theo tháng"));
        checkBoxThang.setEnabled(false);
        bWest.add(bWest4);
        bWest.add(Box.createVerticalStrut(10));

        Box bWest5 = Box.createHorizontalBox();
        bWest5.add(cbThang = new JComboBox<String>());
        for (int i = 1; i <= 12; i++) {
            cbThang.addItem("Tháng " + i);
        }
        cbThang.setEnabled(false);
        bWest.add(bWest5);
        bWest.add(Box.createVerticalStrut(20));

        // Nút thống kê
        Box bWest6 = Box.createHorizontalBox();
        btnThongKe = new JButton("Thống kê");
        btnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThongKe.setBackground(new Color(65, 165, 238));
        btnThongKe.setForeground(Color.WHITE);
        btnThongKe.setBorder(BorderFactory.createLineBorder(new Color(65, 165, 238), 2, true));
        btnThongKe.setFocusPainted(false);
        btnThongKe.setPreferredSize(new Dimension(120, 35));
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThongKe.setBackground(Color.WHITE);
                btnThongKe.setForeground(new Color(65, 165, 238));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThongKe.setBackground(new Color(65, 165, 238));
                btnThongKe.setForeground(Color.WHITE);
            }
        });
        bWest6.add(btnThongKe);
        bWest.add(bWest6);
        bWest.add(Box.createVerticalStrut(40));

        // Tổng doanh thu
        bWest7 = Box.createHorizontalBox();
        bWest7.add(lblTongDoanhThu = new JLabel("Tổng doanh thu: "));
        lblTongDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTong = new JLabel("0 VNĐ");
        lblTong.setForeground(Color.RED);
        lblTong.setFont(new Font("Segoe UI", Font.BOLD, 18));
        bWest7.add(lblTong);
        bWest.add(bWest7);

        jpWest.add(bWest);
        jpWest.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(jpWest, BorderLayout.WEST);

        // ======= PANEL CENTER =======
        jpCenter = new JPanel();
        jpCenter.setBackground(Color.WHITE);
        Box bCenter = Box.createVerticalBox();
        bCenter.add(Box.createVerticalStrut(30));

        Box bCenter1 = Box.createHorizontalBox();
        bCenter1.add(lblHoaDon = new JLabel("Danh sách hóa đơn"));
        lblHoaDon.setForeground(new Color(65, 165, 238));
        lblHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        bCenter.add(bCenter1);
        bCenter.add(Box.createVerticalStrut(10));

        String[] row = {"STT", "Mã hóa đơn", "Mã nhân viên", "Tên nhân viên", "Ngày lập hóa đơn", "Tổng tiền"};
        tableModel = new DefaultTableModel(row, 0);
        JScrollPane scroll = new JScrollPane(table = new JTable(tableModel));
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        table.setPreferredScrollableViewportSize(new Dimension(1050, 200));
        bCenter.add(scroll);
        bCenter.add(Box.createVerticalStrut(30));

        Box bCenter3 = Box.createHorizontalBox();
        bCenter3.add(lblBieuDo = new JLabel("Biểu đồ thống kê"));
        lblBieuDo.setForeground(new Color(65, 165, 238));
        lblBieuDo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        bCenter.add(bCenter3);
        bCenter.add(Box.createVerticalStrut(10));

        jpCenter.add(bCenter);

        // Biểu đồ
        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawChart(g);
            }
        };
        chartPanel.setPreferredSize(new Dimension(1050, 300));
        jpCenter.add(chartPanel);
        add(jpCenter, BorderLayout.CENTER);

        
        btnDoanhThu.addActionListener(this);

        quanLyMenu.addMenuListener(this);
        thongKeMenu.addMenuListener(this);
        dangXuatMenu.addMenuListener(this);
        btnThongKe.addActionListener(this);
        checkBoxNam.addActionListener(this);
        checkBoxThang.addActionListener(this);

        // Load dữ liệu mặc định
        DocDuLieuDatabaseVaoTable();
        taoCayDoanhThu(dsdt.layDoanhThuTatCa());
        capNhatTongDoanhThuTheoDanhSach(dsdt.layDoanhThuTatCa());
        capNhatBieuDo();
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
        if (o.equals(btnThongKe)) {
            actionThongKe();
        }
        if (o.equals(checkBoxNam)) {
            boolean selected = checkBoxNam.isSelected();
            checkBoxThang.setEnabled(selected);
            cbThang.setEnabled(selected && checkBoxThang.isSelected());
            if (!selected) {
                checkBoxThang.setSelected(false);
                cbThang.setEnabled(false);
            }
        }
        if (o.equals(checkBoxThang)) {
            cbThang.setEnabled(checkBoxThang.isSelected());
        }
    }

    private void actionThongKe() {
        XoaDataTrenModel();

        boolean theoNam = checkBoxNam.isSelected();
        boolean theoThang = checkBoxThang.isSelected();
        String nam = (String) cbNam.getSelectedItem();
        String thang = (String) cbThang.getSelectedItem();

        ArrayList<DoanhThu> listDoanhThu = null;
        ArrayList<HoaDon> listHoaDon = null;

        if (theoNam && !theoThang) {
            if ("Tất cả".equals(nam)) {
                listDoanhThu = dsdt.layDoanhThuTatCa();
                listHoaDon = dshd.layDanhSachHoaDon();
            } else {
                String namStr = nam.split(" ")[1];
                listDoanhThu = dsdt.layDoanhThuTrongNam(namStr);
                listHoaDon = dshd.timHoaDonTheoNamLap(namStr); // Chỉ năm
            }
        } else if (theoNam && theoThang) {
            if ("Tất cả".equals(nam)) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn năm cụ thể để thống kê theo tháng!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String namStr = nam.split(" ")[1];
            String thangStr = thang.split(" ")[1];
            listDoanhThu = dsdt.layDoanhThuTrongThang(namStr, thangStr);
            listHoaDon = dshd.timHoaDonTheoNamLap(namStr, thangStr);
        } else {
            listDoanhThu = dsdt.layDoanhThuTatCa();
            listHoaDon = dshd.layDanhSachHoaDon();
        }

        // TÍNH TỔNG DOANH THU
        double tongDoanhThu = 0;
        if (listDoanhThu != null) {
            for (DoanhThu dt : listDoanhThu) {
                tongDoanhThu += dt.getDoanhThu();
            }
        }
        lblTong.setText(String.format("%,.0f VNĐ", tongDoanhThu));

        // CẬP NHẬT BẢNG HÓA ĐƠN
        if (listHoaDon != null && !listHoaDon.isEmpty()) {
            addHoaDonToTable(listHoaDon);
        } else {
            XoaDataTrenModel();
        }

        // CẬP NHẬT BIỂU ĐỒ
        taoCayDoanhThu(listDoanhThu != null ? listDoanhThu : new ArrayList<>());
        capNhatBieuDo();

        bWest7.revalidate();
        bWest7.repaint();
    }

    private void capNhatTongDoanhThuTheoDanhSach(ArrayList<DoanhThu> list) {
        double tong = 0;
        if (list != null) {
            for (DoanhThu dt : list) {
                tong += dt.getDoanhThu();
            }
        }
        lblTong.setText(String.format("%,.0f VNĐ", tong));
    }

    private void taoCayDoanhThu(ArrayList<DoanhThu> list) {
        doanhThu.clear();
        if (list != null) {
            for (DoanhThu dt : list) {
                doanhThu.put(dt.getThoiGian(), dt.getDoanhThu());
            }
        }
    }

    private void capNhatBieuDo() {
        jpCenter.remove(chartPanel);
        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawChart(g);
            }
        };
        chartPanel.setPreferredSize(new Dimension(1050, 300));
        jpCenter.add(chartPanel);
        jpCenter.revalidate();
        jpCenter.repaint();
    }

    private void drawChart(Graphics g) {
        if (doanhThu.isEmpty()) {
            g.drawString("Không có dữ liệu để hiển thị", 50, 150);
            return;
        }

        int width = chartPanel.getWidth();
        int height = chartPanel.getHeight();
        int marginLeft = 70, marginBottom = 50, marginTop = 50;
        int chartWidth = width - marginLeft - 50;
        int chartHeight = height - marginBottom - marginTop;

        int xOrigin = marginLeft;
        int yOrigin = height - marginBottom;

        double maxValue = doanhThu.values().stream().mapToDouble(Double::doubleValue).max().orElse(1);
        double scaleY = chartHeight / maxValue;

        // Vẽ trục
        g.setColor(Color.BLACK);
        g.drawLine(xOrigin, yOrigin, xOrigin + chartWidth, yOrigin);
        g.drawLine(xOrigin, yOrigin, xOrigin, marginTop);
        g.drawString("Doanh thu (VNĐ)", 10, height / 2);
        g.drawString("Thời gian", width / 2, height - 10);

        int barWidth = chartWidth / Math.max(doanhThu.size(), 1);
        int x = xOrigin;

        for (Map.Entry<Integer, Double> entry : doanhThu.entrySet()) {
            int key = entry.getKey();
            double value = entry.getValue();
            int barHeight = (int) (value * scaleY);

            g.setColor(new Color(65, 165, 238));
            g.fillRect(x + 5, yOrigin - barHeight, barWidth - 10, barHeight);

            g.setColor(Color.BLACK);
            String label = checkBoxNam.isSelected() && checkBoxThang.isSelected() ? 
                String.valueOf(key) : 
                (checkBoxNam.isSelected() ? "Tháng " + key : "Năm " + key);
            g.drawString(label, x + 5, yOrigin + 15);
            g.drawString(String.format("%,.0f", value), x + 5, yOrigin - barHeight - 5);

            x += barWidth;
        }
    }

    private void addHoaDonToTable(ArrayList<HoaDon> list) {
        XoaDataTrenModel();
        int i = 1;
        for (HoaDon hd : list) {
            tableModel.addRow(new Object[]{
                i++,
                hd.getMaHoaDon(),
                hd.getNhanVien().getMaNhanVien(),
                hd.getNhanVien().getHoTen(),
                hd.getNgayLap(),
                String.format("%,.0f", hd.getTongTien())
            });
        }
    }

    private void DocDuLieuDatabaseVaoTable() {
        XoaDataTrenModel();
        ArrayList<HoaDon> list = dshd.layDanhSachHoaDon();
        if (list != null) {
            int i = 1;
            for (HoaDon hd : list) {
                tableModel.addRow(new Object[]{
                    i++,
                    hd.getMaHoaDon(),
                    hd.getNhanVien().getMaNhanVien(),
                    hd.getNhanVien().getHoTen(),
                    hd.getNgayLap(),
                    String.format("%,.0f", hd.getTongTien())
                });
            }
        }
    }

    public void XoaDataTrenModel() {
        tableModel.setRowCount(0);
    }

    // ===== MENU LISTENER =====
    @Override
    public void menuSelected(MenuEvent e) {
        JMenu source = (JMenu) e.getSource();
        toolBar.removeAll();

        if ("Quản lý".equals(source.getText())) {
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

            btnPhim.addActionListener(this);
            btnSuatChieu.addActionListener(this);
            btnKhachHang.addActionListener(this);
            btnNhanVien.addActionListener(this);
            btnHoaDon.addActionListener(this);
            btnVe.addActionListener(this);
        }

        if ("Thống kê".equals(source.getText())) {
            chinhMauMenu(thongKeMenu, quanLyMenu);
            toolBar.add(btnDoanhThu = createControlButton("Doanh thu", "image//doanhthu-icon.png", 40, 40));
            btnDoanhThu.addActionListener(this);
        }

        if ("Đăng xuất".equals(source.getText())) {
            new Gui_DangNhap().setVisible(true);
            this.setVisible(false);
        }

        toolBar.revalidate();
        toolBar.repaint();
    }

    @Override public void menuDeselected(MenuEvent e) {}
    @Override public void menuCanceled(MenuEvent e) {}

    private static void chinhMauMenu(JMenu menu1, JMenu menu2) {
        menu2.setForeground(Color.WHITE);
        menu2.setBackground(new Color(104, 109, 224));
        menu2.setOpaque(true);
        menu1.setBackground(Color.WHITE);
        menu1.setForeground(Color.BLACK);
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

    public static void main(String[] args) {
        new Gui_ThongKeDoanhThu().setVisible(true);
    }
}