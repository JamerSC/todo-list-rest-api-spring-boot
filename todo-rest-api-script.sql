create database if not exists `todo_db`;
use `todo_db`;

drop table if exists `todo`;

create table `todo` (
 `id` integer not null auto_increment,
 `description` varchar(255),
 `status` varchar(255),
 `title` varchar(255),
 primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

insert into `todo` (`description`, `status`, `title`) values
('Buy vegetables and fruits for the week', 'Pending', 'Grocery Shopping'),
('Fix the leaking kitchen faucet', 'In Progress', 'Home Maintenance'),
('Prepare slides for Monday''s meeting', 'Pending', 'Work Presentation'),
('Finish reading "Atomic Habits"', 'Completed', 'Reading Goal'),
('Call mom and dad', 'Pending', 'Family Time'),
('Update LinkedIn profile with recent project', 'Completed', 'Career Task'),
('Pay internet and electricity bills', 'Pending', 'Monthly Bills'),
('Clean the garage and organize tools', 'In Progress', 'Weekend Cleanup'),
('Practice Spring Boot REST API CRUD', 'Pending', 'Coding Practice'),
('Schedule a doctor''s appointment', 'Pending', 'Health Checkup');

select * from `todo`;
