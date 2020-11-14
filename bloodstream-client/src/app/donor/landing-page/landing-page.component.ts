import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import { Donor } from 'src/app/domain/donor';
import { DonorService } from '../donor.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {

  donor: Donor;


  constructor(
    private route: ActivatedRoute,
    private donorService: DonorService,
    public authService: AuthService
  ) { }

  async ngOnInit() {
    this.donor = await this.donorService.getDonor(this.authService.user.id);
  }
}
