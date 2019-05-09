
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anonymous
 */
public class Calculation {

    Connection conn;

    public Calculation() {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/calculator";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "root", "root");
        } catch (Exception ex) {
            Logger.getLogger(Calculation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        try {
            Calculation calc = new Calculation();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Integer one");
            Integer value1 = Integer.parseInt(br.readLine());
            System.out.println("Enter Integer two");
            Integer value2 = Integer.parseInt(br.readLine());
            System.out.println("Specify the operation");
            String operation = br.readLine();

            switch (operation) {
                case "add":
                    calc.addition(value1, value2);
                    break;
                case "subtract":
                    calc.subtraction(value1, value2);
                    break;
                case "multiply":
                    calc.multiplication(value1, value2);
                    break;
                case "divide":
                    calc.division(value1, value2);
                    break;
                default:
                    break;

            }

        } catch (IOException ex) {
            Logger.getLogger(Calculation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int addition(int a, int b) {
        try {
            Calculation cal = new Calculation();

            int sum = cal.add(a, b);

            // the mysql insert statement
            String query = " insert into addition(first, second, sum) values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, a);
            preparedStmt.setInt(2, b);
            preparedStmt.setInt(3, sum);
            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
            return sum;

        } catch (Exception ex) {
            Logger.getLogger(Calculation.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public double division(int a, int b) {
        try {
            Calculation cal = new Calculation();
            double division = cal.divide(a, b);
            // the mysql insert statement

            DecimalFormat df = new DecimalFormat("#.00");
            String angleFormated = df.format(division);
            String query = " insert into division (first, second, division)"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, a);
            preparedStmt.setInt(2, b);
            preparedStmt.setString(3, angleFormated);
            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
            return division;
        } catch (Exception ex) {
            Logger.getLogger(Calculation.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public double multiplication(int a, int b) {
        try {
            Calculation cal = new Calculation();
            Integer multiplication = cal.multiply(a, b);

            // the mysql insert statement
            String query = " insert into multiplication (first, second, multiplication)"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, a);
            preparedStmt.setInt(2, b);
            preparedStmt.setInt(3, multiplication);
            // execute the preparedstatement
            preparedStmt.execute();
            return multiplication;
        } catch (Exception ex) {
            Logger.getLogger(Calculation.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public int subtraction(int a, int b) {
        try {
            Calculation cal = new Calculation();
            int subtraction = cal.subtract(a, b);
            // the mysql insert statement
            String query = " insert into subtraction (first, second, subtraction)"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, a);
            preparedStmt.setInt(2, b);
            preparedStmt.setInt(3, subtraction);
            // execute the preparedstatement
            preparedStmt.execute();
            return subtraction;
        } catch (Exception ex) {
            Logger.getLogger(Calculation.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }
//service to do addition of 2 numbers

    private static int add(int intA, int intB) {
        ws.Calculator service = new ws.Calculator();
        ws.CalculatorSoap port = service.getCalculatorSoap();
        return port.add(intA, intB);
    }
//service to do division of 2 numbers
    private static int divide(int intA, int intB) {
        ws.Calculator service = new ws.Calculator();
        ws.CalculatorSoap port = service.getCalculatorSoap();
        return port.divide(intA, intB);
    }
//service to do multiplication of 2 numbers
    private static int multiply(int intA, int intB) {
        ws.Calculator service = new ws.Calculator();
        ws.CalculatorSoap port = service.getCalculatorSoap();
        return port.multiply(intA, intB);
    }
//service to do subtraction of 2 numbers
    private static int subtract(int intA, int intB) {
        ws.Calculator service = new ws.Calculator();
        ws.CalculatorSoap port = service.getCalculatorSoap();
        return port.subtract(intA, intB);
    }

}
