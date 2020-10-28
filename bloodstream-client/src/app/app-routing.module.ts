import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FaqListComponent } from './faq-list/faq-list.component';
import { DonorListComponent } from './donor-list/donor-list.component';

const routes: Routes = [
  {
    path: '',
    component: FaqListComponent,
    pathMatch: 'full'
  },
  {
    path: 'donor',
    component: DonorListComponent
  },
  {
    path: '**',
    redirectTo: '/',
  },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
