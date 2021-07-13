import java.util.Dictionary;

public class Test {
    public static void main(String[] args)
    {
        int[] list = {1, 2, 3, 4, 5};
        rotateRight(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + ", ");
        }
    }
    public static double findMin(double[] data)
    {
        double min = data[0];
        for (int i = 1; i < data.length; i++)
        {
            if (data[i] < min) { min = data[i]; }
        }
        return min;
    }

    public static int mod (int a, int b)
    {
        if (b < 0) {
            return -mod(-a, -b);
        }
        int r = a % b;
        if(r < 0) {
            r += b;
        }
        return r;
    }

    public static void rotateRight(int[] values) {
        int[] valuesCopy = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            valuesCopy[i] = values[i];
        }
        for (int i = 0; i < values.length; i++) {
            int index = mod(i - 1, values.length);
            values[i] = valuesCopy[index];
        }
    }
}

