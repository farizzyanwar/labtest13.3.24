import java.io.*;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class EmployeeSalaryCalculatorProgram {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");

        // Variables to store top-performing and least experienced employees
        String topPerformer = "";
        String leastExperienced = "";
        double highestSalary = Double.MIN_VALUE;
        int leastYearsOfService = Integer.MAX_VALUE;

        try {
            BufferedReader inputFile = new BufferedReader(new FileReader("employeeSalaries.txt"));
            PrintWriter outputFile = new PrintWriter(new FileWriter("employeeData.txt"));

            String line;
            while ((line = inputFile.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "-");

                // Extract employee data
                String name = tokenizer.nextToken();
                double baseSalary = Double.parseDouble(tokenizer.nextToken());
                int yearsOfService = Integer.parseInt(tokenizer.nextToken());

                // Calculate annual salary with yearly increment
                double annualSalary = baseSalary * Math.pow(1.05, yearsOfService);

                // Update top-performing employee if necessary
                if (annualSalary > highestSalary) {
                    highestSalary = annualSalary;
                    topPerformer = name;
                }

                // Update least experienced employee if necessary
                if (yearsOfService < leastYearsOfService) {
                    leastYearsOfService = yearsOfService;
                    leastExperienced = name;
                }

                // Write employee data from the txt file created to output file
                outputFile.println("|Name|\t " + name);
                outputFile.println("------------------------------------------");

                outputFile.println("|Annual Salary| " + "[" +"RM" + df.format(annualSalary)+ "]");
                outputFile.println("------------------------------------------");
                outputFile.println("|Years of Service| " + "[" + yearsOfService + "]");
                outputFile.println("------------------------------------------");
                outputFile.println();
            }

            // display and code top-performing and least experienced employees to the output file
            outputFile.println("Top Performing Employee: " + topPerformer);
            outputFile.println("------------------------------------------");
            outputFile.println("Least Experienced Employee: " + leastExperienced);

            // Close the  input and output files to avoid any errors
            
            inputFile.close();
            outputFile.close();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File not found");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading/writing file");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid data format in input file");
        }
    }
}
