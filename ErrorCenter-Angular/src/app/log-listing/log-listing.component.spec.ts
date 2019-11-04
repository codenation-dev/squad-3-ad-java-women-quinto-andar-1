import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogListingComponent } from './log-listing.component';

describe('LogListingComponent', () => {
  let component: LogListingComponent;
  let fixture: ComponentFixture<LogListingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogListingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
