import { AppComponent } from './app.component';
import {  NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { RoutingModule } from './routing/routing.module';

import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatSelectModule} from '@angular/material/select';
import { MatListModule } from '@angular/material/list';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MatNativeDateModule } from '@angular/material/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { FlexLayoutModule } from '@angular/flex-layout';
// import { MatDatetimepickerModule, MatNativeDatetimeModule } from "@mat-datetimepicker/core";

import { DonorListComponent } from './donor/donor-list/donor-list.component';
import { MainNavComponent } from '../assets/main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { BloodtypeFilterComponent } from './donor/bloodtype-filter/bloodtype-filter.component';
import { LoginComponent } from './authentication/login/login.component';
import { DonorDetailComponent } from './donor/donor-detail/donor-detail.component';
import { FaqEditComponent } from './faq/faq-edit/faq-edit.component';
import { FaqFormComponent } from './faq/faq-form/faq-form.component';
import { RegistrationComponent } from './authentication/registration/registration.component';
import { PlaceListComponent } from './place/place-list/place-list.component';
import { NewsListComponent } from './news/news-list/news-list.component';
import { MessageListComponent } from './message/message-list/message-list.component';
import { MessageFormComponent } from './message/message-form/message-form.component';
import { PlaceFormComponent } from './place/place-form/place-form.component';
import { NewsFormComponent } from './news/news-form/news-form.component';
import { FaqListComponent } from './faq/faq-list/faq-list.component';
import { NewsEditComponent } from './news/news-edit/news-edit.component';
import { PlaceEditComponent } from './place/place-edit/place-edit.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { DonorEditComponent } from './donor/donor-edit/donor-edit.component';
import { LandingPageComponent } from './donor/landing-page/landing-page.component';
import { ApplicationListComponent } from './application/application-list/application-list.component';
import { ApplicationFormComponent } from './application/application-form/application-form.component';
import { MyApplicationComponent } from './application/my-application/my-application.component';
import { ApplicationDetailComponent } from './application/application-detail/application-detail.component';
import { MessageDetailComponent } from './message/message-detail/message-detail.component';
import { AdminListComponent } from './donor/admin-list/admin-list.component';
import { InformationPageComponent } from './authentication/information-page/information-page.component';
import { ChoosePlaceComponent } from './application/choose-place/choose-place.component';


@NgModule({
  declarations: [
    AppComponent,
    FaqListComponent,
    DonorListComponent,
    MainNavComponent,
    BloodtypeFilterComponent,
    LoginComponent,
    DonorDetailComponent,
    FaqEditComponent,
    FaqFormComponent,
    RegistrationComponent,
    PlaceListComponent,
    NewsListComponent,
    MessageListComponent,
    MessageFormComponent,
    PlaceFormComponent,
    NewsFormComponent,
    NewsEditComponent,
    PlaceEditComponent,
    DonorEditComponent,
    LandingPageComponent,
    ApplicationListComponent,
    ApplicationFormComponent,
    MyApplicationComponent,
    ApplicationDetailComponent,
    MessageDetailComponent,
    AdminListComponent,
    InformationPageComponent,
    ChoosePlaceComponent,
    // MatDatetimepickerModule,
    // MatNativeDatetimeModule,sd
  ],
  imports: [
    RoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonToggleModule,
    MatSidenavModule,
    MatListModule,
    LayoutModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatCheckboxModule,
    MatSlideToggleModule,
    FlexLayoutModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
