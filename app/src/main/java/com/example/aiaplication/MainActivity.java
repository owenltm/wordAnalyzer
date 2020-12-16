package com.example.aiaplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import android.os.Bundle;
import android.os.Handler;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;

import android.widget.Button;
import android.widget.EditText;

import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener;
import android.view.textservice.SuggestionsInfo;

import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity implements SpellCheckerSessionListener  {
    Button b1;
    TextView tv1;
    EditText ed1;
    private SpellCheckerSession mScs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.button);
        tv1=(TextView)findViewById(R.id.textView3);

        ed1=(EditText)findViewById(R.id.editText);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), ed1.getText().toString(),Toast.LENGTH_SHORT).show();
                mScs.getSuggestions(new TextInfo(ed1.getText().toString()), 3);
            }
        });
    }

    public void onResume() {
        super.onResume();
        final TextServicesManager tsm = (TextServicesManager) getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
        mScs = tsm.newSpellCheckerSession(null, Locale.ENGLISH, this, true);
    }

    public void onPause() {
        super.onPause();
        if (mScs != null) {
            mScs.close();
        }
    }

    public void onGetSuggestions(final SuggestionsInfo[] arg0) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arg0.length; ++i) {
            // Returned suggestions are contained in SuggestionsInfo
            final int len = arg0[i].getSuggestionsCount() ;
            sb.append('\n');

            for (int j = 0; j < len; ++j) {
                if(j !=0 ){
                sb.append("," + arg0[i].getSuggestionAt(j));}
                else sb.append( "Maybe you mean: "+ arg0[i].getSuggestionAt(j));
            }
            if(len == -1) {
                sb.append("your word is correct");
            }
        }
        runOnUiThread(new Runnable() {
            public void run() {
                tv1.append(sb.toString());
            }
        });
    }
    @Override
    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] arg0) {
        // TODO Auto-generated method stub
    }
}