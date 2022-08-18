create table Question( 
Qn serial not null, 
Question text not null, 
Answer text not null, 
answeroption1 text not null, 
answeroption2 text not null, 
answeroption3 text not null,
primary key(Qn)
);
 
create table student(
sn serial not null, 
name text not null, 
email text not null, 
primary key(Sn) 
);

create table Admin(
An serial not null, 
name text not null,
email text not null, 
password text not null,
primary key(An)
);

create table TruthTable(
Sn serial primary key, BooleanValue boolean, foreign key(Sn)
references Question(qn)
);

create table Score(
Sn int not null, scores int not null, 
primary key(Sn), 
foreign key(Sn) references Student(Sn)
);


insert into Admin(An,name,username, email, password)
values(2,'susma','susma', 'tilak@gmail.com',12);


insert into Admin( name, email, password)
values('Tilak Basnet', 'tilak.basnet@epita.fr',1234);




insert into student(sn,name, email)
values(1,'Tilak', 'tilakbsnt@outlook.com');


insert into Score(Sn, scores) 
values(1,5);


select Student.name, Score.scores 
from Student
left join Score on Score.Sn = Student.Sn;


select  Student.name, Score.scores 
from Score left join Student on Score.Sn = Student.Sn;


select * from question;
select * from student;

select email, password  from admin;
select * from admin;
select * from score;
select * from truthtable;


select username, email, password from admin;



delete from student where  sn = 7;

drop table question ;
drop table truthtable;
drop table student;
drop table score;
drop table admin;
