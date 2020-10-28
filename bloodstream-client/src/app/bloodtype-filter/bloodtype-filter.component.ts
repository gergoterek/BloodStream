import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Donor } from '../domain/donor';
import { DonorService } from '../donor.service';

@Component({
  selector: 'app-bloodtype-filter',
  templateUrl: './bloodtype-filter.component.html',
  styleUrls: ['./bloodtype-filter.component.css']
})
export class BloodtypeFilterComponent implements OnInit {

  @Input() bloodtypeFilter: string = '';

  @Output() filterChange: EventEmitter<any> = new EventEmitter();

  constructor(
    private donorService: DonorService
  ) { }

  ngOnInit(): void {
    this.change(this.bloodtypeFilter);
  }

  change(e: string) {
    this.donorService.filterChange(e);
    this.filterChange.emit(e);
  }

}
