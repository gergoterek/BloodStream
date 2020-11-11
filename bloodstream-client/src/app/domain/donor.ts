import { Role } from '../domain/role';
import { BloodType } from '../domain/bloodtype';

export class Donor{
    id: number;
    taj: number;
    birthDate: string;
    bloodType: BloodType;
    donorName: string;
    idCard: string;
    male: boolean;
    nextDonationDate: string;
    role: Role;
    totalDonations: number;
    username: string;
    password: string;
}