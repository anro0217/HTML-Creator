import java.io.File;
import java.util.ArrayList;
import java.util.List;

class MyFileUtils
{
    private List<List<String>> tree_paths;
    
    public MyFileUtils(List<List<String>> tree_paths)
    {
        this.tree_paths=tree_paths;
    }
    public List<List<String>> getTree(){
        return this.tree_paths;
    }
    public static boolean isHTML(File f)
    {
        if(f.toString().contains(".html")) return true;
        return false;
    }
    public void walkAndDestroy(String path) 
    {
        List<String> names = new ArrayList<String>();
        File root = new File(path);
        File[] files = root.listFiles();

        if(files == null) return;  

        for(File f: files)
        {
            if(f.isDirectory()) walkAndDestroy(f.getAbsolutePath());
            else if(isHTML(f)) f.delete();
        }
        tree_paths.add(names);
    }
}
public class clearFolder
{
    public static void main(String[] args)
    {
        List<List<String>> paths = new ArrayList<List<String>>();
        MyFileUtils tree = new MyFileUtils(paths);

        tree.walkAndDestroy("C:\\Users\\Path_to_your\\Pictures");
    }
}