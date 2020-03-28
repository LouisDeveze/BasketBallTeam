create database if not exists basketball;
use basketball;

-- Team Data Table
create table teams (
    t_id		int not null AUTO_INCREMENT,		
    t_name		varchar(100) not null,
    t_city		varchar(100) not null,
    constraint teams_pk primary key (t_id)		
);

-- Match Data Table
create table matches (
    m_id		int not null AUTO_INCREMENT,
    m_date		date,		
    m_first_team      	int,
    m_second_team	int,
    m_latitude		double,
    m_longitude		double,	
    constraint matches_pk primary key (m_id)
);



-- Player Data Table
create table players (
    p_id		int not null AUTO_INCREMENT,
    p_num		int,
    p_name		varchar(100) not null,		
    p_actual_team      	int,
    constraint players_pk primary key (p_id)
);

-- Action Data Table
create table actions (
    a_id		int not null AUTO_INCREMENT,
    a_player		int,
    a_team      	int,
    a_match		int,
    a_score		int,
    a_time		int,	-- time played in seconds
    a_faults		int,
    constraint actions_pk primary key (a_id)
);

-- teams
insert into teams values (1, 'Bucks', 'Milwaukee');
insert into teams values (2, 'Raptors', 'Toronto');
insert into teams values (3, 'Celtics', 'Boston');
insert into teams values (4, 'Heat', 'Miami');

-- Matches
insert into matches values (1, '2019-05-01', 1, 2, 43.0506969,-87.9158148);
insert into matches values (2, '2019-05-06', 1, 3, 43.643862, -79.378244);
insert into matches values (3, '2019-05-08', 1, 4, 43.0506969,-87.9158148);
insert into matches values (4, '2019-05-13', 2, 3, 43.643862, -79.378244);
insert into matches values (5, '2019-05-21', 2, 4, 36.877710, -94.864430);
insert into matches values (6, '2019-05-29', 3, 4, 36.877710, -94.864430);

-- Players
-- Milwaukee Bucks
insert into players values (1, 7, 'Ersan Ilyasova', 1);
insert into players values (2, 11, 'Brook Lopez', 1);
insert into players values (3, 3, 'George Hill', 1);
insert into players values (4, 6, 'Eric Bledsoe', 1);
insert into players values (5, 22, 'Khris Middleton', 1);
insert into players values (6, 34, 'Giannis Antetokounmpo', 1);
insert into players values (7, 24, 'Pat Connaughton', 1);
insert into players values (8, 5, 'D.J. Wilson', 1);
insert into players values (9, 23, 'Sterling Brown', 1);
insert into players values (10, 0, 'Donte DiVincenzo', 1);
-- Toronto Raptors
insert into players values (11, 7, 'Kyle Lowry', 2);
insert into players values (12, 33, 'Marc Gasol', 2);
insert into players values (13, 9, 'Serge Ibaka', 2);
insert into players values (14, 24, 'Norman Powell', 2);
insert into players values (15, 13, 'Malcolm Miller', 2);
insert into players values (16, 43, 'Pascal Siakam', 2);
insert into players values (17, 22, 'Patrick McCaw', 2);
insert into players values (18, 23, 'Fred VanVleet', 2);
insert into players values (19, 3, 'OG Anunoby', 2);
insert into players values (20, 25, 'Chris Boucher', 2);
-- Boston Celtics
insert into players values (21, 20, 'Gordon Hayward', 3);
insert into players values (22, 9, 'Brad Wanamaker', 3);
insert into players values (23, 36, 'Marcus Smart', 3);
insert into players values (24, 7, 'Jaylen Brown', 3);
insert into players values (25, 0, 'Jayson Tatum', 3);
insert into players values (26, 37, 'Semi Ojeleye', 3);
insert into players values (27, 27, 'Daniel Theis', 3);
insert into players values (28, 44, 'Robert Williams', 3);
insert into players values (29, 45, 'Romeo Langford', 3);
insert into players values (30, 12, 'Grant Williams', 3);
-- Miami Heat
insert into players values (31, 40, 'Udonis Haslem', 4);
insert into players values (32, 7, 'Goran Dragic', 4);
insert into players values (33, 9, 'Kelly Olynyk', 4);
insert into players values (34, 5, 'Derrick Jones Jr.', 4);
insert into players values (35, 13, 'Bam Adebayo', 4);
insert into players values (36, 55, 'Duncan Robinson', 4);
insert into players values (37, 14, 'Tyler Herro', 4);
insert into players values (38, 22, 'Jimmy Butler', 4);
insert into players values (39, 0, 'Meyers Leonard', 4);
insert into players values (40, 25, 'Kendrick Nunn', 4);

-- Actions (id, player, team, match, score, time, faults)
-- Match 1 Team 1
insert into actions values (1, 1, 1, 1, 2, 2400, 3);
insert into actions values (2, 2, 1, 1, 0, 2000, 1);
insert into actions values (3, 3, 1, 1, 7, 400, 0);
insert into actions values (4, 4, 1, 1, 5, 2400, 1);
insert into actions values (5, 5, 1, 1, 0, 2400, 0);
insert into actions values (6, 6, 1, 1, 0, 1200, 0);
insert into actions values (7, 7, 1, 1, 12, 1200, 1);
insert into actions values (8, 8, 1, 1, 18, 2400, 2);
insert into actions values (9, 9, 1, 1, 6, 2400, 1);
insert into actions values (10, 10, 1, 1, 0, 2400, 4);
-- Match 1 Team 2
insert into actions values (11, 11, 2, 1, 12, 2400, 1);
insert into actions values (12, 12, 2, 1, 0, 300, 2);
insert into actions values (13, 13, 2, 1, 0, 400, 3);
insert into actions values (14, 14, 2, 1, 5, 2400, 2);
insert into actions values (15, 15, 2, 1, 8, 2400, 0);
insert into actions values (16, 16, 2, 1, 2, 2400, 5);
insert into actions values (17, 17, 2, 1, 2, 1200, 0);
insert into actions values (18, 18, 2, 1, 0, 2100, 0);
insert into actions values (19, 19, 2, 1, 0, 1200, 3);
insert into actions values (20, 20, 2, 1, 8, 2400, 0);
-- Match 2 Team 1
insert into actions values (21, 1, 1, 2, 12, 2400, 0);
insert into actions values (22, 2, 1, 2, 23, 2000, 0);
insert into actions values (23, 3, 1, 2, 0, 400, 4);
insert into actions values (24, 4, 1, 2, 0, 2400, 1);
insert into actions values (25, 5, 1, 2, 0, 2400, 0);
insert into actions values (26, 6, 1, 2, 0, 1200, 2);
insert into actions values (27, 7, 1, 2, 2, 1200, 0);
insert into actions values (28, 8, 1, 2, 8, 2400, 0);
insert into actions values (29, 9, 1, 2, 6, 2400, 1);
insert into actions values (30, 10, 1, 2, 12, 2400, 0);
-- Match 2 Team 3
insert into actions values (31, 21, 3, 2, 0, 1200, 1);
insert into actions values (32, 22, 3, 2, 0, 300, 2);
insert into actions values (33, 23, 3, 2, 17, 2100, 3);
insert into actions values (34, 24, 3, 2, 6, 2400, 2);
insert into actions values (35, 25, 3, 2, 8, 2400, 0);
insert into actions values (36, 26, 3, 2, 0, 1200, 5);
insert into actions values (37, 27, 3, 2, 10, 2400, 0);
insert into actions values (38, 28, 3, 2, 0, 2400, 0);
insert into actions values (39, 29, 3, 2, 0, 1700, 3);
insert into actions values (40, 30, 3, 2, 3, 700, 0);
-- Match 3 Team 1
insert into actions values (41, 1, 1, 3, 0, 2000, 0);
insert into actions values (42, 2, 1, 3, 4, 2400, 1);
insert into actions values (43, 3, 1, 3, 0, 2400, 5);
insert into actions values (44, 4, 1, 3, 0, 400, 1);
insert into actions values (45, 5, 1, 3, 0, 1200, 4);
insert into actions values (46, 6, 1, 3, 0, 2400, 0);
insert into actions values (47, 7, 1, 3, 4, 2400, 2);
insert into actions values (48, 8, 1, 3, 3, 300, 3);
insert into actions values (49, 9, 1, 3, 12, 2100, 0);
insert into actions values (50, 10, 1, 3, 0, 2400, 0);
-- Match 3 Team 4
insert into actions values (51, 31, 4, 3, 13, 2400, 3);
insert into actions values (52, 32, 4, 3, 31, 300, 4);
insert into actions values (53, 33, 4, 3, 12, 400, 1);
insert into actions values (54, 34, 4, 3, 5, 2400, 0);
insert into actions values (55, 35, 4, 3, 8, 2400, 0);
insert into actions values (56, 36, 4, 3, 2, 2400, 0);
insert into actions values (57, 37, 4, 3, 6, 1200, 1);
insert into actions values (58, 38, 4, 3, 3, 2100, 1);
insert into actions values (59, 39, 4, 3, 0, 1200, 3);
insert into actions values (60, 40, 4, 3, 2, 2400, 8);
-- Match 4 Team 2
insert into actions values (61, 11, 2, 4, 23, 2000, 0);
insert into actions values (62, 12, 2, 4, 14, 2400, 1);
insert into actions values (63, 13, 2, 4, 3, 2400, 5);
insert into actions values (64, 14, 2, 4, 7, 400, 1);
insert into actions values (65, 15, 2, 4, 8, 1200, 4);
insert into actions values (66, 16, 2, 4, 0, 2400, 0);
insert into actions values (67, 17, 2, 4, 4, 2400, 2);
insert into actions values (68, 18, 2, 4, 0, 300, 3);
insert into actions values (69, 19, 2, 4, 0, 2100, 0);
insert into actions values (70, 20, 2, 4, 0, 2400, 0);
-- Match 4 Team 3
insert into actions values (71, 21, 3, 4, 0, 2400, 3);
insert into actions values (72, 22, 3, 4, 0, 2400, 0);
insert into actions values (73, 23, 3, 4, 4, 2400, 1);
insert into actions values (74, 34, 3, 4, 0, 2400, 0);
insert into actions values (75, 25, 3, 4, 0, 1600, 0);
insert into actions values (76, 26, 3, 4, 6, 2400, 0);
insert into actions values (77, 27, 3, 4, 0, 1200, 2);
insert into actions values (78, 28, 3, 4, 13, 300, 1);
insert into actions values (79, 29, 3, 4, 4, 1200, 3);
insert into actions values (80, 30, 3, 4, 2, 2100, 8);
-- Match 5 Team 2
insert into actions values (81, 11, 2, 5, 14, 2400, 0);
insert into actions values (82, 12, 2, 5, 0, 2000, 0);
insert into actions values (83, 13, 2, 5, 0, 400, 3);
insert into actions values (84, 14, 2, 5, 0, 2400, 1);
insert into actions values (85, 15, 2, 5, 14, 1200, 4);
insert into actions values (86, 16, 2, 5, 0, 2400, 1);
insert into actions values (87, 17, 2, 5, 6, 2400, 2);
insert into actions values (88, 18, 2, 5, 6, 2300, 0);
insert into actions values (89, 19, 2, 5, 5, 100, 2);
insert into actions values (90, 20, 2, 5, 2, 2400, 0);
-- Match 5 Team 4
insert into actions values (91, 31, 4, 5, 4, 2400, 0);
insert into actions values (92, 32, 4, 5, 4, 300, 4);
insert into actions values (93, 33, 4, 5, 0, 2400, 1);
insert into actions values (94, 34, 4, 5, 0, 2000, 4);
insert into actions values (95, 35, 4, 5, 8, 2400, 0);
insert into actions values (96, 36, 4, 5, 0, 2400, 6);
insert into actions values (97, 37, 4, 5, 0, 1200, 1);
insert into actions values (98, 38, 4, 5, 0, 2100, 3);
insert into actions values (99, 39, 4, 5, 0, 1200, 3);
insert into actions values (100, 40, 4, 5, 2, 2400, 0);
-- Match 6 Team 3
insert into actions values (101, 21, 3, 6, 0, 500, 3);
insert into actions values (102, 22, 3, 6, 0, 1900, 0);
insert into actions values (103, 23, 3, 6, 4, 2400, 1);
insert into actions values (104, 34, 3, 6, 0, 2400, 0);
insert into actions values (105, 25, 3, 6, 2, 1600, 0);
insert into actions values (106, 26, 3, 6, 5, 2400, 0);
insert into actions values (107, 27, 3, 6, 4, 2400, 2);
insert into actions values (108, 28, 3, 6, 7, 300, 1);
insert into actions values (109, 29, 3, 6, 24, 2400, 3);
insert into actions values (110, 30, 3, 6, 28, 2100, 8);
-- Match 6 Team 4
insert into actions values (111, 31, 4, 6, 2, 2400, 1);
insert into actions values (112, 32, 4, 6, 0, 300, 0);
insert into actions values (113, 33, 4, 6, 5, 2400, 2);
insert into actions values (114, 34, 4, 6, 14, 2400, 4);
insert into actions values (115, 35, 4, 6, 17, 2400, 0);
insert into actions values (116, 36, 4, 6, 0, 2400, 1);
insert into actions values (117, 37, 4, 6, 2, 300, 0);
insert into actions values (118, 38, 4, 6, 2, 2100, 2);
insert into actions values (119, 39, 4, 6, 0, 2400, 0);
insert into actions values (120, 40, 4, 6, 0, 2400, 0);