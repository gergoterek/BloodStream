import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FaqListComponent } from '../faq-list/faq-list.component';
import { DonorListComponent } from '../donor-list/donor-list.component';
import { LoginComponent } from '../login/login.component';
import { DonorDetailComponent } from '../donor-detail/donor-detail.component';
import { AuthGuard } from '../auth.guard';
import { FaqEditComponent } from '../faq-edit/faq-edit.component';



const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
  },
  {
    path: 'donor',
    component: DonorListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'donor/profile/:id',
    component: DonorDetailComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'faq',
    component: FaqListComponent,
  },
  {
    path: 'faq/:faqId',
    component: FaqEditComponent,
    canActivate: [AuthGuard]
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




