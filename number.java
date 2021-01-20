package yalantis.com.sidemenu.sample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by jenny on 2016-07-21.
 */
public class number extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_menu);
    }

    public void btn17(View view) {
        Intent intent = null;
        switch(view.getId()){
            case R.id.button17:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01020836919"));
                break;
        }
        if(intent != null){
            startActivity(intent);
        }

    }

    public void btn18(View view) {
        Intent intent = null;
        switch(view.getId()){
            case R.id.button18:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01020836919"));
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }

    public void btn16(View view) {
        Intent intent = null;
        switch(view.getId()){
            case R.id.button16:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01020836919"));
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }

    public void btn15(View view) {
        Intent intent = null;
        switch(view.getId()){
            case R.id.button15:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01020836919"));
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }

    public void btn14(View view) {
        Intent intent = null;
        switch(view.getId()){
            case R.id.button14:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01020836919"));
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }

    public void btn13(View view) {
        Intent intent = null;
        switch(view.getId()){
            case R.id.button13:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01020836919"));
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }
}
