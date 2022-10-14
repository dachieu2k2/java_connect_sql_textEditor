
public class App {
    public static void main(String[] args) {

        config app = new config();
        app.DatabaseConnect();
        new Word(app.viewbyId(1));

    }

}
