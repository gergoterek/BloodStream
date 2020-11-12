import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Place } from 'src/app/domain/place';
import { PlaceService } from '../place.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-place-form',
  templateUrl: './place-form.component.html',
  styleUrls: ['./place-form.component.css']
})
export class PlaceFormComponent implements OnInit, OnChanges {

  constructor(
    private fb: FormBuilder,
    public placeService: PlaceService,
    private router: Router,
    private location: Location,
  ) { }

  
  
  placeForm = this.fb.group({
    name: ['', [Validators.required]],
    city: ['', [Validators.required]],
    address: ['', [Validators.required]],
  });
  
  @Input() place: Place;
  @Input() titleOfPage: string;
  @Input() wantToEdit: boolean;
  @Output() save = new EventEmitter<Place>();
  
  get name() { return this.placeForm.get('name'); }
  get city() { return this.placeForm.get('city'); }
  get address() { return this.placeForm.get('address'); }
  
  // async onFormSave() {
    //   const place = this.placeForm.value as Place;
    //   await this.placeService.addPlace(place);
    // }
    
    
  ngOnInit(): void {
  }

  

  
  
  ngOnChanges() {
    this.placeForm.patchValue(this.place);
  }

  onSubmit() {
    this.save.emit(
      Object.assign(new Place(), this.placeForm.value)
    );
    console.log(JSON.stringify(this.placeForm.value));
  }

  delButton(): void {
      this.placeService.deletePlace(this.place.id);
  }











}
