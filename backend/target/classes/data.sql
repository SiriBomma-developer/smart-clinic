-- Users
INSERT INTO users(email, password_hash, role) VALUES
('admin@clinic.com', 'password', 'ADMIN'),
('doctor1@clinic.com', 'password', 'DOCTOR'),
('doctor2@clinic.com', 'password', 'DOCTOR'),
('patient1@clinic.com', 'password', 'PATIENT'),
('patient2@clinic.com', 'password', 'PATIENT');

-- Doctors
INSERT INTO doctor(name, speciality, available_from, available_to, email) VALUES
('Dr. Alice Smith', 'Cardiology', '09:00:00', '17:00:00', 'doctor1@clinic.com'),
('Dr. Bob Tan', 'Dermatology', '10:00:00', '16:00:00', 'doctor2@clinic.com');

-- Patients (at least 5 to satisfy LIMIT 5 demo)
INSERT INTO patient(name, email, phone) VALUES
('John Doe', 'patient1@clinic.com', '111-111-1111'),
('Jane Roe', 'patient2@clinic.com', '222-222-2222'),
('Sam Patel', 'sam@clinic.com', '333-333-3333'),
('Kim Lee', 'kim@clinic.com', '444-444-4444'),
('Luis Gomez', 'luis@clinic.com', '555-555-5555');

-- Appointments (some for today)
INSERT INTO appointment(doctor_id, patient_id, start_time, end_time, status) VALUES
(1, 1, CONCAT(CURDATE(), ' 09:00:00'), CONCAT(CURDATE(), ' 09:30:00'), 'BOOKED'),
(1, 2, CONCAT(CURDATE(), ' 10:00:00'), CONCAT(CURDATE(), ' 10:30:00'), 'BOOKED'),
(2, 3, CONCAT(CURDATE(), ' 11:00:00'), CONCAT(CURDATE(), ' 11:30:00'), 'BOOKED');
