package com.example.ryota_ko.sensorapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.List;


public class MainActivity extends Activity implements SensorEventListener{

    //現在のカウントを表示させるためのテキストビュー
    TextView text,text2,text3;
    //現在の値を保存しておくための変数
    int number;
    //様々なセンサーの値を取得するためのもの
    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        text = (TextView) findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text.setText("0");
        text2.setText("0");
        text3.setText("0");
    }

    @Override
    protected  void onResume() {
        super.onResume();
        //センサータイプを指定してセンサーを取得
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        //SensorManageにリスナーを登録
        if(sensors.size() > 0){
            Sensor s = sensors.get(0);
            mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);

        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        //Listenerの登録解除
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            String str = "\nX軸:" + event.values[SensorManager.DATA_X];
            String str2 = "\nY軸:" + event.values[SensorManager.DATA_Y];
            String str3 = "\nZ軸:" + event.values[SensorManager.DATA_Z];
            text.setText(str);
            text2.setText(str2);
            text3.setText(str3);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
