package com.example.archComponents.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.archComponents.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    private HashMap<String, ArrayList<String>> mHashMap = new HashMap<>();
    private List<List<String>> queryList = new ArrayList<>();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        addingInitialValueOnHashMap();
        printData();

        textView = findViewById(R.id.test_textView);

        QueryTest queryTest = new QueryTest();
        queryTest.test();

         /* ArrayList<String> test=new ArrayList<>();
          test.add("dheeraj");
          test.add("rahul");
          test.add("rohan");
          int n=test.size();
          permute(test,0,n-1);*/
    }

    private void addingInitialValueOnHashMap() {
        String[] str = {"Flavour", "Source", "Form"};
        String[] flavour = {"Chocolate", "Vanilla", "UnFlavour"};
        String[] source = {"Rice", "Pea"};
        String[] form = {"Powder", "Tablet"};

        ArrayList<String> arrayList;

        for (int i = 0; i < str.length; i++) {
            arrayList = new ArrayList<>();
            Log.i(TAG, "addingInitialValueOnHashMap: " + i);
            if (i == 0)
                for (int j = 0; j < flavour.length; j++) {
                    Log.i(TAG, "addingInitialValueOnHashMap: flavour " + flavour[j]);
                    arrayList.add(flavour[j]);
                }
            else if (i == 1) {
                for (int j = 0; j < source.length; j++) {
                    Log.i(TAG, "addingInitialValueOnHashMap: source " + source[j]);
                    arrayList.add(source[j]);
                }
            } else {
                for (int k = 0; k < form.length; k++) {
                    Log.i(TAG, "addingInitialValueOnHashMap: form " + form[k]);
                    arrayList.add(form[k]);
                }
            }
            queryList.add(arrayList);
            mHashMap.put(str[i], arrayList);

        }


    }

    private void printData() {
        if (mHashMap != null) {
            StringBuilder data = new StringBuilder();
            for (String key : mHashMap.keySet()) {

                Log.i(TAG, "printData: key " + key);
                for (String s : mHashMap.get(key)) {
                    Log.i(TAG, "printData: " + s);
                    data.append(s + " ");
                }

            }
        }
    }

     /*private void logicImplementation() {
          HashMap<String, ArrayList<String>> tempHashMap = new HashMap<>();
          ArrayList<String> tempArrayList;

          if (mHashMap != null) {
               for (ArrayList<String> strings : mHashMap.values()) {

               }
          }

     }


     private void permute(ArrayList<String> updatedList, int l, int r) {
          if (l == r) {
               for (String data : updatedList) {
                    Log.i(TAG, "permute: " + data);
               }
          } else {
               for (int i = l; i <= r; i++) {
                    updatedList = swap(updatedList, l, i);
                    permute(updatedList, l + 1, r);
                    updatedList = swap(updatedList, l, i);
               }
          }
     }


     public ArrayList<String> swap(ArrayList<String> arrayList, int i, int j) {
          String tempString;
          ArrayList<String> tempArrayList = arrayList;
          tempString = tempArrayList.get(i);
          tempArrayList.set(i, tempArrayList.get(j));
          tempArrayList.set(j, tempString);

          return tempArrayList;
     }*/




    /*
     * Testing with final value
     * */


    class QueryTest {

        public Set<Set<String>> multiply(List<List<String>> input) {

            Set<Set<String>> result = queryTest(input);
            return result;
        }

        public Set<Set<String>> queryTest(List<List<String>> in) {
            final Set<Set<String>> out = new HashSet<Set<String>>();
            queryUtil(new ArrayList<List<String>>(in), new HashSet<String>(), out);
            return out;
        }

        private void queryUtil(List<List<String>> in, Set<String> part, Set<Set<String>> out) {
            if (in.isEmpty()) {
                out.add(part);
                return;
            }
            if (out.contains(part))
                return;
            List<List<String>> nextIn = new ArrayList<List<String>>(in);
            for (String s : nextIn.remove(0)) {
                Set<String> nextPart = new LinkedHashSet<String>(part);
                nextPart.add(s);
                queryUtil(nextIn, nextPart, out);
            }
        }

        public void test() {
            StringBuilder stringBuilder=new StringBuilder();
            Set<Set<String>> result = multiply(queryList);
            for (Set<String> stringSet : result) {
                for (String string : stringSet) {
                    Log.i(TAG, "test: " + string);
                    stringBuilder.append(string + " ");
                }
                stringBuilder.append("\n \n ");
                Log.i(TAG, "test: " + "\n\n");
            }
            textView.setText(stringBuilder);
        }
    }


}
