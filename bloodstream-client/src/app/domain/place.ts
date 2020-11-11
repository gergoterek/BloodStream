import { OpeningTime } from './openingTime';

export class Place {
    id: number;
    address: string;
    city: string;
    isActive: boolean;
    name: string;
    openingTime: OpeningTime;
}