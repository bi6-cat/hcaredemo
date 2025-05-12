--Insert Roles 
INSERT INTO roles (id, name) VALUES
(NEWID(), 'ADMIN'),
(NEWID(), 'DOCTOR'),
(NEWID(), 'PATIENT');

-- Insert Hospitals
INSERT INTO hospitals (id, name, address, phone, email, website, description, created_at, updated_at) VALUES
(NEWID(), N'Bệnh viện Bạch Mai', N'78 Giải Phóng, Phương Mai, Đống Đa, Hà Nội', '024 3869 3731', 'info@bachmai.gov.vn', 'bachmai.gov.vn', N'Bệnh viện đa khoa hạng đặc biệt, tuyến trung ương', GETDATE(), GETDATE()),
(NEWID(), N'Bệnh viện Việt Đức', N'40 Tràng Thi, Hàng Bông, Hoàn Kiếm, Hà Nội', '024 3825 3531', 'info@vietduchospital.vn', 'vietduchospital.vn', N'Bệnh viện chuyên khoa ngoại hạng đặc biệt', GETDATE(), GETDATE()),
(NEWID(), N'Bệnh viện Thanh Nhàn', N'42 Thanh Nhàn, Hai Bà Trưng, Hà Nội', '024 3869 2131', 'info@thanhnhan.vn', 'bvthanhnhan.vn', N'Bệnh viện đa khoa hạng I của Hà Nội', GETDATE(), GETDATE()),
(NEWID(), N'Bệnh viện E Hà Nội', N'89 Trần Cung, Nghĩa Tân, Cầu Giấy, Hà Nội', '024 3754 3560', 'contact@benhviene.vn', 'benhviene.vn', N'Bệnh viện đa khoa tuyến trung ương', GETDATE(), GETDATE()),
(NEWID(), N'Bệnh viện Đại học Y Hà Nội', N'1 Tôn Thất Tùng, Kim Liên, Đống Đa, Hà Nội', '024 3574 7788', 'info@bvdhyduoc.vn', 'bvdhyduoc.vn', N'Bệnh viện đào tạo thuộc Đại học Y Hà Nội', GETDATE(), GETDATE()),
(NEWID(), N'Bệnh viện Tim Hà Nội', N'92 Trần Hưng Đạo, Hoàn Kiếm, Hà Nội', '024 3821 5230', 'info@benhvientimhanoi.vn', 'benhvientimhanoi.vn', N'Bệnh viện chuyên khoa tim mạch', GETDATE(), GETDATE()),
(NEWID(), N'Bệnh viện 198 Bộ Công An', N'9 Trần Bình, Mai Dịch, Cầu Giấy, Hà Nội', '024 3868 3407', 'info@bv198.vn', 'bv198.vn', N'Bệnh viện đa khoa của Bộ Công An', GETDATE(), GETDATE()),
(NEWID(), N'Bệnh viện Phụ Sản Hà Nội', N'929 La Thành, Ngọc Khánh, Ba Đình, Hà Nội', '024 3835 5474', 'info@phusan.vn', 'phusan.vn', N'Bệnh viện chuyên khoa sản phụ khoa', GETDATE(), GETDATE()),
(NEWID(), N'Bệnh viện Hữu Nghị', N'1 Trần Khánh Dư, Bạch Đằng, Hai Bà Trưng, Hà Nội', '024 3826 6486', 'info@huunghi.vn', 'huunghi.vn', N'Bệnh viện đa khoa tuyến trung ương', GETDATE(), GETDATE()),
(NEWID(), N'Bệnh viện Đa khoa Xanh Pôn', N'12 Chu Văn An, Ba Đình, Hà Nội', '024 3823 4937', 'info@xanhpon.vn', 'xanhpon.vn', N'Bệnh viện đa khoa hạng I của Hà Nội', GETDATE(), GETDATE());

-- Insert Departments for Bach Mai Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(NEWID(), N'Khoa Nội Tim mạch', N'Chuyên khoa về bệnh lý tim mạch', N'PGS.TS Phạm Mạnh Hùng', '024 3869 3731', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Thần kinh', N'Điều trị các bệnh lý thần kinh', N'GS.TS Nguyễn Văn Thông', '024 3869 3732', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Hồi sức tích cực', N'Điều trị các ca bệnh nặng', N'PGS.TS Nguyễn Thị Tuyến', '024 3869 3733', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Nội tiết', N'Điều trị bệnh đái tháo đường và nội tiết', N'GS.TS Tạ Văn Bình', '024 3869 3734', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Ngoại Tổng hợp', N'Phẫu thuật và điều trị ngoại khoa', N'PGS.TS Nguyễn Quang Nghĩa', '024 3869 3735', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Cấp cứu', N'Xử lý các trường hợp cấp cứu', N'TS.BS Nguyễn Đức Chính', '024 3869 3736', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'), GETDATE(), GETDATE());

-- Insert Departments for Viet Duc Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(NEWID(), N'Khoa Phẫu thuật Tim mạch', N'Phẫu thuật tim và mạch máu', N'GS.TS Lê Ngọc Thành', '024 3825 3532', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Việt Đức'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Phẫu thuật Thần kinh', N'Phẫu thuật não và cột sống', N'PGS.TS Đồng Văn Hệ', '024 3825 3533', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Việt Đức'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Chấn thương chỉnh hình', N'Điều trị chấn thương và chỉnh hình', N'GS.TS Trần Bình Giang', '024 3825 3534', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Việt Đức'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Gây mê hồi sức', N'Gây mê và hồi sức sau phẫu thuật', N'PGS.TS Nguyễn Quốc Kính', '024 3825 3535', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Việt Đức'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Ngoại Tiêu hóa', N'Phẫu thuật đường tiêu hóa', N'PGS.TS Phạm Đức Huấn', '024 3825 3536', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Việt Đức'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Phẫu thuật Gan mật', N'Phẫu thuật gan mật tụy', N'GS.TS Trịnh Hồng Sơn', '024 3825 3537', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Việt Đức'), GETDATE(), GETDATE());

-- Insert Departments for Thanh Nhan Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(NEWID(), N'Khoa Nội Tổng hợp', N'Điều trị nội khoa tổng quát', N'TS.BS Nguyễn Thị Mai', '024 3869 2132', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Thanh Nhàn'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Sản', N'Chăm sóc sức khỏe phụ nữ và thai sản', N'PGS.TS Trần Thị Phương', '024 3869 2133', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Thanh Nhàn'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Nhi', N'Điều trị bệnh nhi khoa', N'TS.BS Lê Thị Hương', '024 3869 2134', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Thanh Nhàn'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Mắt', N'Điều trị các bệnh về mắt', N'BS.CKII Phạm Thị Thu', '024 3869 2135', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Thanh Nhàn'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Răng Hàm Mặt', N'Điều trị răng miệng và hàm mặt', N'TS.BS Nguyễn Văn Minh', '024 3869 2136', (SELECT id FROM hospitals WHERE name =N'Bệnh viện Thanh Nhàn'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Tai Mũi Họng', N'Điều trị bệnh tai mũi họng', N'PGS.TS Đỗ Thị Hà', '024 3869 2137', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Thanh Nhàn'), GETDATE(), GETDATE());

-- Insert Departments for E Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(NEWID(), N'Khoa Nội Tổng hợp', N'Điều trị nội khoa tổng quát', N'TS.BS Nguyễn Thị Mai', '024 3754 3561', (SELECT id FROM hospitals WHERE name =N'Bệnh viện E Hà Nội'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Sản', 'Chăm sóc sức khỏe phụ nữ và thai sản', N'PGS.TS Trần Thị Phương', '024 3754 3562', (SELECT id FROM hospitals WHERE name =N'Bệnh viện E Hà Nội'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Nhi', N'Điều trị bệnh nhi khoa', N'TS.BS Lê Thị Hương', '024 3754 3563', (SELECT id FROM hospitals WHERE name = N'Bệnh viện EHà Nội'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Mắt', N'Điều trị các bệnh về mắt', N'BS.CKII Phạm Thị Thu', '024 3754 3564', (SELECT id FROM hospitals WHERE name = N'Bệnh viện E Hà Nội'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Răng Hàm Mặt', N'Điều trị răng miệng và hàm mặt', N'TS.BS Nguyễn Văn Minh', '024 3754 3565', (SELECT id FROM hospitals WHERE name = N'Bệnh viện E Hà Nội'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Tai Mũi Họng', N'Điều trị bệnh tai mũi họng', N'PGS.TS Đỗ Thị Hà', '024 3754 3566', (SELECT id FROM hospitals WHERE name = N'Bệnh viện E Hà Nội'), GETDATE(), GETDATE());

-- Insert Departments for 198 Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(NEWID(), N'Khoa Nội Tổng hợp', N'Điều trị nội khoa tổng quát', N'TS.BS Nguyễn Thị Mai', '024 3868 3408', (SELECT id FROM hospitals WHERE name = N'Bệnh viện 198 Bộ Công An'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Sản', 'Chăm sóc sức khỏe phụ nữ và thai sản', N'PGS.TS Trần Thị Phương', '024 3868 3409', (SELECT id FROM hospitals WHERE name = N'Bệnh viện 198 Bộ Công An'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Nhi', N'Điều trị bệnh nhi khoa', N'TS.BS Lê Thị Hương', '024 3868 3410', (SELECT id FROM hospitals WHERE name = N'Bệnh viện 198 Bộ Công An'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Mắt', N'Điều trị các bệnh về mắt', N'BS.CKII Phạm Thị Thu ', '024 3868 3411', (SELECT id FROM hospitals WHERE name = N'Bệnh viện 198 Bộ Công An'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Răng Hàm Mặt', N'Điều trị răng miệng và hàm mặt', N'TS.BS Nguyễn Văn Minh', '024 3868 3412', (SELECT id FROM hospitals WHERE name = N'Bệnh viện 198 Bộ Công An'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Tai Mũi Họng', N'Điều trị bệnh tai mũi họng', N'PGS.TS Đỗ Thị Hà', '024 3868 3413', (SELECT id FROM hospitals WHERE name = N'Bệnh viện 198 Bộ Công An'), GETDATE(), GETDATE());

-- Insert Departments for Huu Nghi Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(NEWID(), N'Khoa Nội Tổng hợp', N'Điều trị nội khoa tổng quát', N'TS.BS Nguyễn Thị Mai', '024 3826 6487', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Hữu Nghị'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Sản', N'Chăm sóc sức khỏe phụ nữ và thai sản', N'PGS.TS Trần Thị Phương', '024 3826 6488', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Hữu Nghị'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Nhi', N'Điều trị bệnh nhi khoa', N'TS.BS Lê Thị Hương', '024 3826 6489', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Hữu Nghị'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Mắt', N'Điều trị các bệnh về mắt', N'BS.CKII Phạm Thị Thu ', '024 3826 6490', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Hữu Nghị'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Răng Hàm Mặt', N'Điều trị răng miệng và hàm mặt', N'TS.BS Nguyễn Văn Minh', '024 3826 6491', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Hữu Nghị'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Tai Mũi Họng', N'Điều trị bệnh tai mũi họng', N'PGS.TS Đỗ Thị Hà', '024 3826 6492', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Hữu Nghị'), GETDATE(), GETDATE());

-- Insert Departments for Phu San Ha Noi Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(NEWID(), N'Khoa Nội Tổng hợp', N'Điều trị nội khoa tổng quát', N'TS.BS Nguyễn Thị Mai', '024 3835 5475', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Phụ Sản Hà Nội'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Sản', N'Chăm sóc sức khỏe phụ nữ và thai sản', N'PGS.TS Trần Thị Phương', '024 3835 5476', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Phụ Sản Hà Nội'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Nhi', N'Điều trị bệnh nhi khoa', N'TS.BS Lê Thị Hương', '024 3835 5477', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Phụ Sản Hà Nội'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Mắt', N'Điều trị các bệnh về mắt', N'BS.CKII Phạm Thị Thu ', '024 3835 5478', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Phụ Sản Hà Nội'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Răng Hàm Mặt', N'Điều trị răng miệng và hàm mặt', N'TS.BS Nguyễn Văn Minh', '024 3835 5479', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Phụ Sản Hà Nội'), GETDATE(), GETDATE()),
(NEWID(), N'Khoa Tai Mũi Họng', N'Điều trị bệnh tai mũi họng', N'PGS.TS Đỗ Thị Hà', '024 3835 5480', (SELECT id FROM hospitals WHERE name = N'Bệnh viện Phụ Sản Hà Nội'), GETDATE(), GETDATE());


INSERT INTO doctors (id, full_name, degree, gender, email, phone_number, experience, profile_picture_url, rating, review_count, department_id) VALUES
(NEWID(), N'TS.BS Nguyễn Thanh Hải', 'ThS.', N'Nam', 'nguyen.thanhhai@bachmai.vn', '024 3869 3731', N'10 năm kinh nghiệm trong điều trị bệnh tim mạch', 'https://cdn.youmed.vn/photos/0aa82b6d-e359-4175-942a-5184eec89fdf.png?width=160', 4.5, 120, (SELECT id FROM departments WHERE name = N'Khoa Nội Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),
(NEWID(), N'PGS.TS Phạm Hồng Quân', N'GS.', N'Nam', 'pham.hongquan@bachmai.vn', '024 3869 3731', N'25 năm kinh nghiệm trong điều trị tim mạch', 'https://cdn.youmed.vn/photos/d95f2862-5c20-44ad-835c-b95e43cccffa.png?width=160', 4.7, 200, (SELECT id FROM departments WHERE name = N'Khoa Nội Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),
(NEWID(), N'TS.BS Trần Thị Hồng', N'TS.', N'Nữ', 'tran.thihong@bachmai.vn', '024 3869 3731', N'15 năm kinh nghiệm trong điều trị bệnh lý tim mạch', 'https://cdn.youmed.vn/photos/9780336b-66d7-45a8-9087-8bb474485a52.jpg?width=160', 4.6, 150, (SELECT id FROM departments WHERE name = N'Khoa Nội Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),
(NEWID(), N'TS.BS Lê Văn Tuấn', N'TS.', N'Nam', 'le.vantu@bachmai.vn', '024 3869 3731', N'12 năm kinh nghiệm trong khám và điều trị bệnh lý tim mạch', 'https://cdn.youmed.vn/photos/8fc0573a-69c6-4e94-9615-38b0c20f9ebe.jpg?width=160', 4.8, 180, (SELECT id FROM departments WHERE name = N'Khoa Nội Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),

(NEWID(), N'GS.TS Lê Minh Tuấn', N'GS.', N'Nam', 'le.minhtuan@vietduc.vn', '024 3825 3532', N'30 năm kinh nghiệm trong phẫu thuật tim mạch', 'https://cdn.youmed.vn/photos/979e6850-92c6-44fb-933a-e4de4426eff4.jpg?width=160', 4.9, 220, (SELECT id FROM departments WHERE name = N'Khoa Phẫu thuật Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Việt Đức'))),
(NEWID(), N'PGS.TS Nguyễn Ngọc Lan', N'PGS.', N'Nữ', 'nguyen.ngoclan@vietduc.vn', '024 3825 3532', N'20 năm kinh nghiệm trong phẫu thuật tim mạch', 'https://cdn.youmed.vn/photos/c2b20a10-01ab-4378-8fb9-a4cecf73fc1d.jpg?width=160', 4.7, 190, (SELECT id FROM departments WHERE name = N'Khoa Phẫu thuật Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Việt Đức'))),
(NEWID(), N'TS.BS Lương Đức Khuê', N'TS.', N'Nam', 'luong.duckhoe@vietduc.vn', '024 3825 3532', N'12 năm kinh nghiệm trong phẫu thuật tim mạch', 'https://cdn.youmed.vn/photos/b585248d-ecb6-4b0b-8494-4fb1507528c9.jpg?width=160', 4.6, 160, (SELECT id FROM departments WHERE name = N'Khoa Phẫu thuật Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Việt Đức'))),
(NEWID(), N'TS.BS Nguyễn Thiện Thuật', N'TS.', N'Nam', 'nguyen.thienthuat@vietduc.vn', '024 3825 3532', N'14 năm kinh nghiệm trong phẫu thuật tim', 'https://cdn.youmed.vn/photos/9b3708e5-40ba-4575-89f7-eac5f07ab704.png?width=160', 4.8, 170, (SELECT id FROM departments WHERE name = N'Khoa Phẫu thuật Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Việt Đức'))),

(NEWID(), N'BS. Vũ Minh Anh', 'ThS.', N'Nam', 'vuminhe@bachmai.vn', '024 3869 3744', N'6 năm kinh nghiệm trong chuyên ngành thần kinh', 'https://cdn.youmed.vn/photos/9b3708e5-40ba-4575-89f7-eac5f07ab704.png?width=160', 4.6, 110, (SELECT id FROM departments WHERE name = N'Khoa Thần kinh' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),
(NEWID(), N'BS. Hoàng Thị Phương', N'BS.', N'Nữ', 'hoangthif@bachmai.vn', '024 3869 3745', N'4 năm kinh nghiệm trong chuyên ngành thần kinh', 'https://cdn.youmed.vn/photos/e4b3d168-6b25-4ec8-ac08-4e7ab4842241.jpg?width=160', 4.4, 90, (SELECT id FROM departments WHERE name = N'Khoa Thần kinh' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),
(NEWID(), N'BS. Trần Mạnh Giang', N'BS.', N'Nam', 'tranmang@bachmai.vn', '024 3869 3746', N'5 năm kinh nghiệm trong chuyên ngành thần kinh', 'https://cdn.youmed.vn/photos/70161c11-bca0-420f-9a3d-2417be9bca2a.png?width=160', 4.7, 115, (SELECT id FROM departments WHERE name = N'Khoa Thần kinh' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),
(NEWID(), N'BS. Lý Thị Hạnh', 'ThS.', N'Nữ', 'lythih@bachmai.vn', '024 3869 3747', N'6 năm kinh nghiệm trong chuyên ngành thần kinh', 'https://cdn.youmed.vn/photos/ff645619-2287-4adc-b323-da495e79a38c.jpg?width=160', 4.5, 120, (SELECT id FROM departments WHERE name = N'Khoa Thần kinh' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),

(NEWID(), N'BS. Nguyễn Thị Yến', N'BS.', N'Nữ', 'nguyenthii@bachmai.vn', '024 3869 3748', N'8 năm kinh nghiệm trong chuyên ngành điều dưỡng', 'https://cdn.youmed.vn/photos/9a69233f-2da3-4aaa-9665-7bc4f1ad2a6a.jpg?width=160', 4.9, 140, (SELECT id FROM departments WHERE name = N'Khoa Hồi sức tích cực' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),
(NEWID(), N'BS. Phan Minh Trí', 'ThS.', N'Nam', 'phanminhj@bachmai.vn', '024 3869 3749', N'7 năm kinh nghiệm trong chuyên ngành điều dưỡng', 'https://cdn.youmed.vn/photos/753915d9-25ba-4ef6-a91b-70b824db0107.jpg?width=160', 4.8, 130, (SELECT id FROM departments WHERE name = N'Khoa Hồi sức tích cực' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),
(NEWID(), N'BS. Mai Thị Lan', N'BS.', N'Nữ', 'maithik@bachmai.vn', '024 3869 3750', N'5 năm kinh nghiệm trong chuyên ngành điều dưỡng', 'https://cdn.youmed.vn/photos/5d6755e0-dda8-41bf-bb27-2c992052f3ec.jpg?width=160', 4.6, 125, (SELECT id FROM departments WHERE name = N'Khoa Hồi sức tích cực' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai'))),
(NEWID(), N'BS. Trần Đức Lâm', 'ThS.', N'Nam', 'tranducl@bachmai.vn', '024 3869 3751', N'6 năm kinh nghiệm trong chuyên ngành điều dưỡng', 'https://cdn.youmed.vn/photos/79eab5b1-438c-4f1c-b783-ec4f5cdcc721.jpeg?width=160', 4.7, 135, (SELECT id FROM departments WHERE name = N'Khoa Hồi sức tích cực' AND hospital_id = (SELECT id FROM hospitals WHERE name = N'Bệnh viện Bạch Mai')));

INSERT INTO medicines (id, name, description, price, quantity, created_at, updated_at) VALUES
(NEWID(), 'Paracetamol', N'Thuốc giảm đau, hạ sốt', 5000, 100, GETDATE(), GETDATE()),
(NEWID(), 'Ibuprofen', N'Thuốc chống viêm, giảm đau', 7000, 150, GETDATE(), GETDATE()),
(NEWID(), 'Amoxicillin', N'Kháng sinh điều trị nhiễm khuẩn', 15000, 200, GETDATE(), GETDATE()),
(NEWID(), 'Metformin', N'Thuốc điều trị tiểu đường type 2', 30000, 50, GETDATE(), GETDATE()),
(NEWID(), 'Aspirin', N'Thuốc giảm đau, chống viêm', 8000, 120, GETDATE(), GETDATE()),
(NEWID(), 'Losartan', N'Thuốc điều trị cao huyết áp', 25000, 80, GETDATE(), GETDATE()),
(NEWID(), 'Simvastatin', N'Thuốc hạ cholesterol', 22000, 90, GETDATE(), GETDATE()),
(NEWID(), 'Omeprazole', N'Thuốc điều trị loét dạ dày', 12000, 60, GETDATE(), GETDATE()),
(NEWID(), 'Cetirizine', N'Thuốc chống dị ứng', 12000, 140, GETDATE(), GETDATE()),
(NEWID(), 'Loratadine', N'Thuốc chống dị ứng', 10000, 130, GETDATE(), GETDATE()),
(NEWID(), 'Hydrochlorothiazide', N'Thuốc lợi tiểu', 17000, 110, GETDATE(), GETDATE()),
(NEWID(), 'Furosemide', N'Thuốc lợi tiểu', 12000, 95, GETDATE(), GETDATE()),
(NEWID(), 'Diazepam', N'Thuốc an thần', 20000, 70, GETDATE(), GETDATE()),
(NEWID(), 'Ciprofloxacin', N'Kháng sinh điều trị nhiễm khuẩn', 18000, 150, GETDATE(), GETDATE()),
(NEWID(), 'Prednisone', N'Thuốc chống viêm, giảm đau', 23000, 80, GETDATE(), GETDATE()),
(NEWID(), 'Fluoxetine', N'Thuốc điều trị trầm cảm', 25000, 90, GETDATE(), GETDATE()),
(NEWID(), 'Lorazepam', N'Thuốc an thần', 19000, 110, GETDATE(), GETDATE()),
(NEWID(), 'Clopidogrel', N'Thuốc chống đông máu', 30000, 75, GETDATE(), GETDATE()),
(NEWID(), 'Gabapentin', N'Thuốc điều trị đau thần kinh', 17000, 100, GETDATE(), GETDATE()),
(NEWID(), 'Sertraline', N'Thuốc chống trầm cảm', 22000, 85, GETDATE(), GETDATE()),
(NEWID(), 'Ranitidine', N'Thuốc chống loét dạ dày', 15000, 140, GETDATE(), GETDATE()),
(NEWID(), 'Fexofenadine', N'Thuốc chống dị ứng', 16000, 130, GETDATE(), GETDATE()),
(NEWID(), 'Mometasone', N'Thuốc chống viêm', 21000, 50, GETDATE(), GETDATE()),
(NEWID(), 'Levothyroxine', N'Thuốc điều trị suy giáp', 28000, 90, GETDATE(), GETDATE()),
(NEWID(), 'Vitamins C', N'Vitamin C tăng cường miễn dịch', 10000, 150, GETDATE(), GETDATE()),
(NEWID(), 'Vitamins D', N'Vitamin D hỗ trợ xương', 12000, 100, GETDATE(), GETDATE()),
(NEWID(), 'Azithromycin', N'Kháng sinh điều trị nhiễm khuẩn', 22000, 60, GETDATE(), GETDATE()),
(NEWID(), 'Prednisolone', N'Thuốc chống viêm', 24000, 80, GETDATE(), GETDATE()),
(NEWID(), 'Salbutamol', N'Thuốc điều trị hen suyễn', 17000, 130, GETDATE(), GETDATE()),
(NEWID(), 'Doxycycline', N'Kháng sinh điều trị nhiễm khuẩn', 16000, 140, GETDATE(), GETDATE()),
(NEWID(), 'Levocetirizine', N'Thuốc chống dị ứng', 13000, 120, GETDATE(), GETDATE()),
(NEWID(), 'Bromhexine', N'Thuốc giảm ho', 9000, 140, GETDATE(), GETDATE()),
(NEWID(), 'Codeine', N'Thuốc giảm đau, ho', 22000, 110, GETDATE(), GETDATE()),
(NEWID(), 'Paroxetine', N'Thuốc chống trầm cảm', 25000, 75, GETDATE(), GETDATE()),
(NEWID(), 'Citalopram', N'Thuốc chống trầm cảm', 24000, 95, GETDATE(), GETDATE()),
(NEWID(), 'Furosemide', N'Thuốc lợi tiểu', 18000, 100, GETDATE(), GETDATE()),
(NEWID(), 'Vitamins B12', N'Vitamin B12 hỗ trợ thần kinh', 12000, 200, GETDATE(), GETDATE()),
(NEWID(), 'Fluticasone', N'Thuốc chống viêm mũi', 21000, 80, GETDATE(), GETDATE()),
(NEWID(), 'Amlodipine', N'Thuốc điều trị cao huyết áp', 25000, 130, GETDATE(), GETDATE()),
(NEWID(), 'Atorvastatin', N'Thuốc hạ cholesterol', 28000, 150, GETDATE(), GETDATE()),
(NEWID(), 'Diphenhydramine', N'Thuốc kháng histamine', 9000, 140, GETDATE(), GETDATE()),
(NEWID(), 'Chlorpheniramine', N'Thuốc kháng histamine', 8500, 160, GETDATE(), GETDATE()),
(NEWID(), 'Naproxen', N'Thuốc giảm đau, chống viêm', 15000, 90, GETDATE(), GETDATE()),
(NEWID(), 'Ceftriaxone', N'Kháng sinh điều trị nhiễm khuẩn', 35000, 60, GETDATE(), GETDATE()),
(NEWID(), 'Tramadol', N'Thuốc giảm đau', 22000, 85, GETDATE(), GETDATE()),
(NEWID(), 'Tamsulosin', N'Thuốc điều trị tuyến tiền liệt', 27000, 75, GETDATE(), GETDATE()),
(NEWID(), 'Methylprednisolone', N'Thuốc chống viêm', 30000, 65, GETDATE(), GETDATE()),
(NEWID(), 'Rifampin', N'Kháng sinh điều trị lao', 24000, 50, GETDATE(), GETDATE()),
(NEWID(), 'Clarithromycin', N'Kháng sinh điều trị nhiễm khuẩn', 25000, 90, GETDATE(), GETDATE()),
(NEWID(), 'Ethambutol', N'Kháng sinh điều trị lao', 23000, 60, GETDATE(), GETDATE()),
(NEWID(), 'Propranolol', N'Thuốc điều trị cao huyết áp', 15000, 100, GETDATE(), GETDATE()),
(NEWID(), 'Lansoprazole', N'Thuốc điều trị dạ dày', 18000, 110, GETDATE(), GETDATE());


-- Khoa Hồi sức tích cực
INSERT INTO department_services (id, department_id, service_name, description, price, created_at, updated_at) VALUES
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Nội Tim mạch'), N'Khám thường BYTH', N'Khám sức khỏe tổng quát cơ bản', 30000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Nội Tim mạch'), N'Khám theo yêu cầu', N'Khám theo yêu cầu cá nhân', 200000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Nội Tim mạch'), N'Khám nâng cao', N'Khám sức khỏe chuyên sâu', 500000, GETDATE(), GETDATE()),

(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Thần kinh'), N'Khám thường BYTH', N'Khám sức khỏe tổng quát cơ bản', 30000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Thần kinh'), N'Khám theo yêu cầu', N'Khám theo yêu cầu cá nhân', 200000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Thần kinh'), N'Khám nâng cao', N'Khám sức khỏe chuyên sâu', 500000, GETDATE(), GETDATE()),

(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Hồi sức tích cực'), N'Khám thường BYTH', N'Khám sức khỏe tổng quát cơ bản', 30000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Hồi sức tích cực'), N'Khám theo yêu cầu', N'Khám theo yêu cầu cá nhân', 200000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Hồi sức tích cực'), N'Khám nâng cao', N'Khám sức khỏe chuyên sâu', 500000, GETDATE(), GETDATE()),

(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Nội tiết'), N'Khám thường BYTH', N'Khám sức khỏe tổng quát cơ bản', 30000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Nội tiết'), N'Khám theo yêu cầu', N'Khám theo yêu cầu cá nhân', 200000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Nội tiết'), N'Khám nâng cao', N'Khám sức khỏe chuyên sâu', 500000, GETDATE(), GETDATE()),

(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Ngoại Tổng hợp'), N'Khám thường BYTH', N'Khám sức khỏe tổng quát cơ bản', 30000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Ngoại Tổng hợp'), N'Khám theo yêu cầu', N'Khám theo yêu cầu cá nhân', 200000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Ngoại Tổng hợp'), N'Khám nâng cao', N'Khám sức khỏe chuyên sâu', 500000, GETDATE(), GETDATE()),

(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Cấp cứu'), N'Khám thường BYTH', N'Khám sức khỏe tổng quát cơ bản', 30000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Cấp cứu'), N'Khám theo yêu cầu', N'Khám theo yêu cầu cá nhân', 200000, GETDATE(), GETDATE()),
(NEWID(), (SELECT id FROM departments WHERE name = N'Khoa Cấp cứu'), N'Khám nâng cao', N'Khám sức khỏe chuyên sâu', 500000, GETDATE(), GETDATE());
