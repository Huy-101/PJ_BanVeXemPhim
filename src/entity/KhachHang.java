package entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhachHang {
	private String maKhachHang, hoTen;
	private LocalDate ngaySinh;
	private String diaChi, soDienThoai, email;

	public KhachHang() {
		this.maKhachHang = "";
		this.hoTen = "";
        this.ngaySinh = LocalDate.now();
		this.diaChi = "";
		this.soDienThoai = "";
		this.email = "";

	}

	public KhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
		this.hoTen = "";
        this.ngaySinh = LocalDate.now();
		this.diaChi = "";
		this.soDienThoai = "";
		this.email = "";
	}

	public KhachHang(String maKhachHang, String hoTen, LocalDate ngaySinh, String diaChi, String soDienThoai, String email) {
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.email = email;
	}
	
	public KhachHang(String maKhachHang, String hoTen, String diaChi, String soDienThoai) {
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public String getMaKhachHang() {
		return maKhachHang;
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

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
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

	@Override
	public int hashCode() {
		return Objects.hash(maKhachHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKhachHang, other.maKhachHang);
	}

}