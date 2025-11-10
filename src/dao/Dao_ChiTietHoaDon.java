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
import entity.ChiTietHoaDon;
import entity.HoaDon;


public class Dao_ChiTietHoaDon {
	private ArrayList<ChiTietHoaDon> dscthd;

	public Dao_ChiTietHoaDon() {
		dscthd = new ArrayList<ChiTietHoaDon>();
	}

	public ArrayList<ChiTietHoaDon> layDanhSachChiTietHoaDon() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "select * from ChiTietHoaDon_VePhim";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				HoaDon hoaDon = new HoaDon(rs.getString(1));
				String maVP = rs.getString(2);
				double thanhTien = rs.getDouble(3);
				ChiTietHoaDon cthd = new ChiTietHoaDon(hoaDon, 0, thanhTien);
				dscthd.add(cthd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dscthd;
	}

	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " ChiTietHoaDon values(?, ?, ? )");
			stmt.setString(1, cthd.getHoaDon().getMaHoaDon());
			
			stmt.setDouble(6, cthd.getThanhTien());
			n = stmt.executeUpdate();
			dscthd.add(cthd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<ChiTietHoaDon> timSanPhamTheoMa(String maSanPham) {
		Dao_ChiTietHoaDon dao = new Dao_ChiTietHoaDon();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from ChiTietHoaDon where maSP=?");
			stmt.setString(1, maSanPham);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hoaDon = new HoaDon(rs.getString(1));
			
				int soLuongSP = rs.getInt(4);
				double thanhTien = rs.getDouble(6);
	
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dscthd;
	}

}
