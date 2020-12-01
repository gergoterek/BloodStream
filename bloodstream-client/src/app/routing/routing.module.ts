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
import { DonorEditComponent } from '../donor/donor-edit/donor-edit.component';
import { LandingPageComponent } from '../donor/landing-page/landing-page.component';
import { ApplicationListComponent } from '../application/application-list/application-list.component';
import { ApplicationFormComponent } from '../application/application-form/application-form.component';
import { MyApplicationComponent } from '../application/my-application/my-application.component';
import { ApplicationDetailComponent } from '../application/application-detail/application-detail.component';
import { MessageDetailComponent } from '../message/message-detail/message-detail.component';
import { AdminListComponent } from '../donor/admin-list/admin-list.component';
import { InformationPageComponent } from '../authentication/information-page/information-page.component';



const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
  },



  {
    path: 'donor',
    component: DonorListComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'admin',
    component: AdminListComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN']
    }
  },
  {
    path: 'profile',
    component: LandingPageComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'donor/profile/:id',
    component: DonorDetailComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'donor/profile/:id/:applyId',
    component: DonorDetailComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'donor/:id/edit',
    component: DonorEditComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },



  {
    path: 'faq',
    component: FaqListComponent,
  },
  {
    path: 'faq/:faqId/del',
    component: FaqListComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'faq/add',
    component: FaqEditComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'faq/:faqId',
    component: FaqEditComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  
  {
    path: 'faq/:faqId/edit',
    component: FaqFormComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },



  {
    path: 'place',
    component: PlaceListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'place/:id/del',
    component: PlaceListComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'place/add',
    component: PlaceEditComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'place/:id',
    component: PlaceEditComponent,
    canActivate: [AuthGuard]
  },
  
  {
    path: 'place/:id/edit',
    component: PlaceFormComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },




  {
    path: 'message',
    component: MessageListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'message/del',
    component: MessageListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'message/detail/:id',
    component: MessageDetailComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'message/new',
    component: MessageFormComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'message/new/:id',
    component: MessageFormComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },




  {
    path: 'news',
    component: NewsListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'news/:newsId/del',
    component: NewsListComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'news/add',
    component: NewsEditComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'news/:newsId',
    component: NewsEditComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },
  {
    path: 'news/:newsId/edit',
    component: NewsFormComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_ADMIN', 'ROLE_NURSE']
    }
  },




  {
    path: 'application',
    component: PlaceListComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_NURSE', 'ROLE_ADMIN']
    }
  },
  {
    path: 'application/:applyId',
    component: ApplicationDetailComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'application/place/:placeId',
    component: ApplicationListComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_NURSE', 'ROLE_ADMIN']
    }
  },
  {
    path: 'application/new/place/:placeId',
    component: ApplicationFormComponent,
    canActivate: [AuthGuard],
    data: {
      roles: ['ROLE_DONOR']
    }
  },
  {
    path: 'donation',
    component: MyApplicationComponent,
    canActivate: [AuthGuard], 
    data: {
      roles: ['ROLE_DONOR']
    }
  },
  {
    path: 'donation/:id',
    component: MyApplicationComponent,
    canActivate: [AuthGuard],
  },
  



  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'information',
    component: InformationPageComponent
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




