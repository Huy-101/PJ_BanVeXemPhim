package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.PhongChieu;

public class Dao_PhongChieu {
	private ArrayList<PhongChieu> dspc;

	public Dao_PhongChieu() {
		dspc = new ArrayList<PhongChieu>();
	}

	public ArrayList<PhongChieu> layDanhSachPhongChieu() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from PhongChieu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maPhong = rs.getString(1);
				int soghe = rs.getInt(2);
				PhongChieu p = new PhongChieu(maPhong, soghe);
				dspc.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dspc;
	}

	public boolean themPhongChieu(PhongChieu pc) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " PhongChieu values(?, ?)");
			stmt.setString(1, pc.getMaPhong());
			stmt.setInt(2, pc.getSoGheTrong());
			n = stmt.executeUpdate();
			dspc.add(pc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

//	public boolean capNhatPhongChieu(PhongChieu pc) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		PreparedStatement stmt = null;
//		int n = 0;
//		try {
//			stmt = con.prepareStatement(
//					"update PhongChieu set tenPhong=?, ,soHangGhe=?, soCotGhe=?, loaiPhong=? where maPhong=?");
//			stmt.setString(1, pc.getTenPhong());
//			stmt.setInt(2, pc.getSoHangGhe());
//			stmt.setInt(3, pc.getSoCotGhe());
//			stmt.setString(4, pc.getLoaiPhong());
//			stmt.setString(5, pc.getMaPhong());
//			n = stmt.executeUpdate();
//			dspc.set(dspc.indexOf(pc), pc);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return n > 0;
//	}
//	
//	public boolean xoaPhongChieu(String maPhong) {
//		Connection con = ConnectDB.getInstance().getConnection();
//		PreparedStatement stmt = null;
//		int n = 0;
//		try {
//			stmt = con.prepareStatement("delete from PhongChieu where maPhong = ?");
//			stmt.setString(1, maPhong);
//			n = stmt.executeUpdate();
//			dspc.removeIf(ph -> ph.getMaPhong().equals(maPhong));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return n > 0;
//	}
	
//	public ArrayList<PhongChieu> timPhongChieuTheoMa(String maPhong) {
//		Dao_PhongChieu dao = new Dao_PhongChieu();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			PreparedStatement stmt = con.prepareStatement("Select * from PhongChieu where maPhong=?");
//			stmt.setString(1, maPhong);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				String maPC = rs.getString(1);
//				String tenPC = rs.getString(2);
//				int soHangGhe = rs.getInt(3);
//				int soCotGhe = rs.getInt(4);
//				String loaiPhong = rs.getString(5);
//				PhongChieu pc = new PhongChieu(maPC, tenPC, soHangGhe, soCotGhe, loaiPhong);
//				dao.dspc.add(pc);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dspc;
//	}
//
//	public ArrayList<PhongChieu> timPhongChieuTheoTen(String tenPhong) {
//		Dao_PhongChieu dao = new Dao_PhongChieu();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			PreparedStatement stmt = con.prepareStatement("Select * from PhongChieu where tenPhong=?");
//			stmt.setString(1, tenPhong);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				String maPC = rs.getString(1);
//				String tenPC = rs.getString(2);
//				int soHangGhe = rs.getInt(3);
//				int soCotGhe = rs.getInt(4);
//				String loaiPhong = rs.getString(5);
//				PhongChieu pc = new PhongChieu(maPC, tenPC, soHangGhe, soCotGhe, loaiPhong);
//				dao.dspc.add(pc);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dspc;
//	}
//
//	public ArrayList<PhongChieu> sapXepTheoMa() {
//		Dao_PhongChieu dao = new Dao_PhongChieu();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "Select * from PhongChieu order by maPhong";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maPC = rs.getString(1);
//				String tenPC = rs.getString(2);
//				int soHangGhe = rs.getInt(3);
//				int soCotGhe = rs.getInt(4);
//				String loaiPhong = rs.getString(5);
//				PhongChieu pc = new PhongChieu(maPC, tenPC, soHangGhe, soCotGhe, loaiPhong);
//				dao.dspc.add(pc);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dspc;
//	}
//
//	public ArrayList<PhongChieu> sapXepTheoHoTen() {
//		Dao_PhongChieu dao = new Dao_PhongChieu();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "Select * from PhongChieu order by tenPhong";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String maPC = rs.getString(1);
//				String tenPC = rs.getString(2);
//				int soHangGhe = rs.getInt(3);
//				int soCotGhe = rs.getInt(4);
//				String loaiPhong = rs.getString(5);
//				PhongChieu pc = new PhongChieu(maPC, tenPC, soHangGhe, soCotGhe, loaiPhong);
//				dao.dspc.add(pc);
//				;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dao.dspc;
//	}
}
