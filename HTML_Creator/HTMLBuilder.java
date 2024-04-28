import java.util.List;
import java.time.LocalDate;

class HTMLBuilder 
{
    private HTMLBuilder() {
        // üres
    }

    public static String StartPage(String root)
    {
        root = root.replace(Main.root, "");
        int depth = 0;
        if(MyFileUtils.replaceType(root).contains(".html")) depth--;
        for(char c: root.toCharArray())
        {
            if(c == '\\') depth++;
        }
        return "..\\".repeat(depth) + "index.html";
    }

    public static String listOfDirectories(List<String> directories)
    {
        String result = "";
        for(int i = 0; i < directories.size(); i++)
        {   
            result += "<li><a href=\"";
            result += MyFileUtils.setDirectory(directories.get(i) + "\\index.html\"");
            result += ">";
            result += directories.get(i).substring(directories.get(i).lastIndexOf("\\")+1);
            if(i == directories.size()-1){
                result += "</a></li>";
            }
            else{
                result += "</a></li>\n";
            }
        }
        return result;
    }
    
    public static String listOfImages(List<String> images)
    {
        String result = "";
        for(int i = 0; i < images.size(); i++)
        {
            result += "<li><a href=\"";
            result += MyFileUtils.setName(MyFileUtils.replaceType(images.get(i)));
            result += "\">";
            if(i == images.size()-1){
                result += MyFileUtils.setName(images.get(i)) + "</a></li>";
            }
            else{
                result += MyFileUtils.setName(images.get(i)) + "</a></li>\n";
            }   
        }
        return result;
    }
    public static String isItRoot(String directory, String s)
    {
        String result = "";
        if(!directory.equals(Main.root)) 
        {
            result += "<a href=";
            result += "..\\index.html";
            result += ">Back</a>\n";
        }
        return result;
    }
    public static String getTime()
    {
        return LocalDate.now().toString();
    }
    public static String imageContent(Images image)
    {
        String s = StartPage(image.getPath());
        String newLine = System.getProperty("line.separator");
        String html = String.join(newLine,
            "<html>",
            "<head>",
            "<title>Képnézegető</title>",
            "</head>",
            "<body>",
            "<h2 style='background-color:Lightgray;'><center><a href=\"" + s + "\">Start Page</a></center> </h2>",
            "<center><b>Üdvözöllek!</b></div><center>",
            "<div align='center'><b>Mai dátum:",
            getTime(),
            "<br>",
            "<a href=\".\\index.html\">^^</a><br>",
            "<a href=\"" + MyFileUtils.setName(image.getLeft()) +"\"><<</a>" + image.getName() + "<a href=\"" + MyFileUtils.setName(image.getRight()) + "\">>></a><br>",
            "<a href=\"" + MyFileUtils.setName(image.getRight()) + "\"><img src=\"" + MyFileUtils.setName(image.getPath()) + "\"></a>",
            "</body>",
            "</html>");
           
        return html;
    }
    public static String indexContent(String directory, List<String> images, List<String> directories)
    {
        String s = StartPage(directory);
        String newLine = System.getProperty("line.separator");
        String html = String.join(newLine,
            "<!doctype html>",
            "<html>",
            "<head>",
            "<title>Képnézegető</title>",
            "</head>",
            "<body>",
            "<h2 style='background-color:Lightgray;'><center><a href=\"" + s + "\">Start Page</a></center> </h2>",
            "<center><b></b></div></center>",
            "<strong>Directories:</strong><br>",
            isItRoot(directory,Main.root) + "\n<ul>",  //vissza gomb
            listOfDirectories(directories),
            "</ul>",
            "<hr>",
            "<strong>Images:</strong>",
            "<ul>",
            listOfImages(images),
            "</ul>",
            "</body>",
            "</html>");
        return html;
    }
}