import { Injectable } from '@angular/core';

@Injectable()
export class Notification {
  private messages: string[] = [];

  addMessage(msg: string) {
    this.messages.push(msg);
  }

  getMessages() {
    return this.messages;
  }
}
