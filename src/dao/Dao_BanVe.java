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
	            SuatChieu sc = new SuatChieu(ngayKhoiChieu,thoiGianBatDau);
	            Phim p = new Phim(tenPhim,moTa);
	            PhongChieu pc = new PhongChieu(maPhong);
	            VePhim ve = new VePhim(maVePhim,tenGhe,p,sc,pc);
	            dsVePhim.add(ve);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsVePhim;
	}

}
