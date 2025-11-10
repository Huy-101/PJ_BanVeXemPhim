package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ghe;
import entity.PhongChieu;

public class Dao_Ghe {
	private ArrayList<Ghe> dsg;

	public Dao_Ghe() {
		dsg = new ArrayList<Ghe>();
	}

	public ArrayList<Ghe> layDanhSachGhe() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from Ghe";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql); 
			while (rs.next()) {
				String maGhe = rs.getString(1);
				String tenGhe = rs.getString(2);
				PhongChieu phongChieu = (PhongChieu) rs.getObject(3);
				boolean trangThai = rs.getBoolean(4);
				Ghe ghe = new Ghe(maGhe, tenGhe, phongChieu, trangThai);
				dsg.add(ghe); 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsg;
	}

	public boolean themGhe(Ghe ghe) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " Ghe values(?, ?, ?, ?)");
			stmt.setString(1, ghe.getMaGhe());
			stmt.setString(2, ghe.getTenGhe());
			stmt.setString(3, ghe.getPhongChieu().getMaPhong());
			stmt.setBoolean(4, ghe.isTrangThai());
			n = stmt.executeUpdate();
			dsg.add(ghe);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capNhatGhe(Ghe ghe) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update Ghe set tenGhe=?, maPhongChieu=?, trangThai=? where maGhe=?");
			stmt.setString(1, ghe.getTenGhe());
			stmt.setString(2, ghe.getPhongChieu().getMaPhong());
			stmt.setBoolean(3, ghe.isTrangThai());
			stmt.setString(4, ghe.getMaGhe());
			n = stmt.executeUpdate();
			dsg.set(dsg.indexOf(ghe), ghe);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean xoaGhe(String maghe) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from Ghe where maGhe = ?");
			stmt.setString(1, maghe);
			n = stmt.executeUpdate();
			dsg.removeIf(ph -> ph.getMaGhe().equals(maghe));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<Ghe> timGheTheoMa(String maghe) {
		Dao_Ghe dao = new Dao_Ghe();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from Ghe where maGhe=?");
			stmt.setString(1, maghe);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maGhe = rs.getString(1);
				String loaiGhe = rs.getString(2);
				PhongChieu phongChieu = (PhongChieu) rs.getObject(3);
				boolean trangThai = rs.getBoolean(4);
				Ghe ghe = new Ghe(maGhe, loaiGhe, phongChieu, trangThai);
				dao.dsg.add(ghe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dsg;
	}

	public ArrayList<Ghe> timGheTheoTrangThai(boolean trangthai) {
		Dao_Ghe dao = new Dao_Ghe();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from Ghe where trangthai=?");
			stmt.setBoolean(1, trangthai);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maGhe = rs.getString(1);
				String loaiGhe = rs.getString(2);
				PhongChieu phongChieu = (PhongChieu) rs.getObject(3);
				boolean trangThai = rs.getBoolean(4);
				Ghe ghe = new Ghe(maGhe, loaiGhe, phongChieu, trangThai);
				dao.dsg.add(ghe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dsg;
	}

	public ArrayList<Ghe> sapXepTheoMa() {
		Dao_Ghe dao = new Dao_Ghe();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from Ghe order by maGhe";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maGhe = rs.getString(1);
				String loaiGhe = rs.getString(2);
				PhongChieu phongChieu = (PhongChieu) rs.getObject(3);
				boolean trangThai = rs.getBoolean(4);
				Ghe ghe = new Ghe(maGhe, loaiGhe, phongChieu, trangThai);
				dao.dsg.add(ghe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dsg;
	}
}
