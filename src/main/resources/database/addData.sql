--insert user_roles
INSERT INTO user_roles (user_id, role_id) VALUES
((SELECT id FROM users WHERE username = 'doctor'), (SELECT id FROM roles WHERE name = 'DOCTOR')),



-- Insert Hospitals
INSERT INTO hospitals (id, name, address, phone, email, website, description, created_at, updated_at) VALUES
(UNHEX(REPLACE(UUID(), '-', '')), 'Central City Hospital', '123 Main Street, Downtown', '0123456789', 'central@hospital.com', 'www.centralhospital.com', 'A leading healthcare facility with state-of-the-art medical technology', NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Sunrise Medical Center', '456 Health Avenue, Midtown', '0987654321', 'info@sunrisemed.com', 'www.sunrisemedical.com', 'Comprehensive healthcare services with a patient-first approach', NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Green Valley Hospital', '789 Wellness Road, Suburbs', '0564738291', 'contact@greenvalley.com', 'www.greenvalleyhospital.com', 'Specialized in holistic and community-centered healthcare', NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Metropolitan Medical Complex', '789 Urban Plaza, Cityscape', '0543216789', 'info@metromedical.com', 'www.metropolitanhospital.com', 'Advanced healthcare solutions in the heart of the city', NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Riverside Health Institute', '234 River Road, Waterfront', '0876543210', 'contact@riversidehealth.com', 'www.riversidehealthinstitute.com', 'Cutting-edge medical research and patient care', NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Mountain View Medical Center', '567 Highland Street, Mountainside', '0321654987', 'hello@mountainviewmed.com', 'www.mountainviewmedical.com', 'Specialized healthcare serving mountain communities', NOW(), NOW());


-- Insert Departments (using the previously inserted hospital IDs)
-- Note: You'll need to replace the hospital_id with the actual UUIDs from the previous insert
INSERT INTO departments (id, name, description, head_of_department, phone, hospital_id, created_at, updated_at) VALUES
(UNHEX(REPLACE(UUID(), '-', '')), 'Cardiology', 'Specialized heart and cardiovascular care', 'Dr. Emily Wong', '0234567890', (SELECT id FROM hospitals WHERE name = 'Central City Hospital'), NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Neurology', 'Advanced brain and nervous system treatments', 'Dr. Michael Chen', '0345678901', (SELECT id FROM hospitals WHERE name = 'Central City Hospital'), NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Pediatrics', 'Comprehensive child healthcare services', 'Dr. Sarah Kim', '0456789012', (SELECT id FROM hospitals WHERE name = 'Sunrise Medical Center'), NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Oncology', 'Specialized cancer treatment and care', 'Dr. David Lee', '0567890123', (SELECT id FROM hospitals WHERE name = 'Sunrise Medical Center'), NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Orthopedics', 'Bone and joint specialized medical services', 'Dr. Jessica Park', '0678901234', (SELECT id FROM hospitals WHERE name = 'Green Valley Hospital'), NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dermatology', 'Skin health and cosmetic dermatology', 'Dr. Lisa Nguyen', '0987654321', (SELECT id FROM hospitals WHERE name = 'Metropolitan Medical Complex'), NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Gastroenterology', 'Digestive system and gut health specialists', 'Dr. Robert Garcia', '0654321789', (SELECT id FROM hospitals WHERE name = 'Riverside Health Institute'), NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Emergency Medicine', 'Urgent and critical care services', 'Dr. Amanda Rodriguez', '0456789123', (SELECT id FROM hospitals WHERE name = 'Mountain View Medical Center'), NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Gynecology', 'Women health and reproductive services', 'Dr. Karen Thompson', '0789123456', (SELECT id FROM hospitals WHERE name = 'Sunrise Medical Center'), NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), 'Psychiatry', 'Mental health and psychological services', 'Dr. James Wu', '0234567890', (SELECT id FROM hospitals WHERE name = 'Green Valley Hospital'), NOW(), NOW());


-- Insert Department Services
INSERT INTO department_services (id, department_id, service_name, description, price, created_at, updated_at) VALUES
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Cardiology'), 'Heart Check-up', 'Comprehensive cardiovascular examination', 250.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Cardiology'), 'ECG Test', 'Electrocardiogram screening', 100.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Neurology'), 'Brain Scan', 'Advanced neurological imaging', 500.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Neurology'), 'Neurological Consultation', 'Detailed brain health consultation', 200.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Pediatrics'), 'Child Wellness Check', 'Comprehensive pediatric health examination', 150.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Oncology'), 'Cancer Screening', 'Comprehensive cancer detection package', 400.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Orthopedics'), 'Joint Consultation', 'Specialized bone and joint consultation', 180.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Dermatology'), 'Skin Consultation', 'Detailed skin health examination', 150.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Dermatology'), 'Acne Treatment', 'Comprehensive acne management program', 300.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Gastroenterology'), 'Digestive Health Screening', 'Comprehensive digestive system check', 275.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Gastroenterology'), 'Endoscopy', 'Diagnostic internal examination', 450.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Emergency Medicine'), 'Trauma Assessment', 'Immediate critical care evaluation', 500.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Gynecology'), 'Prenatal Check-up', 'Comprehensive pregnancy health monitoring', 200.00, NOW(), NOW()),
(UNHEX(REPLACE(UUID(), '-', '')), (SELECT id FROM departments WHERE name = 'Psychiatry'), 'Mental Health Consultation', 'Initial psychological assessment', 180.00, NOW(), NOW());


-- Insert Doctors
INSERT INTO doctors (id, full_name, degree, gender, email, phone_number, experience, profile_picture_url, rating, review_count, department_id) VALUES
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. Emily Wong', 'MD', 'FEMALE', 'emily.wong@hospital.com', '0901234567', '15 years of experience in Cardiology', 'https://example.com/emily-wong.jpg', 4.8, 250, (SELECT id FROM departments WHERE name = 'Cardiology')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. Michael Chen', 'PhD', 'MALE', 'michael.chen@hospital.com', '0912345678', '12 years specializing in Neurology', 'https://example.com/michael-chen.jpg', 4.7, 180, (SELECT id FROM departments WHERE name = 'Neurology')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. Sarah Kim', 'MD', 'FEMALE', 'sarah.kim@hospital.com', '0923456789', '10 years of pediatric care', 'https://example.com/sarah-kim.jpg', 4.9, 300, (SELECT id FROM departments WHERE name = 'Pediatrics')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. David Lee', 'MD', 'MALE', 'david.lee@hospital.com', '0934567890', '20 years in Oncology research', 'https://example.com/david-lee.jpg', 4.6, 150, (SELECT id FROM departments WHERE name = 'Oncology')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. Jessica Park', 'PhD', 'FEMALE', 'jessica.park@hospital.com', '0945678901', '14 years of Orthopedic surgery', 'https://example.com/jessica-park.jpg', 4.7, 200, (SELECT id FROM departments WHERE name = 'Orthopedics')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. Lisa Nguyen', 'MD', 'FEMALE', 'lisa.nguyen@hospital.com', '0567890123', '12 years in Dermatology', 'https://example.com/lisa-nguyen.jpg', 4.7, 220, (SELECT id FROM departments WHERE name = 'Dermatology')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. Robert Garcia', 'PhD', 'MALE', 'robert.garcia@hospital.com', '0678901234', '15 years in Gastroenterology research', 'https://example.com/robert-garcia.jpg', 4.6, 190, (SELECT id FROM departments WHERE name = 'Gastroenterology')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. Amanda Rodriguez', 'MD', 'FEMALE', 'amanda.rodriguez@hospital.com', '0789012345', '10 years of Emergency Medicine experience', 'https://example.com/amanda-rodriguez.jpg', 4.8, 270, (SELECT id FROM departments WHERE name = 'Emergency Medicine')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. Karen Thompson', 'MD', 'FEMALE', 'karen.thompson@hospital.com', '0890123456', '18 years in Gynecology', 'https://example.com/karen-thompson.jpg', 4.9, 310, (SELECT id FROM departments WHERE name = 'Gynecology')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. James Wu', 'PhD', 'MALE', 'james.wu@hospital.com', '0901234567', '20 years of Psychiatric research', 'https://example.com/james-wu.jpg', 4.5, 160, (SELECT id FROM departments WHERE name = 'Psychiatry')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. Alex Johnson', 'MD', 'MALE', 'alex.johnson@hospital.com', '0612345678', '8 years specializing in sports medicine', 'https://example.com/alex-johnson.jpg', 4.6, 200, (SELECT id FROM departments WHERE name = 'Orthopedics')),
(UNHEX(REPLACE(UUID(), '-', '')), 'Dr. Elena Martinez', 'MD', 'FEMALE', 'elena.martinez@hospital.com', '0723456789', '14 years in pediatric neurology', 'https://example.com/elena-martinez.jpg', 4.7, 240, (SELECT id FROM departments WHERE name = 'Neurology'));

-- Insert 30 fake medicines
INSERT INTO medicines (id, name, description, price, quantity, created_at, updated_at)
VALUES 
(UUID_TO_BIN(UUID()), 'Paracetamol', 'Pain reliever and fever reducer', 2.50, 500, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Amoxicillin', 'Antibiotic used for infections', 10.00, 200, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Ibuprofen', 'Anti-inflammatory and pain reliever', 5.00, 300, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Aspirin', 'Used to reduce pain, fever, or inflammation', 4.00, 150, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Cetirizine', 'Antihistamine for allergy relief', 3.00, 400, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Metformin', 'Used for diabetes management', 6.00, 250, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Atorvastatin', 'Lowers cholesterol levels', 8.00, 100, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Omeprazole', 'Treats acid reflux and ulcers', 7.50, 180, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Salbutamol', 'Relief for asthma and COPD symptoms', 12.00, 120, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Levothyroxine', 'Thyroid hormone replacement', 5.50, 220, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Losartan', 'Treats high blood pressure', 9.00, 300, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Simvastatin', 'Reduces cholesterol levels', 6.50, 150, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Ciprofloxacin', 'Broad-spectrum antibiotic', 15.00, 130, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Hydrochlorothiazide', 'Diuretic for high blood pressure', 4.50, 200, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Prednisone', 'Treats inflammation and autoimmune diseases', 7.00, 160, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Montelukast', 'Prevents asthma attacks', 11.00, 180, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Clopidogrel', 'Prevents blood clots', 14.00, 100, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Ranitidine', 'Treats ulcers and GERD', 5.50, 220, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Furosemide', 'Diuretic for fluid retention', 3.50, 250, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Gabapentin', 'Used for nerve pain and seizures', 8.50, 170, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Sertraline', 'Antidepressant for anxiety and depression', 16.00, 90, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Warfarin', 'Prevents blood clots', 13.50, 80, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Vitamin C', 'Supports immune system', 2.00, 500, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Zinc Supplement', 'Boosts immune system', 3.50, 450, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Azithromycin', 'Antibiotic for bacterial infections', 18.00, 140, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Doxycycline', 'Treats bacterial infections', 9.50, 110, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Loratadine', 'Relieves allergy symptoms', 4.00, 350, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Amlodipine', 'Treats high blood pressure', 6.00, 200, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Pantoprazole', 'Treats acid reflux', 8.00, 180, NOW(), NOW()),
(UUID_TO_BIN(UUID()), 'Insulin', 'Manages blood sugar levels', 20.00, 50, NOW(), NOW());
