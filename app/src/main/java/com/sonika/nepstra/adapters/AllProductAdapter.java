package com.sonika.nepstra.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.sonika.nepstra.DetailsActivity;
import com.sonika.nepstra.OrderedProducts;
import com.sonika.nepstra.R;
import com.sonika.nepstra.helpers.ArtAndCraftHelper;
import com.sonika.nepstra.helpers.JwelleryHelper;
import com.sonika.nepstra.helpers.MensHelper;
import com.sonika.nepstra.helpers.MySharedPreference;
import com.sonika.nepstra.helpers.NewArrivalsHelper;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.helpers.SportsHelper;
import com.sonika.nepstra.helpers.WomenHelper;
import com.sonika.nepstra.pojo.AllProducts;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sonika on 9/19/2017.
 */

public class AllProductAdapter extends RecyclerView.Adapter<AllProductHolder> {
    public Context context;
    private List<AllProducts> allProductList;


    MySharedPreference sharedPreference;
    Gson gson;

    String oname, oprice, oimage;
    Integer cat_id;
    OrderHelper dbHelper;
    List<OrderedProducts_pojo> cartProductsList = new ArrayList<>();
    WomenHelper womenHelper;
    NewArrivalsHelper arrivalsHelper;
    ArtAndCraftHelper artAndCraftHelper;
    MensHelper mensHelper;
    JwelleryHelper jwelleryHelper;
    SportsHelper sportsHelper;
    public AllProductAdapter(Context context, List<AllProducts> allproductList) {
        this.context = context;
        this.allProductList = allproductList;
    }

    @Override
    public AllProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.all_productlist, parent, false);


        dbHelper = new OrderHelper(context);
        womenHelper = new WomenHelper(context);
        arrivalsHelper = new NewArrivalsHelper(context);
        artAndCraftHelper = new ArtAndCraftHelper(context);
        mensHelper = new MensHelper(context);
        jwelleryHelper = new JwelleryHelper(context);
        sportsHelper = new SportsHelper(context);
        return new AllProductHolder(view);
    }

    @Override
    public void onBindViewHolder(AllProductHolder allholder, final int position) {
        allholder.allproductName.setText(allProductList.get(position).getName());
        allholder.allproductPrice.setText("$" + allProductList.get(position).getPrice());
        Picasso.with(context).load(allProductList.get(position).getI_src()).into(allholder.allproductImage);
      Log.e("chankhey", "monkey");


        allholder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllProducts intentprod = allProductList.get(position);
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("hello", intentprod);
                context.startActivity(intent);
            }
        });
        assert allholder.addtocart != null;
        allholder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();

                oname = allProductList.get(position).getName();
                oprice = allProductList.get(position).getPrice();
                oimage = allProductList.get(position).getI_src();

                ContentValues contentValues = new ContentValues();
                contentValues.put("name", oname);
                contentValues.put("price", oprice);
                contentValues.put("imageone", oimage);
//                Log.e("pizza", "lovet");
//                Log.e("momo", oname);
//                Log.e("burger", oprice);
//                Log.e("oooo", oimage);
                dbHelper.insertOrderInfo(contentValues);
                cartProductsList = dbHelper.getOrderMessage();
                Log.e("orderedsonika", String.valueOf(cartProductsList.size()));
            }
        });


        oname = allProductList.get(position).getName();
        oprice = allProductList.get(position).getPrice();
        oimage = allProductList.get(position).getI_src();
        cat_id = allProductList.get(position).getC_id();

        if (cat_id == 29) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            womenHelper.insertwomen(contentValues);
        }
        if (cat_id == 17) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            arrivalsHelper.insertarrivals(contentValues);
        }
        if (cat_id == 30) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            artAndCraftHelper.insertwartandcraft(contentValues);
        }
        if (cat_id == 28){
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            mensHelper.insertmen(contentValues);
        }
        if (cat_id == 25){
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            jwelleryHelper.insertjwellery(contentValues);
        }
        if (cat_id == 27){
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            sportsHelper.insertsports(contentValues);
        }

        }



    @Override
    public int getItemCount() {
        Log.e("sanjeev", String.valueOf(allProductList.size()));
        return allProductList.size();
    }
}




