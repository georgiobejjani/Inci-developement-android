package com.example.projetandroid

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.renderscript.Sampler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    var recyclerView: RecyclerView ?= null
    var productList = ArrayList<Product>()
    private var database: FirebaseDatabase? = null
    private var reference: DatabaseReference? = null
//    val product1 = Product(R.drawable.airpods,"airpods","$170")
//    val product2 = Product(R.drawable.appwatch,"apple watch","$320")
//    val product3 = Product(R.drawable.iph13,"iphone 13","$890")
//    val product4 = Product(R.drawable.macbook,"macbook pro","$1200")
//    val product5 = Product(R.drawable.ps5,"ps5","$899")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_home, container, false)

        database = FirebaseDatabase.getInstance()
        reference = database?.getReference("products")

        val FirebaseListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val child = snapshot.children
                child.forEach{
                    var products = Product(it.child("img").value.toString(),
                    it.child("name").value.toString(),
                    it.child("price").value.toString())

                    productList.add(products)
                }

                val adapter = ProductAdapter(productList)
                recyclerView?.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }

        reference?.addValueEventListener(FirebaseListener)


        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)


        view.findViewById<Button>(R.id.btn_logout).setOnClickListener {
            Firebase.auth.signOut()
            var navLogin = activity as FragmentNavigation
            navLogin.navigateFrag(LoginFragment(),addToStack = false)
        }

        view.findViewById<Button>(R.id.btn_map).setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            startActivity(intent)
        }
        return view
    }


}