import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';

function noCourseCode(control: AbstractControl): ValidationErrors | null {
  if (control.value && typeof control.value === 'string' && control.value.startsWith('XX')) {
    return { noCourseCode: true };
  }
  return null;
}

function simulateEmailCheck(control: AbstractControl): Promise<ValidationErrors | null> {
  return new Promise(resolve => {
    setTimeout(() => {
      if (control.value && control.value.includes('test@')) {
        resolve({ emailTaken: true });
      } else {
        resolve(null);
      }
    }, 800);
  });
}

@Component({
  selector: 'app-reactive-enrollment-form',
  imports: [ReactiveFormsModule, NgIf, NgFor],
  templateUrl: './reactive-enrollment-form.html',
  styleUrl: './reactive-enrollment-form.css'
})
export class ReactiveEnrollmentForm implements OnInit {
  enrollForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: this.fb.control('', [Validators.required, Validators.email], [simulateEmailCheck]),
      courseId: ['', [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', Validators.required],
      agreeToTerms: [false, Validators.requiredTrue],
      additionalCourses: this.fb.array([])
    });
  }

  // A typed getter is better than casting directly in the template because:
  // 1. It keeps the template clean and readable (no "$any()" or "as FormArray" clutter)
  // 2. The cast logic lives in one place in the TypeScript file, easy to find and fix
  // 3. TypeScript can catch type errors on .push()/.removeAt() calls made against this getter
  get additionalCourses() {
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  addCourse() {
    this.additionalCourses.push(new FormControl('', Validators.required));
  }

  removeCourse(index: number) {
    this.additionalCourses.removeAt(index);
  }

  onSubmit() {
    // enrollForm.value excludes any controls marked as disabled.
    // enrollForm.getRawValue() includes every control regardless of disabled state.
    console.log('enrollForm.value:', this.enrollForm.value);
    console.log('enrollForm.getRawValue():', this.enrollForm.getRawValue());
  }
}
