import { Component } from '@angular/core';
import { CourseService } from '../../services/course';
import { Course } from '../../models/course.model';

@Component({
  selector: 'app-course-summary-widget',
  imports: [],
  templateUrl: './course-summary-widget.html',
  styleUrl: './course-summary-widget.css'
})
export class CourseSummaryWidget {

  constructor(private courseService: CourseService) {}

  get count() {
    return this.courseService.getCourses().length;
  }

  addSampleCourse() {
    const newCourse: Course = {
      id: this.courseService.getCourses().length + 1,
      name: 'New Sample Course',
      code: 'CS999',
      credits: 3,
      gradeStatus: 'pending'
    };
    this.courseService.addCourse(newCourse);
  }
}
