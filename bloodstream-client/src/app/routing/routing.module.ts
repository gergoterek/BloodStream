import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FaqListComponent } from '../faq-list/faq-list.component';
import { DonorListComponent } from '../donor-list/donor-list.component';
import { LoginComponent } from '../login/login.component';



const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
  },
  {
    path: 'donor',
    component: DonorListComponent
  },
  {
    path: 'faq',
    component: FaqListComponent
  },
  {
    path: '**',
    redirectTo: '/',
  },
  {
    path: 'login',
    component: LoginComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule { }




