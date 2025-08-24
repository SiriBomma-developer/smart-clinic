DELIMITER //
CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN p_doctor_id BIGINT, IN p_date DATE)
BEGIN
  SELECT a.id, a.start_time, a.end_time, a.status
  FROM appointment a
  WHERE a.doctor_id = p_doctor_id AND DATE(a.start_time) = p_date
  ORDER BY a.start_time;
END //
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(IN p_year INT, IN p_month INT)
BEGIN
  SELECT d.id, d.name, COUNT(DISTINCT a.patient_id) AS patient_count
  FROM appointment a JOIN doctor d ON d.id = a.doctor_id
  WHERE YEAR(a.start_time)=p_year AND MONTH(a.start_time)=p_month
  GROUP BY d.id
  ORDER BY patient_count DESC
  LIMIT 1;
END //
CREATE PROCEDURE GetDoctorWithMostPatientsByYear(IN p_year INT)
BEGIN
  SELECT d.id, d.name, COUNT(DISTINCT a.patient_id) AS patient_count
  FROM appointment a JOIN doctor d ON d.id = a.doctor_id
  WHERE YEAR(a.start_time)=p_year
  GROUP BY d.id
  ORDER BY patient_count DESC
  LIMIT 1;
END //
DELIMITER ;
