package com.example.squeezymo.exampletask;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    private EditText loginEt;
    private EditText passwordEt;
    private RadioGroup methodRg;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        loginEt = findViewById(R.id.et_login);
        passwordEt = findViewById(R.id.et_password);
        methodRg = findViewById(R.id.rg_method);
        submitBtn = findViewById(R.id.btn_submit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startObserving();
    }

    private void startObserving() {
        final Observable<TextViewAfterTextChangeEvent> loginTextObservable =
                RxTextView.afterTextChangeEvents(loginEt);

        final Observable<TextViewAfterTextChangeEvent> passwordTextObservable =
                RxTextView.afterTextChangeEvents(passwordEt);

        final Observable<Integer> methodChangeObservable =
                RxRadioGroup.checkedChanges(methodRg);

        final Observable<Object> submitClicksObservable =
                RxView.clicks(submitBtn);
    }

    private void logInWithEmail(@NonNull final String email) {
        /* Log in logic here */
    }

    private void logInWithPhone(@NonNull final String phone) {
        /* Log in logic here */
    }

}
