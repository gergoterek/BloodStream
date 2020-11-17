import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApplicationService } from 'src/app/application/application.service';
import { AuthService } from 'src/app/authentication/auth.service';
import { Application } from 'src/app/domain/application';
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

  nextApp: Application;
  pastApp: Application[] = [];
  unTransported: Application[] = [];
  transportedApp: Application[] = [];
  selectedApp = null;

  constructor(
    private route: ActivatedRoute,
    private donorService: DonorService,
    private appService: ApplicationService,
    public authService: AuthService,
    private router: Router,
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.id = +id;
      this.donor = await this.donorService.getDonor(this.id);
    }    
    this.pastApp = await this.appService.getDonorPastApplications(this.id);
    this.nextApp = await this.appService.getNextApplication(this.id);


    this.unTransported = this.pastApp.filter(app => app.donation.transportDate === null);
    this.transportedApp = this.pastApp.filter(app => app.donation.transportDate !== null);
    
    //console.log(JSON.stringify(this.unTransported));
    //console.log(JSON.stringify(this.transportedApp));

  }  

  
}
