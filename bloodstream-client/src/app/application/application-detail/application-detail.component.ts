import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthGuard } from 'src/app/authentication/auth.guard';
import { AuthService } from 'src/app/authentication/auth.service';
import { Application } from 'src/app/domain/application';
import { DonorDetailComponent } from 'src/app/donor/donor-detail/donor-detail.component';
import { DonorService } from 'src/app/donor/donor.service';
import { ApplicationService } from '../application.service';
import { MyApplicationComponent } from '../my-application/my-application.component';

@Component({
  selector: 'app-application-detail',
  templateUrl: './application-detail.component.html',
  styleUrls: ['./application-detail.component.css']
})
export class ApplicationDetailComponent implements OnInit {

  id: number;
  application: Application;

  title = "";

  constructor(
    private route: ActivatedRoute,
    private appService: ApplicationService,
    public authService: AuthService,
    private router: Router,
    //private myService: MyApplicationComponent,
  ) { }

  async ngOnInit() {
    const id = this.route.snapshot.paramMap.get('applyId');
    if (id) {
      this.id = +id;
      this.application = await this.appService.getApplication(this.id);
      //console.log(JSON.stringify(this.application));
      if(this.authService.isDonor() && this.application.donor.id !== this.authService.user.id){
        this.authService.logout();
      }
    }
    if (!this.definedBlood()){ this.title = "You have to set the donor blood type before check in"}
  }

  delButton(): void {
    //console.log("deleteButton" + this.application.applyId);
    this.appService.deleteApplication(this.application.applyId)
    if(this.authService.isDonor()){
      this.router.navigate(['/donation']);
    } else {
      this.router.navigate(['/donor/profile', this.application.donor.id]);
    }
    //this.donorDetail.nextApp = null;
    //this.router.navigate(['/donation', 'del']);
}

  async setConfirmed() {
    await this.appService.setConfirmed(this.application.applyId, this.application);
    this.application = await this.appService.getApplication(this.id);
  }


  async setTransport() {
    await this.appService.setTransport(this.application.donation.donationId, this.application);
    this.application = await this.appService.getApplication(this.id);
    //this.router.navigate(['/donor', 'profile', 'application.donor.id']);
  }

    //console.log(JSON.stringify(this.selectedApp));
    //await this.appService.setTransport(this.selectedApp.donation.donationId, this.selectedApp);
    //const found = this.pastApp.find(app => app === app.find(id => id === this.selectedApp.applyId));
    //this.persons =  this.personService.getPersons().filter(x => x.id == this.personId)[0];
    
    
    // const c = this.pastApp.filter(app =>  app.applyId === this.selectedApp.applyId);

    // const index = this.pastApp.indexOf(this.selectedApp.applyId, 0);
    // if (index > -1) {
    //   this.pastApp.splice(index, 1);
    // }

    //this.data = this.data.filter(item => item !== data_item);
    //this.pastApp = this.pastApp.filter(app => app.applyId !== this.selectedApp.applyId);
    //this.pastApp.push( await this.appService.getApplication(this.selectedApp.applyId));
    
    //console.log(JSON.stringify(index));

    //this.router.navigate(['/donor', 'profile', 'id']);
  
  definedBlood(): boolean{
      return this.application.donor.bloodType !== null;
  }

  
  isConfirmed(): boolean{
      return this.application.donor.bloodType !== null && this.application.donation !== null;
  }

  isTransported(): boolean{
    if(this.isConfirmed()){
      return this.application.donation.transportDate !== null;
    } else{
      return false;
    }
}

}
