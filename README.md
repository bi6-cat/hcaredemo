# HCare - Hệ thống đặt lịch khám và chăm sóc sức khỏe

## Mô tả
**HCare** là một hệ thống quản lý chăm sóc sức khỏe được thiết kế để hỗ trợ đặt lịch hẹn, quản lý bệnh nhân, bác sĩ, và các dịch vụ liên quan.  
Dự án được phát triển với mục tiêu cung cấp một nền tảng dễ sử dụng và hiệu quả cho các bệnh viện hoặc phòng khám.

---

## Tính năng chính
- **Quản lý bệnh nhân**: Thêm, sửa, xóa và xem thông tin bệnh nhân.
- **Đặt lịch khám**: Đặt lịch khám bệnh nhanh chóng qua các bước hướng dẫn.
- **Quản lý bác sĩ**: Thông tin về bác sĩ và lịch làm việc.
- **Dịch vụ đặt lịch**: Quản lý các phòng ban và dịch vụ bệnh viện.
- **Theo dõi và kê đơn**: Theo dõi lịch trình và tình trạng bệnh nhân, bác sĩ.
- **Tương tác thân thiện với người dùng**: Giao diện đơn giản và trực quan.

---

## Công nghệ sử dụng
- **Backend**: Java Spring Boot
- **Frontend**: HTML/CSS/JavaScript (Tích hợp API nếu cần)
- **Database**: MySQL
- **ORM**: Hibernate
- **IDE**: IntelliJ IDEA / VSCode

---

## Cách cài đặt

### Yêu cầu hệ thống
- **Java**: Phiên bản 21 trở lên
- **MySQL**: Phiên bản 8.0+
- **Maven**: Để quản lý dependencies
- **IDE**: IntelliJ IDEA hoặc NetBeans để phát triển và chạy dự án

### Hướng dẫn cài đặt
1. **Clone repository**:
   ```bash
   git clone https://github.com/bi6-cat/hcaredemo.git
   cd hcaredemo
2. **Cấu hình cơ sở dữ liệu:**
   Tạo một database MySQL mới:
   sql
   ```bash
   CREATE DATABASE hmsdb;
3. **Cập nhật file cấu hình application.properties:**
   properties
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/hmsdb
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
4. **Chạy dự án:**
   Sử dụng Maven để build và chạy:
   ```bash
   mvn spring-boot:run
5. **Truy cập ứng dụng tại:** http://localhost:8080

