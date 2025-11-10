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
			String sql = "select * from HoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				LocalDate ngayLap = rs.getDate(2).toLocalDate();
				NhanVien nhanVien = new NhanVien(rs.getString(4), rs.getString(3));
				KhachHang khachHang = new KhachHang(rs.getString(5));
				double tongTien = rs.getDouble(6);
				HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
				dshd.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dshd;
	}

	public ArrayList<HoaDon> layDanhSachHoaDon1() {
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
				double tongTien = rs.getDouble(9);
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

	public ArrayList<HoaDon> timHoaDonTheoMaHD(String maHoaDon) {
	    ArrayList<HoaDon> dshd = new ArrayList<>();
	    System.out.println(">>> Tìm hóa đơn theo mã: '" + maHoaDon + "'");

	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT * FROM HoaDon_KhachHang WHERE MaHoaDon LIKE ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, "%" + maHoaDon + "%");

	        System.out.println("SQL: " + stmt.toString());

	        ResultSet rs = stmt.executeQuery();
	        int count = 0;
	        while (rs.next()) {
	            count++;
	            System.out.println("Dòng " + count + ": " + rs.getString(1) + " | Tổng tiền: " + rs.getDouble(9));

	            String maHD = rs.getString(1);                              // MaHoaDon
	            LocalDate ngayLap = rs.getDate(2).toLocalDate();            // NgayLapHoaDon
	            NhanVien nhanVien = new NhanVien(rs.getString(3), rs.getString(4)); // MaNV, TenNV
	            KhachHang khachHang = new KhachHang(
	                rs.getString(5),        // MaKhachHang
	                rs.getString(6),        // HoTen
	                rs.getString(8),        // DiaChi
	                rs.getString(7)         // SDT
	            );
	            double tongTien = rs.getDouble(9);                          // TongTien

	            HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
	            dshd.add(hd);
	        }
	        System.out.println("Tìm thấy: " + count + " hóa đơn");
	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        System.out.println("LỖI SQL: timHoaDonTheoMaHD");
	        e.printStackTrace();
	    }
	    return dshd;
	}

	public ArrayList<HoaDon> timHoaDonTheoTenNV(String tenNhanVien) {
	    ArrayList<HoaDon> dshd = new ArrayList<>();
	    System.out.println(">>> Tìm hóa đơn theo tên NV: '" + tenNhanVien + "'");

	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT * FROM HoaDon_KhachHang WHERE TenNhanVien LIKE ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, "%" + tenNhanVien + "%");

	        System.out.println("SQL: " + stmt.toString());

	        ResultSet rs = stmt.executeQuery();
	        int count = 0;
	        while (rs.next()) {
	            count++;
	            System.out.println("Dòng " + count + ": " + rs.getString(1) + " | NV: " + rs.getString(4));

	            String maHD = rs.getString(1);
	            LocalDate ngayLap = rs.getDate(2).toLocalDate();
	            NhanVien nhanVien = new NhanVien(rs.getString(3), rs.getString(4));
	            KhachHang khachHang = new KhachHang(
	                rs.getString(5),
	                rs.getString(6),
	                rs.getString(8),
	                rs.getString(7)
	            );
	            double tongTien = rs.getDouble(9);

	            HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
	            dshd.add(hd);
	        }
	        System.out.println("Tìm thấy: " + count + " hóa đơn");
	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        System.out.println("LỖI SQL: timHoaDonTheoTenNV");
	        e.printStackTrace();
	    }
	    return dshd;
	}

	public ArrayList<HoaDon> timHoaDonTheoMaNV(String maNhanVien) {
	    ArrayList<HoaDon> dshd = new ArrayList<>();
	    System.out.println(">>> Tìm hóa đơn theo mã NV: '" + maNhanVien + "'");

	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT * FROM HoaDon_KhachHang WHERE MaNhanVien LIKE ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, "%" + maNhanVien + "%");

	        System.out.println("SQL: " + stmt.toString());

	        ResultSet rs = stmt.executeQuery();
	        int count = 0;
	        while (rs.next()) {
	            count++;
	            System.out.println("Dòng " + count + ": " + rs.getString(1) + " | NV: " + rs.getString(3));

	            String maHD = rs.getString(1);
	            LocalDate ngayLap = rs.getDate(2).toLocalDate();
	            NhanVien nhanVien = new NhanVien(rs.getString(3), rs.getString(4));
	            KhachHang khachHang = new KhachHang(
	                rs.getString(5),
	                rs.getString(6),
	                rs.getString(8),
	                rs.getString(7)
	            );
	            double tongTien = rs.getDouble(9);

	            HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
	            dshd.add(hd);
	        }
	        System.out.println("Tìm thấy: " + count + " hóa đơn");
	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        System.out.println("LỖI SQL: timHoaDonTheoMaNV");
	        e.printStackTrace();
	    }
	    return dshd;
	}


	public ArrayList<HoaDon> sapXepTheoMa() {
	    ArrayList<HoaDon> dshd = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT * FROM HoaDon_KhachHang ORDER BY MaHoaDon";
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(sql);
	        while (rs.next()) {
	            String maHD = rs.getString(1);
	            LocalDate ngayLap = rs.getDate(2).toLocalDate();
	            NhanVien nhanVien = new NhanVien(rs.getString(3), rs.getString(4)); // MaNV, TenNV
	            KhachHang khachHang = new KhachHang(
	                rs.getString(5),  // MaKH
	                rs.getString(6),  // HoTen
	                rs.getString(8),  // DiaChi
	                rs.getString(7)   // SDT
	            );
	            double tongTien = rs.getDouble(9);
	            HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
	            dshd.add(hd);
	        }
	        rs.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dshd;
	}

	public ArrayList<HoaDon> sapXepTheoTen() {
	    ArrayList<HoaDon> dshd = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT * FROM HoaDon_KhachHang ORDER BY TenNhanVien";
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(sql);
	        while (rs.next()) {
	            String maHD = rs.getString(1);
	            LocalDate ngayLap = rs.getDate(2).toLocalDate();
	            NhanVien nhanVien = new NhanVien(rs.getString(3), rs.getString(4));
	            KhachHang khachHang = new KhachHang(
	                rs.getString(5),
	                rs.getString(6),
	                rs.getString(8),
	                rs.getString(7)
	            );
	            double tongTien = rs.getDouble(9);
	            HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
	            dshd.add(hd);
	        }
	        rs.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dshd;
	}

	public ArrayList<HoaDon> sapXepTheoTien() {
	    ArrayList<HoaDon> dshd = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT * FROM HoaDon_KhachHang ORDER BY TongTien DESC";
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(sql);
	        while (rs.next()) {
	            String maHD = rs.getString(1);
	            LocalDate ngayLap = rs.getDate(2).toLocalDate();
	            NhanVien nhanVien = new NhanVien(rs.getString(3), rs.getString(4));
	            KhachHang khachHang = new KhachHang(
	                rs.getString(5),
	                rs.getString(6),
	                rs.getString(8),
	                rs.getString(7)
	            );
	            double tongTien = rs.getDouble(9);
	            HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
	            dshd.add(hd);
	        }
	        rs.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dshd;
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
	
//	public ArrayList<HoaDon> timHoaDonTheoNamLap(String nam) {
//	    Dao_HoaDon dao = new Dao_HoaDon();
//	    try {
//	        Connection con = ConnectDB.getConnection(); // DÙNG CỦA BẠN
//	        if (con == null) {
//	            System.out.println("Chưa kết nối CSDL!");
//	            return dao.dshd;
//	        }
//	        PreparedStatement stmt = con.prepareStatement(
//	            "SELECT * FROM HoaDon WHERE YEAR(NgayLapHoaDon) = ? ORDER BY NgayLapHoaDon"
//	        );
//	        stmt.setString(1, nam);
//	        ResultSet rs = stmt.executeQuery();
//	        while (rs.next()) {
//	            String maHD = rs.getString(1);
//	            LocalDate ngayLap = rs.getDate(2).toLocalDate();
//	            NhanVien nhanVien = new NhanVien(rs.getString(4), rs.getString(3));
//	            KhachHang khachHang = new KhachHang(rs.getString(5));
//	            double tongTien = rs.getDouble(6);
//	            HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
//	            dao.dshd.add(hd);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return dao.dshd;
//	}
//	public ArrayList<HoaDon> timHoaDonTheoNamVaThang(String nam, String thang) {
//	    Dao_HoaDon dao = new Dao_HoaDon();
//	    try {
//	        Connection con = ConnectDB.getConnection();
//	        if (con == null) {
//	            System.out.println("Chưa kết nối CSDL!");
//	            return dao.dshd;
//	        }
//	        PreparedStatement stmt = con.prepareStatement(
//	            "SELECT * FROM HoaDon WHERE YEAR(NgayLapHoaDon) = ? AND MONTH(NgayLapHoaDon) = ? ORDER BY NgayLapHoaDon"
//	        );
//	        stmt.setString(1, nam);
//	        stmt.setString(2, thang);
//	        ResultSet rs = stmt.executeQuery();
//	        while (rs.next()) {
//	            String maHD = rs.getString(1);
//	            LocalDate ngayLap = rs.getDate(2).toLocalDate();
//	            NhanVien nhanVien = new NhanVien(rs.getString(4), rs.getString(3));
//	            KhachHang khachHang = new KhachHang(rs.getString(5));
//	            double tongTien = rs.getDouble(6);
//	            HoaDon hd = new HoaDon(maHD, ngayLap, tongTien, nhanVien, khachHang);
//	            dao.dshd.add(hd);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return dao.dshd;
//	}
	
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
