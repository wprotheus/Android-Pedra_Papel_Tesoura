package com.wprotheus.mentoriaaulajokenpo.view;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.wprotheus.mentoriaaulajokenpo.R;
import com.wprotheus.mentoriaaulajokenpo.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding mBinding;
    private String[] resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        resultados = getResources().getStringArray(R.array.resultado);
        mBinding.ivStone.setOnClickListener(this);
        mBinding.ivPaper.setOnClickListener(this);
        mBinding.ivScissors.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int appChoice = escolhaDoApp();
        if (mBinding.ivStone.getId() == v.getId()) verificarRodada(0, appChoice, 2, 1);
        if (mBinding.ivPaper.getId() == v.getId()) verificarRodada(1, appChoice, 0, 2);
        if (mBinding.ivScissors.getId() == v.getId()) verificarRodada(2, appChoice, 1, 0);
    }

    private void verificarRodada(int userChoice, int appChoice, int win, int lose) {
        escolhaPeloApp(appChoice);
        if (userChoice == appChoice) {
            mBinding.tvResult.setText(resultados[2]);
            mBinding.tvResult.setTextColor(getResources().getColor(R.color.draw));
        }
        if (appChoice == win) {
            mBinding.tvResult.setText(resultados[1]);
            mBinding.tvResult.setTextColor(getResources().getColor(R.color.win));
        }
        if (appChoice == lose) {
            mBinding.tvResult.setText(resultados[0]);
            mBinding.tvResult.setTextColor(getResources().getColor(R.color.lose));
        }
    }

    private void escolhaPeloApp(int appChoice) {
        if (appChoice == 0) mBinding.ivPChoice.setImageResource(R.drawable.pedra);
        if (appChoice == 1) mBinding.ivPChoice.setImageResource(R.drawable.papel);
        if (appChoice == 2) mBinding.ivPChoice.setImageResource(R.drawable.tesoura);
    }

    private int escolhaDoApp() {
        return new Random().nextInt(3);
    }
}