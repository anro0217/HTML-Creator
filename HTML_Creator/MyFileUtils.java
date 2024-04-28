import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MyFileUtils 
{
    private List<List<String>> tree_paths;
    public static List<String> parents = new ArrayList<String>();
    
    public MyFileUtils(List<List<String>> tree_paths)
    {
        this.tree_paths=tree_paths;
    }
    public List<List<String>> getTree(){
        return this.tree_paths;
    }
    public static String setName(String path)
    {
        return path.substring(path.lastIndexOf("\\")+1);         
    }
    public static String setDirectory(String path)
    {
        String tmp1 = path.substring(path.lastIndexOf("\\"));
        String tmp2 = path.replace(tmp1, "");
        tmp2 = tmp2.substring(tmp2.lastIndexOf("\\")+1);
        return tmp2 + tmp1;
    }
    public static boolean isPath(String path)
    {
        File file = new File(path);
        return file.isDirectory();
    }
    public static boolean isImage(String s)
    {
        if(s.contains(".jpg") || s.contains(".jpeg") || s.contains(".png")) return true;
        return false;
    }
    public void walk(String path) 
    {
        List<String> names = new ArrayList<String>();
        File root = new File(path);
        File[] files = root.listFiles();
        String tmp = new String();

        if(files == null) return;  

        for(File f: files)
        {
            if(f.isDirectory())
            {
                walk(f.getAbsolutePath());
                names.add(f.getAbsoluteFile().toString());
                System.out.println("egy mappa hozza lett adva:\n" +
                 f.getAbsoluteFile().toString());
                parents.add(f.getPath());
            }
            else
            {
                tmp = f.toString();
                if(isImage(tmp) || isImage(tmp) || isImage(tmp)){
                    names.add(f.getAbsoluteFile().toString());
                } 
            }
        }
        tree_paths.add(names);
    }
    //További statikus függvények
    public static String replaceType(String s)
    {
        return s.replace(".jpg", ".html").replace(".jpeg", ".html").replace(".png", ".html");
    }
    public static void sortByNums(List<String> lists)
    {
        Collections.sort(lists, new Comparator<String>() 
        {
            public int compare(String o1, String o2) 
            {
                return extractInt(o1) - extractInt(o2);
            }
        
            int extractInt(String s) 
            {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        }
        );
    }
    public static void splitByType(List<List<String>> content, List<List<String>> images, List<List<String>> directories)
    {
        for(int i = 0; i < content.size(); i++)
        {
            List<String> tmpForImages = new ArrayList<>();
            List<String> tmpForDirectories = new ArrayList<>();
            for(int j = 0; j < content.get(i).size(); j++)
            {
                if(MyFileUtils.isImage(content.get(i).get(j))) 
                {
                    tmpForImages.add(content.get(i).get(j));
                }
                else
                {
                    tmpForDirectories.add(content.get(i).get(j));
                }
            }
            sortByNums(tmpForImages);
            images.add(tmpForImages);
            directories.add(tmpForDirectories);
        }
    }
    public static void addNewImages(List<List<String>> listsOfImages, List<Images> everyImage)
    {
        String path = "";
        String l_neigh = "";
        String r_neigh = "";
        for(int i = 0; i < listsOfImages.size(); i++)
        {
            if(listsOfImages.get(i).isEmpty()) continue;  //ures
            if(listsOfImages.get(i).size() == 1)  //egy kep
                {
                    path = listsOfImages.get(i).get(0);
                    everyImage.add(new Images(path,replaceType(path),replaceType(path))) ; 
                }
            else  //egynel tobb kep
            {
                path = listsOfImages.get(i).get(0);
                l_neigh = replaceType(listsOfImages.get(i).get(0));
                r_neigh = replaceType(listsOfImages.get(i).get(1));
                everyImage.add(new Images(path,l_neigh,r_neigh));

                for(int j = 1; j < listsOfImages.get(i).size()-1; j++)
                {
                    path = listsOfImages.get(i).get(j);
                    l_neigh = replaceType(listsOfImages.get(i).get(j-1));
                    r_neigh = replaceType(listsOfImages.get(i).get(j+1));
                    everyImage.add(new Images(path,l_neigh,r_neigh));
                }

                path = listsOfImages.get(i).get(listsOfImages.get(i).size()-1);
                l_neigh = replaceType(listsOfImages.get(i).get(listsOfImages.get(i).size()-2));
                r_neigh = replaceType(listsOfImages.get(i).get(listsOfImages.get(i).size()-1));
                everyImage.add(new Images(path,l_neigh,r_neigh));
            }
        }
    }
    public static void createImageHTML(List<Images> everyImage)
    {
        String tmp = "";
        for(int i = 0; i < everyImage.size(); i++)
        {
            try(PrintWriter pw = new PrintWriter(replaceType(everyImage.get(i).getPath())))
            {
                tmp = HTMLBuilder.imageContent(everyImage.get(i));
                pw.print(tmp);
            }
            catch(IOException e) 
            {
                System.err.println("Hiba a fajl irasakor");
            }
        }
    }
    public static void createIndexHTML(List<String> parents, List<List<String>> listsOfImages, List<List<String>> listsOfDirectories)
    {
        for(int i = 0; i  < parents.size(); i++)
        { 
            String tmp = "";
            try(PrintWriter pw = new PrintWriter(parents.get(i) + "\\index.html"))
            {
                tmp = HTMLBuilder.indexContent(parents.get(i),listsOfImages.get(i), listsOfDirectories.get(i));
                pw.print(tmp);   
            }
            catch(IOException e) 
            {
                System.err.println("Hiba a fajl irasakor");
            }
        }
    }
}