export interface Product{
    map(arg0: (prod: any) => void): unknown;
id:number,
name:string,
description:string,
price:number,
imageUrl:string,
active:boolean,
unitsInStock:number,
categoryId:number,
categoryName:string
}