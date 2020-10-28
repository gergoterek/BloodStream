import { Role } from '../domain/role';
import { BloodType } from '../domain/bloodtype';

export interface Donor{
    id: number;
    taj: number;
    birthDate: string;
    bloodType: BloodType;
    name: string;
    idCard: string;
    male: boolean;
    nextDonationDate: string;
    password: string;
    role: Role;
    totalDonations: number;
    userName: string;

}