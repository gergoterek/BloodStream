import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import { Application } from 'src/app/domain/application';
import { ApplicationService } from '../application.service';

@Component({
  selector: 'app-my-application',
  templateUrl: './my-application.component.html',
  styleUrls: ['./my-application.component.css']
})
export class MyApplicationComponent implements OnInit {

  pastApp: Application[] = [];
  nextApp: Application;
  selectedApp = null;

  constructor(
    public authService: AuthService,
    private applicationService: ApplicationService,
    private route: ActivatedRoute,
    private router: Router,

  ) { }

  async ngOnInit() {
    this.pastApp = await this.applicationService.getDonorPastApplications(this.authService.user.id);
    this.nextApp = await this.applicationService.getNextApplication(this.authService.user.id);
    //console.log(JSON.stringify(this.pastApp));    
  }

  letDonate(): boolean {
    return new Date(this.authService.user.nextDonationDate).getDate() >= new Date().getDate();
  }

//   delButton(): void {
//     console.log("deleteButton" + this.nextApp.applyId);
//     this.applicationService.deleteApplication(this.nextApp.applyId);
//     this.nextApp = null;
//     this.title = "Application deleted";
//     //this.router.navigate(['/donation', 'del']);
// }


}
