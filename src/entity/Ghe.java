package entity;

import java.util.Objects;

public class Ghe {
    private String maGhe;
    private String tenGhe;
    private PhongChieu phongChieu;
    private boolean trangThai;

    // Constructor mặc định
    public Ghe() {
        this.maGhe = "";
        this.tenGhe = "";
        this.phongChieu = new PhongChieu();
        this.trangThai = false; // false có thể hiểu là 'sẵn sàng'
    }

    // Constructor với mã ghế
    public Ghe(String maGhe) {
        this(maGhe, "", new PhongChieu(), false);
    }

    // Constructor đầy đủ 
    public Ghe(String maGhe, String tenGhe, PhongChieu phongChieu, boolean trangThai) {
        this.maGhe = maGhe;
        this.tenGhe = tenGhe;
        this.phongChieu = phongChieu;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public String getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(String maGhe) {
        this.maGhe = maGhe;
    }

    public String getTenGhe() {
        return tenGhe;
    }

    public void setTenGhe(String tenGhe) {
        this.tenGhe = tenGhe;
    }

    public PhongChieu getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(PhongChieu phongChieu) {
        this.phongChieu = phongChieu;
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
        return Objects.hash(maGhe);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ghe other = (Ghe) obj;
        return Objects.equals(maGhe, other.maGhe);
    }

    @Override
    public String toString() {
        return "Ghe{" +
                "maGhe='" + maGhe + '\'' +
                ", tenGhe='" + tenGhe + '\'' +
                ", phongChieu=" + phongChieu +
                ", trangThai=" + trangThai +
                '}';
    }
}