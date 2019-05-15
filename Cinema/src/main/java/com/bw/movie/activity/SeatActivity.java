package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.bw.movie.customview.MoveSeatView;
import com.bw.movie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeatActivity extends AppCompatActivity {

    @BindView(R.id.seat_cinemaName)
    TextView seatCinemaName;
    @BindView(R.id.seat_cinemaAddress)
    TextView seatCinemaAddress;
    @BindView(R.id.seat_filmName)
    TextView seatFilmName;
    @BindView(R.id.seat_startTime)
    TextView seatStartTime;
    @BindView(R.id.seat_endTime)
    TextView seatEndTime;
    @BindView(R.id.seat_TicketName)
    TextView seatTicketName;
    private MoveSeatView moveSeatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);
        ButterKnife.bind(this);
        moveSeatView = findViewById(R.id.seat_MoveSeatView);
        Intent intent = getIntent();
        String cinemaName = intent.getStringExtra("cinemaName");
        String cinemaAddress = intent.getStringExtra("cinemaAddress");
        String filmName = intent.getStringExtra("filmName");
        String startTime = intent.getStringExtra("startTime");
        String endTime = intent.getStringExtra("endTime");
        String TicketName = intent.getStringExtra("TicketName");
        int seatsTotal = intent.getIntExtra("seatsTotal", 0);
        int seatsUseCount = intent.getIntExtra("seatsUseCount", 0);
        Log.e("tag",seatsTotal+"");
        seatCinemaName.setText(cinemaName);
        seatCinemaAddress.setText(cinemaAddress);
        seatFilmName.setText(filmName);
        seatStartTime.setText(startTime);
        seatEndTime.setText(endTime);
        seatTicketName.setText(TicketName);

        moveSeatView.setScreenName(TicketName);//设置屏幕名称
        moveSeatView.setMaxSelected(5);//设置最多选中

        moveSeatView.setSeatChecker(new MoveSeatView.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        int i = seatsTotal / 15;

        Log.e("tag","   %     " + seatsTotal % 15);
            moveSeatView.setData(i, 15);
    }
}


