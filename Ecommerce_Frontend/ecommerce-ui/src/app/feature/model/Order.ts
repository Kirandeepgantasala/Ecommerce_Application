import { OrderItem } from './OrderItem';
export interface Order {
    orderId: number;
    totalQuantity: number;
    totalPrice: number;
    orderStatus: string;
    orderItems: OrderItem[];
    addressId:number;
    createdAt:string;
}
