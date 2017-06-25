import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import {HomeService} from './homeService';
import {Http} from '@angular/http';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
  providers:[HomeService]
})
export class HomePage {

  quotes: any;
  quote1: any;

  constructor(public navCtrl: NavController, public homeService:HomeService) {
    homeService.getAllQuotes().subscribe(data =>{
      this.quotes = data;
    })
    // homeService.getQuote(1).subscribe(data =>{
    //   this.quote1 = data;
    // })
  }

}
