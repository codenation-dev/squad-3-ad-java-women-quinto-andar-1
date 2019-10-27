import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessfulRegisterComponent } from './successful-register.component';

describe('SuccessfulRegisterComponent', () => {
  let component: SuccessfulRegisterComponent;
  let fixture: ComponentFixture<SuccessfulRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuccessfulRegisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessfulRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
