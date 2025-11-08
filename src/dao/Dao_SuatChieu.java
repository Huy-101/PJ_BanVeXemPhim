package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Phim;
import entity.PhongChieu;
import entity.SuatChieu;

public class Dao_SuatChieu {
	private ArrayList<SuatChieu> dssc;
	private ArrayList<Phim> dsp;
	private ArrayList<PhongChieu> dspc;

	public Dao_SuatChieu() {
		dssc = new ArrayList<SuatChieu>();
		ConnectDB db = ConnectDB.getInstance();
        db.connect();
		dsp = new Dao_Phim().layDanhSachPhim();
		dspc = new Dao_PhongChieu().layDanhSachPhongChieu();
	}
	
	public int tinhTongSuatChieu() {
		return dssc.size();
	}
	 
	
	public ArrayList<SuatChieu> layDanhSachSuatChieu() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT sc.MaSuatChieu, sc.MaPhim, sc.MaPhong, p.TenPhim, sc.NgayKhoiChieu, " +
		             "sc.HinhThucChieu, sc.ThoiGianBatDau, p.ThoiLuong, ph.SoGheTrong " +
		             "FROM SuatChieu sc " +
		             "JOIN Phim p ON sc.MaPhim = p.MaPhim " +
		             "JOIN PhongChieu ph ON sc.MaPhong = ph.MaPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSC = rs.getString("MaSuatChieu");
		        String maPhim = rs.getString("MaPhim"); 
		        String maPhong = rs.getString("MaPhong");
		        String tenPhim = rs.getString("TenPhim");
		        LocalDate ngayKhoiChieu = rs.getDate("NgayKhoiChieu").toLocalDate();
		        String hinhThucChieu = rs.getString("HinhThucChieu");
		        String thoiGianBatDau = rs.getString("ThoiGianBatDau");
		        int thoiLuong = rs.getInt("ThoiLuong");
		        int soGheTrong = rs.getInt("SoGheTrong");
		        SuatChieu sc = new SuatChieu(maSC, new Phim(maPhim, tenPhim, thoiLuong), new PhongChieu(maPhong, soGheTrong), ngayKhoiChieu, thoiGianBatDau, hinhThucChieu, false);
				dssc.add(sc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dssc;
	}

	public boolean themSuatChieu(SuatChieu sc) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " SuatChieu values(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, sc.getMaSuatChieu());
			stmt.setString(2, sc.getPhim().getMaPhim());
			stmt.setString(3, sc.getPhongChieu().getMaPhong());
			stmt.setDate(4, Date.valueOf(sc.getNgayKhoiChieu()));
			stmt.setString(5, sc.getThoiGianBatDau());
			stmt.setString(6, sc.getHinhThucChieu());
			stmt.setBoolean(7, sc.isTrangThai());
			n = stmt.executeUpdate();
			dssc.add(sc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capNhatSuatChieu(SuatChieu sc) {
	    String sql = "UPDATE SuatChieu SET MaPhim=?, MaPhong=?, NgayKhoiChieu=?, ThoiGianBatDau=?, HinhThucChieu=?, TrangThai=? WHERE maSuatChieu=?";
	    
	    try (Connection con = ConnectDB.getInstance().getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, sc.getPhim().getMaPhim());
	        stmt.setString(2, sc.getPhongChieu().getMaPhong());
	        stmt.setDate(3, java.sql.Date.valueOf(sc.getNgayKhoiChieu()));
	        stmt.setString(4, sc.getThoiGianBatDau());
	        stmt.setString(5, sc.getHinhThucChieu());
	        stmt.setBoolean(6, sc.isTrangThai());
	        stmt.setString(7, sc.getMaSuatChieu());

	        return stmt.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	//Đổi lớp cập nhật suất chiếu

	public boolean xoaSuatChieu(String maSuatChieu) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from SuatChieu where maSuatChieu = ?");
			stmt.setString(1, maSuatChieu);
			n = stmt.executeUpdate();
			dssc.removeIf(ph -> ph.getMaSuatChieu().equals(maSuatChieu));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<SuatChieu> timSuatChieuTheoMaPhong(String maPhong) {
		Dao_SuatChieu dao = new Dao_SuatChieu();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT sc.MaSuatChieu, sc.MaPhim, sc.MaPhong, p.TenPhim, sc.NgayKhoiChieu, " +
                    "sc.HinhThucChieu, sc.ThoiGianBatDau, p.ThoiLuong, ph.SoGheTrong " +
                    "FROM SuatChieu sc " +
                    "JOIN Phim p ON sc.MaPhim = p.MaPhim " +
                    "JOIN PhongChieu ph ON sc.MaPhong = ph.MaPhong " +
                    "WHERE sc.MaPhong = ?"; 
			PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, maPhong); // Gán giá trị cho placeholder

	        ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String maSC = rs.getString("MaSuatChieu");
		        String maPhim = rs.getString("MaPhim");
		        String maPhong1 = rs.getString("MaPhong");
		        String tenPhim = rs.getString("TenPhim");
		        LocalDate ngayKhoiChieu = rs.getDate("NgayKhoiChieu").toLocalDate();
		        String hinhThucChieu = rs.getString("HinhThucChieu");
		        String thoiGianBatDau = rs.getString("ThoiGianBatDau");
		        int thoiLuong = rs.getInt("ThoiLuong");
		        int soGheTrong = rs.getInt("SoGheTrong");
		        SuatChieu sc = new SuatChieu(maSC, new Phim(maPhim, tenPhim, thoiLuong), new PhongChieu(maPhong1, soGheTrong), ngayKhoiChieu, thoiGianBatDau, hinhThucChieu, false);
				dao.dssc.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dssc;
	}
	
	public ArrayList<SuatChieu> timSuatChieuTheoTenPhim(String tenPhim) {
		Dao_SuatChieu dao = new Dao_SuatChieu();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT sc.MaSuatChieu, sc.MaPhim, sc.MaPhong, p.TenPhim, sc.NgayKhoiChieu, " +
                    "sc.HinhThucChieu, sc.ThoiGianBatDau, p.ThoiLuong, ph.SoGheTrong " +
                    "FROM SuatChieu sc " +
                    "JOIN Phim p ON sc.MaPhim = p.MaPhim " +
                    "JOIN PhongChieu ph ON sc.MaPhong = ph.MaPhong " +
                    "WHERE p.TenPhim = ?"; 
			PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, tenPhim); // Gán giá trị cho placeholder

	        ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String maSC = rs.getString("MaSuatChieu");
		        String maPhim = rs.getString("MaPhim");
		        String maPhong1 = rs.getString("MaPhong");
		        String tenPhim1 = rs.getString("TenPhim");
		        LocalDate ngayKhoiChieu = rs.getDate("NgayKhoiChieu").toLocalDate();
		        String hinhThucChieu = rs.getString("HinhThucChieu");
		        String thoiGianBatDau = rs.getString("ThoiGianBatDau");
		        int thoiLuong = rs.getInt("ThoiLuong");
		        int soGheTrong = rs.getInt("SoGheTrong");
		        SuatChieu sc = new SuatChieu(maSC, new Phim(maPhim, tenPhim, thoiLuong), new PhongChieu(maPhong1, soGheTrong), ngayKhoiChieu, thoiGianBatDau, hinhThucChieu, false);
				dao.dssc.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dssc;
	}
	
	public ArrayList<SuatChieu> sapXepTheoThoiGian() {
		Dao_SuatChieu dao = new Dao_SuatChieu();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from SuatChieu order by ThoiGianBatDau";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSC = rs.getString("MaSuatChieu");
		        String maPhim = rs.getString("MaPhim"); 
		        String maPhong = rs.getString("MaPhong");
		        String tenPhim = rs.getString("TenPhim");
		        LocalDate ngayKhoiChieu = rs.getDate("NgayKhoiChieu").toLocalDate();
		        String hinhThucChieu = rs.getString("HinhThucChieu");
		        String thoiGianBatDau = rs.getString("ThoiGianBatDau");
		        int thoiLuong = rs.getInt("ThoiLuong");
		        int soGheTrong = rs.getInt("SoGheTrong");
		        SuatChieu sc = new SuatChieu(maSC, new Phim(maPhim, tenPhim, thoiLuong), new PhongChieu(maPhong, soGheTrong), ngayKhoiChieu, thoiGianBatDau, hinhThucChieu, false);
				dao.dssc.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dssc;
	}

	public ArrayList<SuatChieu> sapXepTheoMa() {
		Dao_SuatChieu dao = new Dao_SuatChieu();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from SuatChieu order by maSuatChieu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSC = rs.getString("MaSuatChieu");
		        String maPhim = rs.getString("MaPhim"); 
		        String maPhong = rs.getString("MaPhong");
		        String tenPhim = rs.getString("TenPhim");
		        LocalDate ngayKhoiChieu = rs.getDate("NgayKhoiChieu").toLocalDate();
		        String hinhThucChieu = rs.getString("HinhThucChieu");
		        String thoiGianBatDau = rs.getString("ThoiGianBatDau");
		        int thoiLuong = rs.getInt("ThoiLuong");
		        int soGheTrong = rs.getInt("SoGheTrong");
		        SuatChieu sc = new SuatChieu(maSC, new Phim(maPhim, tenPhim, thoiLuong), new PhongChieu(maPhong, soGheTrong), ngayKhoiChieu, thoiGianBatDau, hinhThucChieu, false);
				dao.dssc.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dssc;
	}

}
