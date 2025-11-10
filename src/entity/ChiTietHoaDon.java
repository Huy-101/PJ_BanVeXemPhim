package entity;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private int soLuongSanPham;
	private double thanhTien;

	public ChiTietHoaDon() {
		this.hoaDon = new HoaDon();
		this.soLuongSanPham = 0;
		this.thanhTien = 0;
	}

	public ChiTietHoaDon(HoaDon hoaDon, int soLuongSanPham, double thanhTien) {
		this.hoaDon = hoaDon;
		this.soLuongSanPham = soLuongSanPham;
		this.thanhTien = thanhTien;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public int getSoLuongSanPham() {
		return soLuongSanPham;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public void setSoLuongSanPham(int soLuongSanPham) {
		this.soLuongSanPham = soLuongSanPham;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

}
