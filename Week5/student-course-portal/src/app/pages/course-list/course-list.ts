import { Component } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { CourseCard } from '../../components/course-card/course-card';

@Component({
  selector: 'app-course-list',
  imports: [CourseCard, NgFor, NgIf],
  templateUrl: './course-list.html',
  styleUrl: './course-list.css'
})
export class CourseList {
  courses = [
    { id: 1, name: 'Intro to Angular', code: 'CS101', credits: 3 },
    { id: 2, name: 'Data Structures', code: 'CS201', credits: 4 },
    { id: 3, name: 'Database Systems', code: 'CS301', credits: 3 },
    { id: 4, name: 'Operating Systems', code: 'CS302', credits: 4 },
    { id: 5, name: 'Web Development', code: 'CS401', credits: 3 }
  ];

  selectedCourseId: number | null = null;

  onEnroll(courseId: number) {
    console.log('Enrolling in course: ' + courseId);
    this.selectedCourseId = courseId;
  }
}