import java.util.Random;

public class Person implements  Comparable<Person>{
    private String name;
    private int ID; //This attribute will be used for sorting algorithms.

    public Person(String name,int ID){  //If necessary...
        this.ID = ID;
        this.name = name;
    }
    public Person(){  //This default constructor will help the Random Object Generator.
        //The array of strings below will be helpful while generating random names.
        String words[] = {"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n",
                            "o","p","q","r","s","t","u","v","w","x","y","z"};
        this.name = "";
        Random random = new Random();
        for(int i=0;i<5;i++){
            this.name+= words[random.nextInt(35)];
        }
        int randomNumber = random.nextInt(100000000); //Generates a random int number.
        this.ID = randomNumber; // Random number between 0-99999999;
    }
    public void display(){
        String info = "Name : "+this.name+" ID: "+this.ID;
        System.out.println(info);
    }

    @Override
    public int compareTo(Person o) { // From now on,an object which is created from this class is Comparable.
        return Integer.valueOf(this.ID).compareTo(Integer.valueOf(o.getID()));
    }

    // Getters and Setters.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
