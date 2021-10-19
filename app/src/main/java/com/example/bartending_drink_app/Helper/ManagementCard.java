//package com.example.foodfinder.Helper;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import com.example.foodfinder.model.FoodDomain;
//
//import java.util.ArrayList;
//
//public class ManagementCard {
//    private Context context;
//    private TinyDB tinyDB;
//
//    public ManagementCard(Context context) {
//        this.context = context;
//        this.tinyDB = new TinyDB();
//    }
//
//    public void insertFood(FoodDomain item) {
////        ArrayList<FoodDomain> listFood = getListCard();
//        boolean existAlready = false;
//        int n = 0;
//        for (int i = 0; i < listFood.size(); i++) {
//            if (listFood.get(i).getTitle().equals(item.getTitle())) {
//                existAlready = true;
//                n = i;
//                break;
//            }
//        }
//
//        if (existAlready) {
//            listFood.get(n).setNumberIntCard(item.getNumberIntCard());
//        } else {
//            listFood.add(item);
//        }
//
////        tinyDB.putListObject("CardList", listFood);
////        Toast.makeText(context, "Added To Your Card", Toast.LENGTH_SHORT).show();
//    }
//
////    public ArrayList<FoodDomain> getListCard(){
////        return tinyDB.getListObject("CardList");
////    }
//}
