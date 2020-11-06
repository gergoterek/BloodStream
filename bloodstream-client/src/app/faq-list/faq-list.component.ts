import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Faq } from '../domain/faq';

import { FaqService } from '../faq.service'

@Component({
  selector: 'app-faq-list',
  templateUrl: './faq-list.component.html',
  styleUrls: ['./faq-list.component.css']
})
export class FaqListComponent implements OnInit {

  faqs: Faq[] = [];
  selectedFaq = null;
  
  constructor(
    public faqService: FaqService,
    public authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  async ngOnInit() {
    this.faqs = await this.faqService.getFaqs();
    
    let del = this.route.snapshot.url;
    
    if(String(del).split(",")[2] === "del"){
      this.router.navigate(['/faq'])
    }
  } 
}


// const del = this.route.snapshot.url;//paramMap.get('del');
    // console.log("del" + String(del).split(","));
    // if (del) {
    //   const delID = this.route.snapshot.paramMap.get('faqId');
    //   if(delID){
    //     console.log(delID + "megkapt");
        
    //     let pos = this.faqs.map(function(faq) { return faq.faqId; }).indexOf(parseInt(delID));
    //     var a;
    //     console.log(JSON.stringify(this.faqs));
    //     if (pos> -1) {
    //       a = this.faqs.splice(pos, 1);
    //     }
    //     this.faqs = a;
    //     console.log(JSON.stringify(this.faqs));
    //   }
    //}


    // this.route.params
    //   .subscribe(
    //     (params: Params) => {
    //       this.id1 = +params['id1'];
    //       this.id2 = +params['id2'];
    //       console.log(id1 + '' + id2);
    //     }
    //   );
    //}
    