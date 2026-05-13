import { OrderItem } from "./OrderItem";

export interface PlaceOrderRequest{

	customerId:number,
	totalPrice:number,
    totalQuantity:number,
    orderItems:OrderItem[]

}