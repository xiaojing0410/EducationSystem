select * from student;
delete from student;
-- 新增学生数据
-- 查询学生单科成绩
select grades.id,grades.course_id,grades.semester,grades.score from course, grades
             where course.id = grades.course_id
             and course.course_name='数学'
             and grades.id=1;
-- 查询学期成绩
select grades.id,grades.course_id,grades.semester,grades.score from course, grades
where course.id = grades.course_id and grades.id=#{userId} and semester=#{semester};

-- 查询所有成绩
select grades.id,grades.course_id,grades.semester,grades.score from course, grades
where course.id = grades.course_id and grades.id=#{userId};

select * from scholarships;