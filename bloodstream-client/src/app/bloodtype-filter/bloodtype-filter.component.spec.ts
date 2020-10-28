import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodtypeFilterComponent } from './bloodtype-filter.component';

describe('BloodtypeFilterComponent', () => {
  let component: BloodtypeFilterComponent;
  let fixture: ComponentFixture<BloodtypeFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodtypeFilterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BloodtypeFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
