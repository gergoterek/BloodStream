import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Donor } from '../domain/donor';
import { DonorService } from '../donor.service';

@Component({
  selector: 'app-bloodtype-filter',
  templateUrl: './bloodtype-filter.component.html',
  styleUrls: ['./bloodtype-filter.component.css']
})
export class BloodtypeFilterComponent implements OnInit {


  @Input('bloodType') selectedBloodType = ''
  @Output() change = new EventEmitter<string>();

  constructor() { }

  ngOnInit() {
  }

  onFilterChange(data) {
    this.selectedBloodType = data.value;
    this.change.emit(this.selectedBloodType);
  }
}
