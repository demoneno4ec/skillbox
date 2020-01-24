import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CoffeeMachine gaggia = new CoffeeMachine();
        Helper helper = new Helper();

        ArrayList<Drink> drinks = new ArrayList<>();
        Drink cappuccino = new Drink();
        Drink espresso = new Drink();
        Drink water = new Drink();

        cappuccino.setName("капучино");
        espresso.setName("эспрессо");
        water.setName("вода");

        cappuccino.setPrice(150);
        espresso.setPrice(80);
        water.setPrice(20);

        drinks.add(cappuccino);
        drinks.add(espresso);
        drinks.add(water);

        gaggia.setDrinks(drinks);

        System.out.println("Кофе-машина");

        String userAmount = helper.getUserInput("Введите сумму, для покупки напитка ");
        int moneyAmount = Integer.parseInt(userAmount);

        boolean canBuyAnything = gaggia.whatCanBuy(moneyAmount);

        if (!canBuyAnything) {
            System.out.println("Недостаточно средств");
        }

        printDateByFormat("yyyyMMdd_HHmmss");

        getCaptureSize();
    }

    private static void getCaptureSize(){
        try {
            Robot robot = new Robot();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width;
            int height = screenSize.height;
            Rectangle screenRect = new Rectangle(screenSize);
            BufferedImage createScreenCapture = robot.createScreenCapture(screenRect);
            System.out.println("Параметры скриншота: ширина - " + width + " высота " + height);
        }catch (AWTException e){
            e.printStackTrace();
        }
    }

    private static void printDateByFormat(String format) {
        SimpleDateFormat date = new SimpleDateFormat(format);
        System.out.println("Форматирование даты " + date.format(new Date()));
    }
}

class CoffeeMachine {
    private ArrayList<Drink> drinks;

    public void setDrinks(ArrayList<Drink> drinks){
        this.drinks = drinks;
    }

    public boolean whatCanBuy(int amount){
        boolean result = false;
        int drinkPrice = 0;
        String drinkName;

        for (Drink drink : this.drinks) {
            drinkPrice = drink.getPrice();
            drinkName = drink.getName();
            if (amount >= drinkPrice) {
                System.out.println("Вы можете купить " + drinkName);
                result = true;
            }
        }

        return result;
    }
}

class Drink{
    private String name;
    private int price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class Helper{
    public String getUserInput(String promt) {
        String inputLine = null;
        System.out.print(promt + " ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0){
                return "";
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

        return inputLine;
    }
}