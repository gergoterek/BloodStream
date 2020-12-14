import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if (this.authService.isLoggedIn) {
      if (next.data && next.data.roles) {
        if (next.data.roles.includes(this.authService.user.role)) {
          return true;
        } else {
          return false;
        }
      }
      else return true
    }

    this.authService.redirectUrl = state.url;
    this.router.navigate(['/login']);
    return false;
  }
  
}
