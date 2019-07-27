package com.example.webbiestest;

public class TestChipModel {
     private static final String TAG = "TestChipModel";

     private int dataId;
     private String dataValue;
     private String dataDescription;

     public TestChipModel() {
     }

     public TestChipModel(int dataId, String dataValue, String dataDescription) {
          this.dataId = dataId;
          this.dataValue = dataValue;
          this.dataDescription = dataDescription;
     }

     public int getDataId() {
          return dataId;
     }

     public void setDataId(int dataId) {
          this.dataId = dataId;
     }

     public String getDataValue() {
          return dataValue;
     }

     public void setDataValue(String dataValue) {
          this.dataValue = dataValue;
     }

     public String getDataDescription() {
          return dataDescription;
     }

     public void setDataDescription(String dataDescription) {
          this.dataDescription = dataDescription;
     }
}
