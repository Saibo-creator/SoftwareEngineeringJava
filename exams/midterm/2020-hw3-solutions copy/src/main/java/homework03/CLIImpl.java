// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// You CAN change the bodies of existing methods/constructors
// You CAN add new private methods/constructors
// You CANNOT add interface implementations unless explicitly instructed to do so
// You CANNOT add new public, package-private or protected methods/constructors
// You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
// You CANNOT delete existing methods/constructors
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
package homework03;

import homework03.util.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You CAN change the bodies of existing methods/constructors
 * You CAN add new private methods/constructors
 * You CANNOT add interface implementations unless explicitly instructed to do so
 * You CANNOT add new public, package-private or protected methods/constructors
 * You CANNOT edit the names, parameters, checked exceptions or return types of existing methods/constructors
 * You CANNOT delete existing methods/constructors
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */

/**
 * Gets the query from standard input and executes the command given by the user, printing the result.
 * <p>
 * Possible commands:
 * <p>
 * - book DATE
 * <p>
 * - getBookings
 * <p>
 * The DATE format is dd.MM.yyyy. (WARNING: the DATE must exist!!)
 * <p>
 * You can assume that correct commands will have only one space character between each argument and no before nor
 * after. Everything that does not conform to this format can be considered as being wrongly formatted.
 * <p>
 * The method prints "Please enter your query:\n" and then waits for an input.
 * <p>
 * The outcomes for the command must have this form:
 * book DATE:
 * - if the booking is successful: "Your booking for {DATE} was successful.\n"
 * - if the booking is NOT successful because all slots are occupied: "Your booking for {DATE} was unsuccessful. All the available slots are occupied.\n"
 * - if the booking is NOT successful because your already booked this date: "Your booking for {DATE} was unsuccessful. You already booked this date.\n"
 * - if the date is wrong:
 * "Your date has the wrong format or represents a date that does not exist. The correct format is 'dd.MM.yyyy'.\n"
 * - if the booking is NOT successful for some other reason: "An error occurred, please try again later.\n"
 * <p>
 * where {DATE} is replaced by the date booked
 * <p>
 * getBookings:
 * - if the bookings are retrieved:
 * "Here are your current booking(s):\n"
 * then each booking on a new line preceded by a tabulation character:
 * "\t{BOOKING}\n" where {BOOKING} is replaced by a booking's date. The order does not matter.
 * - if an error occurs: "An error occurred, please try again later.\n"
 * <p>
 * <p>
 * If the command has the wrong format: "Your query does not conform to the syntax.\n"
 */
public class CLIImpl implements CLI{

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final Client client;

    public CLIImpl(Client client) {
        if (client==null) throw  new IllegalArgumentException("client must not be null");
        this.client = client;
    }

    @Override
    public void getQueryAndExecute() {
        Scanner userInput=new Scanner(System.in);
        System.out.println("Please enter your query:\n");
        final String input = userInput.nextLine();
        if (input.isEmpty()) {
            System.out.println("Your query does not conform to the syntax.\n");
            return;
        }
        if (input.startsWith("book")){
            String date = input.substring(5,input.length());
            if (!isDateValid(date)) {
                System.out.println("Your date has the wrong format or represents a date that does not exist. The correct format is 'dd.MM.yyyy'.\n");
            }else{
                client.book(date, new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(String.format("Your booking for %s was successful.\n",date));

                    }
                }, new Consumer<Exception>() {
                    @Override
                    public void accept(Exception e) {
                        if (e instanceof DayFullException) System.out.println(String.format("Your booking for %s was unsuccessful. All the available slots are occupied.\n",date));
                        else if (e instanceof AlreadyBookedException) System.out.println(String.format("Your booking for %s was unsuccessful. You already booked this date.\\n\"",date));
                        else System.out.println("An error occurred, please try again later.\n");
                    }
                });

            }

        }else if (input.equals("getBookings")){
            client.getBookings(new Consumer<Set<String>>() {
                @Override
                public void accept(Set<String> strings) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Here are your current booking(s):\n");
                    for (String date:strings) {
                        sb.append(String.format("\t%s\n",date));
                    }
                    System.out.println(sb.toString());
                }
            }, new Consumer<Exception>() {
                @Override
                public void accept(Exception e) {
                    System.out.println("An error occurred, please try again later.\n");
                }
            });

        }else{
            System.out.println("Your query does not conform to the syntax.\n");
        }
    }


    private boolean isDateValid(String dateString) {
        if (Pattern.matches("[0-3][0-9].[0-1][0-9].[0-9]{4}", dateString)) {
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(dateString);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        return false;
    }

}
