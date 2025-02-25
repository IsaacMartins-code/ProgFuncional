package application;

import model.entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main (String [] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path + "\\Input.csv"))) {
            List<Employee> emp = new ArrayList<>();
            String line = br.readLine();

            while(line != null) {
                String[] field = line.split(",");
                emp.add(new Employee(field[0], field[1], Double.parseDouble(field[2])));
                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            Double min = sc.nextDouble();

            List<String> emails = emp.stream()
                    .filter(e -> e.getSalary() > min)
                    .map(e -> e.getEmail())
                    .sorted()
                    .collect(Collectors.toList());


            double sum = emp.stream()
                    .filter(e -> e.getName().charAt(0) == 'M')
                    .map(e -> e.getSalary())
                    .reduce(0.0, (x, y) -> x + y);

            System.out.println("Email of people whose salary is more than " + String.format("%.2f", min) + ": ");
            emails.forEach(System.out::println);

            System.out.print("Sum of salary of people whose name starts with 'M': ");
            System.out.print(sum);
        }
        catch (IOException e) {
            System.out.print("Error: " + e.getMessage());
        }
        finally {
            sc.close();
        }
    }
}
