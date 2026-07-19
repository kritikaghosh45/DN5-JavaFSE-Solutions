import { Component } from '@angular/core';
import { NgFor } from '@angular/common';
import { Notification } from '../../services/notification';

// providers: [Notification] here creates a NEW, separate instance of the
// Notification service scoped only to this component and its children.
// This is different from providedIn: 'root', which shares ONE instance
// across the whole app. Component-level providers are useful when each
// instance of a component needs its own isolated state — for example,
// if this NotificationComponent were used multiple times on a page
// (e.g. one per widget), each copy would get its own independent
// message list instead of sharing one global list.
@Component({
  selector: 'app-notification',
  imports: [NgFor],
  providers: [Notification],
  templateUrl: './notification.html',
  styleUrl: './notification.css'
})
export class NotificationComponent {

  constructor(private notification: Notification) {}

  get messages() {
    return this.notification.getMessages();
  }

  addTestMessage() {
    this.notification.addMessage('Test notification at ' + new Date().toLocaleTimeString());
  }
}
