public class Main {
    private static final int size = 10000000;

    private static final int h = size / 2;

    public float[] calculate(float[] arr) {

        for (int i = 0; i < arr.length; i++)

            arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) * Math.cos(0.4f + arr[i] / 2));

        return arr;

    }
    public void myOneThread() {

        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;

        long a = System.currentTimeMillis();

        calculate(arr);

        System.out.println("Время работы: " + (System.currentTimeMillis() - a));

    }

    public void myTwoThreads() {

        float[] arr = new float[size];

        float[] arr1 = new float[h];

        float[] arr2 = new float[h];

        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, arr1, 0, h);

        System.arraycopy(arr, h, arr2, 0, h);

        Thread t1=new Thread(()->calculate(arr1));
        Thread t2=new Thread(()->calculate(arr2));

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);

        System.arraycopy(arr2, 0, arr, h, h);

        System.out.println("Время работы: " + (System.currentTimeMillis() - a));
    }

    public static void main(String[] args) {

        Main start = new Main();

        start.myOneThread();

        start.myTwoThreads();

    }}
