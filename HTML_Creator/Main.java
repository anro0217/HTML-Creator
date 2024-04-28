import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static String root;
    public static List<List<String>> paths = new ArrayList<>();
    public static MyFileUtils tree = new MyFileUtils(paths);

    public static void error()
    {
        System.out.println("Valos eleresi utat adjon meg!");
        System.exit(1);
    }
    public static void main(String[] args)
    {
        if(args.length !=1 || !(MyFileUtils.isPath(args[0]))) error(); 

        List<Images> everyImage = new ArrayList<>();
        List<List<String>> listsOfImages = new ArrayList<>();  
        List<List<String>> listsOfDirectories = new ArrayList<>();  
        
        root = args[0];

        tree.walk(root);                //A mappák rekurzív bejárása
        MyFileUtils.parents.add(root);  //A MyFileUtils.parents tartalmazza a szülő mappákat 
            //(ezt bővíti ki a gyökérmappával ez a sor, a többit a bejárás közben gyűjti ki)

        MyFileUtils.splitByType(tree.getTree(),listsOfImages,listsOfDirectories);    //széválogatja a képeket és mappákat külön
        MyFileUtils.addNewImages(listsOfImages,everyImage);     //minden képet példányosít,majd az everyImage listába teszi
        MyFileUtils.createImageHTML(everyImage);                //kép html-ek készítése
        MyFileUtils.createIndexHTML(MyFileUtils.parents, listsOfImages, listsOfDirectories); //index.html-ek készítése
    }
}