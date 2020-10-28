import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonorListComponent } from './donor-list.component';

describe('DonorListComponent', () => {
  let component: DonorListComponent;
  let fixture: ComponentFixture<DonorListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DonorListComponent ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DonorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
