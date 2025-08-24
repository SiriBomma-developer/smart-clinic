# Commands for Screenshots

## Curl
- Q24: `curl -s -H "Authorization: Bearer $TOKEN" http://localhost:8080/api/doctors | jq .`
- Q25: `curl -s -H "Authorization: Bearer $PATIENT_TOKEN" http://localhost:8080/api/appointments/my | jq .`
- Q26: `curl -s -H "Authorization: Bearer $TOKEN" "http://localhost:8080/api/doctors/search?speciality=Cardiology&date=2025-08-24&from=09:00&to=12:00" | jq .`

## SQL inside MySQL shell
- `SHOW TABLES;`
- `SELECT * FROM patient LIMIT 5;`
- `SOURCE /procedures.sql;`  (if you mounted the file into container)
- `CALL GetDailyAppointmentReportByDoctor(1, CURDATE());`
- `CALL GetDoctorWithMostPatientsByMonth(YEAR(CURDATE()), MONTH(CURDATE()));`
- `CALL GetDoctorWithMostPatientsByYear(YEAR(CURDATE()));`

## macOS Screenshot
- `Shift+Cmd+4` to select region.
