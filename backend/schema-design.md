# MySQL Schema Design (Smart Clinic)

## Tables

### `users`
- `id` BIGINT PK
- `email` VARCHAR(255) UNIQUE NOT NULL
- `password_hash` VARCHAR(255) NOT NULL
- `role` ENUM('ADMIN','DOCTOR','PATIENT') NOT NULL

### `doctor`
- `id` BIGINT PK
- `name` VARCHAR(255) NOT NULL
- `speciality` VARCHAR(100) NOT NULL
- `available_from` TIME NOT NULL
- `available_to` TIME NOT NULL
- `email` VARCHAR(255) UNIQUE NOT NULL

### `patient`
- `id` BIGINT PK
- `name` VARCHAR(255) NOT NULL
- `email` VARCHAR(255) UNIQUE NOT NULL
- `phone` VARCHAR(32) UNIQUE NOT NULL

### `appointment`
- `id` BIGINT PK
- `doctor_id` BIGINT FK -> `doctor.id`
- `patient_id` BIGINT FK -> `patient.id`
- `start_time` DATETIME NOT NULL
- `end_time` DATETIME NOT NULL
- `status` ENUM('BOOKED','CANCELLED','COMPLETED') DEFAULT 'BOOKED'

### `prescription`
- `id` BIGINT PK
- `appointment_id` BIGINT FK -> `appointment.id`
- `notes` TEXT
- `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP

## Relationships
- A `doctor` has many `appointment`s; an `appointment` belongs to one doctor.
- A `patient` has many `appointment`s; an `appointment` belongs to one patient.
- A `prescription` is created for one `appointment`.
