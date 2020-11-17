import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyApplicationComponent } from './my-application.component';

describe('MyApplicationComponent', () => {
  let component: MyApplicationComponent;
  let fixture: ComponentFixture<MyApplicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyApplicationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
