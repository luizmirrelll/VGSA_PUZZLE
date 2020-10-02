package id.dicoding.mirel.vgsa_aloysius_mirel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public  int button_1 ;
    public  int button_2 ;
    public  int row_1 ;
    public  int row_2;
    public  String variabel_1;
    public  String variabel_2;
    public  Button btnval1;
    public  Button btnval2;
    public  String[] rules_alfabet ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
    public  String[] answer ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
    public  int[] buttonId = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    public  int move = 0;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater chose = getMenuInflater();
        chose.inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moveAlfa();


    }

    public  void cekbtn(View view){

        String q = "" + view.getParent();

        String p = q.substring(q.length() - 2);

        p =String.valueOf(p.charAt(0)) ;

        String bId = "" +view.getTag();

        Button bt = (Button) view;
        String btnText = bt.getText().toString();

        if(row_1 == 0){
            row_1 = Integer.parseInt(p);
            button_1 = Integer.parseInt(bId);
            btnval1 = bt;
            variabel_1 = btnText;
        }else{
            row_2 = Integer.parseInt(p);
            button_2 = Integer.parseInt(bId);
            btnval2 = bt;
            variabel_2 = btnText;
        }

        if(row_1 != 0 && row_2 != 0 && button_1 != 0 && button_2 != 0){
            move(row_1,row_2,button_1,button_2,variabel_1,variabel_2,btnval1,btnval2);
        }

    }
    public  void  move(int row1, int row2,int btn1 ,int btn2 , String aText , String bText,Button b1,Button b2 ){
        int Row = row1 - row2;
        int buttom = btn1 - btn2;
        Button defbtn=new Button(this);
        if(aText.equals("*") || bText.equals("*")) {
            if (Row == 1 || Row == -1) {
                if (buttom == 0) {
                    move ++;
                    b1.setText(bText);
                    b2.setText(aText);
                    resetVariable();
                    Finish();
                    if(b1.getText().equals("*")){
                        b1.setBackgroundColor(Color.GREEN);
                        b2.setBackground(defbtn.getBackground());
                    }else {
                        b2.setBackgroundColor(Color.GREEN);
                        b1.setBackground(defbtn.getBackground());
                    }
                } else {
                    Context ct = getApplicationContext();
                    CharSequence t = " MOVE RIGHT ";
                    int duration = Toast.LENGTH_SHORT;
                    Toast tt = Toast.makeText(ct, t, duration);
                    tt.show();
                    resetVariable();
                }


            }

            else if (Row == 0) {
                if (buttom == 1 || buttom == -1) {
                    move ++;
                    b1.setText(bText);
                    b2.setText(aText);
                    resetVariable();
                    Finish();
                    if(b1.getText().equals("*")){
                        b1.setBackgroundColor(Color.GREEN);
                        b2.setBackground(defbtn.getBackground());
                    }else {
                        b2.setBackgroundColor(Color.GREEN);
                        b1.setBackground(defbtn.getBackground());
                    }
                } else {

                    Context ct = getApplicationContext();
                    CharSequence t = " NO MOVE ";
                    int durasi = Toast.LENGTH_SHORT;
                    Toast tt = Toast.makeText(ct, t, durasi);
                    tt.show();
                }
                resetVariable();
            } else {

                Context ct = getApplicationContext();
                CharSequence t = " NO MOVE ";
                int durasi = Toast.LENGTH_SHORT;
                Toast tt = Toast.makeText(ct, t, durasi);
                tt.show();
                resetVariable();
            }
        }else{
            Context ct = getApplicationContext();
            CharSequence t = " NOT MOVE";
            int duration = Toast.LENGTH_SHORT;
            Toast tt = Toast.makeText(ct, t, duration);
            tt.show();
            resetVariable();
        }
    }

    public  void  resetVariable(){
        button_1 = 0;
        button_2 = 0;
        row_2 = 0;
        row_1 = 0;
        variabel_1 = null;
        variabel_2 = null;
        btnval1 = null;
        btnval2 = null;
    }
    public void  moveAlfa(){
        move = 0;
        Button defbtn=new Button(this);
        List<String> alfabet = Arrays.asList(rules_alfabet);
        Collections.shuffle(alfabet);
        String[] move = alfabet.toArray(rules_alfabet);
        Button emptybtn = (Button) findViewById(R.id.b16);
        emptybtn.setText("*");
        emptybtn.setBackgroundColor(Color.GREEN);
        for(int a = 0 ; a< 15 ; a++){
            int fix = a+1;
            String iden = "b"+ fix;
            buttonId[a]= getResources().getIdentifier(iden,"id",this.getPackageName());
            Button bb = (Button) findViewById(buttonId[a]);
            bb.setText(move[a]);
            bb.setBackground(defbtn.getBackground());


            Log.i("test", "MOVEALFABET: " + move[a]);
            Log.i("test", "id: " + getResources().getIdentifier(iden,"id",this.getPackageName()));
            Log.i("test", "ANSWER: " + answer[a]);
        }

    }
    public void Finish(){
        int answeright = 0;
        for(int a = 0; a<15 ; a++){
            Button rightbtn = (Button) findViewById(buttonId[a]);
            String velue = (String) rightbtn.getText();
            if(velue.equals(answer[a])){
                answeright ++;
            }
            Log.i("test", "ANSWER RIGHT?: " + answeright);
            if(answeright == 15){
                // game finish show dialog mulai kembali atau keluar game
                dialogManager("done","YOU WIN");

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.m1){
            moveAlfa();
        }else if(item.getItemId() == R.id.m2){
            dialogManagerExit();
        }
        return true ;
    }
    public void  dialogManager(String title, String value){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(value);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"LET'S GO AGAIN",Toast.LENGTH_SHORT).show();
                moveAlfa();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"SEE YOU AGAIN :* ",Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        });
        builder.show();
    }
    public void  dialogManagerExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("EXIT?");
        builder.setMessage("YOU WNAT TO OUT??");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"LET'S GO THE GAME",Toast.LENGTH_SHORT).show();

            }
        });
        builder.show();
    }


}