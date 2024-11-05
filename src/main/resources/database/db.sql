CREATE DATABASE hms_db_final;

USE hms_db_final;

CREATE TABLE Role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id INT,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    is_active BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Patient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name NVARCHAR(100),
    date_of_birth DATE,
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    address NVARCHAR(255),
    emergency_contact NVARCHAR(20),
    blood_type NVARCHAR(10),
    allergies NVARCHAR(1000),
    profile_picture_url NVARCHAR(255),
    user_id INT,
);

CREATE TABLE Doctor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name NVARCHAR(100),
    degree NVARCHAR(10),
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    email NVARCHAR(100),
    experience NVARCHAR(1000),
    profile_picture_url NVARCHAR(255),
    department_id INT,
    user_id INT,
);

CREATE TABLE DoctorSchedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT,
    schedule_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Hospital (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100),
    website NVARCHAR(255),
    description TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    hospital_id INT,
    description TEXT,
    head_of_department NVARCHAR(100),
    phone VARCHAR(15),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE DepartmentService (
    id INT AUTO_INCREMENT PRIMARY KEY,
    department_id INT,
    service_name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP
);

-- CREATE TABLE Room (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     room_number VARCHAR(50) NOT NULL,
--     type ENUM('EXAMINATION', 'TESTING', 'WAITING', 'VACCINATION') NOT NULL,
--     department_id INT,
--     created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
--     updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP
-- );

CREATE TABLE HealthCheckAppointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hospital_id INT,
    department_id INT,
    departmentService_id INT,
    patient_id INT,
    doctor_id INT,
    appointment_date DATETIME NOT NULL,
    status ENUM('PENDING', 'COMPLETED', 'CANCELLED') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Prescription (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    appointment_id INT,
    notes VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE PrescriptionMedicine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prescription_id INT,
    medicine_id INT,
    dosage VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    notes VARCHAR(255)
);

CREATE TABLE LabTest (
    id INT AUTO_INCREMENT PRIMARY KEY,
    test_name VARCHAR(100) NOT NULL,
    description TEXT,
    room VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE LabTestAppointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    lab_test_id INT,
    appointment_date DATETIME NOT NULL,
    status ENUM('PENDING', 'COMPLETED', 'CANCELLED') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE MedicalRecord (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    diagnosis TEXT,
    treatment_plan TEXT,
    notes TEXT,
    prescription_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Medicine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Payment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    status ENUM('PENDING', 'COMPLETED', 'FAILED') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Notification (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    message TEXT NOT NULL,
    type VARCHAR(50) NOT NULL,
    status ENUM('SENT', 'READ', 'UNREAD') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Foreign Key Constraints
ALTER TABLE User ADD FOREIGN KEY (role_id) REFERENCES Role(id);
ALTER TABLE Doctor ADD FOREIGN KEY (department_id) REFERENCES Department(id);
ALTER TABLE DoctorSchedule ADD FOREIGN KEY (doctor_id) REFERENCES Doctor(id);
ALTER TABLE Department ADD FOREIGN KEY (hospital_id) REFERENCES Hospital(id);
ALTER TABLE DepartmentService ADD FOREIGN KEY (department_id) REFERENCES Department(id);
-- ALTER TABLE Room ADD FOREIGN KEY (department_id) REFERENCES Department(id);
-- ALTER TABLE HealthCheckAppointment ADD FOREIGN KEY (room_id) REFERENCES Room(id);
ALTER TABLE HealthCheckAppointment ADD FOREIGN KEY (patient_id) REFERENCES Patient(id);
ALTER TABLE HealthCheckAppointment ADD FOREIGN KEY (doctor_id) REFERENCES Doctor(id);
ALTER TABLE HealthCheckAppointment ADD FOREIGN KEY (departmentService_id) REFERENCES DepartmentService(id);
ALTER TABLE HealthCheckAppointment ADD FOREIGN KEY (hospital_id) REFERENCES Hospital(id);
ALTER TABLE HealthCheckAppointment ADD FOREIGN KEY (department_id) REFERENCES Department(id);
ALTER TABLE Prescription ADD FOREIGN KEY (patient_id) REFERENCES Patient(id);
ALTER TABLE Prescription ADD FOREIGN KEY (doctor_id) REFERENCES Doctor(id);
ALTER TABLE Prescription ADD FOREIGN KEY (appointment_id) REFERENCES HealthCheckAppointment(id);
ALTER TABLE PrescriptionMedicine ADD FOREIGN KEY (prescription_id) REFERENCES Prescription(id);
ALTER TABLE PrescriptionMedicine ADD FOREIGN KEY (medicine_id) REFERENCES Medicine(id);
-- ALTER TABLE LabTest ADD FOREIGN KEY (room_id) REFERENCES Room(id);
ALTER TABLE LabTestAppointment ADD FOREIGN KEY (patient_id) REFERENCES Patient(id);
ALTER TABLE LabTestAppointment ADD FOREIGN KEY (lab_test_id) REFERENCES LabTest(id);
ALTER TABLE MedicalRecord ADD FOREIGN KEY (patient_id) REFERENCES Patient(id);
ALTER TABLE MedicalRecord ADD FOREIGN KEY (doctor_id) REFERENCES Doctor(id);
ALTER TABLE MedicalRecord ADD FOREIGN KEY (prescription_id) REFERENCES Prescription(id);
ALTER TABLE Payment ADD FOREIGN KEY (patient_id) REFERENCES Patient(id);
ALTER TABLE Notification ADD FOREIGN KEY (user_id) REFERENCES User(id);
