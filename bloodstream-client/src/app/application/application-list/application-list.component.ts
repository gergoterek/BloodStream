import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import { Donation } from 'src/app/domain/donation';
import { Donor } from 'src/app/domain/donor';
import { Place } from 'src/app/domain/place';
import { Application } from '../../domain/application';
import { ApplicationService } from '../application.service';

@Component({
  selector: 'app-application-list',
  templateUrl: './application-list.component.html',
  styleUrls: ['./application-list.component.css']
})
export class ApplicationListComponent implements OnInit {

  id: number;

  applications: Application[] = [];
  nextApps: Application[] = [];
  unTransported: Application[] = [];
  transported: Application[] = [];
  temp: Application[] = [];
  
  selectedApplication = null;

  constructor(
    public applicationService: ApplicationService,
    public authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }


  async ngOnInit() {
    this.applications = await this.applicationService.getApplications();
    //console.log(JSON.stringify(this.applications))
    const id = this.route.snapshot.paramMap.get('placeId');
    if (id) {
      this.id = +id;
      this.applications = this.applications.filter(app => app.place.id === this.id);
    }
    this.initArrays();
  }

  initArrays(){
    this.applications.sort(function(a,b) {
      return new Date(b.appliedDate).getTime() - new Date(a.appliedDate).getTime();
    });

    this.nextApps = this.applications.filter(app => app.donation === null);
    this.temp = this.applications.filter(app => app.donation !== null);

    this.unTransported = this.temp.filter(app => app.donation.transportDate === null);
    this.temp = this.temp.filter(app => app.donation.transportDate !== null);

    this.transported = this.temp;
  }
  
}
