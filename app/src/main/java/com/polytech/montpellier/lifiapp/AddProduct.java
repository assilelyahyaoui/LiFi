package com.polytech.montpellier.lifiapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import com.oledcomm.soft.lifiapp.R;
import com.polytech.montpellier.lifiapp.DAO.AbstractDAO.DepartmentDAO;
import com.polytech.montpellier.lifiapp.DAO.AbstractDAO.LampDAO;
import com.polytech.montpellier.lifiapp.DAO.AbstractDAO.ProductDAO;
import com.polytech.montpellier.lifiapp.DAO.DAOFactory.AbstractDAOFactory;
import com.polytech.montpellier.lifiapp.Helper.Helper;
import com.polytech.montpellier.lifiapp.Helper.ResponseHandler;
import com.polytech.montpellier.lifiapp.Model.Department;
import com.polytech.montpellier.lifiapp.Model.Lamp;
import com.polytech.montpellier.lifiapp.Model.Product;
import android.support.constraint.ConstraintLayout;
import 	android.view.LayoutInflater;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity{

    EditText editText_price;
    EditText editText_brand;
    EditText editText_name;
    MultiAutoCompleteTextView editText_description;



    //Float price;
    String price;
    String brand;
    String description;
    String name;
    Button validate;
    String token = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8" ;
    DepartmentDAO dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.MYSQL_DAO_FACTORY).getDepartmentDAO();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_add);
        Helper.getInstance(this);
        initializeUI();



        editText_price = (EditText) findViewById(R.id.editText_product_addPrice);
        editText_brand = (EditText) findViewById(R.id.editText_product_addBrand);
        editText_name = (EditText) findViewById(R.id.editText_product_addPrice);
        editText_description = (MultiAutoCompleteTextView) findViewById(R.id.multiTextView_product_addDescription);


        //TODO: faire une alerte si un des champs est vide

        if (!editText_name.getText().toString().isEmpty() && !editText_description.getText().toString().isEmpty()
                && !editText_brand.getText().toString().isEmpty() && !editText_price.getText().toString().isEmpty()) {

            name = editText_name.getText().toString();
            description = editText_description.getText().toString();
            //price = Float.parseFloat(editText_price.getText().toString());
            price = editText_price.getText().toString();

            brand = editText_brand.getText().toString();

        }

        validate = (Button) findViewById(R.id.button_addProduct);

        validate.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                   /* Helper.getInstance(this).POST("http://81.64.139.113:1337/api/Product", token, params, new ResponseHandler() {

                        @Override
                        public void onSuccess(Object object) {
                            System.out.println("POST" + object.toString());
                        }

                        @Override
                        public void onError(Object object) {

                        }
                    });*/
                System.out.println("click");
                return true;
            }
        });
    }








    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onBackPressed(){

    }

    private void initializeUI() {

        final Spinner spinnerDepartment = (Spinner) findViewById(R.id.spinner_ProductDepartement);
        final ArrayList<String> dep = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list,R.id.list1, dep);
        dao.getAll(new ResponseHandler() {

            @Override
            public void onSuccess(Object object) {

                if (object instanceof ArrayList) {

                    ArrayList<Department> array = (ArrayList<Department>) object;
                    for (int i = 0; i < array.size(); i++) {
                        Department department = array.get(i);
                        dep.add(department.getName());

                    }
                    adapter.setDropDownViewResource(R.layout.product_add);
                    spinnerDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onError(Object object) {
                System.out.println("Nom:");
            }
        });

    }

}
