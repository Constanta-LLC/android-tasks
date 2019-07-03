package com.example.squeezymo.exampletask2.repo

import com.example.squeezymo.exampletask2.data.Product
import com.example.squeezymo.exampletask2.data.ProductDetails
import com.example.squeezymo.exampletask2.data.ProductID
import io.reactivex.Observable
import io.reactivex.Single

interface IProductsRepo {

    fun getProducts(): Observable<List<Product>>

    fun getDetailsByProductId(productId: ProductID): Single<ProductDetails>

}
