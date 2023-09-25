INSERT INTO tb_employee (is_active, name, role) VALUES (true, 'Jhonny Azevedo', 'HairStylist');
INSERT INTO tb_employee (is_active, name, role) VALUES (true, 'Mylena Moraes', 'Manicure');


INSERT INTO tb_schedule (appointment_date, employee_id, client_name, end_time, start_time, service_booked)
VALUES
    ('2023-09-15', 1, 'Cliente 1', 'H_8_30', 'H_8_00', 'Serviço 1'),
    ('2023-09-15', 1, 'Cliente 2', 'H_9_30', 'H_9_00', 'Serviço 2'),
    ('2023-09-15', 1, 'Cliente 3', 'H_10_30', 'H_10_00', 'Serviço 3');



INSERT INTO tb_schedule (appointment_date, employee_id, client_name, end_time, start_time, service_booked)
VALUES
    ('2023-09-15', 2, 'Cliente 4', 'H_11_30', 'H_11_00', 'Serviço 4'),
    ('2023-09-15', 2, 'Cliente 5', 'H_14_00', 'H_13_30', 'Serviço 5'),
    ('2023-09-15', 2, 'Cliente 6', 'H_15_00', 'H_14_30', 'Serviço 6');

