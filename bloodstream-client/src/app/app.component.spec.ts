import { NO_ERRORS_SCHEMA } from '@angular/core';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { TestBed } from '@angular/core/testing';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectionList } from '@angular/material/list';
import { MatSidenavContainer, MatSidenavContent, MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule, MatToolbarRow } from '@angular/material/toolbar';
import { BrowserDynamicTestingModule, platformBrowserDynamicTesting } from '@angular/platform-browser-dynamic/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
//import { NO_ERRORS_SCHEMA } from '@angular/core';
import { DonorListComponent } from './donor-list/donor-list.component'
import { DonorService } from './donor.service';
import { FaqListComponent } from './faq-list/faq-list.component';
import { MainNavComponent } from './main-nav/main-nav.component';

describe('AppComponent', () => {
  beforeEach(async () => {
    
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        AppComponent,
        DonorListComponent,
        FaqListComponent,
        MainNavComponent
      ],
      //schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
      schemas: [ NO_ERRORS_SCHEMA ]
    }).compileComponents();

    TestBed.resetTestEnvironment();
    TestBed.initTestEnvironment(BrowserDynamicTestingModule,
       platformBrowserDynamicTesting());
  });


  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'bloodstream-client'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('bloodstream-client');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('bloodstream-client app is running!');
  });
});
