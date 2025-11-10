package entity;

public class DoanhThu {
	private int thoiGian;
	private double doanhThu;
	public DoanhThu() {
		this.thoiGian = 0;
		this.doanhThu = 0;
	}
	public DoanhThu(int thoiGian, double doanhThu) {
		this.thoiGian = thoiGian;
		this.doanhThu = doanhThu;
	}
	public int getThoiGian() {
		return thoiGian;
	}
	public double getDoanhThu() {
		return doanhThu;
	}
	public void setThoiGian(int thoiGian) {
		this.thoiGian = thoiGian;
	}
	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}
	
	
	
}
