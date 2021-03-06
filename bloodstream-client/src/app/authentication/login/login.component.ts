import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  message: string;
  hidePassword = true;
  form = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(6)]],
    password: ['', [Validators.required, Validators.minLength(8)]],
  });

  get username() { return this.form.get('username'); }
  get password() { return this.form.get('password'); }


  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
    
  ) {
    
   }

  ngOnInit(): void {
    // if (this.authService.isLoggedIn){
    //   this.authService.logout();
    // }
    
  }

  async onSubmit() {
    const success = await this.authService.login(
      this.username.value, 
      this.password.value
    )
    if (success) {
      const url = this.authService.redirectUrl
        ? this.authService.redirectUrl
        : '/donor';
      this.router.navigate([url])
    } else {
      this.message = 'Login failed!'
    }
  }

}
