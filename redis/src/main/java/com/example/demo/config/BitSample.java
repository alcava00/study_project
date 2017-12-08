package com.example.demo.config;

public class BitSample {
    public static void main(String[] args) {
        int num1 = 105; // 00000000 00000000 00000000 01101001
        int num2 = 22;  // 00000000 00000000 00000000 00010110

        System.out.println(num1 + " & " + num2 + " = " + (num1 & num2));
        System.out.println(num1 + " | " + num2 + " = " + (num1 | num2));
        System.out.println(num1 + " ^ " + num2 + " = " + (num1 ^ num2));


        System.out.println("~" + num1 + " = " + ~num1);
        System.out.println(Integer.toBinaryString(num1));
        System.out.println(Integer.toBinaryString(~num1));

        int total = 16;
        int[] kkk={1,2,3,4,5,6,7,8,9,9,11,12,13,14,15,16};

        int byteArrayCount = (int) Math.ceil(total / 8);
        byte[] k = new byte[byteArrayCount];

        for(int i:kkk){
            int order=(i%8)==0?7:(i%8)-1;
            int arrayOrder=(int) Math.floor((i-1) / 8) ;
            System.out.println(arrayOrder);
            int ss=k[arrayOrder]|(int)Math.pow(2,order);
            System.out.println(">>>>>>>>>>>>> ss"+i+":"+order+":"+ss);
            k[arrayOrder]=(byte)ss;
            System.out.println("k[arrayOrder]>>>" + k[arrayOrder]);
        }

        System.out.println(">>>>>>>>>>>>>"+k[0]+":"+k[1]);

        for (byte z:k){
            System.out.println((int)z +":"+Integer.toBinaryString(253));
        }
    }



}
