import { Injectable } from '@angular/core';
import { Donor  } from './domain/donor';
import { HttpHeaders, HttpClient } from '@angular/common/http';



export const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': '',
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn = false;
  user: Donor;
  redirectUrl: string;

  private usersUrl = 'http://localhost:8080/donor';

  constructor(
    private http: HttpClient
  ) { }

  async login(username: string, password: string): Promise<boolean> {
    const token = btoa(`${username}:${password}`);
    httpOptions.headers =
      httpOptions.headers.set(
        'Authorization',
        `Basic ${token}`
      );
    try {
      const user = await this.http.post<Donor>(
        `${this.usersUrl}/login`,
        {},
        httpOptions
      ).toPromise();

      this.isLoggedIn = true;
      this.user = user;
      return Promise.resolve(true);
    } catch (e) {
      console.log('hiba', e);
      return Promise.resolve(false);
    }
  }

  isAdmin() {
    return this.user.role === 'ROLE_ADMIN';
  }
  isNurse() {
    return this.user.role === 'ROLE_NURSE';
  }
  isDonor() {
    return this.user.role === 'ROLE_DONOR';
  }
  isGuest() {
    return this.user.role === 'ROLE_GUEST';
  }
  logout() {
    this.isLoggedIn = false;
    this.user = null;
    this.redirectUrl = null;
  }

}
