import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Validator {
    private static Map<String, Integer> violations = new HashMap<>();
    private static BufferedReader textReader;
    private static String inputFile = "src/main/resources/input.txt";

    static {
        try {
            textReader = new BufferedReader(new FileReader(inputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        violations.put("ticket type", 0);
        violations.put("start date", 0);
        violations.put("price", 0);
    }
    public static void main(String[] args) throws JsonProcessingException {
        int x = 0;
        int totalCount = 0; int validCount = 0;

        do {
            String input = getFileInput();
            BusTicket ticket = new ObjectMapper().readValue(input, BusTicket.class);
            if (typeChecker(ticket) && dateChecker(ticket) && priceChecker(ticket)) {
                validCount++;
            }
            totalCount++;
            //System.out.println(ticket);
            x++;
        } while (x < 17);
        System.out.println("Total = {" +totalCount+"};"+
                "\n"+"Valid = {"+validCount+"};");
        System.out.println("Most popular violation = {"+getPopularViolations()+"}");
    }

    private static boolean dateChecker(BusTicket ticket) {
        if(ticket.getStartDate() != null && !ticket.getStartDate().isEmpty()) {
            boolean res = !LocalDate.parse(ticket.getStartDate()).isAfter(LocalDate.now());
            if(!res) {
                violations.put("start date", violations.get("start date")+1);
            }
            return res;
        }
        violations.put("start date", violations.get("start date")+1);
        return false;
    }

    private static boolean priceChecker(BusTicket ticket) {
        if(ticket.getPrice() != null) {
            int p = Integer.parseInt(ticket.getPrice());
            if(p == 0) {
                violations.put("price", violations.get("price")+1);
                return false;
            }
            boolean res = p % 2 == 0;
            if(!res) {
                violations.put("price", violations.get("price")+1);
            }
            return res;
        }
        return false;
    }

    private static boolean typeChecker(BusTicket ticket) {
        if(ticket.getTicketType() == null) {
            violations.put("ticket type", violations.get("ticket type")+1);
            return false;
        }
        else if (ticket.getTicketType().equals("DAY") || ticket.getTicketType().equals("WEEK") ||
                ticket.getTicketType().equals("MONTH") || ticket.getTicketType().equals("YEAR")){
            return true;
        }
        return false;
    }
    private static String getPopularViolations() {
        if (violations.get("ticket type") > violations.get("price") &&
        violations.get("start date") > violations.get("price")) {
            return "ticket type and start date";
        }
        else if (violations.get("ticket type") > violations.get("start date") &&
        violations.get("price") > violations.get("start date")) {
            return "ticket type and price";
        }
        else {
            return "start date and price";
        }
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }

    private static String getFileInput() {
        try {
            return textReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
