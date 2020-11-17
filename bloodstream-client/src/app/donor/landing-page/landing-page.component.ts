import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { ApplicationService } from 'src/app/application/application.service';
import { AuthService } from 'src/app/authentication/auth.service';
import { Application } from 'src/app/domain/application';
import { Donor } from 'src/app/domain/donor';
import { DonorService } from '../donor.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {

  donor: Donor;
  nextApp: Application;


  constructor(
    private route: ActivatedRoute,
    private donorService: DonorService,
    public authService: AuthService,
    private appService: ApplicationService,
    private appComp: AppComponent,
  ) { }

  async ngOnInit() {
    this.donor = await this.donorService.getDonor(this.authService.user.id);
    this.nextApp = await this.appService.getNextApplication(this.authService.user.id);
    this.appComp.fillMsg();
    //console.log(JSON.stringify(this.nextApp));
  }
}
