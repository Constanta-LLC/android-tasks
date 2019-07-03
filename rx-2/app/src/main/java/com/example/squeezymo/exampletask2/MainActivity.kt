package com.example.squeezymo.exampletask2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.squeezymo.exampletask2.data.Product
import com.example.squeezymo.exampletask2.data.ProductDetails
import com.example.squeezymo.exampletask2.repo.IProductsRepo
import com.example.squeezymo.exampletask2.repo.ProductsRepo
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    private lateinit var productsRepo: IProductsRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)

        findViewById<Button>(R.id.btn_subscribe).apply {
            setOnClickListener {
                stopObserving()
                startObserving()
            }
        }

        productsRepo = ProductsRepo()
    }

    private fun startObserving() {
        productsRepo
            .getProducts()
            .flatMap { products: List<Product> ->
                products
                    .map { product: Product ->
                        productsRepo
                            .getDetailsByProductId(product.id)
                    }
                    .let { details: List<Single<ProductDetails>> ->
                        Single.merge(details)
                    }
                    .toList()
                    .toObservable()
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .also { disposable ->
                disposables.add(disposable)
            }
    }

    private fun stopObserving() {
        disposables.clear()
    }

}
