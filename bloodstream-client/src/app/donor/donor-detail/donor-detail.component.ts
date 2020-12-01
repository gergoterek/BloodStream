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
  applyId: number;
  donor: Donor;

  nextApp: Application;
  pastApp: Application[] = [];
  unTransported: Application[] = [];
  transportedApp: Application[] = [];
  selectedApp = null;

  constructor(
    private route: ActivatedRoute,
    private donorService: DonorService,
    private applicationService: ApplicationService,
    public authService: AuthService,
    private router: Router,
  ) { }

  async ngOnInit() {
    this.routeManage(this.route.snapshot.url);
      
    this.pastApp = await this.applicationService.getDonorPastApplications(this.id);
    this.nextApp = await this.applicationService.getNextApplication(this.id);


    this.unTransported = this.pastApp.filter(app => app.donation.transportDate === null);
    this.transportedApp = this.pastApp.filter(app => app.donation.transportDate !== null);
    
    //console.log(JSON.stringify(this.unTransported));
    //console.log(JSON.stringify(this.transportedApp));

  }  

  async routeManage(del: any){
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.id = +id;
      this.donor = await this.donorService.getDonor(this.id);
    }  

    const applyId = this.route.snapshot.paramMap.get('applyId');
    if (applyId) {
      this.applyId = +applyId;
      if (this.nextApp !== null){
          this.nextApp = null; 
        } else {         
          this.nextApp = await this.applicationService.getApplication(this.applyId);
        }        
    }
    this.router.navigate(["/donor/profile", this.id]);
  }

  
}
