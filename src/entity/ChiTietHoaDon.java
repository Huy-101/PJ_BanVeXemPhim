package entity;

public class ChiTietHoaDon {
	private HoaDon hoaDon;

	private double thanhTien;

	public ChiTietHoaDon() {
		this.hoaDon = new HoaDon();
		this.thanhTien = 0;
	}

	public ChiTietHoaDon(HoaDon hoaDon, int soLuongSanPham, double thanhTien) {
		this.hoaDon = hoaDon;

		this.thanhTien = thanhTien;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}



	public double getThanhTien() {
		return thanhTien;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

}
