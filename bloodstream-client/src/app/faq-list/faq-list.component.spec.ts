import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FaqListComponent } from './faq-list.component';

describe('FaqListComponent', () => {
  let component: FaqListComponent;
  let fixture: ComponentFixture<FaqListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FaqListComponent ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FaqListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
