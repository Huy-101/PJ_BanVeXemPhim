package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
    private String maNhanVien, hoTen, password;
    private LocalDate ngaySinh;
    private String diaChi, soDienThoai, email;
    private boolean chucVu; // Thay đổi kiểu dữ liệu thành boolean
    private boolean gioiTinh;
    private double luong;

    public NhanVien() {
        this.maNhanVien = "";
        this.hoTen = "";
        this.password = ""; // Khởi tạo password
        this.ngaySinh = LocalDate.now();
        this.diaChi = "";
        this.soDienThoai = "";
        this.email = "";
        this.chucVu = false; // Khởi tạo chucVu
        this.gioiTinh = false;
        this.luong = 0;
    }

    public NhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
        this.hoTen = "";
        this.password = ""; // Khởi tạo password
        this.ngaySinh = LocalDate.now();
        this.diaChi = "";
        this.soDienThoai = "";
        this.email = "";
        this.chucVu = false; // Khởi tạo chucVu
        this.gioiTinh = false;
        this.luong = 0;
    }

    
    public NhanVien(String maNhanVien, String tenNhanVien) {
        this.maNhanVien = maNhanVien;
        this.hoTen = tenNhanVien;
        this.password = ""; // Khởi tạo password
        this.ngaySinh = LocalDate.now();
        this.diaChi = "";
        this.soDienThoai = "";
        this.email = "";
        this.chucVu = false; // Khởi tạo chucVu
        this.gioiTinh = false;
        this.luong = 0;
    }
   
    public NhanVien(String maNhanVien, String hoTen, String password, LocalDate ngaySinh, String diaChi, 
                    String soDienThoai, String email, boolean chucVu, boolean gioiTinh, double luong) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.password = password; // Khởi tạo password
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.chucVu = chucVu; // Khởi tạo chucVu
        this.gioiTinh = gioiTinh;
        this.luong = luong;
    }

    // Getter và setter cho password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public boolean isChucVu() { // Getter cho chucVu
        return chucVu;
    }

    public void setChucVu(boolean chucVu) { // Setter cho chucVu
        this.chucVu = chucVu;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public double getLuong() {
        return luong;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNhanVien);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NhanVien other = (NhanVien) obj;
        return Objects.equals(maNhanVien, other.maNhanVien);
    }
}