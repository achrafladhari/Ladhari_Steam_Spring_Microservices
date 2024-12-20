import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddmodifyComponent } from './addmodify.component';

describe('AddmodifyComponent', () => {
  let component: AddmodifyComponent;
  let fixture: ComponentFixture<AddmodifyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddmodifyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddmodifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
