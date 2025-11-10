package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.*;

public class Dao_BanVe {
	
	
	private ArrayList<VePhim> vp;
	
	
	public Dao_BanVe() {
		super();
		this.vp = new ArrayList<VePhim>();
	}
	public ArrayList<VePhim> layDanhSachVePhim() {
	    ArrayList<VePhim> dsVePhim = new ArrayList<>();

	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getInstance().getConnection();

	        String sql = "SELECT v.MaVePhim, v.MaPhong, p.TenPhim, sc.NgayKhoiChieu, "
	                   + "sc.ThoiGianBatDau, v.TenGhe, p.MoTa "
	                   + "FROM VePhim v "
	                   + "JOIN SuatChieu sc ON v.MaSuatChieu = sc.MaSuatChieu "
	                   + "JOIN Phim p ON sc.MaPhim = p.MaPhim";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        while (rs.next()) {
	            String maVePhim = rs.getString(1);
	            String maPhong = rs.getString(2);
	            String tenPhim = rs.getString(3);
	            LocalDate ngayKhoiChieu = rs.getDate(4).toLocalDate();
	            String thoiGianBatDau = rs.getString(5);
	            String tenGhe = rs.getString(6);
	            String moTa = rs.getString(7);
	            SuatChieu sc = new SuatChieu();
	            sc.getNgayKhoiChieu();
	            sc.getThoiGianBatDau();
	            Phim p = new Phim(tenPhim,moTa);
	            p.getTenPhim();
	            p.getMoTa();
	            PhongChieu pc = new PhongChieu(maPhong);
	            VePhim ve = new VePhim(maVePhim,tenGhe,p,sc,pc);
	            dsVePhim.add(ve);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsVePhim;
	}
	public ArrayList<VePhim> timVePhimTheoMa(String ma) {
	    ArrayList<VePhim> dsVePhim = new ArrayList<>();

	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getInstance().getConnection();

	        String sql = "SELECT v.MaVePhim, v.MaPhong, p.TenPhim, sc.NgayKhoiChieu, "
	                   + "sc.ThoiGianBatDau, v.TenGhe, p.MoTa "
	                   + "FROM VePhim v "
	                   + "JOIN SuatChieu sc ON v.MaSuatChieu = sc.MaSuatChieu "
	                   + "JOIN Phim p ON sc.MaPhim = p.MaPhim "
	                   + "WHERE v.MaVePhim LIKE '%" + ma + "%'";

	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        while (rs.next()) {
	            String maVePhim = rs.getString(1);
	            String maPhong = rs.getString(2);
	            String tenPhim = rs.getString(3);
	            LocalDate ngayKhoiChieu = rs.getDate(4) != null ? rs.getDate(4).toLocalDate() : null;
	            String thoiGianBatDau = rs.getString(5);
	            String tenGhe = rs.getString(6);
	            String moTa = rs.getString(7);

	            SuatChieu sc = new SuatChieu();
	            sc.setNgayKhoiChieu(ngayKhoiChieu);
	            sc.setThoiGianBatDau(thoiGianBatDau);

	            Phim p = new Phim();
	            p.setTenPhim(tenPhim);
	            p.setMoTa(moTa);

	            PhongChieu pc = new PhongChieu();
	            pc.setMaPhong(maPhong);

	            VePhim ve = new VePhim(maVePhim, tenGhe, p, sc, pc);
	            dsVePhim.add(ve);
	        }

	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsVePhim;
	}
	public ArrayList<VePhim> sapXepTheoMa() {
	    ArrayList<VePhim> dsVePhim = new ArrayList<>();

	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getInstance().getConnection();

	        String sql = "SELECT v.MaVePhim, v.MaPhong, p.TenPhim, sc.NgayKhoiChieu, "
	                   + "sc.ThoiGianBatDau, v.TenGhe, p.MoTa "
	                   + "FROM VePhim v "
	                   + "JOIN SuatChieu sc ON v.MaSuatChieu = sc.MaSuatChieu "
	                   + "JOIN Phim p ON sc.MaPhim = p.MaPhim "
	                   + "ORDER BY v.MaVePhim ASC";

	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        while (rs.next()) {
	            String maVePhim = rs.getString(1);
	            String maPhong = rs.getString(2);
	            String tenPhim = rs.getString(3);
	            LocalDate ngayKhoiChieu = rs.getDate(4) != null ? rs.getDate(4).toLocalDate() : null;
	            String thoiGianBatDau = rs.getString(5);
	            String tenGhe = rs.getString(6);
	            String moTa = rs.getString(7);

	            SuatChieu sc = new SuatChieu();
	            sc.setNgayKhoiChieu(ngayKhoiChieu);
	            sc.setThoiGianBatDau(thoiGianBatDau);

	            Phim p = new Phim();
	            p.setTenPhim(tenPhim);
	            p.setMoTa(moTa);

	            PhongChieu pc = new PhongChieu();
	            pc.setMaPhong(maPhong);

	            VePhim ve = new VePhim(maVePhim, tenGhe, p, sc, pc);
	            dsVePhim.add(ve);
	        }

	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsVePhim;
	}
	public ArrayList<VePhim> sapXepTheoGia() {
	    // Vì không có cột giá → sắp xếp theo mã (hoặc thêm cột giá sau)
	    // Dưới đây là phiên bản mô phỏng: sắp xếp theo ngày chiếu gần nhất (thay thế)
	    ArrayList<VePhim> dsVePhim = new ArrayList<>();

	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getInstance().getConnection();

	        String sql = "SELECT v.MaVePhim, v.MaPhong, p.TenPhim, sc.NgayKhoiChieu, "
	                   + "sc.ThoiGianBatDau, v.TenGhe, p.MoTa "
	                   + "FROM VePhim v "
	                   + "JOIN SuatChieu sc ON v.MaSuatChieu = sc.MaSuatChieu "
	                   + "JOIN Phim p ON sc.MaPhim = p.MaPhim "
	                   + "ORDER BY sc.NgayKhoiChieu DESC, sc.ThoiGianBatDau DESC";

	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        while (rs.next()) {
	            String maVePhim = rs.getString(1);
	            String maPhong = rs.getString(2);
	            String tenPhim = rs.getString(3);
	            LocalDate ngayKhoiChieu = rs.getDate(4) != null ? rs.getDate(4).toLocalDate() : null;
	            String thoiGianBatDau = rs.getString(5);
	            String tenGhe = rs.getString(6);
	            String moTa = rs.getString(7);

	            SuatChieu sc = new SuatChieu();
	            sc.setNgayKhoiChieu(ngayKhoiChieu);
	            sc.setThoiGianBatDau(thoiGianBatDau);

	            Phim p = new Phim();
	            p.setTenPhim(tenPhim);
	            p.setMoTa(moTa);

	            PhongChieu pc = new PhongChieu();
	            pc.setMaPhong(maPhong);

	            VePhim ve = new VePhim(maVePhim, tenGhe, p, sc, pc);
	            dsVePhim.add(ve);
	        }

	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsVePhim;
	}
	public boolean xoaVePhim(String maVe) {
	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getInstance().getConnection();

	        String sql = "DELETE FROM VePhim WHERE MaVePhim = '" + maVe + "'";
	        Statement stmt = con.createStatement();

	        int rows = stmt.executeUpdate(sql);
	        stmt.close();

	        return rows > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}