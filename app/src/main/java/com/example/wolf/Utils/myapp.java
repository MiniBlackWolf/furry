package com.example.wolf.Utils;

import android.app.Application;
import android.util.Log;

import com.example.wolf.seed.Seed;
import com.example.wolf.seed.seedDataBase;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
//        DbManager.DaoConfig daoConfig=new DbManager.DaoConfig();
//        daoConfig.setDbName("cain.db");
//      // daoConfig.setDbDir(new File("/mnt/sdcard/"));
//        daoConfig.setDbVersion(1);
//        daoConfig.setDbUpgradeListener(new DbManager.DbUpgradeListener() {
//            @Override
//            public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
//
//            }
//        });
//        daoConfig.setDbOpenListener(new DbManager.DbOpenListener() {
//            @Override
//            public void onDbOpened(DbManager db) {
//                db.getDatabase().enableWriteAheadLogging();
//            }
//        });
//        daoConfig.setTableCreateListener(new DbManager.TableCreateListener() {
//            @Override
//            public void onTableCreated(DbManager db, TableEntity<?> table) {
//
//            }
//        });

    }
}
