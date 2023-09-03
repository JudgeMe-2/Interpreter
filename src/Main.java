import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("What is your question? ");

        String questionAsked = sc.nextLine();

        // Add the question to the context for analysis

        ConversionContext question = new ConversionContext(questionAsked);

        String fromConversion = question.getFromConversion();

        String toConversion = question.getToConversion();

        double quantity = question.getQuantity();


        try {

            // Get class based on the from conversion string

            Class tempClass = Class.forName(fromConversion);

            // Get the constructor dynamically for the conversion string

            Constructor con = tempClass.getConstructor();

            // Create a new instance of the object you want to convert from

            Object convertFrom = (Expression) con.newInstance();

            // Define the method parameters expected by the method that
            // will convert to your chosen unit of measure

            Class[] methodParams = new Class[]{Double.TYPE};

            // Get the method dynamically that will be needed to convert
            // into your chosen unit of measure

            Method conversionMethod = tempClass.getMethod(toConversion, methodParams);

            // Define the method parameters that will be passed to the above method

            Object[] params = new Object[]{new Double(quantity)};

            // Get the quantity after the conversion

            String toQuantity = (String) conversionMethod.invoke(convertFrom, params);

            // Print the results

            String answerToQues = question.getResponse() +
                    toQuantity + " " + toConversion;

            System.out.println(answerToQues);


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}