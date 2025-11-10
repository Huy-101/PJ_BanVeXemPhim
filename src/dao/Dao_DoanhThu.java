package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DoanhThu;

public class Dao_DoanhThu {
	private ArrayList<DoanhThu> dsdt;

	public Dao_DoanhThu() {
		dsdt = new ArrayList<DoanhThu>();
	}

	public ArrayList<DoanhThu> layDoanhThuTatCa() {
		ArrayList<DoanhThu> dsdt = new ArrayList<DoanhThu>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"Select YEAR(NgayLapHoaDon) as nam, SUM(TongTien) as DoanhThu from HoaDon group by YEAR(NgayLapHoaDon) order by nam");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int thang = rs.getInt(1);
				double doanhThuThang = rs.getDouble(2);
				DoanhThu doanhThu = new DoanhThu(thang, doanhThuThang);
				dsdt.add(doanhThu);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsdt;
	}

	public ArrayList<DoanhThu> layDoanhThuTrongNam(String nam) {
		ArrayList<DoanhThu> dsdt = new ArrayList<DoanhThu>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"Select MONTH(NgayLapHoaDon) as thang, SUM(TongTien) as DoanhThu from HoaDon where YEAR(NgayLapHoaDon) = ? group by MONTH(NgayLapHoaDon) order by thang");
			stmt.setString(1, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int thang = rs.getInt(1);
				double doanhThuThang = rs.getDouble(2);
				DoanhThu doanhThu = new DoanhThu(thang, doanhThuThang);
				dsdt.add(doanhThu);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsdt;
	}

	public ArrayList<DoanhThu> layDoanhThuTrongThang(String nam, String thang) {
		ArrayList<DoanhThu> dsdt = new ArrayList<DoanhThu>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"Select DAY(NgayLapHoaDon) as ngay, SUM(TongTien) as DoanhThu from HoaDon where YEAR(NgayLapHoaDon) = ? and MONTH(NgayLapHoaDon) = ? group by DAY(NgayLapHoaDon) order by ngay");
			stmt.setString(1, nam);
			stmt.setString(2, thang);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int ngay = rs.getInt(1);
				double doanhThuThang = rs.getDouble(2);
				DoanhThu doanhThu = new DoanhThu(ngay, doanhThuThang);
				dsdt.add(doanhThu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsdt;
	}
}
