import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { NgSwitch, NgSwitchCase, NgClass, NgStyle, NgIf } from '@angular/common';
import { CreditLabelPipe } from '../../pipes/credit-label-pipe';
import { Highlight } from '../../directives/highlight';

@Component({
  selector: 'app-course-card',
  imports: [NgSwitch, NgSwitchCase, NgClass, NgStyle, NgIf, CreditLabelPipe, Highlight],
  templateUrl: './course-card.html',
  styleUrl: './course-card.css'
})
export class CourseCard implements OnChanges {
  @Input() course!: { id: number, name: string, code: string, credits: number, gradeStatus?: string };
  @Output() enrollRequested = new EventEmitter<number>();

  isEnrolled = false;
  isExpanded = false;

  ngOnChanges(changes: SimpleChanges) {
    if (changes['course']) {
      console.log('previous:', changes['course'].previousValue);
      console.log('current:', changes['course'].currentValue);
    }
  }

  get borderColor() {
    if (this.course.gradeStatus === 'passed') return 'green';
    if (this.course.gradeStatus === 'failed') return 'red';
    return 'grey';
  }

  get cardClasses() {
    return {
      'card--enrolled': this.isEnrolled,
      'card--full': this.course.credits >= 4,
      'expanded': this.isExpanded
    };
  }

  toggleExpanded() {
    this.isExpanded = !this.isExpanded;
  }

  onEnrollClick() {
    this.isEnrolled = true;
    this.enrollRequested.emit(this.course.id);
  }
}