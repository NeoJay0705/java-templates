package com.example.templates.data;

import java.io.Serializable;

public class EchoData {
    private int ia1 = 0;
    private String sa1 = "asdf";
    private EchoDataInternal titleA = null;

    public int getIa1() {
        return ia1;
    }

    public String getSa1() {
        return sa1;
    }

    private String getSa2() {
        return sa1 + ia1;
    }

    public void setIa1(int a) {
        ia1 = a;
    }

    public void setSa1(String s) {
        sa1 = s;
    }

    public EchoDataInternal getTitleA() {
        return titleA;
    }

    public void setTitleA(EchoDataInternal ei) {
        this.titleA = ei;
    }

    public class EchoDataInternal {
        private String titleA = "";

        public EchoDataInternal() {

        }

        public String getTitleA() {
            return titleA;
        }

        public void setTitleA(String s) {
            titleA = s;
        }
    }
}
