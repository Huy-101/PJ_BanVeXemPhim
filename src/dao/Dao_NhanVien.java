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
import entity.NhanVien;

public class Dao_NhanVien {
	private ArrayList<NhanVien> dsnv;

	public Dao_NhanVien() {
		dsnv = new ArrayList<NhanVien>();
	}

	public int tinhTongNhanVien() {
		return dsnv.size();
	}

//	public ArrayList<NhanVien> layDanhSachNhanVien() {
//		try {
//			ConnectDB.getInstance().connect();
//			Connection con = ConnectDB.getInstance().getConnection(); 
//			String sql = "Select * from NhanVien";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maNV = rs.getString(1);
//				String hoTen = rs.getString(2);
//				String mk = rs.getString(3);
//				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
//				String diaChi = rs.getString(5);
//				String soDienThoai = rs.getString(6);
//				String email = rs.getString(7);
//				boolean gioiTinh = rs.getBoolean(8);
//				boolean chucVu = rs.getBoolean(9);
//				double luong = rs.getDouble(10);
//				NhanVien nv = new NhanVien(maNV, hoTen, mk, ngaySinh, diaChi, soDienThoai, email, chucVu, gioiTinh, luong);
//				dsnv.add(nv);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dsnv;
//	}

//	public boolean themNhanVien(NhanVien nv) {
//	Connection con = ConnectDB.getInstance().getConnection();
//	PreparedStatement stmt = null;
//	int n = 0;
//	try {
//		stmt = con.prepareStatement("insert into" + " NhanVien values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
//		stmt.setString(1, nv.getMaNhanVien());
//		stmt.setString(2, nv.getHoTen());
//		stmt.setString(3, nv.getPassword());
//		stmt.setDate(4, Date.valueOf(nv.getNgaySinh()));
//		stmt.setString(5, nv.getDiaChi());
//		stmt.setString(6, nv.getSoDienThoai());
//		stmt.setString(7, nv.getEmail());
//		stmt.setBoolean(8, nv.isGioiTinh());
//		stmt.setBoolean(9, nv.isChucVu());
//		stmt.setDouble(10, nv.getLuong());
//		n = stmt.executeUpdate();
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	return n > 0;
//}

//	public boolean capNhatNhanVien(NhanVien nv) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		PreparedStatement stmt = null;
//		int n = 0;
//		try {
//			stmt = con.prepareStatement(
//					"update NhanVien set HoTen=?, MatKhau=? ,NgaySinh=?, DiaChi=?, SDT=?, Email=?, gioiTinh=?, ChucVu=?, luong=? where MaNhanVien=?");
//			stmt.setString(1, nv.getHoTen());
//			stmt.setString(2, nv.getPassword());
//			stmt.setDate(3, Date.valueOf(nv.getNgaySinh()));
//			stmt.setString(4, nv.getDiaChi());
//			stmt.setString(5, nv.getSoDienThoai());
//			stmt.setString(6, nv.getEmail());
//			stmt.setBoolean(7, nv.isGioiTinh());
//			stmt.setBoolean(8, nv.isChucVu());
//			stmt.setDouble(9, nv.getLuong());
//			stmt.setString(10, nv.getMaNhanVien());
//			n = stmt.executeUpdate();
//			dsnv.set(dsnv.indexOf(nv), nv);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return n > 0;
//	}

//	public boolean xoaNhanVien(String maNV) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		PreparedStatement stmt = null;
//		int n = 0;
//		try {
//			stmt = con.prepareStatement("delete from nhanvien where MaNhanVien = ?");
//			stmt.setString(1, maNV);
//			n = stmt.executeUpdate();
//			dsnv.removeIf(ph -> ph.getMaNhanVien().equals(maNV));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return n > 0;
//	}

//	public ArrayList<NhanVien> timNhanVienTheoMa(String maNhanVien) {
//		Dao_NhanVien dao = new Dao_NhanVien();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			PreparedStatement stmt = con.prepareStatement("Select * from nhanvien where MaNhanVien=?");
//			stmt.setString(1, maNhanVien);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				String maNV = rs.getString(1);
//				String hoTen = rs.getString(2);
//				String mk = rs.getString(3);
//				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
//				String diaChi = rs.getString(5);
//				String soDienThoai = rs.getString(6);
//				String email = rs.getString(7);
//				boolean gioiTinh = rs.getBoolean(8);
//				boolean chucVu = rs.getBoolean(9);
//				double luong = rs.getDouble(10);
//				NhanVien nv = new NhanVien(maNV, hoTen, mk, ngaySinh, diaChi, soDienThoai, email, chucVu, gioiTinh,
//						luong);
//				dao.dsnv.add(nv);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dsnv;
//	}

//	public ArrayList<NhanVien> timNhanVienTheoTen(String tenNV) {
//		Dao_NhanVien dao = new Dao_NhanVien();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			PreparedStatement stmt = con.prepareStatement("Select * from NhanVien where HoTen LIKE ?");
//			stmt.setString(1, "%" + tenNV + "%");
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				String maNV = rs.getString(1);
//				String hoTen = rs.getString(2);
//				String mk = rs.getString(3);
//				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
//				String diaChi = rs.getString(5);
//				String soDienThoai = rs.getString(6);
//				String email = rs.getString(7);
//				boolean gioiTinh = rs.getBoolean(8);
//				boolean chucVu = rs.getBoolean(9);
//				double luong = rs.getDouble(10);
//				NhanVien nv = new NhanVien(maNV, hoTen, mk, ngaySinh, diaChi, soDienThoai, email, chucVu, gioiTinh,
//						luong);
//				dao.dsnv.add(nv);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dsnv;
//	}

//	public ArrayList<NhanVien> sapXepTheoTen() {
//		Dao_NhanVien dao = new Dao_NhanVien();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "Select * from nhanvien order by HoTen";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maNV = rs.getString(1);
//				String hoTen = rs.getString(2);
//				String mk = rs.getString(3);
//				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
//				String diaChi = rs.getString(5);
//				String soDienThoai = rs.getString(6);
//				String email = rs.getString(7);
//				boolean gioiTinh = rs.getBoolean(8);
//				boolean chucVu = rs.getBoolean(9);
//				double luong = rs.getDouble(10);
//				NhanVien nv = new NhanVien(maNV, hoTen, mk, ngaySinh, diaChi, soDienThoai, email, chucVu, gioiTinh,
//						luong);
//				dao.dsnv.add(nv);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dsnv;
//	}

//	public ArrayList<NhanVien> sapXepTheoLuong() {
//		Dao_NhanVien dao = new Dao_NhanVien();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "Select * from nhanvien order by Luong";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maNV = rs.getString(1);
//				String hoTen = rs.getString(2);
//				String mk = rs.getString(3);
//				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
//				String diaChi = rs.getString(5);
//				String soDienThoai = rs.getString(6);
//				String email = rs.getString(7);
//				boolean gioiTinh = rs.getBoolean(8);
//				boolean chucVu = rs.getBoolean(9);
//				double luong = rs.getDouble(10);
//				NhanVien nv = new NhanVien(maNV, hoTen, mk, ngaySinh, diaChi, soDienThoai, email, chucVu, gioiTinh,
//						luong);
//				dao.dsnv.add(nv);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dsnv;
//	}
	
	// Viet cho store producer
	public ArrayList<NhanVien> layDanhSachNhanVien() {
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "{CALL sp_layAllNhanVien()}";
			CallableStatement stmt = con.prepareCall(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString(2);
				String mk = rs.getString(3);
				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
				String diaChi = rs.getString(5);
				String soDienThoai = rs.getString(6);
				String email = rs.getString(7);
				boolean gioiTinh = rs.getBoolean(8);
				boolean chucVu = rs.getBoolean(9);
				double luong = rs.getDouble(10);
				NhanVien nv = new NhanVien(maNV, hoTen, mk, ngaySinh, diaChi, soDienThoai, email, chucVu, gioiTinh,
						luong);
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}

	public boolean themNhanVien(NhanVien nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("{CALL sp_themNhanVien(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, nv.getHoTen());
			stmt.setString(3, nv.getPassword());
			stmt.setDate(4, Date.valueOf(nv.getNgaySinh()));
			stmt.setString(5, nv.getDiaChi());
			stmt.setString(6, nv.getSoDienThoai());
			stmt.setString(7, nv.getEmail());
			stmt.setBoolean(8, nv.isGioiTinh());
			stmt.setBoolean(9, nv.isChucVu());
			stmt.setDouble(10, nv.getLuong());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatNhanVien(NhanVien nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"{CALL sp_capNhatNhanVien(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			stmt.setString(1, nv.getHoTen());
			stmt.setString(2, nv.getPassword());
			stmt.setDate(3, Date.valueOf(nv.getNgaySinh()));
			stmt.setString(4, nv.getDiaChi());
			stmt.setString(5, nv.getSoDienThoai());
			stmt.setString(6, nv.getEmail());
			stmt.setBoolean(7, nv.isGioiTinh());
			stmt.setBoolean(8, nv.isChucVu());
			stmt.setDouble(9, nv.getLuong());
			stmt.setString(10, nv.getMaNhanVien());
			n = stmt.executeUpdate();
			dsnv.set(dsnv.indexOf(nv), nv);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n > 0;
	}
	
	public boolean xoaNhanVien(String maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("{CALL sp_xoaNhanVien(?)}");
			stmt.setString(1, maNV);
			n = stmt.executeUpdate();
			dsnv.removeIf(ph -> ph.getMaNhanVien().equals(maNV));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<NhanVien> timNhanVienTheoMa(String maNhanVien) {
		Dao_NhanVien dao = new Dao_NhanVien();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("{CALL sp_timNhanVienTheoMa(?)}");
			stmt.setString(1, maNhanVien);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString(2);
				String mk = rs.getString(3);
				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
				String diaChi = rs.getString(5);
				String soDienThoai = rs.getString(6);
				String email = rs.getString(7);
				boolean gioiTinh = rs.getBoolean(8);
				boolean chucVu = rs.getBoolean(9);
				double luong = rs.getDouble(10);
				NhanVien nv = new NhanVien(maNV, hoTen, mk, ngaySinh, diaChi, soDienThoai, email, chucVu, gioiTinh,
						luong);
				dao.dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dsnv;
	}
	
	public ArrayList<NhanVien> timNhanVienTheoTen(String tenNV) {
		Dao_NhanVien dao = new Dao_NhanVien();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("{CALL sp_timNhanVienTheoTen(?)}");
			stmt.setString(1, "%" + tenNV + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString(2);
				String mk = rs.getString(3);
				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
				String diaChi = rs.getString(5);
				String soDienThoai = rs.getString(6);
				String email = rs.getString(7);
				boolean gioiTinh = rs.getBoolean(8);
				boolean chucVu = rs.getBoolean(9);
				double luong = rs.getDouble(10);
				NhanVien nv = new NhanVien(maNV, hoTen, mk, ngaySinh, diaChi, soDienThoai, email, chucVu, gioiTinh,
						luong);
				dao.dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dsnv;
	}
	
	public ArrayList<NhanVien> sapXepTheoTen() {
		Dao_NhanVien dao = new Dao_NhanVien();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "{CALL sp_sapXepNhanVienTheoTen()}";
			CallableStatement stmt = con.prepareCall(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString(2);
				String mk = rs.getString(3);
				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
				String diaChi = rs.getString(5);
				String soDienThoai = rs.getString(6);
				String email = rs.getString(7);
				boolean gioiTinh = rs.getBoolean(8);
				boolean chucVu = rs.getBoolean(9);
				double luong = rs.getDouble(10);
				NhanVien nv = new NhanVien(maNV, hoTen, mk, ngaySinh, diaChi, soDienThoai, email, chucVu, gioiTinh,
						luong);
				dao.dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dsnv;
	}
	
	public ArrayList<NhanVien> sapXepTheoLuong() {
		Dao_NhanVien dao = new Dao_NhanVien();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "{CALL sp_sapXepNhanVienTheoLuong()}";
			CallableStatement stmt = con.prepareCall(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTen = rs.getString(2);
				String mk = rs.getString(3);
				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
				String diaChi = rs.getString(5);
				String soDienThoai = rs.getString(6);
				String email = rs.getString(7);
				boolean gioiTinh = rs.getBoolean(8);
				boolean chucVu = rs.getBoolean(9);
				double luong = rs.getDouble(10);
				NhanVien nv = new NhanVien(maNV, hoTen, mk, ngaySinh, diaChi, soDienThoai, email, chucVu, gioiTinh,
						luong);
				dao.dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dsnv;
	}
}