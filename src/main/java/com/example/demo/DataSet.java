package com.example.demo;

import java.util.ArrayList;

public class DataSet {

    public static class SingleDataFormat{
        public int index;
        public double value;
        public SingleDataFormat(int index, String value){
            this.index = index;
            try{
                this.value = Double.parseDouble(value);
            }catch (Exception e){
                this.value=0.0;
            }
        }
    }

    public ArrayList<Data> data;

    DataSet(){
        data = new ArrayList<Data>();
    }

    public ArrayList<SingleDataFormat> getColData(int col){
        ArrayList<SingleDataFormat> temDataSet = new ArrayList<SingleDataFormat>();
        for(int i = 0; i < data.size(); i++){
            temDataSet.add(new SingleDataFormat(data.get(i).index, data.get(i).value[col]));
        }
        return temDataSet;
    }

}
