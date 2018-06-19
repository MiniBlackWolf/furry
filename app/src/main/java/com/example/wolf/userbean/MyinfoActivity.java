package com.example.wolf.userbean;


import android.app.DatePickerDialog;
import android.content.DialogInterface;

import android.os.Bundle;

import android.support.v7.app.AlertDialog;

import android.util.Log;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import com.example.wolf.R;
import com.example.wolf.Utils.Getuserinfo;
import com.example.wolf.Utils.GlideCircleTransform;
import com.example.wolf.Utils.GsonUtil.GsonUtil;
import com.example.wolf.Utils.ImageCompressUtil;
import com.example.wolf.Utils.Xutils;
import com.example.wolf.Utils.encryption_algorithm.algorithm;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;

import java.io.File;

import java.io.UnsupportedEncodingException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyinfoActivity extends TakePhotoActivity {
    private File file;
    @BindView(R.id.myinfofh)
    ImageView myinfofh;
    @BindView(R.id.textView46)
    TextView textView46;
    @BindView(R.id.myinfosave)
    TextView myinfosave;
    @BindView(R.id.infoname)
    TextView infoname;
    @BindView(R.id.myinfoname)
    LinearLayout myinfoname;
    @BindView(R.id.infosex)
    TextView infosex;
    @BindView(R.id.muinfosex)
    LinearLayout muinfosex;
    @BindView(R.id.infobr)
    TextView infobr;
    @BindView(R.id.myinfobr)
    LinearLayout myinfobr;
    @BindView(R.id.infohead)
    ImageView infohead;
    @BindView(R.id.myinfohead)
    LinearLayout myinfohead;
    Xutils xutils = new Xutils(MyinfoActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);
        ButterKnife.bind(this);
        Map<String, String> map = new HashMap<>();
        map.put("userName", new Getuserinfo(this).getusername());
        xutils.get(getResources().getString(R.string.Userinfo), map, new Xutils.XCallBack() {
            @Override
            public void onResponse(String result) {
                String userinfo = null;
                try {
                    userinfo = new String(algorithm.encryptDecode(result.getBytes("iso8859-1")), "utf-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                GsonUtil gsonUtil = new GsonUtil();
                List<UserInfo> UserInfo = gsonUtil.Gson(userinfo, UserInfo.class);
                infoname.setText(UserInfo.get(0).getName());
                if (UserInfo.get(0).getGender()) {
                    infosex.setText("男");

                } else {
                    infosex.setText("女");

                }
                Date date = new Date(UserInfo.get(0).getBirthday());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                String format = simpleDateFormat.format(date);
                infobr.setText(format);
                Log.i("iiiiiiiiii", UserInfo.get(0).getHeadimgurl());
                Glide.with(MyinfoActivity.this).load(UserInfo.get(0).getHeadimgurl()).placeholder(R.mipmap.load).transform(new GlideCircleTransform(MyinfoActivity.this)).into(infohead);
            }
        });

    }

    @OnClick({R.id.myinfofh, R.id.myinfosave, R.id.myinfohead, R.id.myinfoname, R.id.muinfosex, R.id.myinfobr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myinfofh:
                finish();
                break;
            case R.id.myinfosave:
                Date date = null;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    date = simpleDateFormat.parse(infobr.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.i("iiiiiiiiiii", infobr.getText().toString());
                Map<String, String> map3 = new HashMap<>();
                map3.put("username", new Getuserinfo(MyinfoActivity.this).getusername());
                map3.put("name", infoname.getText().toString());
                map3.put("gender", infosex.getText().toString().equals("男") ? "true" : "false");
                map3.put("timebirthday", String.valueOf(date != null ? date.getTime() : 0));
                xutils.post(getResources().getString(R.string.change), map3, new Xutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {

                    }
                });
if(file!=null) {
    ImageCompressUtil.compressImage(MyinfoActivity.this, file.getAbsolutePath(), new ImageCompressUtil.ProcessImgCallBack() {
        @Override
        public void compressSuccess(String imgPath) {
            File file2 = new File(imgPath);
            Map<String, File> map = new HashMap<>();
            map.put("uploadFile", file2);
            Map<String, String> map2 = new HashMap<>();
            map2.put("type", "0");
            map2.put("uid", String.valueOf(new Getuserinfo(MyinfoActivity.this).getuid()));
            xutils.upLoadFile("http://192.168.0.72:8080/fileupload/general", map2, map, new Xutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    Toast.makeText(MyinfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
            });
        }
    });
}

                break;
            case R.id.myinfohead:
                TakePhoto takePhoto = getTakePhoto();
                takePhoto.onPickFromGallery();
                break;
            //修改名字
            case R.id.myinfoname:
                final EditText et = new EditText(this);
                et.setMaxLines(8);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(et);
                builder.setTitle("修改名字");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (et.getText().toString().length() <= 0) {
                            Toast.makeText(MyinfoActivity.this, "请输入名字", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            infoname.setText(et.getText().toString());
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            //选择性别
            case R.id.muinfosex:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("选择性别");
                //    指定下拉列表的显示数据
                final String[] cities = {"男", "女"};
                //    设置一个下拉的列表选择项
                builder2.setItems(cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        infosex.setText(cities[which]);
                    }
                });
                builder2.show();
                break;
            //日期选择器
            case R.id.myinfobr:
                Calendar ca = Calendar.getInstance();
                final int[] mYear = {ca.get(Calendar.YEAR)};
                final int[] mMonth = {ca.get(Calendar.MONTH)};
                final int[] mDay = {ca.get(Calendar.DAY_OF_MONTH)};
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mYear[0] = year;
                        mMonth[0] = monthOfYear;
                        mDay[0] = dayOfMonth;
                        String days;
                        if (mMonth[0] + 1 < 10) {
                            if (mDay[0] < 10) {
                                days = new StringBuffer().append(mYear[0]).append("年").append("0").
                                        append(mMonth[0] + 1).append("月").append("0").append(mDay[0]).append("日").toString();
                            } else {
                                days = new StringBuffer().append(mYear[0]).append("年").append("0").
                                        append(mMonth[0] + 1).append("月").append(mDay[0]).append("日").toString();
                            }

                        } else {
                            if (mDay[0] < 10) {
                                days = new StringBuffer().append(mYear[0]).append("年").
                                        append(mMonth[0] + 1).append("月").append("0").append(mDay[0]).append("日").toString();
                            } else {
                                days = new StringBuffer().append(mYear[0]).append("年").
                                        append(mMonth[0] + 1).append("月").append(mDay[0]).append("日").toString();
                            }

                        }
                        infobr.setText(days);
                    }
                };
                new DatePickerDialog(this, onDateSetListener, mYear[0], mMonth[0], mDay[0]).show();

                break;
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        RequestManager glideRequest;
        file = new File(result.getImage().getOriginalPath());

        if (file.length() <= 5242880) {
            glideRequest = Glide.with(this);
            glideRequest.load(file).transform(new GlideCircleTransform(this)).into(infohead);
        } else {
            Toast.makeText(MyinfoActivity.this, "只能选择5mb一下的图片", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }


}
