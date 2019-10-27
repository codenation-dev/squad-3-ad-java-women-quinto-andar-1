import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalNotRegisteredComponent } from './modal-not-registered.component';

describe('ModalNotRegisteredComponent', () => {
  let component: ModalNotRegisteredComponent;
  let fixture: ComponentFixture<ModalNotRegisteredComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalNotRegisteredComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalNotRegisteredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
