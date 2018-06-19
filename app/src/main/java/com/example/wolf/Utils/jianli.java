package com.example.wolf.Utils;

import android.view.View;
import android.widget.TextView;

public class jianli implements View.OnClickListener {
        private TextView vs;
        private int adds;

        public jianli(TextView vs, int adds) {
            this.vs = vs;
            this.adds = adds;
        }

    public jianli() {

    }

    public int getAdds() {
            return adds;
        }

        public void setAdds(int adds) {
            this.adds = adds;
        }

        @Override
        public void onClick(View v) {


            if (adds <=0) {
                vs.setText("0");
                adds = 0;
            } else {
                adds -= 1;
                vs.setText(adds + "");
            }
        }

        public   View.OnClickListener jiali() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (adds <0) {
                        vs.setText("0");
                        adds = 0;
                    } else {
                        adds += 1;
                        vs.setText(adds + "");

                    }
                }
            };

        }

    }