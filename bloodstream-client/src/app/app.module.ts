import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';

import { RoutingModule } from './routing/routing.module';
import { AppComponent } from './app.component';
import { FaqListComponent } from './faq-list/faq-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import { DonorListComponent } from './donor-list/donor-list.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatListModule } from '@angular/material/list';
import { BloodtypeFilterComponent } from './bloodtype-filter/bloodtype-filter.component';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatSelectModule} from '@angular/material/select';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DonorDetailComponent } from './donor-detail/donor-detail.component';
import { FaqEditComponent } from './faq-edit/faq-edit.component';
import { FaqFormComponent } from './faq-form/faq-form.component';

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
    FaqFormComponent
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

  ],
  //schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
