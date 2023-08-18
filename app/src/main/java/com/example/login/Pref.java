package com.example.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Pref {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "androidhive-welcome";

    @SuppressLint("CommitPrefEdits")
    public Pref(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void saveAEMEmployeeID(String AEMEmployeeID) {
        editor.putString("AEMEmployeeID", AEMEmployeeID);
        editor.commit();
    }

    public String getAEMEmployeeID() {
        return pref.getString("AEMEmployeeID","");
    }

}
