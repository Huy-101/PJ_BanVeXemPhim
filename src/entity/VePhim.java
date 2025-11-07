package entity;

import java.time.LocalDate;
import java.util.Objects;

public class VePhim {
	private String maVe;
	private KhachHang kh;
	private NhanVien nv;
	private Phim phim;
	private SuatChieu suatChieu;
	public VePhim(String maVe, KhachHang kh, NhanVien nv, Phim phim, SuatChieu suatChieu) {
		super();
		this.maVe = maVe;
		this.kh = kh;
		this.nv = nv;
		this.phim = phim;
		this.suatChieu = suatChieu;
	}
	public VePhim(String maVe) {
		super();
		this.maVe = maVe;
	}
	public String getMaVe() {
		return maVe;
	}
	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}
	public KhachHang getKh() {
		return kh;
	}
	public void setKh(KhachHang kh) {
		this.kh = kh;
	}
	public NhanVien getNv() {
		return nv;
	}
	public void setNv(NhanVien nv) {
		this.nv = nv;
	}
	public Phim getPhim() {
		return phim;
	}
	public void setPhim(Phim phim) {
		this.phim = phim;
	}
	public SuatChieu getSuatChieu() {
		return suatChieu;
	}
	public void setSuatChieu(SuatChieu suatChieu) {
		this.suatChieu = suatChieu;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maVe);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VePhim other = (VePhim) obj;
		return Objects.equals(maVe, other.maVe);
	}
	
	
	
	
	
	
}
