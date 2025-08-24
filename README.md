# Smart Clinic — Starter

This repo matches your grading rubric. Follow the steps to run backend, seed sample data, and produce screenshots.

## Prereqs
- Java 21, Maven
- Docker (for MySQL) _or_ local MySQL 8+
- `jq` for pretty JSON (macOS: `brew install jq`)

## 1) Start MySQL
```bash
docker run --name mysql-smart -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=smart_clinic -p 3306:3306 -d mysql:8
```

## 2) Build & Run Backend
```bash
cd backend
mvn spring-boot:run
```

## 3) Log in to get a JWT
```bash
curl -s -X POST http://localhost:8080/api/auth/login   -H "Content-Type: application/json"   -d '{"email":"admin@clinic.com","password":"password"}' | jq .
```
Copy `token` from the response.

## 4) Required `curl` commands
**All doctors (Q24)**
```bash
TOKEN="<paste>"
curl -s -H "Authorization: Bearer $TOKEN" http://localhost:8080/api/doctors | jq .
```

**Patient's appointments (Q25) - login as patient**  
Login first:
```bash
curl -s -X POST http://localhost:8080/api/auth/login   -H "Content-Type: application/json"   -d '{"email":"patient1@clinic.com","password":"password"}' | jq .
```
Then:
```bash
TOKEN="<patient_token>"
curl -s -H "Authorization: Bearer $TOKEN" http://localhost:8080/api/appointments/my | jq .
```

**Doctor search (Q26)**  
```bash
curl -s -H "Authorization: Bearer $TOKEN"  "http://localhost:8080/api/doctors/search?speciality=Cardiology&date=2025-08-24&from=09:00&to=12:00" | jq .
```

## 5) SQL items
Enter the container shell:
```bash
docker exec -it mysql-smart mysql -uroot -proot smart_clinic
```
Run:
```sql
SHOW TABLES;
SELECT * FROM patient LIMIT 5;
SOURCE /docker-entrypoint-initdb.d/procedures.sql;
CALL GetDailyAppointmentReportByDoctor(1, CURDATE());
CALL GetDoctorWithMostPatientsByMonth(YEAR(CURDATE()), MONTH(CURDATE()));
CALL GetDoctorWithMostPatientsByYear(YEAR(CURDATE()));
```

## 6) Screenshots to submit
- `docs/screenshots/admin-login.png` — from `frontend/admin-login.html`
- `docs/screenshots/doctor-login.png` — from `frontend/doctor-login.html`
- `docs/screenshots/patient-login.png` — from `frontend/patient-login.html`
- `docs/screenshots/admin-add-doctor.png` — after logging in via `frontend/admin-panel.html`, submit the form
- `docs/screenshots/patient-search-doctor.png` — `frontend/patient-search.html`
- `docs/screenshots/doctor-appointments.png` — `frontend/doctor-appointments.html`
- SQL outputs and curl outputs as terminal screenshots

**macOS quick tip:** `Shift + Cmd + 4` to capture region.

## 7) Dockerize backend
```bash
cd backend
docker build -t smart-clinic-backend .
docker run --rm -p 8080:8080 --name smart-clinic --link mysql-smart:mysql smart-clinic-backend
```

## 8) GitHub Links (paste these in answers)
- Issues board: `https://github.com/<you>/smart-clinic/issues`
- `backend/schema-design.md`
- Code files as per rubric paths
- Workflow: `.github/workflows/build-backend.yml`
- Dockerfile: `backend/Dockerfile`
