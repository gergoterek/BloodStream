import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FaqListComponent } from '../faq/faq-list/faq-list.component';
import { DonorListComponent } from '../donor/donor-list/donor-list.component';
import { LoginComponent } from '../authentication/login/login.component';
import { DonorDetailComponent } from '../donor/donor-detail/donor-detail.component';
import { AuthGuard } from '../authentication/auth.guard';
import { FaqEditComponent } from '../faq/faq-edit/faq-edit.component';
import { FaqFormComponent } from '../faq/faq-form/faq-form.component';
import { RegistrationComponent } from '../authentication/registration/registration.component';
import { PlaceListComponent } from '../place/place-list/place-list.component';
import { NewsListComponent } from '../news/news-list/news-list.component';
import { MessageListComponent } from '../message/message-list/message-list.component';
import { MessageFormComponent } from '../message/message-form/message-form.component';
import { NewsFormComponent } from '../news/news-form/news-form.component';
import { NewsEditComponent } from '../news/news-edit/news-edit.component';
import { PlaceFormComponent } from '../place/place-form/place-form.component';
import { PlaceEditComponent } from '../place/place-edit/place-edit.component';



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
    path: 'faq/add',
    component: FaqEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'faq/:faqId',
    component: FaqEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'faq/:faqId/del',
    component: FaqListComponent,
    canActivate: [AuthGuard],
    // data: {
    //   roles: ['ROLE_ADMIN']
    // }
  },
  {
    path: 'faq/:faqId/edit',
    component: FaqFormComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'place',
    component: PlaceListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'place/add',
    component: PlaceEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'place/:id',
    component: PlaceEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'place/:id/del',
    component: PlaceListComponent,
    canActivate: [AuthGuard],
    // data: {
    //   roles: ['ROLE_ADMIN']
    // }
  },
  {
    path: 'place/:id/edit',
    component: PlaceFormComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'message',
    component: MessageListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'message/new',
    component: MessageFormComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'news',
    component: NewsListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'news/add',
    component: NewsEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'news/:newsId',
    component: NewsEditComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'news/:newsId/del',
    component: NewsListComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'news/:newsId/edit',
    component: NewsFormComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: '**',
    redirectTo: '/',
  },
  {
    path: 'login',
    component: LoginComponent
  },
  
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule { }




