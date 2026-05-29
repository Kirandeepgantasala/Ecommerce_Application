import { OrderItem } from "./OrderItem";

export interface PlaceOrderRequest{
	totalPrice:number,
    totalQuantity:number,
    orderItems:OrderItem[],
    addressId:number|null,
    

}