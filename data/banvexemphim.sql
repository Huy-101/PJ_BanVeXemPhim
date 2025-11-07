create database QuanLyRapChieuPhim
USE QuanLyRapChieuPhim
GO
CREATE TABLE Phim (
    MaPhim NVARCHAR(50) PRIMARY KEY NOT NULL,
    TenPhim NVARCHAR(150) NOT NULL,
	DaoDien VARCHAR(100),
	ThoiLuong INT,
    NgayKhoiChieu DATETIME,
	NgayKetThuc DATETIME,
	QuocGia NVARCHAR(100),
	TheLoai NVARCHAR(200),
	MoTa TEXT
);

CREATE TABLE PhongChieu(
	MaPhong NVARCHAR(20) PRIMARY KEY NOT NULL,
	SoGheTrong INT
);


CREATE TABLE Ghe (
	MaGhe NVARCHAR(20) PRIMARY KEY,
	MaPhong NVARCHAR(20),
    TenGhe NVARCHAR(20),
	TrangThai BIT  
	FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong) ON DELETE CASCADE

);

CREATE TABLE SuatChieu (
    MaSuatChieu NVARCHAR(20) PRIMARY KEY NOT NULL,
    MaPhim NVARCHAR(50),  
    MaPhong NVARCHAR(20), 
	NgayKhoiChieu DATETIME,
    ThoiGianBatDau NVARCHAR(20),
    HinhThucChieu NVARCHAR(50),
    TrangThai BIT,
    FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim) ON DELETE CASCADE,
    FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong) ON DELETE CASCADE
);

CREATE TABLE VePhim(
	MaVePhim NVARCHAR(20) PRIMARY KEY NOT NULL,
	TenGhe NVARCHAR(20),
	MaPhong NVARCHAR(20),
	MaSuatChieu NVARCHAR(20) NOT NULL,
	FOREIGN KEY (MaSuatChieu) REFERENCES SuatChieu(MaSuatChieu) ON DELETE CASCADE
);



CREATE TABLE NhanVien (
    MaNhanVien NVARCHAR(50) PRIMARY KEY NOT NULL,
    HoTen NVARCHAR(150) NOT NULL,
	MatKhau NVARCHAR(50),
    NgaySinh DATETIME,
    DiaChi NVARCHAR(255),
    SDT NVARCHAR(15),
    Email NVARCHAR(100),
    GioiTinh BIT,
    ChucVu BIT,
    Luong MONEY
);

CREATE TABLE KhachHang (
    MaKhachHang NVARCHAR(50) PRIMARY KEY NOT NULL,
    HoTen NVARCHAR(150) NOT NULL,
    NgaySinh DATETIME,
    DiaChi NVARCHAR(255),
    SDT NVARCHAR(15),
    Email NVARCHAR(100)
);

CREATE TABLE HoaDon(
	MaHoaDon NVARCHAR(50) PRIMARY KEY NOT NULL,
	NgayLapHoaDon DATETIME,
	TenNhanVien NVARCHAR(50),
	MaNhanVien NVARCHAR(50),
	MaKhachHang NVARCHAR(50),
	TongTien MONEY,
	FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien) ON DELETE CASCADE,
	FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang) ON DELETE CASCADE
);



CREATE TABLE ChiTietHoaDon_VePhim (
    MaHoaDon NVARCHAR(50) PRIMARY KEY NOT NULL,
    MaVePhim NVARCHAR(20),
    DonGia MONEY,
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon) ON DELETE CASCADE,
    FOREIGN KEY (MaVePhim) REFERENCES VePhim(MaVePhim) ON DELETE CASCADE
);

INSERT INTO Phim (MaPhim, TenPhim, DaoDien, ThoiLuong, NgayKhoiChieu, NgayKetThuc, QuocGia, TheLoai, MoTa) VALUES
('P001', 'Bí Ẩn Dưới Đại Dương', 'Nguyễn Hữu Tài', 120, '2025-01-10', '2025-02-20', 'Việt Nam', 'Phiêu lưu', 'Một nhóm thợ lặn phát hiện bí mật cổ xưa trong lòng biển.'),
('P002', 'Kẻ Trộm Bóng Đêm', 'Trần Anh Quân', 110, '2025-01-15', '2025-03-01', 'Mỹ', 'Hành động', 'Một tên trộm tài ba đánh cắp bí mật quốc gia.'),
('P003', 'Giấc Mơ Mùa Hạ', 'Lê Phương Mai', 95, '2025-02-01', '2025-03-15', 'Hàn Quốc', 'Tình cảm', 'Câu chuyện tình yêu tuổi trẻ dưới ánh nắng mùa hè.'),
('P004', 'Bóng Ma Trong Thành Phố', 'Phạm Duy', 105, '2025-02-10', '2025-03-25', 'Nhật Bản', 'Kinh dị', 'Một thành phố bị ám bởi linh hồn cổ xưa.'),
('P005', 'Cuộc Đua Sinh Tử', 'Hoàng Minh Quang', 130, '2025-03-05', '2025-04-10', 'Anh', 'Hành động', 'Các tay đua tham gia cuộc đua nguy hiểm nhất thế giới.'),
('P006', 'Ngày Cuối Cùng Trái Đất', 'Lê Hoàng', 125, '2025-03-20', '2025-05-01', 'Mỹ', 'Khoa học viễn tưởng', 'Loài người cố gắng sống sót trước thảm họa toàn cầu.'),
('P007', 'Nhật Ký Thiên Thần', 'Trần Thu Hằng', 98, '2025-04-01', '2025-05-10', 'Pháp', 'Tâm lý', 'Một thiên thần giáng thế để giúp con người tìm lại niềm tin.'),
('P008', 'Ngôi Nhà Trên Đồi', 'Nguyễn Hải', 115, '2025-04-15', '2025-06-01', 'Canada', 'Kinh dị', 'Một gia đình dọn đến căn nhà có quá khứ bí ẩn.'),
('P009', 'Sứ Mệnh Cuối Cùng', 'Bùi Văn Cường', 100, '2025-05-01', '2025-06-20', 'Mỹ', 'Hành động', 'Một điệp viên thực hiện nhiệm vụ cuối cùng trước khi nghỉ hưu.'),
('P010', 'Tình Yêu Sau Mưa', 'Phan Minh Hòa', 90, '2025-05-10', '2025-06-30', 'Việt Nam', 'Lãng mạn', 'Hai con người xa lạ gặp nhau trong cơn mưa định mệnh.'),
('P011', 'Không Gian Song Song', 'Trần Quốc Huy', 125, '2025-06-01', '2025-07-20', 'Mỹ', 'Khoa học viễn tưởng', 'Một nhà khoa học mở cánh cổng đến thế giới song song.'),
('P012', 'Khu Rừng Bóng Đêm', 'Vũ Khánh Linh', 102, '2025-06-15', '2025-07-30', 'Thái Lan', 'Kinh dị', 'Một nhóm bạn trẻ bị lạc trong khu rừng bị nguyền rủa.'),
('P013', 'Người Máy Và Tôi', 'Phạm Văn Lâm', 115, '2025-07-01', '2025-08-15', 'Nhật Bản', 'Khoa học viễn tưởng', 'Tình bạn giữa con người và robot giữa thế giới tương lai.'),
('P014', 'Khi Ánh Sáng Tắt Dần', 'Đỗ Thanh Tú', 98, '2025-07-10', '2025-08-25', 'Hàn Quốc', 'Tâm lý', 'Một người phụ nữ mù tìm lại niềm tin vào cuộc sống.'),
('P015', 'Thành Phố Ngủ Quên', 'Lê Minh Tuấn', 110, '2025-07-20', '2025-09-01', 'Mỹ', 'Bí ẩn', 'Một thành phố nhỏ nơi mọi người đều mang cùng một giấc mơ.'),
('P016', 'Chiến Binh Ánh Sáng', 'Ngô Đức Long', 140, '2025-08-01', '2025-09-15', 'Anh', 'Hành động', 'Những chiến binh bảo vệ Trái Đất khỏi thế lực bóng tối.'),
('P017', 'Vết Sẹo Thời Gian', 'Trần Ngọc Mai', 108, '2025-08-10', '2025-09-25', 'Pháp', 'Tình cảm', 'Một chuyện tình vượt qua không gian và thời gian.'),
('P018', 'Cuộc Gọi Bí Ẩn', 'Phạm Hồng Sơn', 95, '2025-08-20', '2025-10-01', 'Mỹ', 'Hình sự', 'Một cảnh sát điều tra chuỗi cuộc gọi kỳ lạ liên quan đến án mạng.'),
('P019', 'Đêm Trăng Máu', 'Nguyễn Quang Hưng', 105, '2025-09-01', '2025-10-15', 'Nhật Bản', 'Kinh dị', 'Một nghi lễ cổ xưa hồi sinh quái vật dưới ánh trăng.'),
('P020', 'Hành Tinh Xanh', 'Lê Bảo', 115, '2025-09-10', '2025-10-30', 'Mỹ', 'Khoa học viễn tưởng', 'Hành trình của con người đi tìm sự sống trên hành tinh mới.'),
('P021', 'Con Đường Không Lối Thoát', 'Vũ Hoàng', 122, '2025-09-20', '2025-11-01', 'Đức', 'Hành động', 'Một tay đua phải chạy trốn khỏi quá khứ tội lỗi.'),
('P022', 'Hoa Nở Giữa Đông', 'Trịnh Thu Hương', 98, '2025-09-25', '2025-11-10', 'Hàn Quốc', 'Tình cảm', 'Tình yêu mong manh giữa mùa đông giá lạnh.'),
('P023', 'Kẻ Thay Thế', 'Nguyễn Tấn Phát', 110, '2025-10-01', '2025-11-15', 'Mỹ', 'Bí ẩn', 'Một người đàn ông tỉnh dậy trong thân phận của người khác.'),
('P024', 'Bước Chân Trên Sao Hỏa', 'Đặng Nhật Minh', 130, '2025-10-10', '2025-11-30', 'Mỹ', 'Khoa học viễn tưởng', 'Phi hành gia đối mặt với sự cô đơn trên sao Hỏa.'),
('P025', 'Ánh Đèn Phố Cổ', 'Phạm Ngọc Quý', 100, '2025-10-15', '2025-12-01', 'Việt Nam', 'Tình cảm', 'Câu chuyện tình nhẹ nhàng giữa phố cổ Hội An.'),
('P026', 'Cuộc Chiến Bất Tận', 'Bùi Văn Long', 135, '2025-10-20', '2025-12-15', 'Mỹ', 'Chiến tranh', 'Những người lính chiến đấu trong cuộc chiến không hồi kết.'),
('P027', 'Cánh Cửa Bí Mật', 'Nguyễn Gia Hân', 108, '2025-10-25', '2025-12-20', 'Anh', 'Phiêu lưu', 'Một cánh cửa dẫn đến thế giới song song đầy kỳ bí.'),
('P028', 'Thời Gian Đóng Băng', 'Trần Minh Tâm', 112, '2025-11-01', '2025-12-25', 'Pháp', 'Giả tưởng', 'Thế giới dừng lại, chỉ một người còn chuyển động.'),
('P029', 'Giấc Ngủ Dài', 'Hoàng Văn Trí', 100, '2025-11-05', '2026-01-01', 'Mỹ', 'Kinh dị', 'Một người bị mắc kẹt trong giấc mơ không thể tỉnh dậy.'),
('P030', 'Người Trở Lại', 'Đỗ Hữu Đức', 95, '2025-11-10', '2026-01-05', 'Việt Nam', 'Bí ẩn', 'Một người mất tích 10 năm bất ngờ quay lại quê hương.'),
('P031', 'Ký Ức Lãng Quên', 'Phan Văn Khôi', 105, '2025-11-15', '2026-01-15', 'Nhật Bản', 'Tâm lý', 'Một cô gái mất trí nhớ tìm lại quá khứ của mình.'),
('P032', 'Ngọn Núi Câm Lặng', 'Nguyễn Quốc Hùng', 120, '2025-11-20', '2026-01-25', 'Trung Quốc', 'Phiêu lưu', 'Một đoàn leo núi đối mặt với bí ẩn siêu nhiên.'),
('P033', 'Mật Danh 47', 'Lê Hữu Tài', 118, '2025-11-25', '2026-02-01', 'Mỹ', 'Hành động', 'Một đặc vụ bí mật mang trong mình nhiệm vụ tối mật.'),
('P034', 'Khuôn Mặt Của Kẻ Giết Người', 'Phạm Đức Hòa', 110, '2025-12-01', '2026-02-10', 'Đức', 'Hình sự', 'Một thám tử truy tìm hung thủ hàng loạt khét tiếng.'),
('P035', 'Đảo Của Những Linh Hồn', 'Trương Lan', 105, '2025-12-05', '2026-02-15', 'Indonesia', 'Kinh dị', 'Hòn đảo nơi linh hồn không thể siêu thoát.'),
('P036', 'Bước Chân Người Hùng', 'Trần Hữu Bình', 125, '2025-12-10', '2026-02-25', 'Mỹ', 'Hành động', 'Một người lính trở thành biểu tượng của lòng dũng cảm.'),
('P037', 'Mùa Xuân Năm Ấy', 'Lê Ngọc Diệp', 97, '2025-12-15', '2026-03-01', 'Việt Nam', 'Tình cảm', 'Câu chuyện tình cảm động trong bối cảnh chiến tranh.'),
('P038', 'Không Gian Vô Tận', 'Vũ Đức Lộc', 128, '2025-12-20', '2026-03-10', 'Mỹ', 'Khoa học viễn tưởng', 'Phi hành đoàn lạc vào chiều không gian khác.'),
('P039', 'Kẻ Gác Đêm', 'Nguyễn Tấn Phú', 102, '2025-12-25', '2026-03-20', 'Anh', 'Bí ẩn', 'Người gác đêm tại bảo tàng phát hiện điều khủng khiếp.'),
('P040', 'Lời Hứa Dưới Trăng', 'Đỗ Minh Châu', 95, '2026-01-01', '2026-03-25', 'Hàn Quốc', 'Lãng mạn', 'Lời hứa giữa hai người trẻ dưới ánh trăng mùa thu.'),
('P041', 'Cánh Đồng Chết', 'Phạm Văn Thành', 120, '2026-01-05', '2026-03-30', 'Campuchia', 'Chiến tranh', 'Bức tranh chân thực về thời kỳ diệt chủng.'),
('P042', 'Sóng Dữ Đại Dương', 'Nguyễn Khánh Toàn', 118, '2026-01-10', '2026-04-05', 'Mỹ', 'Thảm họa', 'Một con tàu du lịch đối mặt với cơn sóng thần khổng lồ.'),
('P043', 'Thị Trấn Ma', 'Hoàng Văn Lợi', 108, '2026-01-15', '2026-04-10', 'Mexico', 'Kinh dị', 'Thị trấn bị bỏ hoang chứa đựng lời nguyền cổ xưa.'),
('P044', 'Ám Ảnh', 'Lê Thanh Bình', 112, '2026-01-20', '2026-04-15', 'Mỹ', 'Tâm lý', 'Một người đàn ông bị ám ảnh bởi tội lỗi trong quá khứ.'),
('P045', 'Người Bảo Vệ Thời Gian', 'Trần Đức Tài', 130, '2026-01-25', '2026-04-20', 'Anh', 'Khoa học viễn tưởng', 'Một người có khả năng du hành qua thời gian để cứu thế giới.'),
('P046', 'Đứa Trẻ Mồ Côi', 'Nguyễn Mai Phương', 100, '2026-02-01', '2026-04-25', 'Việt Nam', 'Tâm lý', 'Câu chuyện cảm động về cậu bé tìm lại gia đình thật.'),
('P047', 'Vòng Xoáy Tội Ác', 'Phan Hoàng', 118, '2026-02-05', '2026-05-01', 'Mỹ', 'Hình sự', 'Một vụ án kéo theo chuỗi tội ác kinh hoàng.'),
('P048', 'Thiên Đường Giả Tạo', 'Đặng Bảo Ngọc', 110, '2026-02-10', '2026-05-05', 'Nhật Bản', 'Khoa học viễn tưởng', 'Thế giới ảo nơi con người đánh mất bản thân.'),
('P049', 'Ánh Sáng Cuối Đường Hầm', 'Lưu Thanh Sơn', 122, '2026-02-15', '2026-05-10', 'Pháp', 'Tâm lý', 'Một người tù tìm thấy hi vọng trong những ngày cuối đời.'),
('P050', 'Người Trong Gương', 'Ngô Hải Long', 105, '2026-02-20', '2026-05-15', 'Mỹ', 'Kinh dị', 'Chiếc gương cổ phản chiếu điều mà không ai muốn thấy.');


INSERT INTO NhanVien (MaNhanVien, HoTen, MatKhau, NgaySinh, DiaChi, SDT, Email, GioiTinh, ChucVu, Luong) VALUES
('NV001', 'Nguyễn Văn An', '123456', '1995-03-10', 'Hà Nội', '0912345678', 'an.nguyen@example.com', 0, 0, 10000000),
('NV002', 'Trần Thị Bình', '123456', '1997-06-21', 'TP.HCM', '0987654321', 'binh.tran@example.com', 1, 0, 9500000),
('NV003', 'Lê Văn Cường', '123456', '1992-01-15', 'Đà Nẵng', '0905123456', 'cuong.le@example.com', 0, 1, 15000000),
('NV004', 'Phạm Thị Dung', '123456', '1998-09-30', 'Hải Phòng', '0938765432', 'dung.pham@example.com', 1, 0, 9200000),
('NV005', 'Hoàng Văn Đức', '123456', '1990-11-05', 'Nha Trang', '0978234561', 'duc.hoang@example.com', 0, 1, 14500000),
('NV006', 'Vũ Thị Hằng', '123456', '1996-04-12', 'Huế', '0945126789', 'hang.vu@example.com', 1, 0, 8800000),
('NV007', 'Ngô Văn Hiếu', '123456', '1993-02-18', 'Cần Thơ', '0921345678', 'hieu.ngo@example.com', 0, 0, 9700000),
('NV008', 'Đặng Thị Lan', '123456', '1999-07-09', 'Hà Nam', '0918762345', 'lan.dang@example.com', 1, 0, 9000000),
('NV009', 'Bùi Văn Minh', '123456', '1994-12-22', 'Bắc Ninh', '0934578123', 'minh.bui@example.com', 0, 1, 15500000),
('NV010', 'Phan Thị Ngọc', '123456', '1998-05-14', 'TP.HCM', '0902789456', 'ngoc.phan@example.com', 1, 0, 9100000);


INSERT INTO PhongChieu (MaPhong, SoGheTrong) VALUES
('PH001', 25),
('PH002', 25),
('PH003', 25),
('PH004', 25),
('PH005', 25),
('PH006', 25);

INSERT INTO SuatChieu (MaSuatChieu, MaPhim, MaPhong, NgayKhoiChieu, ThoiGianBatDau, HinhThucChieu, TrangThai) VALUES
('SC001', 'P001', 'PH001', '2025-11-10', '09:00:00', '2D', 1),
('SC002', 'P002', 'PH002', '2025-11-10', '11:30:00', '3D', 0),
('SC003', 'P003', 'PH003', '2025-11-11', '13:00:00', '2D', 1),
('SC004', 'P004', 'PH001', '2025-11-11', '15:30:00', 'IMAX', 0),
('SC005', 'P005', 'PH002', '2025-11-12', '18:00:00', '2D', 1),
('SC006', 'P006', 'PH003', '2025-11-12', '20:30:00', '3D', 1);

INSERT INTO VePhim (MaVePhim, TenGhe, MaPhong, MaSuatChieu) VALUES
('VP001', 'A1', 'PH001', 'SC001'),
('VP002', 'A2', 'PH001', 'SC001'),
('VP003', 'B1', 'PH002', 'SC002'),
('VP004', 'B2', 'PH002', 'SC002'),
('VP005', 'C1', 'PH003', 'SC003'),
('VP006', 'C2', 'PH003', 'SC003'),
('VP007', 'D1', 'PH001', 'SC004'),
('VP008', 'D2', 'PH001', 'SC004'),
('VP009', 'E1', 'PH002', 'SC005'),
('VP010', 'E2', 'PH002', 'SC005'),
('VP011', 'F1', 'PH003', 'SC006'),
('VP012', 'F2', 'PH003', 'SC006'),
('VP013', 'G1', 'PH001', 'SC001'),
('VP014', 'G2', 'PH001', 'SC002'),
('VP015', 'H1', 'PH002', 'SC003'),
('VP016', 'H2', 'PH002', 'SC004'),
('VP017', 'I1', 'PH003', 'SC005'),
('VP018', 'I2', 'PH003', 'SC006'),
('VP019', 'J1', 'PH001', 'SC003'),
('VP020', 'J2', 'PH001', 'SC004');

SELECT MaSuatChieu FROM SuatChieu;

INSERT INTO KhachHang (MaKhachHang, HoTen, NgaySinh, DiaChi, SDT, Email) VALUES
('KH001', 'Nguyễn Văn An', '1995-03-10', 'Hà Nội', '0901234567', 'an.nguyen@example.com'),
('KH002', 'Trần Thị Bình', '1998-07-12', 'TP.HCM', '0912345678', 'binh.tran@example.com'),
('KH003', 'Lê Văn Cường', '1992-01-05', 'Đà Nẵng', '0923456789', 'cuong.le@example.com'),
('KH004', 'Phạm Thị Dung', '1999-09-20', 'Hải Phòng', '0934567890', 'dung.pham@example.com'),
('KH005', 'Hoàng Văn Đức', '1990-11-15', 'Nha Trang', '0945678901', 'duc.hoang@example.com'),
('KH006', 'Vũ Thị Hằng', '1996-04-02', 'Huế', '0956789012', 'hang.vu@example.com'),
('KH007', 'Ngô Văn Hiếu', '1993-02-18', 'Cần Thơ', '0967890123', 'hieu.ngo@example.com'),
('KH008', 'Đặng Thị Lan', '1999-07-09', 'Hà Nam', '0978901234', 'lan.dang@example.com'),
('KH009', 'Bùi Văn Minh', '1994-12-22', 'Bắc Ninh', '0989012345', 'minh.bui@example.com'),
('KH010', 'Phan Thị Ngọc', '1998-05-14', 'TP.HCM', '0990123456', 'ngoc.phan@example.com'),
('KH011', 'Trịnh Văn Sơn', '1991-03-12', 'Hải Dương', '0902345678', 'son.trinh@example.com'),
('KH012', 'Đỗ Thị Yến', '1997-08-25', 'Hà Nội', '0913456789', 'yen.do@example.com'),
('KH013', 'Phùng Văn Hòa', '1995-11-09', 'Bình Dương', '0924567890', 'hoa.phung@example.com'),
('KH014', 'Lâm Thị Thảo', '1999-02-18', 'Đà Lạt', '0935678901', 'thao.lam@example.com'),
('KH015', 'Trương Minh Tâm', '1993-07-07', 'Huế', '0946789012', 'tam.truong@example.com'),
('KH016', 'Phạm Văn Lợi', '1990-01-30', 'Quảng Nam', '0957890123', 'loi.pham@example.com'),
('KH017', 'Nguyễn Thị Kim', '1994-06-14', 'TP.HCM', '0968901234', 'kim.nguyen@example.com'),
('KH018', 'Lê Minh Hoàng', '1992-09-03', 'Nha Trang', '0979012345', 'hoang.le@example.com'),
('KH019', 'Võ Thị Hoa', '1998-10-29', 'Đồng Nai', '0980123456', 'hoa.vo@example.com'),
('KH020', 'Phan Văn Hùng', '1996-12-25', 'Long An', '0991234567', 'hung.phan@example.com'),
('KH021', 'Nguyễn Thị Hạnh', '1997-05-08', 'Hà Tĩnh', '0902345789', 'hanh.nguyen@example.com'),
('KH022', 'Đỗ Văn Khánh', '1993-03-17', 'Thái Bình', '0913456890', 'khanh.do@example.com'),
('KH023', 'Lê Thị Thu', '1999-11-05', 'Hải Phòng', '0924567901', 'thu.le@example.com'),
('KH024', 'Phạm Văn Tùng', '1995-08-22', 'Hà Nội', '0935678012', 'tung.pham@example.com'),
('KH025', 'Trần Thị Hương', '1998-02-02', 'TP.HCM', '0946789123', 'huong.tran@example.com'),
('KH026', 'Ngô Văn Phú', '1991-10-13', 'Đà Nẵng', '0957890234', 'phu.ngo@example.com'),
('KH027', 'Vũ Thị Mai', '1996-06-20', 'Nam Định', '0968901345', 'mai.vu@example.com'),
('KH028', 'Bùi Văn Kiên', '1994-01-01', 'Bắc Giang', '0979012456', 'kien.bui@example.com'),
('KH029', 'Phan Thị Trang', '1999-03-15', 'Hà Nội', '0980123567', 'trang.phan@example.com'),
('KH030', 'Hoàng Văn Tú', '1992-12-12', 'Thanh Hóa', '0991234678', 'tu.hoang@example.com'),
('KH031', 'Nguyễn Thị Quyên', '1997-07-19', 'Nghệ An', '0902345790', 'quyen.nguyen@example.com'),
('KH032', 'Lê Văn Hải', '1995-05-05', 'Huế', '0913456901', 'hai.le@example.com'),
('KH033', 'Phạm Thị Nhung', '1998-09-21', 'TP.HCM', '0924567012', 'nhung.pham@example.com'),
('KH034', 'Trần Văn Khôi', '1993-02-09', 'Đồng Nai', '0935678123', 'khoi.tran@example.com'),
('KH035', 'Đặng Thị Nga', '1999-10-18', 'Cần Thơ', '0946789234', 'nga.dang@example.com'),
('KH036', 'Hoàng Văn Lâm', '1990-04-28', 'Hà Nội', '0957890345', 'lam.hoang@example.com'),
('KH037', 'Nguyễn Thị Hòa', '1994-08-11', 'Đà Lạt', '0968901456', 'hoa.nguyen@example.com'),
('KH038', 'Võ Văn Khang', '1992-06-16', 'Nha Trang', '0979012567', 'khang.vo@example.com'),
('KH039', 'Phan Thị Duyên', '1997-01-23', 'Bình Dương', '0980123678', 'duyen.phan@example.com'),
('KH040', 'Bùi Văn Toàn', '1996-03-30', 'Hà Nội', '0991234789', 'toan.bui@example.com'),
('KH041', 'Trần Thị Oanh', '1999-11-07', 'TP.HCM', '0902345890', 'oanh.tran@example.com'),
('KH042', 'Ngô Văn Tâm', '1991-05-15', 'Hải Dương', '0913456902', 'tam.ngo@example.com'),
('KH043', 'Phạm Thị Hậu', '1998-09-29', 'Huế', '0924567013', 'hau.pham@example.com'),
('KH044', 'Lê Văn Long', '1993-10-12', 'Đà Nẵng', '0935678124', 'long.le@example.com'),
('KH045', 'Đỗ Thị Vân', '1995-04-06', 'Cần Thơ', '0946789235', 'van.do@example.com'),
('KH046', 'Nguyễn Văn Toản', '1990-02-14', 'Bắc Giang', '0957890346', 'toan.nguyen@example.com'),
('KH047', 'Phan Thị Ly', '1997-07-25', 'Nghệ An', '0968901457', 'ly.phan@example.com'),
('KH048', 'Bùi Văn Hưng', '1994-08-08', 'Hà Nội', '0979012568', 'hung.bui@example.com'),
('KH049', 'Đặng Thị Hồng', '1999-05-03', 'TP.HCM', '0980123679', 'hong.dang@example.com'),
('KH050', 'Lâm Văn Phước', '1992-12-28', 'Đà Lạt', '0991234790', 'phuoc.lam@example.com');

INSERT INTO HoaDon (MaHoaDon, NgayLapHoaDon, TenNhanVien, MaNhanVien, MaKhachHang, TongTien) VALUES
('HD001', '2025-01-02 09:12:00', 'Nguyễn Văn An', 'NV001', 'KH001', 180000),
('HD002', '2025-01-03 10:25:00', 'Trần Thị Bình', 'NV002', 'KH002', 250000),
('HD003', '2025-01-04 15:10:00', 'Lê Văn Cường', 'NV003', 'KH003', 300000),
('HD004', '2025-01-05 18:30:00', 'Phạm Thị Dung', 'NV004', 'KH004', 220000),
('HD005', '2025-01-06 20:15:00', 'Hoàng Văn Đức', 'NV005', 'KH005', 340000),
('HD006', '2025-01-07 11:00:00', 'Vũ Thị Hằng', 'NV006', 'KH006', 270000),
('HD007', '2025-01-08 13:20:00', 'Ngô Văn Hiếu', 'NV007', 'KH007', 190000),
('HD008', '2025-01-09 14:45:00', 'Đặng Thị Lan', 'NV008', 'KH008', 310000),
('HD009', '2025-01-10 16:30:00', 'Bùi Văn Minh', 'NV009', 'KH009', 230000),
('HD010', '2025-01-11 19:10:00', 'Phan Thị Ngọc', 'NV010', 'KH010', 295000),
('HD011', '2025-01-12 08:55:00', 'Nguyễn Văn An', 'NV001', 'KH011', 205000),
('HD012', '2025-01-13 09:45:00', 'Trần Thị Bình', 'NV002', 'KH012', 240000),
('HD013', '2025-01-14 14:10:00', 'Lê Văn Cường', 'NV003', 'KH013', 260000),
('HD014', '2025-01-15 17:30:00', 'Phạm Thị Dung', 'NV004', 'KH014', 320000),
('HD015', '2025-01-16 20:20:00', 'Hoàng Văn Đức', 'NV005', 'KH015', 175000),
('HD016', '2025-01-17 10:35:00', 'Vũ Thị Hằng', 'NV006', 'KH016', 345000),
('HD017', '2025-01-18 15:00:00', 'Ngô Văn Hiếu', 'NV007', 'KH017', 215000),
('HD018', '2025-01-19 12:15:00', 'Đặng Thị Lan', 'NV008', 'KH018', 290000),
('HD019', '2025-01-20 18:45:00', 'Bùi Văn Minh', 'NV009', 'KH019', 305000),
('HD020', '2025-01-21 11:50:00', 'Phan Thị Ngọc', 'NV010', 'KH020', 270000),
('HD021', '2025-01-22 09:25:00', 'Nguyễn Văn An', 'NV001', 'KH021', 230000),
('HD022', '2025-01-23 14:15:00', 'Trần Thị Bình', 'NV002', 'KH022', 315000),
('HD023', '2025-01-24 17:00:00', 'Lê Văn Cường', 'NV003', 'KH023', 260000),
('HD024', '2025-01-25 10:45:00', 'Phạm Thị Dung', 'NV004', 'KH024', 210000),
('HD025', '2025-01-26 20:10:00', 'Hoàng Văn Đức', 'NV005', 'KH025', 355000),
('HD026', '2025-01-27 15:30:00', 'Vũ Thị Hằng', 'NV006', 'KH026', 245000),
('HD027', '2025-01-28 13:10:00', 'Ngô Văn Hiếu', 'NV007', 'KH027', 325000),
('HD028', '2025-01-29 09:55:00', 'Đặng Thị Lan', 'NV008', 'KH028', 180000),
('HD029', '2025-01-30 16:15:00', 'Bùi Văn Minh', 'NV009', 'KH029', 305000),
('HD030', '2025-01-31 19:40:00', 'Phan Thị Ngọc', 'NV010', 'KH030', 195000),
('HD031', '2025-02-01 09:30:00', 'Nguyễn Văn An', 'NV001', 'KH031', 275000),
('HD032', '2025-02-02 15:10:00', 'Trần Thị Bình', 'NV002', 'KH032', 320000),
('HD033', '2025-02-03 18:50:00', 'Lê Văn Cường', 'NV003', 'KH033', 190000),
('HD034', '2025-02-04 11:40:00', 'Phạm Thị Dung', 'NV004', 'KH034', 340000),
('HD035', '2025-02-05 08:25:00', 'Hoàng Văn Đức', 'NV005', 'KH035', 295000),
('HD036', '2025-02-06 13:05:00', 'Vũ Thị Hằng', 'NV006', 'KH036', 215000),
('HD037', '2025-02-07 19:00:00', 'Ngô Văn Hiếu', 'NV007', 'KH037', 230000),
('HD038', '2025-02-08 12:20:00', 'Đặng Thị Lan', 'NV008', 'KH038', 350000),
('HD039', '2025-02-09 17:15:00', 'Bùi Văn Minh', 'NV009', 'KH039', 310000),
('HD040', '2025-02-10 10:50:00', 'Phan Thị Ngọc', 'NV010', 'KH040', 280000),
('HD041', '2025-02-11 09:10:00', 'Nguyễn Văn An', 'NV001', 'KH041', 210000),
('HD042', '2025-02-12 11:45:00', 'Trần Thị Bình', 'NV002', 'KH042', 260000),
('HD043', '2025-02-13 15:30:00', 'Lê Văn Cường', 'NV003', 'KH043', 205000),
('HD044', '2025-02-14 18:25:00', 'Phạm Thị Dung', 'NV004', 'KH044', 325000),
('HD045', '2025-02-15 20:35:00', 'Hoàng Văn Đức', 'NV005', 'KH045', 285000),
('HD046', '2025-02-16 10:00:00', 'Vũ Thị Hằng', 'NV006', 'KH046', 195000),
('HD047', '2025-02-17 09:35:00', 'Ngô Văn Hiếu', 'NV007', 'KH047', 265000),
('HD048', '2025-02-18 19:50:00', 'Đặng Thị Lan', 'NV008', 'KH048', 330000),
('HD049', '2025-02-19 13:40:00', 'Bùi Văn Minh', 'NV009', 'KH049', 185000),
('HD050', '2025-02-20 17:05:00', 'Phan Thị Ngọc', 'NV010', 'KH050', 245000),
('HD051', '2025-02-21 09:25:00', 'Nguyễn Văn An', 'NV001', 'KH001', 270000),
('HD052', '2025-02-22 11:10:00', 'Trần Thị Bình', 'NV002', 'KH002', 330000),
('HD053', '2025-02-23 15:55:00', 'Lê Văn Cường', 'NV003', 'KH003', 205000),
('HD054', '2025-02-24 18:35:00', 'Phạm Thị Dung', 'NV004', 'KH004', 350000),
('HD055', '2025-02-25 10:15:00', 'Hoàng Văn Đức', 'NV005', 'KH005', 295000),
('HD056', '2025-02-26 14:50:00', 'Vũ Thị Hằng', 'NV006', 'KH006', 275000),
('HD057', '2025-02-27 12:05:00', 'Ngô Văn Hiếu', 'NV007', 'KH007', 240000),
('HD058', '2025-02-28 20:25:00', 'Đặng Thị Lan', 'NV008', 'KH008', 290000),
('HD059', '2025-03-01 09:45:00', 'Bùi Văn Minh', 'NV009', 'KH009', 310000),
('HD060', '2025-03-02 13:35:00', 'Phan Thị Ngọc', 'NV010', 'KH010', 250000),
('HD061', '2025-03-03 18:00:00', 'Nguyễn Văn An', 'NV001', 'KH011', 335000),
('HD062', '2025-03-04 11:20:00', 'Trần Thị Bình', 'NV002', 'KH012', 190000),
('HD063', '2025-03-05 14:30:00', 'Lê Văn Cường', 'NV003', 'KH013', 310000),
('HD064', '2025-03-06 17:45:00', 'Phạm Thị Dung', 'NV004', 'KH014', 230000),
('HD065', '2025-03-07 09:50:00', 'Hoàng Văn Đức', 'NV005', 'KH015', 260000),
('HD066', '2025-03-08 15:10:00', 'Vũ Thị Hằng', 'NV006', 'KH016', 185000),
('HD067', '2025-03-09 10:40:00', 'Ngô Văn Hiếu', 'NV007', 'KH017', 205000),
('HD068', '2025-03-10 19:25:00', 'Đặng Thị Lan', 'NV008', 'KH018', 245000),
('HD069', '2025-03-11 08:50:00', 'Bùi Văn Minh', 'NV009', 'KH019', 270000),
('HD070', '2025-03-12 13:00:00', 'Phan Thị Ngọc', 'NV010', 'KH020', 295000),
('HD071', '2025-03-13 09:25:00', 'Nguyễn Văn An', 'NV001', 'KH021', 310000),
('HD072', '2025-03-14 11:35:00', 'Trần Thị Bình', 'NV002', 'KH022', 215000),
('HD073', '2025-03-15 17:45:00', 'Lê Văn Cường', 'NV003', 'KH023', 340000),
('HD074', '2025-03-16 10:10:00', 'Phạm Thị Dung', 'NV004', 'KH024', 280000),
('HD075', '2025-03-17 19:40:00', 'Hoàng Văn Đức', 'NV005', 'KH025', 295000),
('HD076', '2025-03-18 14:50:00', 'Vũ Thị Hằng', 'NV006', 'KH026', 235000),
('HD077', '2025-03-19 10:25:00', 'Ngô Văn Hiếu', 'NV007', 'KH027', 305000),
('HD078', '2025-03-20 16:15:00', 'Đặng Thị Lan', 'NV008', 'KH028', 220000),
('HD079', '2025-03-21 19:35:00', 'Bùi Văn Minh', 'NV009', 'KH029', 325000),
('HD080', '2025-03-22 12:20:00', 'Phan Thị Ngọc', 'NV010', 'KH030', 250000);

INSERT INTO ChiTietHoaDon_VePhim (MaHoaDon, MaVePhim, DonGia) VALUES
('HD001', 'VP001', 180000),
('HD002', 'VP002', 250000),
('HD003', 'VP003', 300000),
('HD004', 'VP004', 220000),
('HD005', 'VP005', 340000),
('HD006', 'VP006', 270000),
('HD007', 'VP007', 190000),
('HD008', 'VP008', 310000),
('HD009', 'VP009', 230000),
('HD010', 'VP010', 295000),
('HD011', 'VP011', 205000),
('HD012', 'VP012', 240000),
('HD013', 'VP013', 260000),
('HD014', 'VP014', 320000),
('HD015', 'VP015', 175000),
('HD016', 'VP016', 345000),
('HD017', 'VP017', 215000),
('HD018', 'VP018', 290000),
('HD019', 'VP019', 305000),
('HD020', 'VP020', 270000),
('HD021', 'VP001', 230000),
('HD022', 'VP002', 315000),
('HD023', 'VP003', 260000),
('HD024', 'VP004', 210000),
('HD025', 'VP005', 355000),
('HD026', 'VP006', 245000),
('HD027', 'VP007', 325000),
('HD028', 'VP008', 180000),
('HD029', 'VP009', 305000),
('HD030', 'VP010', 195000),
('HD031', 'VP011', 275000),
('HD032', 'VP012', 320000),
('HD033', 'VP013', 190000),
('HD034', 'VP014', 340000),
('HD035', 'VP015', 295000),
('HD036', 'VP016', 215000),
('HD037', 'VP017', 230000),
('HD038', 'VP018', 350000),
('HD039', 'VP019', 310000),
('HD040', 'VP020', 280000),
('HD041', 'VP001', 210000),
('HD042', 'VP002', 260000),
('HD043', 'VP003', 205000),
('HD044', 'VP004', 325000),
('HD045', 'VP005', 285000),
('HD046', 'VP006', 195000),
('HD047', 'VP007', 265000),
('HD048', 'VP008', 330000),
('HD049', 'VP009', 185000),
('HD050', 'VP010', 245000),
('HD051', 'VP011', 270000),
('HD052', 'VP012', 330000),
('HD053', 'VP013', 205000),
('HD054', 'VP014', 350000),
('HD055', 'VP015', 295000),
('HD056', 'VP016', 275000),
('HD057', 'VP017', 240000),
('HD058', 'VP018', 290000),
('HD059', 'VP019', 310000),
('HD060', 'VP020', 250000),
('HD061', 'VP001', 335000),
('HD062', 'VP002', 190000),
('HD063', 'VP003', 310000),
('HD064', 'VP004', 230000),
('HD065', 'VP005', 260000),
('HD066', 'VP006', 185000),
('HD067', 'VP007', 205000),
('HD068', 'VP008', 245000),
('HD069', 'VP009', 270000),
('HD070', 'VP010', 295000),
('HD071', 'VP011', 310000),
('HD072', 'VP012', 215000),
('HD073', 'VP013', 340000),
('HD074', 'VP014', 280000),
('HD075', 'VP015', 295000),
('HD076', 'VP016', 235000),
('HD077', 'VP017', 305000),
('HD078', 'VP018', 220000),
('HD079', 'VP019', 325000),
('HD080', 'VP020', 250000);



SELECT sc.MaSuatChieu, sc.MaPhim, sc.MaPhong, p.TenPhim, sc.NgayKhoiChieu, sc.HinhThucChieu, sc.ThoiGianBatDau, p.ThoiLuong, ph.SoGheTrong
FROM SuatChieu sc 
JOIN Phim p ON sc.MaPhim = p.MaPhim 
JOIN PhongChieu ph ON sc.MaPhong = ph.MaPhong

SELECT sc.MaSuatChieu, sc.MaPhim, sc.MaPhong, p.TenPhim, sc.NgayKhoiChieu, sc.HinhThucChieu, sc.ThoiGianBatDau, p.ThoiLuong, ph.SoGheTrong 
FROM SuatChieu sc 
JOIN Phim p ON sc.MaPhim = p.MaPhim 
JOIN PhongChieu ph ON sc.MaPhong = ph.MaPhong 
 WHERE p.TenPhim = 'Bóng Ma Trong Thành Phố'
 select * from VePhim
 select * from SuatChieu
 select * from ChiTietHoaDon_VePhim
 /* Store procedure NhanVien*/
CREATE PROCEDURE sp_layAllNhanVien
AS
BEGIN
    SELECT * FROM NhanVien;
END;

CREATE PROCEDURE sp_themNhanVien(
    @maNhanVien NVARCHAR(50),
    @HoTen NVARCHAR(150),
	@MatKhau NVARCHAR(50),
    @NgaySinh DATETIME,
    @DiaChi NVARCHAR(255),
    @SDT NVARCHAR(15),
    @Email NVARCHAR(100),
    @GioiTinh BIT,
    @ChucVu BIT,
    @Luong MONEY)
AS
BEGIN
    INSERT INTO NhanVien VALUES (@maNhanVien, @HoTen, @MatKhau, @NgaySinh, @DiaChi, @SDT, @Email, @GioiTinh, @ChucVu, @Luong);
END;

CREATE PROCEDURE sp_capNhatNhanVien(
    @HoTen NVARCHAR(150),
	@MatKhau NVARCHAR(50),
    @NgaySinh DATETIME,
    @DiaChi NVARCHAR(255),
    @SDT NVARCHAR(15),
    @Email NVARCHAR(100),
    @GioiTinh BIT,
    @ChucVu BIT,
    @Luong MONEY,
	@maNhanVien NVARCHAR(50))
AS
BEGIN
    UPDATE NhanVien
    SET HoTen = @HoTen,
        MatKhau = @MatKhau,
        NgaySinh = @NgaySinh,
        DiaChi = @DiaChi,
        SDT = @SDT,
        Email = @Email,
        GioiTinh = @GioiTinh,
        ChucVu = @ChucVu,
        Luong = @Luong
    WHERE MaNhanVien = @MaNhanVien;
END;

CREATE PROCEDURE sp_xoaNhanVien(
    @MaNhanVien NVARCHAR(50)
)
AS
BEGIN
    DELETE FROM NhanVien
    WHERE MaNhanVien = @MaNhanVien;
END;

CREATE PROCEDURE sp_timNhanVienTheoMa(
    @MaNhanVien NVARCHAR(50)
)
AS
BEGIN
    SELECT * FROM NhanVien
    WHERE MaNhanVien = @MaNhanVien;
END;

CREATE PROCEDURE sp_timNhanVienTheoTen(
    @HoTen NVARCHAR(150)
)
AS
BEGIN
    SELECT * FROM NhanVien
    WHERE HoTen LIKE @HoTen;
END;

CREATE PROCEDURE sp_sapXepNhanVienTheoTen
AS
BEGIN
    SELECT * FROM NhanVien
    ORDER BY HoTen;
END;

CREATE PROCEDURE sp_sapXepNhanVienTheoLuong
AS
BEGIN
    SELECT * FROM NhanVien
    ORDER BY Luong;
END;

/*Viet Store producer cho KhachHang*/
CREATE PROCEDURE sp_layAllKhachHang
AS
BEGIN
    SELECT * FROM KhachHang;
END;

CREATE PROCEDURE sp_themKhachHang(
    @MaKhachHang NVARCHAR(50),
    @HoTen NVARCHAR(150),
    @NgaySinh DATETIME,
    @DiaChi NVARCHAR(255),
    @SDT NVARCHAR(15),
    @Email NVARCHAR(100))
AS
BEGIN
    INSERT INTO KhachHang VALUES (@MaKhachHang, @HoTen, @NgaySinh, @DiaChi, @SDT, @Email);
END;

CREATE PROCEDURE sp_capNhatKhachHang(
    @HoTen NVARCHAR(150),
    @NgaySinh DATETIME,
    @DiaChi NVARCHAR(255),
    @SDT NVARCHAR(15),
    @Email NVARCHAR(100),
	@MaKhachHang NVARCHAR(50))
AS
BEGIN
    UPDATE KhachHang
    SET HoTen = @HoTen,
        NgaySinh = @NgaySinh,
        DiaChi = @DiaChi,
        SDT = @SDT,
        Email = @Email
    WHERE MaKhachHang = @MaKhachHang;
END;

CREATE PROCEDURE sp_xoaKhachHang(
    @MaKhachHang NVARCHAR(50)
)
AS
BEGIN
    DELETE FROM KhachHang
    WHERE MaKhachHang = @MaKhachHang;
END;

CREATE PROCEDURE sp_timKhachHangTheoMa(
    @MaKhachHang NVARCHAR(50)
)
AS
BEGIN
    SELECT * FROM KhachHang
    WHERE MaKhachHang = @MaKhachHang;
END;

CREATE PROCEDURE sp_timKhachHangTheoTen(
    @HoTen NVARCHAR(150)
)
AS
BEGIN
    SELECT * FROM KhachHang
    WHERE HoTen LIKE @HoTen;
END;

CREATE PROCEDURE sp_sapXepKhachHangTheoMa
AS
BEGIN
    SELECT * FROM KhachHang
    ORDER BY MaKhachHang;
END;

CREATE PROCEDURE sp_sapXepKhachHangTheoTen
AS
BEGIN
    SELECT * FROM KhachHang
    ORDER BY HoTen;
END;

/*Viet Store producer cho Phim*/
CREATE PROCEDURE sp_layAllPhim
AS
BEGIN
    SELECT * FROM Phim;
END;

CREATE PROCEDURE sp_themPhim(
    @MaPhim NVARCHAR(50),
    @TenPhim NVARCHAR(150),
	@DaoDien VARCHAR(100),
	@ThoiLuong INT,
    @NgaybatDau DATETIME,
	@NgayKetThuc DATETIME,
	@QuocGia NVARCHAR(100),
	@TheLoai NVARCHAR(200),
	@MoTa TEXT)
AS
BEGIN
    INSERT INTO Phim VALUES ( @MaPhim, @TenPhim, @DaoDien, @ThoiLuong, @NgaybatDau, @NgayKetThuc, @QuocGia, @TheLoai, @MoTa);
END;

CREATE PROCEDURE sp_capNhatPhim(
    @TenPhim NVARCHAR(150),
	@DaoDien VARCHAR(100),
	@ThoiLuong INT,
    @NgaybatDau DATETIME,
	@NgayKetThuc DATETIME,
	@QuocGia NVARCHAR(100),
	@TheLoai NVARCHAR(200),
	@MoTa TEXT,
	@MaPhim NVARCHAR(50))
AS
BEGIN
    UPDATE Phim
    SET TenPhim = @TenPhim,
        DaoDien = @DaoDien,
        ThoiLuong = @ThoiLuong,
        NgaybatDau = @NgaybatDau,
        NgayKetThuc = @NgayKetThuc,
		QuocGia = @QuocGia,
		TheLoai = @TheLoai,
		MoTa = @MoTa
    WHERE MaPhim = @MaPhim;
END;

CREATE PROCEDURE sp_xoaPhim(
    @MaPhim NVARCHAR(50)
)
AS
BEGIN
    DELETE FROM Phim
    WHERE MaPhim = @MaPhim;
END;

CREATE PROCEDURE sp_timPhimTheoNgayChieu(
    @NgaybatDau DATETIME,
	@NgayKetThuc DATETIME
)
AS
BEGIN
    SELECT * FROM Phim
    WHERE NgayBatDau >= @NgaybatDau AND NgayKetThuc <= @NgayKetThuc;
END;

CREATE PROCEDURE sp_timPhimTheoTen(
    @TenPhim NVARCHAR(150)
)
AS
BEGIN
    SELECT * FROM Phim
    WHERE TenPhim LIKE @TenPhim;
END;

CREATE PROCEDURE sp_sapXepPhimTheoMa
AS
BEGIN
    SELECT * FROM Phim
    ORDER BY MaPhim;
END;

CREATE PROCEDURE sp_sapXepPhimTheoTen
AS
BEGIN
    SELECT * FROM Phim
    ORDER BY TenPhim;
END;