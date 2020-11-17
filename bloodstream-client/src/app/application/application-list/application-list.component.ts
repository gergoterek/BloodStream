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



  applications: Application[] = [];
  // filteredDonors = [];
  // selectedBloodType = '';
   selectedApplication = null;

  constructor(
    public applicationService: ApplicationService,
    public authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }


  async ngOnInit() {
    this.applications = await this.applicationService.getApplications();
    //console.log(JSON.stringify(this.applications));

    let del = this.route.snapshot.url;
    if (del.length === 3){
      if(String(del).split(",")[2] === "del"){
        this.router.navigate(['/place'])
      }
    }
  }

  
}
