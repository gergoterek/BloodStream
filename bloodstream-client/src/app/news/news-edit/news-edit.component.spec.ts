import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsEDITComponent } from './news-edit.component';

describe('NewsEDITComponent', () => {
  let component: NewsEDITComponent;
  let fixture: ComponentFixture<NewsEDITComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewsEDITComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsEDITComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
