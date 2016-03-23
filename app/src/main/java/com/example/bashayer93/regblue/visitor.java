package com.example.bashayer93.regblue;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class visitor extends Activity implements OnClickListener {

    visitorphp biodata = new visitorphp();
    TableLayout visitor;

    Button buttonTambahBiodata;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();

    JSONArray arrayBiodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_visitor);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        visitor = (TableLayout) findViewById(R.id.tableBiodata);


        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        TextView viewHeaderrId = new TextView(this);
        TextView viewHeaderName = new TextView(this);
        TextView viewHeaderRefnum = new TextView(this);
        TextView viewHeaderNumber = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderrId.setText("rID");
        viewHeaderName.setText("Name");
        viewHeaderRefnum.setText("Refnum");
        viewHeaderNumber.setText("Number");
        viewHeaderAction.setText("");

        viewHeaderrId.setPadding(5, 1, 5, 1);
        viewHeaderName.setPadding(5, 1, 5, 1);
        viewHeaderRefnum.setPadding(5, 1, 5, 1);
        viewHeaderNumber.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderrId);
        barisTabel.addView(viewHeaderName);
        barisTabel.addView(viewHeaderRefnum);
        barisTabel.addView(viewHeaderNumber);
        barisTabel.addView(viewHeaderAction);

        visitor.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        try {

            arrayBiodata = new JSONArray(biodata.tampilBiodata());

            for (int i = 0; i < arrayBiodata.length(); i++) {
                JSONObject jsonChildNode = arrayBiodata.getJSONObject(i);
                String name = jsonChildNode.optString("name");
                String refnum = jsonChildNode.optString("Refnum");
                String number = jsonChildNode.optString("Number");
                String rid = jsonChildNode.optString("rid");

                System.out.println("Name :" + name);
                System.out.println("refnum: "+ refnum);
                System.out.println("number" + number);
                System.out.println("rID :" + rid);

                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewrId = new TextView(this);
                viewrId.setText(rid);
                viewrId.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewrId);

                TextView viewName = new TextView(this);
                viewName.setText(name);
                viewName.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewName);


                TextView viewRefnum = new TextView(this);
                viewRefnum.setText(refnum);
                viewRefnum.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewRefnum);

                TextView viewNumber = new TextView(this);
                viewNumber.setText(number);
                viewNumber.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNumber);

                buttonEdit.add(i, new Button(this));
                buttonEdit.get(i).setId(Integer.parseInt(rid));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                barisTabel.addView(buttonEdit.get(i));

                buttonDelete.add(i, new Button(this));
                buttonDelete.get(i).setId(Integer.parseInt(rid));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                barisTabel.addView(buttonDelete.get(i));

                visitor.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {

        for (int i = 0; i < buttonEdit.size(); i++) {


            if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                // Toast.makeText(visitor.this, "Edit : " +
                // buttonEdit.get(i).getId(), Toast.LENGTH_SHORT).show();
                int id = buttonEdit.get(i).getId();
                getDataByID(id);

            }
            else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                // Toast.makeText(visitor.this, "Delete : " +
                // buttonDelete.get(i).getId(), Toast.LENGTH_SHORT).show();
                int id = buttonDelete.get(i).getId();
                deleteBiodata(id);

            }
        }
    }


    public void deleteBiodata(int rid) {
        biodata.deleteBiodata(rid);


        finish();
        startActivity(getIntent());

    }

    public void getDataByID(int rid) {

        String nameEdit = null, refnumEdit = null, numberEdit = null ;
        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(biodata.getBiodataById(rid));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                nameEdit = jsonChildNode.optString("name");
                refnumEdit = jsonChildNode.optString("Refnum");
                numberEdit = jsonChildNode.optString("Number");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        // buat id tersembunyi di alertbuilder
        final TextView viewrId = new TextView(this);
        viewrId.setText(String.valueOf(rid));
        viewrId.setTextColor(Color.TRANSPARENT);
        layoutInput.addView(viewrId);

        final EditText editName = new EditText(this);
        editName.setText(nameEdit);
        layoutInput.addView(editName);


        final EditText editRefnum = new EditText(this);
        editRefnum.setText(refnumEdit);
        layoutInput.addView(editRefnum);

        final EditText editNumber = new EditText(this);
        editNumber.setText(numberEdit);
        layoutInput.addView(editNumber);

        AlertDialog.Builder builderEditBiodata = new AlertDialog.Builder(this);
        builderEditBiodata.setTitle("Update visitorphp");
        builderEditBiodata.setView(layoutInput);
        builderEditBiodata.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editName.getText().toString();
                String refnum = editRefnum.getText().toString();
                String number = editNumber.getText().toString();

                System.out.println("Name : " + name + " Refnum : " + refnum + " Number : " + number);



                finish();
                startActivity(getIntent());
            }

        });

        builderEditBiodata.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditBiodata.show();

    }

    public void tambahBiodata() {
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editName = new EditText(this);
        editName.setHint("Name");
        layoutInput.addView(editName);


        final  EditText editRefnum = new EditText(this);
        editRefnum.setHint("Refnum");
        layoutInput.addView(editRefnum);

        final EditText editNumber = new EditText(this);
        editNumber.setHint("Numebr");
        layoutInput.addView(editNumber);

        AlertDialog.Builder builderInsertBiodata = new AlertDialog.Builder(this);
        builderInsertBiodata.setTitle("Insert visitorphp");
        builderInsertBiodata.setView(layoutInput);
        builderInsertBiodata.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editName.getText().toString();
                String refnum = editRefnum.getText().toString();
                String number = editNumber.getText().toString();

                System.out.println("Name : " + name +  " Refnum : " + refnum + " Number : " + number);

                String laporan = biodata.inserBiodata(name, refnum, number);

                Toast.makeText(visitor.this, laporan, Toast.LENGTH_SHORT).show();

				/* restart acrtivity */
                finish();
                startActivity(getIntent());
            }


        });

        builderInsertBiodata.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertBiodata.show();
    }



}


