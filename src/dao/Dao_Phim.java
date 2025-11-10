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
import javax.swing.JMenuBar;
import connectDB.ConnectDB;
import entity.Phim;

public class Dao_Phim {
	
private ArrayList<Phim> dsp;
	
	public Dao_Phim() {
		dsp = new ArrayList<Phim>();
	}
	//Viet cho Store producer
		public ArrayList<Phim> layDanhSachPhim() {
			try {
				ConnectDB.getInstance().connect();
				Connection con = ConnectDB.getInstance().getConnection();
				String sql = "{CALL sp_layAllPhim()}";
				CallableStatement stmt = con.prepareCall(sql);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String maPhim = rs.getString(1);
					String tenPhim = rs.getString(2); 
					String daoDien = rs.getString(3);
					int thoiLuong = rs.getInt(4);
					LocalDate ngayKhoiChieu = rs.getDate(5).toLocalDate();
					LocalDate ngayKetThuc = rs.getDate(6).toLocalDate();
					String quocGia = rs.getString(7);
					String theLoai = rs.getString(8);
					String moTa = rs.getString(9);
					Phim p = new Phim(maPhim, tenPhim, daoDien, thoiLuong, ngayKhoiChieu, ngayKetThuc, quocGia, theLoai, moTa);
					dsp.add(p);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return dsp;
		}
		
		public boolean themPhim(Phim p) {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = null;
			int n = 0;
			try {
				stmt = con.prepareStatement("{CALL sp_themPhim(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
				stmt.setString(1, p.getMaPhim());
				stmt.setString(2, p.getTenPhim());
				stmt.setString(3, p.getDaoDien());
				stmt.setInt(4, p.getThoiLuong());
				stmt.setDate(5, Date.valueOf(p.getNgayKhoiChieu()));
				stmt.setDate(6, Date.valueOf(p.getNgayKetThuc()));
				stmt.setString(7, p.getQuocGia());
				stmt.setString(8, p.getTheLoai());
				stmt.setString(9, p.getMoTa());
				n = stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return n > 0;
		}
		
		public boolean capNhatPhim(Phim p) {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = null;
			int n = 0;
			try {
				stmt = con.prepareStatement(
						"{CALL sp_capNhatPhim(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
				stmt.setString(1, p.getTenPhim());
				stmt.setString(2, p.getDaoDien());
				stmt.setInt(3, p.getThoiLuong());
				stmt.setDate(4, Date.valueOf(p.getNgayKhoiChieu()));
				stmt.setDate(5, Date.valueOf(p.getNgayKetThuc()));
				stmt.setString(6, p.getQuocGia());
				stmt.setString(7, p.getTheLoai());
				stmt.setString(8, p.getMoTa());
				stmt.setString(9, p.getMaPhim());
				n = stmt.executeUpdate();
//				dsp.set(dsp.indexOf(p), p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return n > 0;
		}
		
		public boolean xoaPhim(String maPhim) {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = null;
			int n = 0;
			try {
				stmt = con.prepareStatement("{CALL sp_xoaPhim(?)}");
				stmt.setString(1, maPhim);
				n = stmt.executeUpdate();
				dsp.removeIf(ph -> ph.getMaPhim().equals(maPhim));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return n > 0;
		}
		
		public ArrayList<Phim> timPhimTheoTen(String ten) {
			Dao_Phim dao = new Dao_Phim();
			try {
				Connection con = ConnectDB.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("{CALL sp_timPhimTheoTen(?)}");
				stmt.setString(1,ten + "%");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String maPhim = rs.getString(1);
					String tenPhim = rs.getString(2);
					String daoDien = rs.getString(3);
					int thoiLuong = rs.getInt(4);
					LocalDate ngayKhoiChieu = rs.getDate(5).toLocalDate();
					LocalDate ngayKetThuc = rs.getDate(6).toLocalDate();
					String quocGia = rs.getString(7);
					String theLoai = rs.getString(8);
					String moTa = rs.getString(9);
					Phim p = new Phim(maPhim, tenPhim, daoDien, thoiLuong, ngayKhoiChieu, ngayKetThuc, quocGia, theLoai, moTa);
					dao.dsp.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dao.dsp;
		}
		
		public ArrayList<Phim> timPhimTheoNgayChieu(LocalDate tuNgay, LocalDate denNgay) {
			Dao_Phim dao = new Dao_Phim();
			try {
				Connection con = ConnectDB.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement("{CALL sp_timPhimTheoNgayChieu(?, ?)}");
				stmt.setDate(1, Date.valueOf(tuNgay));
				stmt.setDate(2, Date.valueOf(denNgay));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String maPhim = rs.getString(1);
					String tenPhim = rs.getString(2);
					String daoDien = rs.getString(3);
					int thoiLuong = rs.getInt(4);
					LocalDate ngayKhoiChieu = rs.getDate(5).toLocalDate();
					LocalDate ngayKetThucPhim  = rs.getDate(6).toLocalDate();
					String quocGia = rs.getString(7);
					String theLoai = rs.getString(8);
					String moTa = rs.getString(9);
					Phim p = new Phim(maPhim, tenPhim, daoDien, thoiLuong, ngayKhoiChieu, ngayKetThucPhim, quocGia, theLoai, moTa);
					dao.dsp.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dao.dsp;
		}
		
		public ArrayList<Phim> sapXepTheoMa() {
			Dao_Phim dao = new Dao_Phim();
			try {
				Connection con = ConnectDB.getInstance().getConnection();
				String sql = "{CALL sp_sapXepPhimTheoMa()}";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					String maPhim = rs.getString(1);
					String tenPhim = rs.getString(2);
					String daoDien = rs.getString(3);
					int thoiLuong = rs.getInt(4);
					LocalDate ngayKhoiChieu = rs.getDate(5).toLocalDate();
					LocalDate ngayKetThuc = rs.getDate(6).toLocalDate();
					String quocGia = rs.getString(7);
					String theLoai = rs.getString(8);
					String moTa = rs.getString(9);
					Phim p = new Phim(maPhim, tenPhim, daoDien, thoiLuong, ngayKhoiChieu, ngayKetThuc, quocGia, theLoai, moTa);
					dao.dsp.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dao.dsp;
		}
		
		public ArrayList<Phim> sapXepTheoTen() {
			Dao_Phim dao = new Dao_Phim();
			try {
				Connection con = ConnectDB.getInstance().getConnection();
				String sql = "{CALL sp_sapXepPhimTheoTen()}";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					String maPhim = rs.getString(1);
					String tenPhim = rs.getString(2);
					String daoDien = rs.getString(3);
					int thoiLuong = rs.getInt(4);
					LocalDate ngayKhoiChieu = rs.getDate(5).toLocalDate();
					LocalDate ngayKetThuc = rs.getDate(6).toLocalDate();
					String quocGia = rs.getString(7);
					String theLoai = rs.getString(8);
					String moTa = rs.getString(9);
					Phim p = new Phim(maPhim, tenPhim, daoDien, thoiLuong, ngayKhoiChieu, ngayKetThuc, quocGia, theLoai, moTa);
					dao.dsp.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dao.dsp;
		}
	}