package entity;

import java.time.LocalDate;
import java.util.Objects;

public class SuatChieu {
    private String maSuatChieu;
    private Phim phim; // Đối tượng Phim
    private PhongChieu phongChieu; // Đối tượng PhongChieu
    private LocalDate ngayKhoiChieu; // Sử dụng LocalDate
    private String thoiGianBatDau;
    private String hinhThucChieu; // Có thể là '2D', '3D', v.v.
    private boolean trangThai;

    // Constructor mặc định
    public SuatChieu() {
        this.maSuatChieu = "";
        this.phim = new Phim(); // Khởi tạo một đối tượng Phim mặc định
        this.phongChieu = new PhongChieu(); // Khởi tạo một đối tượng PhongChieu mặc định
        this.ngayKhoiChieu = LocalDate.now(); // Khởi tạo với ngày hiện tại 
        this.thoiGianBatDau = "";
        this.hinhThucChieu = "";
        this.trangThai = false; // false có thể hiểu là 'sẵn sàng'
    }

    // Constructor với mã suất chiếu
    public SuatChieu(String maSuatChieu) {
        this();
        this.maSuatChieu = maSuatChieu;
    }

    // Constructor đầy đủ
    public SuatChieu(String maSuatChieu, Phim phim, PhongChieu phongChieu, 
                     LocalDate ngayKhoiChieu, String thoiGianBatDau, 
                     String hinhThucChieu, boolean trangThai) {
        this.maSuatChieu = maSuatChieu;
        this.phim = phim;
        this.phongChieu = phongChieu;
        this.ngayKhoiChieu = ngayKhoiChieu;
        this.thoiGianBatDau = thoiGianBatDau;
        this.hinhThucChieu = hinhThucChieu;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public String getMaSuatChieu() {
        return maSuatChieu;
    }

    public void setMaSuatChieu(String maSuatChieu) {
        this.maSuatChieu = maSuatChieu;
    }

    public Phim getPhim() {
        return phim;
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public PhongChieu getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(PhongChieu phongChieu) {
        this.phongChieu = phongChieu;
    }

    public LocalDate getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(LocalDate ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public String getHinhThucChieu() {
        return hinhThucChieu;
    }

    public void setHinhThucChieu(String hinhThucChieu) {
        this.hinhThucChieu = hinhThucChieu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức equals và hashCode
    @Override
    public int hashCode() {
        return Objects.hash(maSuatChieu);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SuatChieu other = (SuatChieu) obj;
        return Objects.equals(maSuatChieu, other.maSuatChieu);
    }

    @Override
    public String toString() {
        return "SuatChieu{" +
                "maSuatChieu='" + maSuatChieu + '\'' +
                ", phim=" + phim +
                ", phongChieu=" + phongChieu +
                ", ngayKhoiChieu=" + ngayKhoiChieu +
                ", thoiGianBatDau='" + thoiGianBatDau + '\'' +
                ", hinhThucChieu='" + hinhThucChieu + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}