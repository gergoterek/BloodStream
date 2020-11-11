import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Donor } from '../../domain/donor';
import { DonorService } from '../donor.service';

@Component({
  selector: 'app-donor-detail',
  templateUrl: './donor-detail.component.html',
  styleUrls: ['./donor-detail.component.css']
})
export class DonorDetailComponent implements OnInit {

  id: number;
  donor: Donor;


  constructor(
    private route: ActivatedRoute,
    private donorService: DonorService,
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.id = +id;
      this.donor = await this.donorService.getDonor(this.id);
    }
  }

}
