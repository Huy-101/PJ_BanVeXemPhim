package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui_InVe extends JDialog{
    public Gui_InVe(JFrame parent, String maHoaDon) {
        super(parent, "Chi tiết hóa đơn", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Thiết lập nội dung cho JDialog
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Ví dụ về thông tin chi tiết
        String detailInfo = "Mã vé phim: " + maHoaDon + "\n" +
                            "Tên vé phim: Nguyễn Văn A\n" +
                            "Tên phim: Nguyễn Văn A\n" +
                            "Mô tả: Nguyễn Văn A\n" +
                            "Bắt đầu: 09:00   Kết thúc: 11:00\n" +
                            "Số ghế: \n"+
                            "Rạp:\n"+
                            "Giá: \n";
        JTextArea textArea = new JTextArea(detailInfo);
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> dispose());
        panel.add(btnClose, BorderLayout.SOUTH);

        add(panel);
    }
}
