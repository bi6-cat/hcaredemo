CREATE DATABASE hms_db_final;

USE hms_db_final;

CREATE TABLE roles (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
    id BINARY(16) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL UNIQUE,
    is_active BIT,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE user_roles (
    user_id BINARY(16) NOT NULL,
    role_id BINARY(16) NOT NULL,
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE patients (
    id BINARY(16) PRIMARY KEY,
    full_name VARCHAR(100),
    date_of_birth DATE,
    gender VARCHAR(10),
    address VARCHAR(255),
    emergency_contact VARCHAR(20),
    blood_type VARCHAR(10),
    allergies VARCHAR(1000),
    profile_picture_url VARCHAR(255),
    phone_number VARCHAR(20),
    health_insurance_number VARCHAR(50),
    ethnicity VARCHAR(50),
    user_id BINARY(16) UNIQUE
);

CREATE TABLE doctors (
    id BINARY(16) PRIMARY KEY,
    full_name VARCHAR(100),
    degree VARCHAR(10),
    gender VARCHAR(10),
    email VARCHAR(100),
    phone_number VARCHAR(20),
    experience VARCHAR(1000),
    profile_picture_url VARCHAR(255),
    rating DECIMAL(5,2),
    review_count INT,
    department_id BINARY(16),
    user_id BINARY(16) UNIQUE
);

CREATE TABLE doctor_schedules (
    id BINARY(16) PRIMARY KEY,
    doctor_id BINARY(16),
    schedule_date DATE NOT NULL,
    time TIME NOT NULL,
    is_available BIT,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE hospitals (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(15),
    email VARCHAR(100),
    website VARCHAR(255),
    description TEXT,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE departments (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    head_of_department VARCHAR(100),
    phone VARCHAR(15),
    hospital_id BINARY(16),
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE department_services (
    id BINARY(16) PRIMARY KEY,
    department_id BINARY(16),
    service_name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE health_check_appointments (
    id BINARY(16) PRIMARY KEY,
    hospital_id BINARY(16),
    department_id BINARY(16),
    department_service_id BINARY(16),
    patient_id BINARY(16),
    doctor_id BINARY(16),
    appointment_date DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    code VARCHAR(50) UNIQUE,
    note TEXT,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE prescriptions (
    id BINARY(16) PRIMARY KEY,
    patient_id BINARY(16),
    doctor_id BINARY(16),
    appointment_id BINARY(16),
    notes VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE prescription_medicines (
    id BINARY(16) PRIMARY KEY,
    prescription_id BINARY(16),
    medicine_id BINARY(16),
    dosage VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    note VARCHAR(255)
);

CREATE TABLE lab_tests (
    id BINARY(16) PRIMARY KEY,
    test_name VARCHAR(100) NOT NULL,
    description TEXT,
    room VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE lab_test_appointments (
    id BINARY(16) PRIMARY KEY,
    patient_id BINARY(16),
    lab_test_id BINARY(16),
    appointment_date DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE medical_records (
    id BINARY(16) PRIMARY KEY,
    patient_id BINARY(16),
    doctor_id BINARY(16),
    diagnosis TEXT,
    treatment_plan TEXT,
    notes TEXT,
    prescription_id BINARY(16) UNIQUE,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE medicines (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE payments (
    id BINARY(16) PRIMARY KEY,
    patient_id BINARY(16),
    amount DECIMAL(10,2) NOT NULL,
    appointment_code VARCHAR(50) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at DATETIME
);

CREATE TABLE notifications (
    id BINARY(16) PRIMARY KEY,
    user_id BINARY(16),
    message VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE activities (
    id BINARY(16) PRIMARY KEY,
    action VARCHAR(255),
    admin_name VARCHAR(255),
    status VARCHAR(255),
    timestamp DATETIME
);

-- Foreign Key Constraints
ALTER TABLE user_roles ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE user_roles ADD FOREIGN KEY (role_id) REFERENCES roles(id);
ALTER TABLE patients ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE doctors ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE doctors ADD FOREIGN KEY (department_id) REFERENCES departments(id);
ALTER TABLE doctor_schedules ADD FOREIGN KEY (doctor_id) REFERENCES doctors(id);
ALTER TABLE departments ADD FOREIGN KEY (hospital_id) REFERENCES hospitals(id);
ALTER TABLE department_services ADD FOREIGN KEY (department_id) REFERENCES departments(id);
ALTER TABLE health_check_appointments ADD FOREIGN KEY (hospital_id) REFERENCES hospitals(id);
ALTER TABLE health_check_appointments ADD FOREIGN KEY (department_id) REFERENCES departments(id);
ALTER TABLE health_check_appointments ADD FOREIGN KEY (department_service_id) REFERENCES department_services(id);
ALTER TABLE health_check_appointments ADD FOREIGN KEY (patient_id) REFERENCES patients(id);
ALTER TABLE health_check_appointments ADD FOREIGN KEY (doctor_id) REFERENCES doctors(id);
ALTER TABLE prescriptions ADD FOREIGN KEY (patient_id) REFERENCES patients(id);
ALTER TABLE prescriptions ADD FOREIGN KEY (doctor_id) REFERENCES doctors(id);
ALTER TABLE prescriptions ADD FOREIGN KEY (appointment_id) REFERENCES health_check_appointments(id);
ALTER TABLE prescription_medicines ADD FOREIGN KEY (prescription_id) REFERENCES prescriptions(id);
ALTER TABLE prescription_medicines ADD FOREIGN KEY (medicine_id) REFERENCES medicines(id);
ALTER TABLE lab_test_appointments ADD FOREIGN KEY (patient_id) REFERENCES patients(id);
ALTER TABLE lab_test_appointments ADD FOREIGN KEY (lab_test_id) REFERENCES lab_tests(id);
ALTER TABLE medical_records ADD FOREIGN KEY (patient_id) REFERENCES patients(id);
ALTER TABLE medical_records ADD FOREIGN KEY (doctor_id) REFERENCES doctors(id);
ALTER TABLE medical_records ADD FOREIGN KEY (prescription_id) REFERENCES prescriptions(id);
ALTER TABLE payments ADD FOREIGN KEY (patient_id) REFERENCES patients(id);
ALTER TABLE notifications ADD FOREIGN KEY (user_id) REFERENCES users(id);