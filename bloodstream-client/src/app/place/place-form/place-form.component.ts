import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Place } from 'src/app/domain/place';
import { PlaceService } from '../place.service';
import { Location } from '@angular/common';
import { OpeningTime } from 'src/app/domain/openingTime';

@Component({
  selector: 'app-place-form',
  templateUrl: './place-form.component.html',
  styleUrls: ['./place-form.component.css']
})
export class PlaceFormComponent implements OnInit, OnChanges {

  constructor(
    private fb: FormBuilder,
    private fb2: FormBuilder,
    public placeService: PlaceService,
    private router: Router,
    private location: Location,
  ) { }

  ngOnInit(): void {
  }

  ngOnChanges() {
    if (this.place.openingTime !== undefined){
      //console.log(JSON.stringify(this.place));
      this.placeForm.patchValue(this.place);
      //console.log(JSON.stringify(this.place));
      //this.openingForm.patchValue(this.place.openingTime); 
      //console.log(this.place.openingTime.startTime);
      //console.log(this.decimalToTime(this.startTime.value));
      //console.log(this.decimalToTime(this.place.openingTime.startTime));
      this.openingForm.patchValue({
          startTime: this.decimalToTime(this.place.openingTime.startTime),
          closingTime: this.decimalToTime(this.place.openingTime.closingTime),
          monday: this.place.openingTime.monday,
          tuesday: this.place.openingTime.tuesday,
          wednesday: this.place.openingTime.wednesday,
          thrusday: this.place.openingTime.thursday,
          friday: this.place.openingTime.friday,
          saturday: this.place.openingTime.saturday,
          sunday: this.place.openingTime.sunday,
      });  
      //console.log(JSON.stringify(this.place.openingTime));
      //console.log("change"); 
    }
  }

    
  @Input() place: Place;
  @Input() titleOfPage: string;
  @Input() wantToEdit: boolean;
  @Output() save = new EventEmitter<Place>();
  
  placeForm = this.fb.group({
    name: ['', [Validators.required]],
    city: ['', [Validators.required]],
    address: ['', [Validators.required]],
    active: [false],
  });
  
  get name() { return this.placeForm.get('name'); }
  get city() { return this.placeForm.get('city'); }
  get address() { return this.placeForm.get('address'); }
  get active() { return this.placeForm.get('active'); }
  


  openingForm = this.fb2.group({
    startTime: [this.decimalToTime(480), [Validators.required]],
    closingTime: [this.decimalToTime(800), [Validators.required]],

    monday: [false, [Validators.required]],
    tuesday: [false, [Validators.required]],
    wednesday: [false, [Validators.required]],
    thursday: [false, [Validators.required]],
    friday: [false, [Validators.required]],
    saturday: [false, [Validators.required]],
    sunday: [false, [Validators.required]],
  });

  get startTime() { return this.openingForm.get('startTime'); }
  get closingTime() { return this.openingForm.get('closingTime'); }
  get monday() { return this.openingForm.get('monday'); }
  get tuesday() { return this.openingForm.get('tuesday'); }
  get wednesday() { return this.openingForm.get('wednesday'); }
  get thursday() { return this.openingForm.get('thursday'); }
  get friday() { return this.openingForm.get('friday'); }
  get saturday() { return this.openingForm.get('saturday'); }
  get sunday() { return this.openingForm.get('sunday'); }
    
     
 

  onSubmit() {    
    this.openingForm.patchValue({
      startTime: this.timeToDecimal(this.startTime.value.toString()),
      closingTime: this.timeToDecimal(this.closingTime.value.toString()),
    });
    
    const openingTime = this.openingForm.value as OpeningTime;
    this.placeForm.value.openingTime = openingTime;

    this.save.emit(
      Object.assign(new Place(), this.placeForm.value)
    );
    //console.log(this.placeForm.value);
  }

  delButton(): void {
      this.placeService.deletePlace(this.place.id);
  }

  timeToDecimal(time: string): number {
    //console.log(time);
    let a = time.split(':');
    let min = (+a[0]) * 60 + (+a[1]);     
    //console.log(min);
    return min;
  }

  decimalToTime(int: number): string{
    //console.log(int);
    let temp = 0;
    while (int>=60){
        int -= 60;
        ++temp;        
    }
    let hour = temp<10 ? ("0" + temp.toString()) : temp.toString();
    let min = int<10 ? ("0" + int.toString()) : int.toString();    
    return hour + ":" + min;
  }

}







// import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
// import { FormBuilder, Validators } from '@angular/forms';
// import { Router } from '@angular/router';
// import { Place } from 'src/app/domain/place';
// import { PlaceService } from '../place.service';
// import { Location } from '@angular/common';
// import { OpeningTime } from 'src/app/domain/openingTime';

// @Component({
//   selector: 'app-place-form',
//   templateUrl: './place-form.component.html',
//   styleUrls: ['./place-form.component.css']
// })
// export class PlaceFormComponent implements OnInit, OnChanges {

//   constructor(
//     private fb: FormBuilder,
//     private fb2: FormBuilder,
//     public placeService: PlaceService,
//     private router: Router,
//     private location: Location,
//   ) { }

//   //active: boolean = false;
  
//   placeForm = this.fb.group({
//     name: ['', [Validators.required]],
//     city: ['', [Validators.required]],
//     address: ['', [Validators.required]],
//     active: ['', [Validators.required]],
//   });
  
//   @Input() place: Place;
//   @Input() openingTime: OpeningTime;
//   @Input() titleOfPage: string;
//   @Input() wantToEdit: boolean;
//   @Output() save = new EventEmitter<Place>();
  
//   get name() { return this.placeForm.get('name'); }
//   get city() { return this.placeForm.get('city'); }
//   get address() { return this.placeForm.get('address'); }
//   get active() { return this.placeForm.get('active'); }
  

//   // Example:
//   // form = new FormGroup({
//   //   first: new FormControl({value: 'Nancy', disabled: true}, Validators.required),
//   //   last: new FormControl('Drew', Validators.required)
//   // });

//   st: string = "08:00";
//   ct: string = "16:00";

//   openingForm = this.fb2.group({
//     startTime: ["08:00", [Validators.required]],
//     closingTime: ["16:00", [Validators.required]],

//     monday: [false, [Validators.required]],
//     tuesday: [false, [Validators.required]],
//     wednesday: [false, [Validators.required]],
//     thursday: [false, [Validators.required]],
//     friday: [false, [Validators.required]],
//     saturday: [false, [Validators.required]],
//     sunday: [false, [Validators.required]],
//   });
//   get startTime() { return this.openingForm.get('startTime'); }
//   get closingTime() { return this.openingForm.get('closingTime'); }

//   get monday() { return this.openingForm.get('monday'); }
//   get tuesday() { return this.openingForm.get('tuesday'); }
//   get wednesday() { return this.openingForm.get('wednesday'); }
//   get thursday() { return this.openingForm.get('thursday'); }
//   get friday() { return this.openingForm.get('friday'); }
//   get saturday() { return this.openingForm.get('saturday'); }
//   get sunday() { return this.openingForm.get('sunday'); }
    
    
//   ngOnInit(): void {
//   }
//    //set: boolean = false;

//   ngOnChanges() {
//     this.placeForm.patchValue(this.place);    
//     this.openingForm.patchValue(this.openingTime);
//     // if (this.place.active !== undefined && !this.set){
//     //   //this.placeForm.controls['active'].setValue(this.place.active);
//     //   this.check();
//     //   console.log("lefutottam");
//     // }
//   }

//   timeToDecimal(time: string): number {
//     let a = time.split(':'); // split it at the colons    
//     // minutes are worth 60 seconds. Hours are worth 60 minutes.
//     let min = (+a[0]) * 60 + (+a[1]);     
//     console.log(min);
//     return min;
//   }
  

//   onSubmit() {
//     this.placeForm.controls['startTime'].setValue(this.timeToDecimal(this.st));
//     this.placeForm.controls['closingTime'].setValue(this.timeToDecimal(this.ct));
//     this.openingForm.value.closingTime = this.timeToDecimal(this.ct);
//     const openingTime = this.openingForm.value as OpeningTime;
//     this.placeForm.value.openingTime = openingTime;
//     this.save.emit(
//       Object.assign(new Place(), this.placeForm.value)
//     );
//   }

//   delButton(): void {
//       this.placeService.deletePlace(this.place.id);
//   }

// }
  
//   dis(){
//     this.openingForm.get('monday').disable()
//     this.openingForm.get('tuesday').disable()
//     this.openingForm.get('wednesday').disable()
//     this.openingForm.get('thursday').disable()
//     this.openingForm.get('friday').disable()
//     this.openingForm.get('saturday').disable()
//     this.openingForm.get('sunday').disable()
// }
// enable(){
//     this.openingForm.get('monday').enable()
//     this.openingForm.get('tuesday').enable()
//     this.openingForm.get('wednesday').enable()
//     this.openingForm.get('thursday').enable()
//     this.openingForm.get('friday').enable()
//     this.openingForm.get('saturday').enable()
//     this.openingForm.get('sunday').enable()
// }

//   check() {
//   }
    //this.placeForm.controls['active'].setValue(!this.placeForm.get['active']);
  //   console.log(this.startTime);
  //   console.log(this.placeForm.value.active);
  //   console.log("set: " + this.set);
  //   if (this.set){
  //     if (this.placeForm.value.active === true){
  //       this.dis();
  //       //this.placeForm.value.active = false;        
  //     }  
  //     else if (this.placeForm.value.active === false){
  //       this.enable();
  //       //this.placeForm.value.active = true; 
  //     } 
  //   } else {
  //     if (this.placeForm.value.active === false){
  //       this.dis();
  //     }  
  //     else if (this.placeForm.value.active === true){
  //       this.enable();        
  //     }
  //     this.set = true;
  //   }
    
  //   console.log(this.placeForm.value.active);
  //   console.log("set: " + this.set);

  // }


  // check() {
  //   console.log(this.place.active);
  //   console.log("set: " + this.set);
  //   if (this.set){
  //     if (this.place.active === false){
  //       //this.place.active === !this.place.active;
  //       this.dis();        
  //       //this.place.active = true;
  //     }  
  //     else if (this.place.active === true){
  //       //this.place.active === !this.place.active;
  //       this.enable();
  //       //this.place.active = false;        
  //     } 
  //   } else {
  //     if (this.place.active === false){
  //       //this.place.active === !this.place.active;
  //       this.dis();
  //     }  
  //     else if (this.place.active === true){
  //       //this.place.active === !this.place.active;
  //       this.enable();        
  //     }
  //     this.set = true;
  //   }
    
  //   console.log(this.place.active);
  //   console.log("set: " + this.set);

  // }
  
    // this.placeForm.controls['active'].setValue(this.place.active);
    // if(this.placeForm.get['active'] == null) {
    //   console.log(this.place.active);
    //   console.log('benn');
    //   this.placeForm.controls['active'].setValue(this.place.active);
    // }


    // //this.placeForm.controls['active'].setValue(true);
    // console.log(this.placeForm.get['active']);
    // //this.placeForm.value.active == true;
    // //console.log(this.placeForm.value.active);
    // if(this.placeForm.get['active']) {

    //   this.openingForm.get('monday').disable()
    //   this.openingForm.get('tuesday').disable()
    //   this.openingForm.get('wednesday').disable()
    //   this.openingForm.get('thursday').disable()
    //   this.openingForm.get('friday').disable()
    //   this.openingForm.get('saturday').disable()
    //   this.openingForm.get('sunday').disable()

    //   this.placeForm.controls['active'].setValue(!this.placeForm.get['active']);
    //   //this.active = !this.active;
    // } else{
    //   this.openingForm.get('monday').enable()
    //   this.openingForm.get('tuesday').enable()
    //   this.openingForm.get('wednesday').enable()
    //   this.openingForm.get('thursday').enable()
    //   this.openingForm.get('friday').enable()
    //   this.openingForm.get('saturday').enable()
    //   this.openingForm.get('sunday').enable()

    //   this.placeForm.controls['active'].setValue(!this.placeForm.get['active']);
    //  //this.active = !this.active;
    // }
    

     
    // this.place.active =true;
    
    //  if(this.place.active !== true){
      
      // } else {
        
        // }
    


