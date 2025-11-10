package entity;

import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private LocalDate ngayLap;
	private double tongTien;
	private NhanVien nhanVien;
	private KhachHang khachHang;

	public HoaDon() {
		this.maHoaDon = "";
		this.ngayLap = LocalDate.now();
		this.tongTien = 0;
		this.nhanVien = new NhanVien();
		this.khachHang = new KhachHang();
	}

	public HoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
		this.ngayLap = LocalDate.now();
		this.tongTien = 0;
		this.nhanVien = new NhanVien();
		this.khachHang = new KhachHang();
	}

	public HoaDon(String maHoaDon, LocalDate ngayLap, double tongTien, NhanVien nhanVien, KhachHang khachHang) {
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
	}

	public HoaDon(String maHD, LocalDate ngayLap2, NhanVien nv, KhachHang kh, double tongTien2) {
		this.maHoaDon = maHD;
		this.ngayLap = ngayLap2;
		this.nhanVien = nv;
		this.khachHang = kh;
		this.tongTien = tongTien2;
		// TODO Auto-generated constructor stub
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public double getTongTien() {
		return tongTien;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

}