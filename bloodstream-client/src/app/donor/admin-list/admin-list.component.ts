import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/authentication/auth.service';
import { Donor } from 'src/app/domain/donor';
import { DonorService } from '../donor.service';

@Component({
  selector: 'app-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['./admin-list.component.css']
})
export class AdminListComponent implements OnInit {

  admins: Donor[] = [];
  selectedAdmin = null;

  constructor(
    public donorService: DonorService,
    public authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }


  async ngOnInit() {
    this.admins = (await this.donorService.getDonors()).filter( donor => donor.role !== "ROLE_DONOR");

    let del = this.route.snapshot.url;
    //console.log(del);
    if (del.length === 2){
      if(String(del).split(",")[1] === "del"){
        this.router.navigate(['/admin'])
      }
    }

    this.admins.sort(function(a, b){
      if(a.donorName < b.donorName) { return -1; }
      if(a.donorName > b.donorName) { return 1; }
      return 0;
    })
  }




}
