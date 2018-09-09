class Hex2Dec{
    public static void main(String[] args)
    {
        // Change the hex number in this variable to change the default hex value.
        String someHex = "0";

        // This part allows you to convert any number in Command Line Interface. For example: $java Hex2Dec ABC2333
        if (args.length != 0){
            someHex = args[0];
        }

        int length = someHex.length();
        int currentValue = 0;
        int result = 0;

        for(int power=0; power<length; power++){
            char currentChar = someHex.toLowerCase().charAt(length - power - 1);

            if (currentChar <= 'f' && currentChar >= 'a'){
                currentValue = currentChar - 'a' + 10;
            } else if (currentChar <= '9' && currentChar >= '0') {
                currentValue = currentChar - '0';
            } else {
                System.out.println("Input Error!");
                return;
            }
            
            result = result + currentValue * (int)Math.pow(16, power);
        }

        System.out.println(result);
    }
}
