import { Component, OnInit, ViewChild } from '@angular/core';
import { CartService } from '../service/cart.service';
import { CartItem } from '../model/CartItem';
import { CommonModule } from '@angular/common';
import { OrderItem } from '../model/OrderItem';
import { PlaceOrderRequest } from '../model/PlaceOrderRequest';
import { OrderService } from '../service/order.service';
import { Router } from '@angular/router';
import { OrderResponse } from '../model/OrderResponse';
import { CustomerDetails } from '../../auth/model/CustomerDetails';
import { CustomerService } from '../service/customer.service';
import { PaymentsResponse } from '../model/PaymentsResponse';
import { AddAddressComponent } from '../address/add-address/add-address.component';
import { AddressListComponent } from '../address/address-list/address-list.component';
import { ToastrService } from 'ngx-toastr';
declare var Razorpay: any;
@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [CommonModule, AddAddressComponent, AddressListComponent],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css',
})
export class CheckoutComponent implements OnInit {
  @ViewChild(AddressListComponent)
  addressList!:AddressListComponent;
  cartItems: CartItem[] = [];
  totalPrice: number = 0;
  orderItems: OrderItem[] = [];
  //orderResponse!:Order;

  totalPriceInPaise: number = 0;

  placeOrderRequest: PlaceOrderRequest = {
    totalPrice: 0,
    totalQuantity: 0,
    orderItems: [],
    addressId: null,
    
  };

  orderResponse: OrderResponse = {
    razorpayOrderId: '',
    key: '',
    currency: '',
    amount: null,
  };
  customerDetails!: CustomerDetails;
  paymentResponse!: PaymentsResponse;
  selectedAddressId: number | null = null;
  showAddressForm:boolean=false;
  isPlacingOrder:boolean=false;







  constructor(
    private cartService: CartService,
    private orderService: OrderService,
    private customerService: CustomerService,
    private route: Router,
    private toast:ToastrService
  ) {}

  ngOnInit() {
    this.cartItems = this.cartService.getCart();
    console.log("Cart items=> : ")
  console.log(this.cartService.getCart());

    this.getCustomerDetails();
    this.placeOrderRequest.orderItems = this.cartItems.map((cartItem) => {
      return {
        productId: cartItem.productId,
        price: cartItem.price,
        quantity: cartItem.quantity,
        productName:cartItem.productName,
        imageUrl:cartItem.imageUrl
      };
    });

    this.placeOrderRequest.totalPrice = this.cartService.showTotalPrice();
    this.totalPriceInPaise = this.placeOrderRequest.totalPrice * 100;
    this.placeOrderRequest.totalQuantity =
      this.placeOrderRequest.orderItems.reduce(
        (total, item) => (total = total + item.quantity),
        0,
      );
  }

  placeOrder() {
    console.log('Place Order method invoked');
    console.log(this.placeOrderRequest);
    this.orderService
      .createOrder(this.placeOrderRequest)
      .subscribe((orderResponse: OrderResponse) => {
        const options = {
          key: orderResponse.key, // Enter the Key ID generated from the Dashboard
          amount: this.totalPriceInPaise, // Amount is in currency subunits.
          currency: 'INR',
          name: 'NexCart',
          description: 'Test Transaction',
          image: 'https://example.com/your_logo',
          order_id: orderResponse.razorpayOrderId, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
          handler: (response: {
            razorpay_payment_id: any;
            razorpay_order_id: any;
            razorpay_signature: any;
          }) => {
            const paymentResponse: PaymentsResponse = {
              razorpayOrderId: response.razorpay_order_id,
              razorpayPaymentId: response.razorpay_payment_id,
              razorpaySignature: response.razorpay_signature,
            };

            console.log(paymentResponse);
            this.orderService.verifyOrder(paymentResponse).subscribe({
              next: (data) => {
                console.log('Payment Verified', data);
                console.log(data);
                console.log('Payment Successfull');
                this.toast.success("Payment Successfull");
              this.isPlacingOrder=true;
                this.clearCart();
                this.route.navigate(['/payment-success']);
              },
              error: (error) => {
                console.log('Payment Verification failed', error);
                this.toast.error("Payment Failed");
                   this.isPlacingOrder=false;
                 this.route.navigate(['/payment-failed']);
           
               
              },
            });
          },
          prefill: {
            name: this.customerDetails.name,
            email: this.customerDetails.email,
            contact: this.customerDetails.phoneNumber,
          },
          notes: {
            address: 'Razorpay Corporate Office',
          },
          theme: {
            color: '#3399cc',
          },
        };
        var rzp1 = new Razorpay(options);
        rzp1.open();
        rzp1.on(
          'payment.failed',
          (response: {
            error: {
              code: any;
              description: any;
              source: any;
              step: any;
              reason: any;
              metadata: { order_id: any; payment_id: any };
            };
          }) => {
            console.log('Payment Failed');
            this.route.navigate(['/payment-failed']);
          },
        );
      });
  }

  getCustomerDetails() {
    return this.customerService.getUserDetails().subscribe({
      next: (data: CustomerDetails) => {
        this.customerDetails = data;
        console.log('Fetched User Details Successfully', data);
      },
      error: (error: any) => {
        console.log('Error Fetching Customer Details:' + error);
      },
    });
  }

  clearCart() {
    localStorage.removeItem('cartItems');
  }

  onAddressSelected(id: number) {
    console.log("On address selected metho invoked");
    this.selectedAddressId = id;
    this.placeOrderRequest.addressId = id;
    console.log(
      'Placeorderrequest addressId' + this.placeOrderRequest.addressId,
    );
    console.log('Selected address id:' + id);
  }



toggleAddressForm(){

  this.showAddressForm =
    !this.showAddressForm;

  console.log(this.showAddressForm);

}

refreshAddressList(){
this.addressList.getCustomerAddressesList();
}
}
