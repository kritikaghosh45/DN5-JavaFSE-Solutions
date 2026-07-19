import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { CourseCard } from '../../components/course-card/course-card';

@Component({
  selector: 'app-course-list',
  imports: [CourseCard, NgFor, NgIf],
  templateUrl: './course-list.html',
  styleUrl: './course-list.css'
})
export class CourseList implements OnInit {
  isLoading = true;

  courses = [
    { id: 1, name: 'Intro to Angular', code: 'CS101', credits: 3, gradeStatus: 'passed' },
    { id: 2, name: 'Data Structures', code: 'CS201', credits: 4, gradeStatus: 'failed' },
    { id: 3, name: 'Database Systems', code: 'CS301', credits: 3, gradeStatus: 'pending' },
    { id: 4, name: 'Operating Systems', code: 'CS302', credits: 4, gradeStatus: 'passed' },
    { id: 5, name: 'Web Development', code: 'CS401', credits: 3, gradeStatus: 'pending' }
  ];

  selectedCourseId: number | null = null;

  ngOnInit() {
    setTimeout(() => {
      this.isLoading = false;
    }, 1500);
  }

  // trackBy improves performance because Angular can identify which specific
  // array item changed (by its unique id) instead of destroying and re-creating
  // every DOM element in the list whenever the array reference changes.
  trackByCourseId(index: number, course: any) {
    return course.id;
  }

  onEnroll(courseId: number) {
    console.log('Enrolling in course: ' + courseId);
    this.selectedCourseId = courseId;
  }
}