INSERT INTO roles
    (id,name)
    VALUES
    ('b1110e6c-3e79-47c9-a89f-63e4e3df61bd','ADMIN'),
    ('df74e04c-4249-4f5b-ba60-ffc5cdc6a661','DOCTOR'),
    ('f220a1d2-ac21-4892-b2ea-ac2f5e6471a3','PATIENT');

GO

INSERT INTO users
    (id,is_active,email,password,phone,username)
    VALUES
    ('e9c1e2e3-77fb-4c8a-8ceb-451342ef21e1',1,'user1@gm.com','123456','1234567891','user1'),
    ('f8c415a5-40e1-40e7-b843-42443bb4629a',1,'abcd@fm.com','$2a$10$0WfeSaXhEWmW5snyqOx2Je8uotYaTze26vW85QzBC/sPYDmE/bABq','1234567892','doctortester'),
    ('55bb4b54-95a2-4aba-ac81-4332564c905e',1,'user3@gm.com','123456','1234567893','user3'),
    ('64eb4df5-407f-4366-bde2-1e89505e36ab',1,'user4@gm.com','123456','1234567894','user4'),
    ('a212f2d7-d7a0-4caf-9ee3-cfda4efb49ee',1,'user5@gm.com','123456','1234567895','user5'),
    ('b896b567-3fdf-4cdf-ad52-3557662503b1',1,'user6@gm.com','123456','1234567896','user6'),
    ('c8528a7c-f42b-424b-87d0-754346078f78',1,'user7@gm.com','123456','1234567897','user7'),
    ('86d1eb60-6055-403a-ab73-8acc20829742',1,'user8@gm.com','123456','1234567898','user8');
GO

INSERT INTO user_roles
    (role_id,user_id)
    VALUES
    ('b1110e6c-3e79-47c9-a89f-63e4e3df61bd','e9c1e2e3-77fb-4c8a-8ceb-451342ef21e1'),
    ('df74e04c-4249-4f5b-ba60-ffc5cdc6a661','64eb4df5-407f-4366-bde2-1e89505e36ab'),
    ('df74e04c-4249-4f5b-ba60-ffc5cdc6a661','b896b567-3fdf-4cdf-ad52-3557662503b1'),
    ('df74e04c-4249-4f5b-ba60-ffc5cdc6a661','f8c415a5-40e1-40e7-b843-42443bb4629a'),
    ('df74e04c-4249-4f5b-ba60-ffc5cdc6a661','55bb4b54-95a2-4aba-ac81-4332564c905e'),
    ('df74e04c-4249-4f5b-ba60-ffc5cdc6a661','c8528a7c-f42b-424b-87d0-754346078f78'),
    ('df74e04c-4249-4f5b-ba60-ffc5cdc6a661','86d1eb60-6055-403a-ab73-8acc20829742'),
    ('df74e04c-4249-4f5b-ba60-ffc5cdc6a661','a212f2d7-d7a0-4caf-9ee3-cfda4efb49ee');

GO

INSERT INTO hospitals
    (created_at, updated_at, id, address, description, email, name, phone, website)
VALUES
    ('2024-01-01 09:00:00', '2024-10-01 12:00:00', '09ff87a3-7181-4861-9863-6148474734be', '123 Main St, Thai Binh, Vietnam', 'General hospital with modern facilities', 'contact@hospital1.com', 'Thai Binh General Hospital', '0123456789', 'www.hospital1.com'),
    ('2024-01-02 09:15:00', '2024-10-02 13:00:00', 'b45e0df3-449c-4ab8-a2f4-fd7dff157290', '456 Nguyen Trai St, Hanoi, Vietnam', 'Specialized in cardiac care', 'info@hospital2.com', 'Hanoi Heart Hospital', '0987654321', 'www.hospital2.com'),
    ('2024-01-03 10:00:00', '2024-10-03 14:30:00', 'f8f43bf1-0aaa-4ce2-bc7b-06c1b2dbed56', '789 Le Loi St, Ho Chi Minh City, Vietnam', 'Provides advanced oncology treatments', 'support@hospital3.com', 'Ho Chi Minh Oncology Center', '0345678901', 'www.hospital3.com'),
    ('2024-01-04 11:30:00', '2024-10-04 15:45:00', 'f4238613-01a5-4eae-aab1-570bb0c468ea', '321 Tran Hung Dao St, Da Nang, Vietnam', 'Pediatric hospital with excellent care', 'pediatrics@hospital4.com', 'Da Nang Pediatric Hospital', '0567890123', 'www.hospital4.com'),
    ('2024-01-05 08:45:00', '2024-10-05 10:00:00', '0305d4ac-2376-498e-81ed-9a2091a5f526', '654 Kim Ma St, Hanoi, Vietnam', 'Focused on orthopedics and rehabilitation', 'contact@hospital5.com', 'Hanoi Orthopedic Institute', '0789012345', 'www.hospital5.com'),
    ('2024-01-06 09:20:00', '2024-10-06 11:15:00', 'c6253a0a-26b4-452b-a9ec-a0518dffb650', '987 Phan Dinh Phung St, Hue, Vietnam', 'Renowned for neurology and brain surgery', 'neuro@hospital6.com', 'Hue Neurology Hospital', '0901234567', 'www.hospital6.com'),
    ('2024-01-07 07:50:00', '2024-10-07 09:30:00', 'ed33b9b9-1c34-4714-93f0-ed3bdf775c35', '147 Hai Ba Trung St, Can Tho, Vietnam', 'Offers maternity and newborn care', 'maternity@hospital7.com', 'Can Tho Maternity Hospital', '0321654987', 'www.hospital7.com'),
    ('2024-01-08 12:00:00', '2024-10-08 16:45:00', '8367ec43-a1f8-45cb-97da-f3331a3bb030', '258 Le Thanh Ton St, Nha Trang, Vietnam', 'Leading hospital in dental care', 'dental@hospital8.com', 'Nha Trang Dental Hospital', '0790246813', 'www.hospital8.com');


GO

INSERT INTO departments
    (created_at, updated_at, hospital_id, id, description, head_of_department, name, phone)
VALUES
    ('2024-01-01 09:00:00', '2024-10-01 12:00:00', '09ff87a3-7181-4861-9863-6148474734be', '07ba074a-57c1-466e-9eb5-0cfc4a95682e', 'Department focusing on general medical care', 'Dr. Nguyen Van A', 'General Medicine', '0123456789'),
    ('2024-01-02 10:15:00', '2024-10-02 13:00:00', '09ff87a3-7181-4861-9863-6148474734be', 'd585cb95-a4c2-4f33-beda-2534582bdbbf', 'Department specializing in cardiac treatments', 'Dr. Tran Thi B', 'Cardiology', '0987654321'),
    ('2024-01-03 11:30:00', '2024-10-03 14:30:00', '09ff87a3-7181-4861-9863-6148474734be', '1b13fde7-e71a-46a7-a685-2f234c3fa347', 'Department for cancer treatment and research', 'Dr. Le Quang C', 'Oncology', '0345678901'),
    ('2024-01-04 12:45:00', '2024-10-04 15:45:00', '09ff87a3-7181-4861-9863-6148474734be', '882c5536-5424-4277-a959-50ffafd5ce81', 'Department dedicated to children’s health', 'Dr. Hoang Thi D', 'Pediatrics', '0567890123'),
    ('2024-01-05 08:20:00', '2024-10-05 10:00:00', '09ff87a3-7181-4861-9863-6148474734be', '7260c41f-e6f6-4d42-9b89-761d82ec044b', 'Orthopedics and rehabilitation department', 'Dr. Nguyen Thanh E', 'Orthopedics', '0789012345'),
    ('2024-01-06 09:50:00', '2024-10-06 11:15:00', 'b45e0df3-449c-4ab8-a2f4-fd7dff157290', '1faf2c8a-d8f6-46f5-8f1b-8c40164b593a', 'Department for brain and neurological issues', 'Dr. Pham Minh F', 'Neurology', '0901234567'),
    ('2024-01-07 07:30:00', '2024-10-07 09:30:00', 'b45e0df3-449c-4ab8-a2f4-fd7dff157290', '0bb5f9ab-b0b5-49c0-b192-924ad986abf9', 'Department for maternity and newborn care', 'Dr. Dang Thi G', 'Maternity', '0321654987'),
    ('2024-01-08 12:10:00', '2024-10-08 16:45:00', 'b45e0df3-449c-4ab8-a2f4-fd7dff157290', '105fc87b-14ff-46ec-a1a9-b30f37621c5a', 'Specialized in dental treatments and surgery', 'Dr. Tran Hoang H', 'Dentistry', '0790246813'),
    ('2024-01-09 10:40:00', '2024-10-09 14:20:00', 'b45e0df3-449c-4ab8-a2f4-fd7dff157290', 'd0b093a6-0b47-445c-ba24-e1ae8f44cdf8', 'Department for emergency and trauma care', 'Dr. Vu Thi I', 'Emergency', '0911234567'),
    ('2024-01-10 09:25:00', '2024-10-10 11:55:00', 'b45e0df3-449c-4ab8-a2f4-fd7dff157290', '8f76d56e-fd18-4d4c-afee-fd1aff97d723', 'Focuses on dermatology and skin issues', 'Dr. Bui Minh J', 'Dermatology', '0854321098');


GO

INSERT INTO doctors
    (department_id, id, user_id, degree, email, experience, full_name, gender, phone_number, profile_picture_url)
VALUES
    ('07ba074a-57c1-466e-9eb5-0cfc4a95682e', '03aae157-9e81-4763-aca2-6e4cb54e25b5', 'f8c415a5-40e1-40e7-b843-42443bb4629a', 'MD', 'nguyenvana@example.com', '10 years in General Medicine', 'Nguyen Van A', 'MALE', '0123456789', 'https://example.com/profile1.jpg'),
    ('d585cb95-a4c2-4f33-beda-2534582bdbbf', 'ee28cef3-6fbe-4fd6-bccc-1846a9626bc2', '55bb4b54-95a2-4aba-ac81-4332564c905e', 'PhD', 'tranthib@example.com', '15 years in Cardiology', 'Tran Thi B', 'FEMALE', '0987654321', 'https://example.com/profile2.jpg'),
    ('1b13fde7-e71a-46a7-a685-2f234c3fa347', '9a82e2d7-afd2-476c-b8bd-8914e6aee438', '64eb4df5-407f-4366-bde2-1e89505e36ab', 'MD', 'lequangc@example.com', '12 years in Oncology', 'Le Quang C', 'MALE', '0345678901', 'https://example.com/profile3.jpg'),
    ('1b13fde7-e71a-46a7-a685-2f234c3fa347', '4d1018b0-a85a-4329-a2a8-939a68b2a394', 'a212f2d7-d7a0-4caf-9ee3-cfda4efb49ee', 'MD', 'hoangthid@example.com', '8 years in Pediatrics', 'Hoang Thi D', 'FEMALE', '0567890123', 'https://example.com/profile4.jpg'),
    ('1b13fde7-e71a-46a7-a685-2f234c3fa347', 'ecaadc5c-9d66-4712-8178-94b465607bf6', 'b896b567-3fdf-4cdf-ad52-3557662503b1', 'PhD', 'nguyenthanhe@example.com', '20 years in Orthopedics', 'Nguyen Thanh E', 'MALE', '0789012345', 'https://example.com/profile5.jpg'),
    ('882c5536-5424-4277-a959-50ffafd5ce81', '11b3a300-466d-459a-9dc8-b26f946413c7', 'c8528a7c-f42b-424b-87d0-754346078f78', 'MD', 'phamminhf@example.com', '14 years in Neurology', 'Pham Minh F', 'MALE', '0901234567', 'https://example.com/profile6.jpg'),
    ('882c5536-5424-4277-a959-50ffafd5ce81', '8f50fc19-63cc-424c-8b3b-c29165841ba3', '86d1eb60-6055-403a-ab73-8acc20829742', 'MD', 'dangthig@example.com', '7 years in Maternity', 'Dang Thi G', 'FEMALE', '0321654987', 'https://example.com/profile7.jpg')
