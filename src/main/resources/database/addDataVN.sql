--Insert Roles 
INSERT INTO roles (id, name) VALUES
(UUID(), 'ADMIN'),
(UUID(), 'DOCTOR'),
(UUID(), 'PATIENT');

-- Insert Hospitals
INSERT INTO hospitals (id, name, address, phone, email, website, description, created_at, updated_at) VALUES
(UUID(), 'Bệnh viện Bạch Mai', '78 Giải Phóng, Phương Mai, Đống Đa, Hà Nội', '024 3869 3731', 'info@bachmai.gov.vn', 'bachmai.gov.vn', 'Bệnh viện đa khoa hạng đặc biệt, tuyến trung ương', NOW(), NOW()),
(UUID(), 'Bệnh viện Việt Đức', '40 Tràng Thi, Hàng Bông, Hoàn Kiếm, Hà Nội', '024 3825 3531', 'info@vietduchospital.vn', 'vietduchospital.vn', 'Bệnh viện chuyên khoa ngoại hạng đặc biệt', NOW(), NOW()),
(UUID(), 'Bệnh viện Thanh Nhàn', '42 Thanh Nhàn, Hai Bà Trưng, Hà Nội', '024 3869 2131', 'info@thanhnhan.vn', 'bvthanhnhan.vn', 'Bệnh viện đa khoa hạng I của Hà Nội', NOW(), NOW()),
(UUID(), 'Bệnh viện E Hà Nội', '89 Trần Cung, Nghĩa Tân, Cầu Giấy, Hà Nội', '024 3754 3560', 'contact@benhviene.vn', 'benhviene.vn', 'Bệnh viện đa khoa tuyến trung ương', NOW(), NOW()),
(UUID(), 'Bệnh viện Đại học Y Hà Nội', '1 Tôn Thất Tùng, Kim Liên, Đống Đa, Hà Nội', '024 3574 7788', 'info@bvdhyduoc.vn', 'bvdhyduoc.vn', 'Bệnh viện đào tạo thuộc Đại học Y Hà Nội', NOW(), NOW()),
(UUID(), 'Bệnh viện Tim Hà Nội', '92 Trần Hưng Đạo, Hoàn Kiếm, Hà Nội', '024 3821 5230', 'info@benhvientimhanoi.vn', 'benhvientimhanoi.vn', 'Bệnh viện chuyên khoa tim mạch', NOW(), NOW()),
(UUID(), 'Bệnh viện 198 Bộ Công An', '9 Trần Bình, Mai Dịch, Cầu Giấy, Hà Nội', '024 3868 3407', 'info@bv198.vn', 'bv198.vn', 'Bệnh viện đa khoa của Bộ Công An', NOW(), NOW()),
(UUID(), 'Bệnh viện Phụ Sản Hà Nội', '929 La Thành, Ngọc Khánh, Ba Đình, Hà Nội', '024 3835 5474', 'info@phusan.vn', 'phusan.vn', 'Bệnh viện chuyên khoa sản phụ khoa', NOW(), NOW()),
(UUID(), 'Bệnh viện Hữu Nghị', '1 Trần Khánh Dư, Bạch Đằng, Hai Bà Trưng, Hà Nội', '024 3826 6486', 'info@huunghi.vn', 'huunghi.vn', 'Bệnh viện đa khoa tuyến trung ương', NOW(), NOW()),
(UUID(), 'Bệnh viện Đa khoa Xanh Pôn', '12 Chu Văn An, Ba Đình, Hà Nội', '024 3823 4937', 'info@xanhpon.vn', 'xanhpon.vn', 'Bệnh viện đa khoa hạng I của Hà Nội', NOW(), NOW());

-- Insert Departments for Bach Mai Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(UUID(), 'Khoa Nội Tim mạch', 'Chuyên khoa về bệnh lý tim mạch', 'PGS.TS Phạm Mạnh Hùng', '024 3869 3731', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'), NOW(), NOW()),
(UUID(), 'Khoa Thần kinh', 'Điều trị các bệnh lý thần kinh', 'GS.TS Nguyễn Văn Thông', '024 3869 3732', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'), NOW(), NOW()),
(UUID(), 'Khoa Hồi sức tích cực', 'Điều trị các ca bệnh nặng', 'PGS.TS Nguyễn Thị Tuyến', '024 3869 3733', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'), NOW(), NOW()),
(UUID(), 'Khoa Nội tiết', 'Điều trị bệnh đái tháo đường và nội tiết', 'GS.TS Tạ Văn Bình', '024 3869 3734', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'), NOW(), NOW()),
(UUID(), 'Khoa Ngoại Tổng hợp', 'Phẫu thuật và điều trị ngoại khoa', 'PGS.TS Nguyễn Quang Nghĩa', '024 3869 3735', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'), NOW(), NOW()),
(UUID(), 'Khoa Cấp cứu', 'Xử lý các trường hợp cấp cứu', 'TS.BS Nguyễn Đức Chính', '024 3869 3736', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'), NOW(), NOW());

-- Insert Departments for Viet Duc Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(UUID(), 'Khoa Phẫu thuật Tim mạch', 'Phẫu thuật tim và mạch máu', 'GS.TS Lê Ngọc Thành', '024 3825 3532', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Việt Đức'), NOW(), NOW()),
(UUID(), 'Khoa Phẫu thuật Thần kinh', 'Phẫu thuật não và cột sống', 'PGS.TS Đồng Văn Hệ', '024 3825 3533', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Việt Đức'), NOW(), NOW()),
(UUID(), 'Khoa Chấn thương chỉnh hình', 'Điều trị chấn thương và chỉnh hình', 'GS.TS Trần Bình Giang', '024 3825 3534', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Việt Đức'), NOW(), NOW()),
(UUID(), 'Khoa Gây mê hồi sức', 'Gây mê và hồi sức sau phẫu thuật', 'PGS.TS Nguyễn Quốc Kính', '024 3825 3535', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Việt Đức'), NOW(), NOW()),
(UUID(), 'Khoa Ngoại Tiêu hóa', 'Phẫu thuật đường tiêu hóa', 'PGS.TS Phạm Đức Huấn', '024 3825 3536', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Việt Đức'), NOW(), NOW()),
(UUID(), 'Khoa Phẫu thuật Gan mật', 'Phẫu thuật gan mật tụy', 'GS.TS Trịnh Hồng Sơn', '024 3825 3537', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Việt Đức'), NOW(), NOW());

-- Insert Departments for Thanh Nhan Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(UUID(), 'Khoa Nội Tổng hợp', 'Điều trị nội khoa tổng quát', 'TS.BS Nguyễn Thị Mai', '024 3869 2132', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Thanh Nhàn'), NOW(), NOW()),
(UUID(), 'Khoa Sản', 'Chăm sóc sức khỏe phụ nữ và thai sản', 'PGS.TS Trần Thị Phương', '024 3869 2133', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Thanh Nhàn'), NOW(), NOW()),
(UUID(), 'Khoa Nhi', 'Điều trị bệnh nhi khoa', 'TS.BS Lê Thị Hương', '024 3869 2134', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Thanh Nhàn'), NOW(), NOW()),
(UUID(), 'Khoa Mắt', 'Điều trị các bệnh về mắt', 'BS.CKII Phạm Thị Thu', '024 3869 2135', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Thanh Nhàn'), NOW(), NOW()),
(UUID(), 'Khoa Răng Hàm Mặt', 'Điều trị răng miệng và hàm mặt', 'TS.BS Nguyễn Văn Minh', '024 3869 2136', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Thanh Nhàn'), NOW(), NOW()),
(UUID(), 'Khoa Tai Mũi Họng', 'Điều trị bệnh tai mũi họng', 'PGS.TS Đỗ Thị Hà', '024 3869 2137', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Thanh Nhàn'), NOW(), NOW());

-- Insert Departments for E Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(UUID(), 'Khoa Nội Tổng hợp', 'Điều trị nội khoa tổng quát', 'TS.BS Nguyễn Thị Mai', '024 3754 3561', (SELECT id FROM hospitals WHERE name = 'Bệnh viện E Hà Nội'), NOW(), NOW()),
(UUID(), 'Khoa Sản', 'Chăm sóc sức khỏe phụ nữ và thai sản', 'PGS.TS Trần Thị Phương', '024 3754 3562', (SELECT id FROM hospitals WHERE name = 'Bệnh viện E Hà Nội'), NOW(), NOW()),
(UUID(), 'Khoa Nhi', 'Điều trị bệnh nhi khoa', 'TS.BS Lê Thị Hương', '024 3754 3563', (SELECT id FROM hospitals WHERE name = 'Bệnh viện EHà Nội'), NOW(), NOW()),
(UUID(), 'Khoa Mắt', 'Điều trị các bệnh về mắt', 'BS.CKII Phạm Thị Thu', '024 3754 3564', (SELECT id FROM hospitals WHERE name = 'Bệnh viện E Hà Nội'), NOW(), NOW()),
(UUID(), 'Khoa Răng Hàm Mặt', 'Điều trị răng miệng và hàm mặt', 'TS.BS Nguyễn Văn Minh', '024 3754 3565', (SELECT id FROM hospitals WHERE name = 'Bệnh viện E Hà Nội'), NOW(), NOW()),
(UUID(), 'Khoa Tai Mũi Họng', 'Điều trị bệnh tai mũi họng', 'PGS.TS Đỗ Thị Hà', '024 3754 3566', (SELECT id FROM hospitals WHERE name = 'Bệnh viện E Hà Nội'), NOW(), NOW());

-- Insert Departments for 198 Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(UUID(), 'Khoa Nội Tổng hợp', 'Điều trị nội khoa tổng quát', 'TS.BS Nguyễn Thị Mai', '024 3868 3408', (SELECT id FROM hospitals WHERE name = 'Bệnh viện 198 Bộ Công An'), NOW(), NOW()),
(UUID(), 'Khoa Sản', 'Chăm sóc sức khỏe phụ nữ và thai sản', 'PGS.TS Trần Thị Phương', '024 3868 3409', (SELECT id FROM hospitals WHERE name = 'Bệnh viện 198 Bộ Công An'), NOW(), NOW()),
(UUID(), 'Khoa Nhi', 'Điều trị bệnh nhi khoa', 'TS.BS Lê Thị Hương', '024 3868 3410', (SELECT id FROM hospitals WHERE name = 'Bệnh viện 198 Bộ Công An'), NOW(), NOW()),
(UUID(), 'Khoa Mắt', 'Điều trị các bệnh về mắt', 'BS.CKII Phạm Thị Thu ', '024 3868 3411', (SELECT id FROM hospitals WHERE name = 'Bệnh viện 198 Bộ Công An'), NOW(), NOW()),
(UUID(), 'Khoa Răng Hàm Mặt', 'Điều trị răng miệng và hàm mặt', 'TS.BS Nguyễn Văn Minh', '024 3868 3412', (SELECT id FROM hospitals WHERE name = 'Bệnh viện 198 Bộ Công An'), NOW(), NOW()),
(UUID(), 'Khoa Tai Mũi Họng', 'Điều trị bệnh tai mũi họng', 'PGS.TS Đỗ Thị Hà', '024 3868 3413', (SELECT id FROM hospitals WHERE name = 'Bệnh viện 198 Bộ Công An'), NOW(), NOW());

-- Insert Departments for Huu Nghi Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(UUID(), 'Khoa Nội Tổng hợp', 'Điều trị nội khoa tổng quát', 'TS.BS Nguyễn Thị Mai', '024 3826 6487', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Hữu Nghị'), NOW(), NOW()),
(UUID(), 'Khoa Sản', 'Chăm sóc sức khỏe phụ nữ và thai sản', 'PGS.TS Trần Thị Phương', '024 3826 6488', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Hữu Nghị'), NOW(), NOW()),
(UUID(), 'Khoa Nhi', 'Điều trị bệnh nhi khoa', 'TS.BS Lê Thị Hương', '024 3826 6489', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Hữu Nghị'), NOW(), NOW()),
(UUID(), 'Khoa Mắt', 'Điều trị các bệnh về mắt', 'BS.CKII Phạm Thị Thu ', '024 3826 6490', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Hữu Nghị'), NOW(), NOW()),
(UUID(), 'Khoa Răng Hàm Mặt', 'Điều trị răng miệng và hàm mặt', 'TS.BS Nguyễn Văn Minh', '024 3826 6491', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Hữu Nghị'), NOW(), NOW()),
(UUID(), 'Khoa Tai Mũi Họng', 'Điều trị bệnh tai mũi họng', 'PGS.TS Đỗ Thị Hà', '024 3826 6492', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Hữu Nghị'), NOW(), NOW());

-- Insert Departments for Phu San Ha Noi Hospital
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(UUID(), 'Khoa Nội Tổng hợp', 'Điều trị nội khoa tổng quát', 'TS.BS Nguyễn Thị Mai', '024 3835 5475', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Phụ Sản Hà Nội'), NOW(), NOW()),
(UUID(), 'Khoa Sản', 'Chăm sóc sức khỏe phụ nữ và thai sản', 'PGS.TS Trần Thị Phương', '024 3835 5476', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Phụ Sản Hà Nội'), NOW(), NOW()),
(UUID(), 'Khoa Nhi', 'Điều trị bệnh nhi khoa', 'TS.BS Lê Thị Hương', '024 3835 5477', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Phụ Sản Hà Nội'), NOW(), NOW()),
(UUID(), 'Khoa Mắt', 'Điều trị các bệnh về mắt', 'BS.CKII Phạm Thị Thu ', '024 3835 5478', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Phụ Sản Hà Nội'), NOW(), NOW()),
(UUID(), 'Khoa Răng Hàm Mặt', 'Điều trị răng miệng và hàm mặt', 'TS.BS Nguyễn Văn Minh', '024 3835 5479', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Phụ Sản Hà Nội'), NOW(), NOW()),
(UUID(), 'Khoa Tai Mũi Họng', 'Điều trị bệnh tai mũi họng', 'PGS.TS Đỗ Thị Hà', '024 3835 5480', (SELECT id FROM hospitals WHERE name = 'Bệnh viện Phụ Sản Hà Nội'), NOW(), NOW());


INSERT INTO doctors (id, full_name, degree, gender, email, phone_number, experience, profile_picture_url, rating, review_count, department_id) VALUES
(UUID(), 'TS.BS Nguyễn Thanh Hải', 'ThS.', 'Nam', 'nguyen.thanhhai@bachmai.vn', '024 3869 3731', '10 năm kinh nghiệm trong điều trị bệnh tim mạch', 'https://cdn.youmed.vn/photos/0aa82b6d-e359-4175-942a-5184eec89fdf.png?width=160', 4.5, 120, (SELECT id FROM departments WHERE name = 'Khoa Nội Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),
(UUID(), 'PGS.TS Phạm Hồng Quân', 'GS.', 'Nam', 'pham.hongquan@bachmai.vn', '024 3869 3731', '25 năm kinh nghiệm trong điều trị tim mạch', 'https://cdn.youmed.vn/photos/d95f2862-5c20-44ad-835c-b95e43cccffa.png?width=160', 4.7, 200, (SELECT id FROM departments WHERE name = 'Khoa Nội Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),
(UUID(), 'TS.BS Trần Thị Hồng', 'Ts.', 'Nữ', 'tran.thihong@bachmai.vn', '024 3869 3731', '15 năm kinh nghiệm trong điều trị bệnh lý tim mạch', 'https://cdn.youmed.vn/photos/9780336b-66d7-45a8-9087-8bb474485a52.jpg?width=160', 4.6, 150, (SELECT id FROM departments WHERE name = 'Khoa Nội Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),
(UUID(), 'TS.BS Lê Văn Tuấn', 'Ts.', 'Nam', 'le.vantu@bachmai.vn', '024 3869 3731', '12 năm kinh nghiệm trong khám và điều trị bệnh lý tim mạch', 'https://cdn.youmed.vn/photos/8fc0573a-69c6-4e94-9615-38b0c20f9ebe.jpg?width=160', 4.8, 180, (SELECT id FROM departments WHERE name = 'Khoa Nội Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),

(UUID(), 'GS.TS Lê Minh Tuấn', 'GS.', 'Nam', 'le.minhtuan@vietduc.vn', '024 3825 3532', '30 năm kinh nghiệm trong phẫu thuật tim mạch', 'https://cdn.youmed.vn/photos/979e6850-92c6-44fb-933a-e4de4426eff4.jpg?width=160', 4.9, 220, (SELECT id FROM departments WHERE name = 'Khoa Phẫu thuật Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Việt Đức'))),
(UUID(), 'PGS.TS Nguyễn Ngọc Lan', 'PGS.', 'Nữ', 'nguyen.ngoclan@vietduc.vn', '024 3825 3532', '20 năm kinh nghiệm trong phẫu thuật tim mạch', 'https://cdn.youmed.vn/photos/c2b20a10-01ab-4378-8fb9-a4cecf73fc1d.jpg?width=160', 4.7, 190, (SELECT id FROM departments WHERE name = 'Khoa Phẫu thuật Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Việt Đức'))),
(UUID(), 'TS.BS Lương Đức Khuê', 'Ts.', 'Nam', 'luong.duckhoe@vietduc.vn', '024 3825 3532', '12 năm kinh nghiệm trong phẫu thuật tim mạch', 'https://cdn.youmed.vn/photos/b585248d-ecb6-4b0b-8494-4fb1507528c9.jpg?width=160', 4.6, 160, (SELECT id FROM departments WHERE name = 'Khoa Phẫu thuật Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Việt Đức'))),
(UUID(), 'TS.BS Nguyễn Thiện Thuật', 'Ts.', 'Nam', 'nguyen.thienthuat@vietduc.vn', '024 3825 3532', '14 năm kinh nghiệm trong phẫu thuật tim', 'https://cdn.youmed.vn/photos/9b3708e5-40ba-4575-89f7-eac5f07ab704.png?width=160', 4.8, 170, (SELECT id FROM departments WHERE name = 'Khoa Phẫu thuật Tim mạch' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Việt Đức'))),

(UUID(), 'Bs. Vũ Minh Anh', 'ThS.', 'Nam', 'vuminhe@bachmai.vn', '024 3869 3744', '6 năm kinh nghiệm trong chuyên ngành thần kinh', 'https://cdn.youmed.vn/photos/9b3708e5-40ba-4575-89f7-eac5f07ab704.png?width=160', 4.6, 110, (SELECT id FROM departments WHERE name = 'Khoa Thần kinh' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),
(UUID(), 'Bs. Hoàng Thị Phương', 'Bs.', 'Nữ', 'hoangthif@bachmai.vn', '024 3869 3745', '4 năm kinh nghiệm trong chuyên ngành thần kinh', 'https://cdn.youmed.vn/photos/e4b3d168-6b25-4ec8-ac08-4e7ab4842241.jpg?width=160', 4.4, 90, (SELECT id FROM departments WHERE name = 'Khoa Thần kinh' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),
(UUID(), 'Bs. Trần Mạnh Giang', 'Bs.', 'Nam', 'tranmang@bachmai.vn', '024 3869 3746', '5 năm kinh nghiệm trong chuyên ngành thần kinh', 'https://cdn.youmed.vn/photos/70161c11-bca0-420f-9a3d-2417be9bca2a.png?width=160', 4.7, 115, (SELECT id FROM departments WHERE name = 'Khoa Thần kinh' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),
(UUID(), 'Bs. Lý Thị Hạnh', 'ThS.', 'Nữ', 'lythih@bachmai.vn', '024 3869 3747', '6 năm kinh nghiệm trong chuyên ngành thần kinh', 'https://cdn.youmed.vn/photos/ff645619-2287-4adc-b323-da495e79a38c.jpg?width=160', 4.5, 120, (SELECT id FROM departments WHERE name = 'Khoa Thần kinh' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),

(UUID(), 'Bs. Nguyễn Thị Yến', 'Bs.', 'Nữ', 'nguyenthii@bachmai.vn', '024 3869 3748', '8 năm kinh nghiệm trong chuyên ngành điều dưỡng', 'https://cdn.youmed.vn/photos/9a69233f-2da3-4aaa-9665-7bc4f1ad2a6a.jpg?width=160', 4.9, 140, (SELECT id FROM departments WHERE name = 'Khoa Hồi sức tích cực' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),
(UUID(), 'Bs. Phan Minh Trí', 'ThS.', 'Nam', 'phanminhj@bachmai.vn', '024 3869 3749', '7 năm kinh nghiệm trong chuyên ngành điều dưỡng', 'https://cdn.youmed.vn/photos/753915d9-25ba-4ef6-a91b-70b824db0107.jpg?width=160', 4.8, 130, (SELECT id FROM departments WHERE name = 'Khoa Hồi sức tích cực' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),
(UUID(), 'Bs. Mai Thị Lan', 'Bs.', 'Nữ', 'maithik@bachmai.vn', '024 3869 3750', '5 năm kinh nghiệm trong chuyên ngành điều dưỡng', 'https://cdn.youmed.vn/photos/5d6755e0-dda8-41bf-bb27-2c992052f3ec.jpg?width=160', 4.6, 125, (SELECT id FROM departments WHERE name = 'Khoa Hồi sức tích cực' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai'))),
(UUID(), 'Bs. Trần Đức Lâm', 'ThS.', 'Nam', 'tranducl@bachmai.vn', '024 3869 3751', '6 năm kinh nghiệm trong chuyên ngành điều dưỡng', 'https://cdn.youmed.vn/photos/79eab5b1-438c-4f1c-b783-ec4f5cdcc721.jpeg?width=160', 4.7, 135, (SELECT id FROM departments WHERE name = 'Khoa Hồi sức tích cực' AND hospital_id = (SELECT id FROM hospitals WHERE name = 'Bệnh viện Bạch Mai')));

INSERT INTO medicines (id, name, description, price, quantity, created_at, updated_at) VALUES
(UUID(), 'Paracetamol', 'Thuốc giảm đau, hạ sốt', 5000, 100, NOW(), NOW()),
(UUID(), 'Ibuprofen', 'Thuốc chống viêm, giảm đau', 7000, 150, NOW(), NOW()),
(UUID(), 'Amoxicillin', 'Kháng sinh điều trị nhiễm khuẩn', 15000, 200, NOW(), NOW()),
(UUID(), 'Metformin', 'Thuốc điều trị tiểu đường type 2', 30000, 50, NOW(), NOW()),
(UUID(), 'Aspirin', 'Thuốc giảm đau, chống viêm', 8000, 120, NOW(), NOW()),
(UUID(), 'Losartan', 'Thuốc điều trị cao huyết áp', 25000, 80, NOW(), NOW()),
(UUID(), 'Simvastatin', 'Thuốc hạ cholesterol', 22000, 90, NOW(), NOW()),
(UUID(), 'Omeprazole', 'Thuốc điều trị loét dạ dày', 12000, 60, NOW(), NOW()),
(UUID(), 'Cetirizine', 'Thuốc chống dị ứng', 12000, 140, NOW(), NOW()),
(UUID(), 'Loratadine', 'Thuốc chống dị ứng', 10000, 130, NOW(), NOW()),
(UUID(), 'Hydrochlorothiazide', 'Thuốc lợi tiểu', 17000, 110, NOW(), NOW()),
(UUID(), 'Furosemide', 'Thuốc lợi tiểu', 12000, 95, NOW(), NOW()),
(UUID(), 'Diazepam', 'Thuốc an thần', 20000, 70, NOW(), NOW()),
(UUID(), 'Ciprofloxacin', 'Kháng sinh điều trị nhiễm khuẩn', 18000, 150, NOW(), NOW()),
(UUID(), 'Prednisone', 'Thuốc chống viêm, giảm đau', 23000, 80, NOW(), NOW()),
(UUID(), 'Fluoxetine', 'Thuốc điều trị trầm cảm', 25000, 90, NOW(), NOW()),
(UUID(), 'Lorazepam', 'Thuốc an thần', 19000, 110, NOW(), NOW()),
(UUID(), 'Clopidogrel', 'Thuốc chống đông máu', 30000, 75, NOW(), NOW()),
(UUID(), 'Gabapentin', 'Thuốc điều trị đau thần kinh', 17000, 100, NOW(), NOW()),
(UUID(), 'Sertraline', 'Thuốc chống trầm cảm', 22000, 85, NOW(), NOW()),
(UUID(), 'Ranitidine', 'Thuốc chống loét dạ dày', 15000, 140, NOW(), NOW()),
(UUID(), 'Fexofenadine', 'Thuốc chống dị ứng', 16000, 130, NOW(), NOW()),
(UUID(), 'Mometasone', 'Thuốc chống viêm', 21000, 50, NOW(), NOW()),
(UUID(), 'Levothyroxine', 'Thuốc điều trị suy giáp', 28000, 90, NOW(), NOW()),
(UUID(), 'Vitamins C', 'Vitamin C tăng cường miễn dịch', 10000, 150, NOW(), NOW()),
(UUID(), 'Vitamins D', 'Vitamin D hỗ trợ xương', 12000, 100, NOW(), NOW()),
(UUID(), 'Azithromycin', 'Kháng sinh điều trị nhiễm khuẩn', 22000, 60, NOW(), NOW()),
(UUID(), 'Prednisolone', 'Thuốc chống viêm', 24000, 80, NOW(), NOW()),
(UUID(), 'Salbutamol', 'Thuốc điều trị hen suyễn', 17000, 130, NOW(), NOW()),
(UUID(), 'Doxycycline', 'Kháng sinh điều trị nhiễm khuẩn', 16000, 140, NOW(), NOW()),
(UUID(), 'Levocetirizine', 'Thuốc chống dị ứng', 13000, 120, NOW(), NOW()),
(UUID(), 'Bromhexine', 'Thuốc giảm ho', 9000, 140, NOW(), NOW()),
(UUID(), 'Codeine', 'Thuốc giảm đau, ho', 22000, 110, NOW(), NOW()),
(UUID(), 'Paroxetine', 'Thuốc chống trầm cảm', 25000, 75, NOW(), NOW()),
(UUID(), 'Citalopram', 'Thuốc chống trầm cảm', 24000, 95, NOW(), NOW()),
(UUID(), 'Furosemide', 'Thuốc lợi tiểu', 18000, 100, NOW(), NOW()),
(UUID(), 'Vitamins B12', 'Vitamin B12 hỗ trợ thần kinh', 12000, 200, NOW(), NOW()),
(UUID(), 'Fluticasone', 'Thuốc chống viêm mũi', 21000, 80, NOW(), NOW()),
(UUID(), 'Amlodipine', 'Thuốc điều trị cao huyết áp', 25000, 130, NOW(), NOW()),
(UUID(), 'Atorvastatin', 'Thuốc hạ cholesterol', 28000, 150, NOW(), NOW()),
(UUID(), 'Diphenhydramine', 'Thuốc kháng histamine', 9000, 140, NOW(), NOW()),
(UUID(), 'Chlorpheniramine', 'Thuốc kháng histamine', 8500, 160, NOW(), NOW()),
(UUID(), 'Naproxen', 'Thuốc giảm đau, chống viêm', 15000, 90, NOW(), NOW()),
(UUID(), 'Ceftriaxone', 'Kháng sinh điều trị nhiễm khuẩn', 35000, 60, NOW(), NOW()),
(UUID(), 'Tramadol', 'Thuốc giảm đau', 22000, 85, NOW(), NOW()),
(UUID(), 'Tamsulosin', 'Thuốc điều trị tuyến tiền liệt', 27000, 75, NOW(), NOW()),
(UUID(), 'Methylprednisolone', 'Thuốc chống viêm', 30000, 65, NOW(), NOW()),
(UUID(), 'Rifampin', 'Kháng sinh điều trị lao', 24000, 50, NOW(), NOW()),
(UUID(), 'Clarithromycin', 'Kháng sinh điều trị nhiễm khuẩn', 25000, 90, NOW(), NOW()),
(UUID(), 'Ethambutol', 'Kháng sinh điều trị lao', 23000, 60, NOW(), NOW()),
(UUID(), 'Propranolol', 'Thuốc điều trị cao huyết áp', 15000, 100, NOW(), NOW()),
(UUID(), 'Lansoprazole', 'Thuốc điều trị dạ dày', 18000, 110, NOW(), NOW());


-- Khoa Hồi sức tích cực
INSERT INTO department_services (id, department_id, service_name, description, price, created_at, updated_at) VALUES
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Nội Tim mạch'), 'Khám thường BYTH', 'Khám sức khỏe tổng quát cơ bản', 30000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Nội Tim mạch'), 'Khám theo yêu cầu', 'Khám theo yêu cầu cá nhân', 200000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Nội Tim mạch'), 'Khám nâng cao', 'Khám sức khỏe chuyên sâu', 500000, NOW(), NOW()),

(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Thần kinh'), 'Khám thường BYTH', 'Khám sức khỏe tổng quát cơ bản', 30000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Thần kinh'), 'Khám theo yêu cầu', 'Khám theo yêu cầu cá nhân', 200000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Thần kinh'), 'Khám nâng cao', 'Khám sức khỏe chuyên sâu', 500000, NOW(), NOW()),

(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Hồi sức tích cực'), 'Khám thường BYTH', 'Khám sức khỏe tổng quát cơ bản', 30000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Hồi sức tích cực'), 'Khám theo yêu cầu', 'Khám theo yêu cầu cá nhân', 200000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Hồi sức tích cực'), 'Khám nâng cao', 'Khám sức khỏe chuyên sâu', 500000, NOW(), NOW()),

(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Nội tiết'), 'Khám thường BYTH', 'Khám sức khỏe tổng quát cơ bản', 30000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Nội tiết'), 'Khám theo yêu cầu', 'Khám theo yêu cầu cá nhân', 200000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Nội tiết'), 'Khám nâng cao', 'Khám sức khỏe chuyên sâu', 500000, NOW(), NOW()),

(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Ngoại Tổng hợp'), 'Khám thường BYTH', 'Khám sức khỏe tổng quát cơ bản', 30000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Ngoại Tổng hợp'), 'Khám theo yêu cầu', 'Khám theo yêu cầu cá nhân', 200000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Ngoại Tổng hợp'), 'Khám nâng cao', 'Khám sức khỏe chuyên sâu', 500000, NOW(), NOW()),

(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Cấp cứu'), 'Khám thường BYTH', 'Khám sức khỏe tổng quát cơ bản', 30000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Cấp cứu'), 'Khám theo yêu cầu', 'Khám theo yêu cầu cá nhân', 200000, NOW(), NOW()),
(UUID(), (SELECT id FROM departments WHERE name = 'Khoa Cấp cứu'), 'Khám nâng cao', 'Khám sức khỏe chuyên sâu', 500000, NOW(), NOW());
