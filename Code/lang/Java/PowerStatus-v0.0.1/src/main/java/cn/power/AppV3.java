package cn.power;

import cn.power.core.*;
import cn.power.interfaces.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ray on 2017/1/12.
 */
public class AppV3 {
    public static DataProvider dataProvider;
    static boolean[][] A = new boolean[138][138];
    static boolean[][] B = new boolean[138][138];

    public static void main(String[] args) {

        readFile("/Users/ray/IdeaProjects/PowerStatus/src/main/res/org", A);
        readFile("/Users/ray/IdeaProjects/PowerStatus/src/main/res/res", B);
        long time = System.currentTimeMillis();
        BooleanMatrix org = new BooleanMatrix(A, 138, 138);
        BooleanMatrix result = new BooleanMatrix(B, 138, 138);

        BooleanMatrix myFinal = CoreV2.getCoreMatrix(org)[0];
        for (int i = 0; i < myFinal.getRowDimension(); i++) {
            for (int j = 0; j < myFinal.getColumnDimension(); j++) {
                System.out.print(myFinal.get(i, j));
            }
            System.out.println("");
        }
        if (myFinal.equal(result)) {
            System.out.println("you got it!!! cast time = " + (System.currentTimeMillis() - time));
        } else {
            System.out.println("something wrong man!");
        }
    }

    public static void readFile(String fileName, boolean[][] T) {

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            while ((tempString = reader.readLine()) != null) {
                String[] vs = tempString.split(",");
                for (int i = 0; i < vs.length; i++) {
                    T[line][i] = vs[i].equals("1");
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
