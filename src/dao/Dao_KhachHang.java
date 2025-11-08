package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class Dao_KhachHang {
	private ArrayList<KhachHang> dskh;

	public Dao_KhachHang() {
		dskh = new ArrayList<KhachHang>();
	}

	public int tinhTongKhachHang() {
		return dskh.size();
	}

//	public ArrayList<KhachHang> layDanhSachKhachHang() { 
//		try {
//			ConnectDB.getInstance().connect();
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "select * from KhachHang";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) { 
//				String maKH = rs.getString(1);
//				String hoTen = rs.getString(2);
//				LocalDate ngaySinh = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
//				String diaChi = rs.getString(4);
//				String soDienThoai = rs.getString(5);
//				String email = rs.getString(6);
//				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, diaChi, soDienThoai, email);
//				dskh.add(kh);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return dskh;
//	}
//
//	public boolean themKhachHang(KhachHang kh) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		PreparedStatement stmt = null;
//		int n = 0;
//		try {
//			stmt = con.prepareStatement("insert into" + " khachhang values(?, ?, ?, ?, ?, ?)");
//			stmt.setString(1, kh.getMaKhachHang());
//			stmt.setString(2, kh.getHoTen());
//			stmt.setDate(3, Date.valueOf(kh.getNgaySinh()));
//			stmt.setString(4, kh.getDiaChi());
//			stmt.setString(5, kh.getSoDienThoai());
//			stmt.setString(6, kh.getEmail());
//			n = stmt.executeUpdate();
////			dskh.add(kh);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return n > 0;
//	}
//
//	public boolean capNhatKhachHang(KhachHang kh) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		PreparedStatement stmt = null;
//		int n = 0;
//		try {
//			stmt = con.prepareStatement(
//					"update khachhang set HoTen=?, NgaySinh=?, DiaChi=?, SDT=?, Email=? where MaKhachHang=?");
//			stmt.setString(1, kh.getHoTen());
//			stmt.setDate(2, Date.valueOf(kh.getNgaySinh()));
//			stmt.setString(3, kh.getDiaChi());
//			stmt.setString(4, kh.getSoDienThoai());
//			stmt.setString(5, kh.getEmail());
//			stmt.setString(6, kh.getMaKhachHang());
//			n = stmt.executeUpdate();
//			dskh.set(dskh.indexOf(kh), kh);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return n > 0;
//	}
//
//	public boolean xoaKhachHang(String maKH) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		PreparedStatement stmt = null; 
//		int n = 0;
//		try {
//			stmt = con.prepareStatement("delete from khachhang where MaKhachHang = ?");
//			stmt.setString(1, maKH);
//			n = stmt.executeUpdate();
//			dskh.removeIf(ph -> ph.getMaKhachHang().equals(maKH));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return n > 0;
//	}
//
//	public ArrayList<KhachHang> timKhachHangTheoMa(String maKhachHang) {
//		Dao_KhachHang dao = new Dao_KhachHang();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			PreparedStatement stmt = con.prepareStatement("Select * from khachhang where MaKhachHang=?");
//			stmt.setString(1, maKhachHang);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				String maKH = rs.getString(1);
//				String hoTen = rs.getString(2);
//				LocalDate ngaySinh = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
//				String diaChi = rs.getString(4);
//				String soDienThoai = rs.getString(5);
//				String email = rs.getString(6);
//				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, diaChi, soDienThoai, email);
//				dao.dskh.add(kh);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dskh;
//	}
//
//	public ArrayList<KhachHang> timKhachHangTheoTen(String tenKhachHang) {
//		Dao_KhachHang dao = new Dao_KhachHang();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			PreparedStatement stmt = con.prepareStatement("Select * from khachhang where hoTen LIKE ?");
//			stmt.setString(1, "%"+tenKhachHang+"%");
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				String maKH = rs.getString(1);
//				String hoTen = rs.getString(2);
//				LocalDate ngaySinh = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
//				String diaChi = rs.getString(4);
//				String soDienThoai = rs.getString(5);
//				String email = rs.getString(6);
//				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, diaChi, soDienThoai, email);
//				dao.dskh.add(kh);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dskh;
//	}
//
//	public ArrayList<KhachHang> sapXepTheoMa() {
//		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "Select * from khachhang order by MaKhachHang";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maKH = rs.getString(1);
//				System.out.println(maKH);
//				String hoTen = rs.getString(2);
//				LocalDate ngaySinh = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
//				String diaChi = rs.getString(4);
//				String soDienThoai = rs.getString(5);
//				String email = rs.getString(6);
//				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, diaChi, soDienThoai, email);
//				list.add(kh);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public ArrayList<KhachHang> sapXepTheoHoTen() {
//		Dao_KhachHang dao = new Dao_KhachHang();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "Select * from khachhang order by hoTen";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maKH = rs.getString(1);
//				String hoTen = rs.getString(2);
//				LocalDate ngaySinh = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
//				String diaChi = rs.getString(4);
//				String soDienThoai = rs.getString(5);
//				String email = rs.getString(6);
//				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, diaChi, soDienThoai, email);
//				dao.dskh.add(kh);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dskh;
//	}
	
	// Viet cho Store producer
	public ArrayList<KhachHang> layDanhSachKhachHang() { 
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "{CALL sp_layAllKhachHang()}";
			CallableStatement stmt = con.prepareCall(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				String maKH = rs.getString(1);
				String hoTen = rs.getString(2);
				LocalDate ngaySinh = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
				String diaChi = rs.getString(4);
				String soDienThoai = rs.getString(5);
				String email = rs.getString(6);
				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, diaChi, soDienThoai, email);
				dskh.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dskh;
	}

	public boolean themKhachHang(KhachHang kh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("{CALL sp_themKhachHang(?, ?, ?, ?, ?, ?)}");
			stmt.setString(1, kh.getMaKhachHang());
			stmt.setString(2, kh.getHoTen());
			stmt.setDate(3, Date.valueOf(kh.getNgaySinh()));
			stmt.setString(4, kh.getDiaChi());
			stmt.setString(5, kh.getSoDienThoai());
			stmt.setString(6, kh.getEmail());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capNhatKhachHang(KhachHang kh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"{CALL sp_capNhatKhachHang(?, ?, ?, ?, ?, ?)}");
			stmt.setString(1, kh.getHoTen());
			stmt.setDate(2, Date.valueOf(kh.getNgaySinh()));
			stmt.setString(3, kh.getDiaChi());
			stmt.setString(4, kh.getSoDienThoai());
			stmt.setString(5, kh.getEmail());
			stmt.setString(6, kh.getMaKhachHang());
			n = stmt.executeUpdate();
			dskh.set(dskh.indexOf(kh), kh);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean xoaKhachHang(String maKH) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null; 
		int n = 0;
		try {
			stmt = con.prepareStatement("{CALL sp_xoaKhachHang(?)}");
			stmt.setString(1, maKH);
			n = stmt.executeUpdate();
			dskh.removeIf(ph -> ph.getMaKhachHang().equals(maKH));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<KhachHang> timKhachHangTheoMa(String maKhachHang) {
		Dao_KhachHang dao = new Dao_KhachHang();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("{CALL sp_timKhachHangTheoMa(?)}");
			stmt.setString(1, maKhachHang);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String hoTen = rs.getString(2);
				LocalDate ngaySinh = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
				String diaChi = rs.getString(4);
				String soDienThoai = rs.getString(5);
				String email = rs.getString(6);
				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, diaChi, soDienThoai, email);
				dao.dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dskh;
	}

	public ArrayList<KhachHang> timKhachHangTheoTen(String tenKhachHang) {
		Dao_KhachHang dao = new Dao_KhachHang();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("{CALL sp_timKhachHangTheoTen(?)}");
			stmt.setString(1, "%"+tenKhachHang+"%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String hoTen = rs.getString(2);
				LocalDate ngaySinh = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
				String diaChi = rs.getString(4);
				String soDienThoai = rs.getString(5);
				String email = rs.getString(6);
				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, diaChi, soDienThoai, email);
				dao.dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dskh;
	}

	public ArrayList<KhachHang> sapXepTheoMa() {
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "{CALL sp_sapXepKhachHangTheoMa()}";
			CallableStatement stmt = con.prepareCall(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				System.out.println(maKH);
				String hoTen = rs.getString(2);
				LocalDate ngaySinh = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
				String diaChi = rs.getString(4);
				String soDienThoai = rs.getString(5);
				String email = rs.getString(6);
				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, diaChi, soDienThoai, email);
				list.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<KhachHang> sapXepTheoHoTen() {
		Dao_KhachHang dao = new Dao_KhachHang();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "{CALL sp_sapXepKhachHangTheoTen()}";
			CallableStatement stmt = con.prepareCall(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String hoTen = rs.getString(2);
				LocalDate ngaySinh = rs.getTimestamp(3).toLocalDateTime().toLocalDate();
				String diaChi = rs.getString(4);
				String soDienThoai = rs.getString(5);
				String email = rs.getString(6);
				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, diaChi, soDienThoai, email);
				dao.dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dskh;
	}
}
