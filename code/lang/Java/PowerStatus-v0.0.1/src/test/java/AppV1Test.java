import Jama.JamaMatrix;
import cn.power.AppV2;
import cn.power.beans.base.Element;
import cn.power.core.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by JieGuo on 16/11/4.
 */
public class AppV1Test {

    private String line = "-------------------------------------------------------------";

    @Test
    public void testV2V3RandomResult() {
        int count = 2000;
        float per = 0.0005f;

        Matrix a = Matrix.random(count, per);
        Matrix b = a.clone();
        BooleanMatrix c = BooleanMatrix.identity(count, count);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                c.set(i, j, b.get(i, j) == 1);
            }
        }
        System.out.println("org================================================================");
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print(a.get(i,j) + "\t");
            }
            System.out.println("");
        }

        System.out.println("res1================================================================");
        Matrix res1 = Core.getCoreMatrix(a, 0.4f);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print(res1.get(i,j) + "\t");
            }
            System.out.println("");
        }

        System.out.println("res2================================================================");
        BooleanMatrix res2 = CoreV2.getCoreMatrix(c)[0];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print((res2.get(i, j) ? 1 : 0) + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    @Test
    public void testV2V3138Result() {
        int count = 138;
        float per = 0.0f;

         int[][] A = new int[138][138];
        AppV2.readFile("/Users/ray/IdeaProjects/PowerStatus/src/main/res/org", A);
//        Matrix a = Matrix.random(count, per);
        Matrix a = new Matrix(A, 138, 138);
        Matrix b = a.clone();
        BooleanMatrix c = BooleanMatrix.identity(count, count);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                c.set(i, j, b.get(i, j) == 1);
            }
        }
        System.out.println("org================================================================");
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print(a.get(i,j) + "\t");
            }
            System.out.println("");
        }

        System.out.println("res1================================================================");
        Matrix res1 = Core.getCoreMatrix(a, 0.4f);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print(res1.get(i,j) + "\t");
            }
            System.out.println("");
        }

        System.out.println("res2================================================================");
        BooleanMatrix res2 = CoreV2.getCoreMatrix(c)[0];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print((res2.get(i, j) ? 1 : 0) + "\t");
            }
            System.out.println("");
        }
        System.out.println("");

    }

    @Test
    public void testSpeedCoreV2() {
        int[] nCount = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000};
        float[] percents = {0.4f, 0.5f, 0.6f, 0.7f};

        for (int count : nCount) {
            for (float per : percents) {
                BooleanMatrix a = BooleanMatrix.random(count, per);
                System.out.println(String.format("matrix %d * %d, percent %f", count, count, per));
                long time = System.currentTimeMillis();
                CoreV2.getCoreMatrix(a);
                long castTimeArrays = System.currentTimeMillis() - time;
                System.out.println(String.format("you cast %d millis time", castTimeArrays));
            }
        }

    }

    @Test
    public void testSpeed() {
//        int[] nCount = {1000, 10000, 100000, 1000000, 10000000};
//        float[] percents = {0.4f, 0.5f, 0.6f, 0.7f};
//        float[] xsls = {0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f};
        int[] nCount = {10000};
        float[] percents = {0.4f, 0.5f};
        float[] xsls = {0.2f, 0.4f};
        for (int count : nCount) {
            for (float per : percents) {
                for (float xsl : xsls) {
                    Matrix a = Matrix.random(count, per);
                    Matrix b = a.clone();
                    System.out.println(String.format("start count %d, percent %f, xsl %f", count, per, xsl));
                    long time = System.currentTimeMillis();
                    Core.getCoreMatrix(a, xsl);
                    long castTimeArrays = System.currentTimeMillis() - time;
                    System.out.println(String.format("array end %d", castTimeArrays));
                    System.out.println(String.format("start count %d, percent %f, xsl %f", count, per, xsl));

                }
            }
        }
        System.out.println("all end");

    }

    @Test
    public void testInit() {
        File file = new File("/documents/testJson.txt");
        StringBuffer sb = new StringBuffer();
        if (file.exists()) {
            try {
                FileInputStream fins = new FileInputStream(file);
                InputStreamReader insReader = new InputStreamReader(fins);
                BufferedReader bufferedReader = new BufferedReader(insReader);

                String lineText;
                while ((lineText = bufferedReader.readLine()) != null) {
                    sb.append(lineText);
                }
                insReader.close();
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("测试数据文件不存在");
        }

        List<Element> elementList = new Gson().fromJson(sb.toString(), new TypeToken<List<Element>>() {
        }.getType());


        assert true;
    }

}
