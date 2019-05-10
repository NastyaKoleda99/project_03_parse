public class Item {
    private String scalematesUrl;
    private String brand;
    private String brandCatno;
    private String name;
    private String scale;
    private String description;
    private String boxartUrl;
    private String year;

    public Item(String scalematesUrl, String brand, String brandCatno, String name, String scale,
                String description, String boxartUrl, String year)
    {
        this.scalematesUrl = scalematesUrl;
        this.brand = brand;
        this.brandCatno = brandCatno;
        this.name = name;
        this.scale = scale;
        this.description = description;
        this.boxartUrl = boxartUrl;
        this.year = year;
    }
    public String getScalematesUrl()
    {
        return  scalematesUrl;
    }
    public String getBrand()
    {
        return brand;
    }
    public String getBrandCatno()
    {
        return brandCatno;
    }
    public String getName()
    {
        return name;
    }
    public String getScale()
    {
        return scale;
    }
    public String getDescription()
    {
        return description;
    }
    public String getBoxartUrl()
    {
        return boxartUrl;
    }
    public  String getYear()
    {
        return year;
    }

    @Override
    public String toString() {
        return "\"" + scalematesUrl + "\"," + "\"" + brand + "\"," + "\""
                + brandCatno + "\"," + "\"" + name + "\","
                + "\"" + scale+"\"," + "\"" + description
                + "\"," + "\"" + boxartUrl + "\"," + "\"" + year + "\"";
    }
}
