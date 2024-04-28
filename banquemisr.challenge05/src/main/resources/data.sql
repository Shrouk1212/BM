INSERT INTO user_profile (username, password, role) VALUES ('user1', '$2y$10$A0bqyud5gF.aB60NZ2J.VOasVI9mHL8hK6X2td0dqpBk47dZucsnW', 'USER');
INSERT INTO user_profile (username, password, role) VALUES ('admin1', '$2y$10$eq8wX6QuqwD6EfEhSeNXm.uteB7qdBJLHJzD8V7FQaAPRGTVMhG/m', 'ADMIN');
INSERT INTO task (title, description, status, priority, due_date) VALUES
('Complete project proposal', 'Finish writing the project proposal document and submit it to the supervisor', 'In Progress', 'High', '2024-05-10');

INSERT INTO task (title, description, status, priority, due_date) VALUES
('Prepare presentation slides', 'Create slides for the upcoming project presentation meeting', 'Pending', 'Medium', '2024-05-15');

INSERT INTO task (title, description, status, priority, due_date) VALUES
('Review code changes', 'Review and test the code changes made by team members in the feature branch', 'Pending', 'Low', '2024-05-08');

INSERT INTO task (title, description, status, priority, due_date) VALUES
('Update user documentation', 'Update the user guide documentation with the latest features and changes', 'Completed', 'Medium', '2024-05-20');

INSERT INTO task (title, description, status, priority, due_date) VALUES
('Fix bug in login module', 'Investigate and fix the issue reported by users related to login functionality', 'In Progress', 'High', '2024-05-12');
