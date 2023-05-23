import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static final String MAIN_PERSON = "Mike";
    static Map<String, List<String>>  relationships = new HashMap<>();
    static List<Person> people = new ArrayList<>();
    static Set<String> visited = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {



        readFromFile();



        if (hasLoanRequest(MAIN_PERSON, 111)){
            System.out.println("Yes, you can receive a request yourself.");
        }
        else {
            System.out.println("No, you cannot receive a request");
        }

    }


   static void readFromFile() throws FileNotFoundException {
       File file = new File("data.txt");
       Scanner scanner = new Scanner(file);



       while (scanner.hasNextLine()) {
           List<String> friends = new ArrayList<>();
           String line = scanner.nextLine();
           String[] names = line.split(" ");
           int i = 2;
           String name = names[0];
           int money = Integer.parseInt(names[1]) ;
           int length = names.length;

           Person person = new Person(money,name);
           people.add(person);


           while (length-2 > 0){

               friends.add(names[i++]);
               --length;

           }
           relationships.put(name, friends);

       }




    }


    private static boolean hasLoanRequest(String person,int amount) {

        if (!relationships.containsKey(person)) {
            return false;
        }
        System.out.println(amount);
        for (Person p: people){
            if (p.name.equals(person) ){
                amount -= p.money;
            }
        }

        List<String> lenders = relationships.get(person);


        for (String lender : lenders) {

            if (lender.equals(MAIN_PERSON) && amount > 0) {
                return true;
            } else {
                if (!visited.contains(lender)) {
                    visited.add(lender);
                    if (hasLoanRequest(lender,amount) ) {
                        return true;
                    }
                }
            }
        }

        return false;
    }



























}