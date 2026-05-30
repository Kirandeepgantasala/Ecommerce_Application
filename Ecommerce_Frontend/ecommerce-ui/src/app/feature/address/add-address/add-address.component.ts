import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Address } from '../../model/Address';
import { Form, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CustomerService } from '../../service/customer.service';
import { CommonModule, JsonPipe } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-address',
  standalone: true,
  imports: [ReactiveFormsModule,JsonPipe,CommonModule],
  templateUrl: './add-address.component.html',
  styleUrl: './add-address.component.css',
})
export class AddAddressComponent implements OnInit {

  addressForm!:FormGroup;
  isSpinnerEnabled:boolean=false;

  address: Address = {
    customerName: '',
    phoneNumber: null,
    houseNumber:null,
    state: '',
    street: '',
    city: '',
    pincode: null,
    country: '',
    landmark: '',
    id: 0,
  };
  message:string='';
  errorMessage:string='';
  @Output() addressAdded = new EventEmitter<void>();
  constructor(private fb:FormBuilder,private customerService:CustomerService,
    private toast:ToastrService
  ){}
  ngOnInit() {
    this.addressForm = this.fb.group({
      customerName: ['',Validators.required],
    phoneNumber: [null,Validators.required],
    houseNumber:[null,Validators.required],
    state: ['',Validators.required],
    street: ['',Validators.required],
    city: ['',Validators.required],
    pincode: [null,Validators.required],
    country: ['',Validators.required],
    landmark: ['',Validators.required],
    })
  }
  
onSubmit(){
  if(this.addressForm.valid){
    this.address = this.addressForm.value;

    this.customerService.addAddress(this.address).subscribe({
      next:(data)=>{
        this.message = data.message;
        console.log(this.message);
        this.toast.success("Address Added Successfully");
        this.addressAdded.emit();
        
      },
      error:(error)=>{
console.log(error);
this.errorMessage=error;
this.toast.error("Unable to add address");
      }

    });
  }
}






}
