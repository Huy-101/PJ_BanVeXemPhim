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
import entity.DoanhThu;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class Dao_HoaDon {
	private ArrayList<HoaDon> dshd;

	public Dao_HoaDon() {
		dshd = new ArrayList<HoaDon>();
	}

	public ArrayList<HoaDon> layDanhSachHoaDon() {
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getInstance().getConnection();
			String sql ="Select * from HoaDon_KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngayLap = rs.getDate(2).toLocalDate();
				NhanVien nhanVien = new NhanVien(rs.getString(3));
				double tongTien = rs.getDouble(8);
				String hoTen = rs.getString(5);
				String sdt = rs.getString(6);
				String diachi = rs.getString(7);
				KhachHang khachHang = new KhachHang(rs.getString(4),hoTen,diachi,sdt);
				HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
				dshd.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dshd;
	}

	public boolean themHoaDon(HoaDon hd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " HoaDon values(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, hd.getMaHoaDon());
			stmt.setDate(2, Date.valueOf(hd.getNgayLap()));
			stmt.setString(3, hd.getNhanVien().getHoTen());
			stmt.setString(4, hd.getNhanVien().getMaNhanVien());
			stmt.setString(5, hd.getKhachHang().getMaKhachHang());
			stmt.setDouble(6, hd.getTongTien());
			n = stmt.executeUpdate();
			dshd.add(hd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capNhatHoaDon(HoaDon hd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update HoaDon set ngayLapHD=?, tongTien=?, maNhanVien=?, maKhachHang=?, tenNhanVien=? where maHoaDon=?");
			stmt.setDate(1, Date.valueOf(hd.getNgayLap()));
			stmt.setDouble(2, hd.getTongTien());
			stmt.setString(3, hd.getNhanVien().getMaNhanVien());
			stmt.setString(4, hd.getKhachHang().getMaKhachHang());
			stmt.setString(5, hd.getMaHoaDon());
			n = stmt.executeUpdate();
			dshd.set(dshd.indexOf(hd), hd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean xoaHoaDon(String maHoaDon) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from HoaDon where maHoaDon = ?");
			stmt.setString(1, maHoaDon);
			n = stmt.executeUpdate();
			dshd.removeIf(ph -> ph.getMaHoaDon().equals(maHoaDon));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<HoaDon> timHoaDonTheoMaHD(String maHD) {
	    ArrayList<HoaDon> dsHD = new ArrayList<>();

	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT * FROM HoaDon_KhachHang WHERE MaHoaDon = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, maHD);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String maHoaDon = rs.getString(1);
	            LocalDate ngayLap = rs.getDate(2).toLocalDate();
	            NhanVien nhanVien = new NhanVien(rs.getString(3));
	            KhachHang khachHang = new KhachHang(
	                rs.getString(4),
	                rs.getString(5),
	                rs.getString(6),
	                rs.getString(7)
	            );
	            double tongTien = rs.getDouble(8);

	            HoaDon hd = new HoaDon(maHoaDon, ngayLap, tongTien, nhanVien, khachHang);
	            dsHD.add(hd);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsHD;
	}


	public ArrayList<HoaDon> timHoaDonTheoTenNV(String maNV) {
	    ArrayList<HoaDon> dshd = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT * FROM HoaDon_KhachHang WHERE MaNhanVien = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, maNV);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String maHD = rs.getString(1);
	            LocalDate ngayLap = rs.getDate(2).toLocalDate();
	            NhanVien nhanVien = new NhanVien(rs.getString(3));
	            KhachHang khachHang = new KhachHang(
	                rs.getString(4),
	                rs.getString(5),
	                rs.getString(6),
	                rs.getString(7)
	            );
	            double tongTien = rs.getDouble(8);
	            HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
	            dshd.add(hd);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dshd;
	}
	public ArrayList<HoaDon> timHoaDonTheoMaNV(String maNV) {
	    ArrayList<HoaDon> dshd = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT * FROM HoaDon_KhachHang WHERE MaNhanVien = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, maNV);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String maHD = rs.getString(1);
	            LocalDate ngayLap = rs.getDate(2).toLocalDate();
	            NhanVien nv = new NhanVien(rs.getString(3));
	            KhachHang kh = new KhachHang(
	                rs.getString(4),
	                rs.getString(5),
	                rs.getString(6),
	                rs.getString(7)
	            );
	            double tongTien = rs.getDouble(8);
	            HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nv, kh);
	            dshd.add(hd);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dshd;
	}
	



	public ArrayList<HoaDon> sapXepTheoMa() {
		Dao_HoaDon dao = new Dao_HoaDon();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from HoaDon order by maHoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngayLap = rs.getDate(2).toLocalDate();
				NhanVien nhanVien = new NhanVien(rs.getString(4), rs.getString(3));
				KhachHang khachHang = new KhachHang(rs.getString(5));
				double tongTien = rs.getDouble(6);
				HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
				dao.dshd.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dshd;
	}

	public ArrayList<HoaDon> sapXepTheoTen() {
		Dao_HoaDon dao = new Dao_HoaDon();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from HoaDon order by TenNhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngayLap = rs.getDate(2).toLocalDate();
				NhanVien nhanVien = new NhanVien(rs.getString(4), rs.getString(3));
				KhachHang khachHang = new KhachHang(rs.getString(5));
				double tongTien = rs.getDouble(6);
				HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
				dao.dshd.add(hd);
				;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dshd;
	}

	public ArrayList<HoaDon> sapXepTheoTien() {
		Dao_HoaDon dao = new Dao_HoaDon();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from HoaDon order by tongTien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngayLap = rs.getDate(2).toLocalDate();
				NhanVien nhanVien = new NhanVien(rs.getString(4), rs.getString(3));
				KhachHang khachHang = new KhachHang(rs.getString(5));
				double tongTien = rs.getDouble(6);
				HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
				dao.dshd.add(hd);
				;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dshd;
	}
	
	public ArrayList<HoaDon> timHoaDonTheoNamLap(String nam) {
		Dao_HoaDon dao = new Dao_HoaDon();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from HoaDon where YEAR(NgayLapHoaDon)=?");
			stmt.setString(1, nam);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngayLap = rs.getDate(2).toLocalDate();
				NhanVien nhanVien = new NhanVien(rs.getString(4), rs.getString(3));
				KhachHang khachHang = new KhachHang(rs.getString(5));
				double tongTien = rs.getDouble(6);
				HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
				dao.dshd.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dshd;
	}
	
	public ArrayList<HoaDon> timHoaDonTheoNamLap(String nam, String thang) {
		Dao_HoaDon dao = new Dao_HoaDon();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from HoaDon where YEAR(NgayLapHoaDon)=? and MONTH(NgayLapHoaDon)=?");
			stmt.setString(1, nam);
			stmt.setString(2, thang);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngayLap = rs.getDate(2).toLocalDate();
				NhanVien nhanVien = new NhanVien(rs.getString(4), rs.getString(3));
				KhachHang khachHang = new KhachHang(rs.getString(5));
				double tongTien = rs.getDouble(6);
				HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
				dao.dshd.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dao.dshd;
	}
	public ArrayList<HoaDon> layDanhSachHoaDon1() {
	    ArrayList<HoaDon> danhSach = new ArrayList<>();
	    String sql = "Select * from HoaDon_KhachHang";
	       
	    Connection con = ConnectDB.getInstance().getConnection();

	    if (con == null) {
	        System.out.println("Kết nối database thất bại!");
	        return danhSach; // trả về danh sách rỗng thay vì NPE
	    }

	    try (PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            String maHD = rs.getString(1);
	            Date ngayLapDate = rs.getDate(2);
	            LocalDate ngayLap = ngayLapDate != null ? ngayLapDate.toLocalDate() : null;

	            String maNV = rs.getString(3);
	            String maKH = rs.getString(4);
	            String hoTen = rs.getString(5);
	            String sdt = rs.getString(6);
	            String diaChi = rs.getString(7);
	            double tongTien = rs.getDouble(8);

	            KhachHang kh = new KhachHang(maKH, hoTen, sdt, diaChi);
	            NhanVien nv = new NhanVien(maNV);
	            HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh, tongTien);
	            danhSach.add(hd);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return danhSach;
	}

	public ArrayList<Integer> layCacNamCoHoaDon() {
	    ArrayList<Integer> listNam = new ArrayList<>();
	    try {
	        ConnectDB.getInstance().connect();
	        Connection con = ConnectDB.getConnection();
	        if (con == null) {
	            System.out.println("Chưa kết nối CSDL!");
	            return listNam;
	        }
	        String sql = "SELECT DISTINCT YEAR(NgayLapHoaDon) FROM HoaDon ORDER BY YEAR(NgayLapHoaDon) DESC";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            listNam.add(rs.getInt(1));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return listNam;
	}


}
