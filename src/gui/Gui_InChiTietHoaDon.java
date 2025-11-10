package gui;

import javax.swing.*;
import java.awt.*;

public class Gui_InChiTietHoaDon extends JDialog {
    public Gui_InChiTietHoaDon(JFrame parent, String maHoaDon) {
        super(parent, "Chi tiết hóa đơn", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Thiết lập nội dung cho JDialog
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Ví dụ về thông tin chi tiết
        String detailInfo = "Mã hóa đơn: " + maHoaDon + "\n" +
                            "Tên nhân viên: Nguyễn Văn A\n" +
                            "Ngày lập: 2023-10-31\n" +
                            "Thực phẩm: \n"+
                            "    - Bắp : 40.000 x 1 (Phần) = 40.000\n"+
                            "    - Nước suối: 15.000 x 2 (chai) = 30.000\n"+
                            "Vé phim:\n"+
                            "Tên: Những chú chó nhỏ\n"+
                            "Bắt đầu: 15:30:00\n"+
                            "Rạp: C03\n"+
                            "Ghế: F1\n"+
                            "Giá: 100.000 x 1 = 100.000\n"+
                            "Tổng tiền: 500.000 VNĐ\n";

        JTextArea textArea = new JTextArea(detailInfo);
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> dispose());
        panel.add(btnClose, BorderLayout.SOUTH);

        add(panel);
    }
}