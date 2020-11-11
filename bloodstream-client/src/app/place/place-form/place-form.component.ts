import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Place } from 'src/app/domain/place';
import { PlaceService } from '../place.service';

@Component({
  selector: 'app-place-form',
  templateUrl: './place-form.component.html',
  styleUrls: ['./place-form.component.css']
})
export class PlaceFormComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    public placeService: PlaceService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }
  
  
  placeForm = this.fb.group({
    name: ['', [Validators.required]],
    city: ['', [Validators.required]],
    address: ['', [Validators.required]],
    // donorID: [null],  
    // bloodType: [null],    
    // nextDonationDate: [null],    
    // role: [null],    
    // id: [null],    
  });
  

  get name() { return this.placeForm.get('name'); }
  get city() { return this.placeForm.get('city'); }
  get address() { return this.placeForm.get('address'); }

  async onFormSave() {
    const place = this.placeForm.value as Place;
    await this.placeService.addPlace(place);
  }
}
