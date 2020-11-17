import { Application } from './application';
import { Donor } from './donor';

export class Message {
    msgId: number;
    message: string;
    seen: boolean;
    sendDate: string;
    title: string;
    application: Application;
    donor: Donor;

}