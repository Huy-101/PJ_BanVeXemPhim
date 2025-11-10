package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Phim {
	private String maPhim, tenPhim, daoDien;
	private int thoiLuong;
	private LocalDate ngayKhoiChieu;
	private LocalDate ngayKetThuc;
	private String quocGia, theLoai;
	private String trangThai;
	private String moTa;

	public Phim() {
		this.maPhim = "";
		this.tenPhim = "";
		this.daoDien = "";
		this.thoiLuong = 0;
		this.ngayKhoiChieu = LocalDate.now();
		this.ngayKetThuc = LocalDate.now();
		this.quocGia = "";
		this.theLoai = "";
		this.trangThai = "";
		this.moTa = ""; 
	}

	public Phim(String maPhim) {
		this.maPhim = maPhim;
		this.tenPhim = "";
		this.daoDien = "";
		this.thoiLuong = 0;
		this.ngayKhoiChieu = LocalDate.now();
		this.ngayKetThuc = LocalDate.now();
		this.quocGia = "";
		this.theLoai = "";
		this.trangThai = "";
		this.moTa = "";
	}
	
	

	public Phim(String tenPhim, String moTa) {
		this.tenPhim = tenPhim;
		this.moTa = moTa;
	}

	public Phim(String maPhim, String tenPhim, int thoiLuong) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.thoiLuong = thoiLuong;
	}
	

	public Phim(String tenPhim, LocalDate ngayKhoiChieu, String moTa) {
		this.tenPhim = tenPhim;
		this.ngayKhoiChieu = ngayKhoiChieu;
		this.moTa = moTa;
	}

	public Phim(String maPhim, String tenPhim, String daoDien, int thoiLuong, LocalDate ngayKhoiChieu,
			LocalDate ngayKetThuc, String quocGia, String theLoai, String moTa) {

		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.daoDien = daoDien;
		this.thoiLuong = thoiLuong;
		this.ngayKhoiChieu = ngayKhoiChieu;
		this.ngayKetThuc = ngayKetThuc;
		this.quocGia = quocGia;
		this.theLoai = theLoai;
		this.trangThai = layTrangThai();
		this.moTa = moTa;
	}

	public String layTrangThai() {
		if(ngayKhoiChieu.isAfter(LocalDate.now()))
			return "Sắp công chiếu";
		else if (ngayKetThuc.isBefore(LocalDate.now()))
			return "Đã công chiếu";
		else
			return "Đang công chiếu";
	}
	
	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public String getTenPhim() {
		return tenPhim;
	}

	public String getDaoDien() {
		return daoDien;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}

	public LocalDate getNgayKhoiChieu() {
		return ngayKhoiChieu;
	}

	public String getQuocGia() {
		return quocGia;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

	public void setDaoDien(String daoDien) {
		this.daoDien = daoDien;
	}

	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public void setNgayKhoiChieu(LocalDate ngayKhoiChieu) {
		this.ngayKhoiChieu = ngayKhoiChieu;
	}

	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhim);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phim other = (Phim) obj;
		return Objects.equals(maPhim, other.maPhim);
	}
}