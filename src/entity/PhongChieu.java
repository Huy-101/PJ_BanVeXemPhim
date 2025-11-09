package entity;

import java.util.Objects;

public class PhongChieu {
    private String maPhong;
    private int soGheTrong;

    // Constructor mặc định
    public PhongChieu() {
        this.maPhong = "";
        this.soGheTrong = 0;
    }

    // Constructor với mã phòng và số ghế
    public PhongChieu(String maPhong, int soGheTrong) {
        this.maPhong = maPhong;
        this.soGheTrong = soGheTrong;
    }

    // Getter và Setter
    public String getMaPhong() {
        return maPhong;
    }

    public PhongChieu(String maPhong) {
		this.maPhong = maPhong;
	}

	public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public int getSoGheTrong() {
        return soGheTrong;
    }

    public void setSoGheTrong(int soGheTrong) {
        this.soGheTrong = soGheTrong;
    }

    // Phương thức equals và hashCode
    @Override
    public int hashCode() {
        return Objects.hash(maPhong);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PhongChieu other = (PhongChieu) obj;
        return Objects.equals(maPhong, other.maPhong);
    }

    @Override
    public String toString() {
        return "PhongChieu{" +
                "maPhong='" + maPhong + '\'' +
                ", soGheTrong=" + soGheTrong +
                '}';
    }
}