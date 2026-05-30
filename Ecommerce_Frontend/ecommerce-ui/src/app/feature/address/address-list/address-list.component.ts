import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { CustomerService } from '../../service/customer.service';
import { Address } from '../../model/Address';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-address-list',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './address-list.component.html',
  styleUrl: './address-list.component.css'
})
export class AddressListComponent implements OnInit {

  addressList:Address[]=[];
  selectedAddressId:number|null=null;

  @Output() selectedAddress = new EventEmitter<number>();
  constructor(private customerService:CustomerService){}
  ngOnInit() {
   this.getCustomerAddressesList();
  }

  getCustomerAddressesList(){
    this.customerService.getAllAddresses().subscribe({
      next:(data: Address[])=>{
         this.addressList=data;
         console.log("Address List : "+data)
         console.log(data)
      },
      error:(error)=>{
        console.log(error);
      }
      
    });
  }

  selectAddress(id:number){
this.selectedAddress.emit(id);
  }

 


}
