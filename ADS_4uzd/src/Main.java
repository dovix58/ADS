import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static final String MAIN_PERSON = "Mike";
    static Map<String, List<String>>  relationships = new HashMap<>();
    static Set<String> visited = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {



        readFromFile();


        System.out.println(hasLoanRequest(MAIN_PERSON));


    }


   static void readFromFile() throws FileNotFoundException {
       File file = new File("data.txt");
       Scanner scanner = new Scanner(file);



       while (scanner.hasNextLine()) {
           List<String> friends = new ArrayList<>();
           String line = scanner.nextLine();
           String[] names = line.split(" ");
           int i = 1;
           String name = names[0];
           int length = names.length;

           while (length-1 > 0){

               friends.add(names[i++]);
               --length;

           }
           relationships.put(name, friends);

       }




    }


    private static boolean hasLoanRequest(String person) {
        if (!relationships.containsKey(person)) {
            return false;
        }
        List<String> lenders = relationships.get(person);


        for (String lender : lenders) {
            if (lender.equals(MAIN_PERSON)) {
                return true;
            } else {
                if (!visited.contains(lender)) {
                    visited.add(lender);
                    if (hasLoanRequest(lender)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }



























}