package demo;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {


        int account1 = 100;
        int account2 = 50;

        //trsnfer 20 from account1 to account2
        //final value of accout1 = 80 account2 = 70

        System.out.println(new App().getGreeting());
    }
}
