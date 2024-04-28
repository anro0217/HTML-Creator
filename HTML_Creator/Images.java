class Images
{
    private String name;
    private String path;
    private String directory;
    private String right_neigh;
    private String left_neigh;

    public Images(String path, String left_neigh, String right_neigh)
    {
        this.path = path;
        this.left_neigh = left_neigh;
        this.right_neigh = right_neigh;
        this.name = MyFileUtils.setName(path); 
        this.directory = MyFileUtils.setDirectory(path);
    }
    public String getName()
    {
        return this.name;
    }
    public String getPath()
    {
        return this.path;
    }
    public String getDirectory()
    {
        return this.directory;
    }
    public String getLeft()
    {
        return this.left_neigh;
    }
    public String getRight()
    {
        return this.right_neigh;
    }
}
