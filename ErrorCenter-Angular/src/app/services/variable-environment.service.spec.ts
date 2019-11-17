import { TestBed } from '@angular/core/testing';

import { VariableEnvironmentService } from './variable-environment.service';

describe('VariableEnvironmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VariableEnvironmentService = TestBed.get(VariableEnvironmentService);
    expect(service).toBeTruthy();
  });
});
