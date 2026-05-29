export interface OrderResponse{
    razorpayOrderId:string,
    key:string,
    currency:string,
    amount:number|null
}