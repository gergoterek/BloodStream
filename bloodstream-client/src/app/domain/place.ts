import { OpeningTime } from './openingTime';

export class Place {
    id: number;
    name: string;
    city: string;
    address: string;
    active: boolean;
    openingTime: OpeningTime;
}