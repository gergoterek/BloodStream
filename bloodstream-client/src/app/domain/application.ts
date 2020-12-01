import { Donation } from './donation';
import { Donor } from './donor';
import { Place } from './place';

export class Application {
    applyId: number;
    appliedDate: string;
    directedDonationCode: number;
    donation: Donation;
    donor: Donor;
    place: Place;
}